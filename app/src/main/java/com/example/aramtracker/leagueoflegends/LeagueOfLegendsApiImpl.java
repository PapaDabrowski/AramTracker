package com.example.aramtracker.leagueoflegends;

import com.example.aramtracker.leagueoflegends.data.AramMatchSummonerInfo;
import com.example.aramtracker.leagueoflegends.retryer.SynchronizedRetryer;
import com.example.aramtracker.properties.Props;
import com.jayway.jsonpath.JsonPath;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import no.stelar7.api.r4j.basic.cache.impl.MemoryCacheProvider;
import no.stelar7.api.r4j.basic.calling.DataCall;
import no.stelar7.api.r4j.basic.constants.api.regions.LeagueShard;
import no.stelar7.api.r4j.basic.constants.api.regions.RegionShard;
import no.stelar7.api.r4j.impl.R4J;
import static no.stelar7.api.r4j.basic.constants.api.regions.RegionShard.EUROPE;
import static no.stelar7.api.r4j.basic.constants.types.lol.GameQueueType.ARAM;
import static no.stelar7.api.r4j.basic.constants.types.lol.MatchlistMatchType.NORMAL;
import no.stelar7.api.r4j.pojo.lol.match.v5.LOLMatch;
import no.stelar7.api.r4j.pojo.lol.match.v5.MatchParticipant;
import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LeagueOfLegendsApiImpl implements LeagueOfLegendsAPI{

    private final static Logger logger = LoggerFactory.getLogger(LeagueOfLegendsApiImpl.class);

    private final static String CHAMPION_INFO_LINK = "https://ddragon.leagueoflegends.com/cdn/12.8.1/data/en_US/champion.json";

    private static Map<String, List<AramMatchSummonerInfo>> cache = new HashMap<>();
    private final R4J riotNewAPI ;
    private final SynchronizedRetryer retryer;

    public LeagueOfLegendsApiImpl(Props props) {
        this.retryer = new SynchronizedRetryer();
        this.riotNewAPI  = new R4J(props.getApiCredentials());
        DataCall.setCacheProvider(new MemoryCacheProvider());

    }

    @Override
    public List<AramMatchSummonerInfo> getAramMatchInfosByChampion(String champion) {
        long championId = getChampionIdByName(champion);
        return cache.values().stream()
                .flatMap(Collection::stream)
                .filter(stats -> stats.getChampionId() == championId)
                .collect(Collectors.toList());
    }

    @Override
    public List<AramMatchSummonerInfo> getAramMatchInfosByNickAndChampion(String nick, String champion) {
        List<AramMatchSummonerInfo> aramStats = getAramMatchesInfo(nick);
        long championId = getChampionIdByName(champion);
        return aramStats.stream()
                .filter(stats -> stats.getChampionId() == championId)
                .collect(Collectors.toList());
    }

    public List<AramMatchSummonerInfo> getAramMatchesInfo(String nick) {
        List<AramMatchSummonerInfo> aramStats = new ArrayList<>();
//        if (cache.containsKey(nick)) {
//            return cache.get(nick);
//        }
        try {
            String puuId = getSummonerStats(nick)
                    .map(Summoner::getPUUID)
                    .orElseThrow(() -> new IllegalStateException("Couldn't find accountPuuid of user with nick:" + nick));
            int beginIndex = 1;
            int count = 100;
            List<String> matchIds = riotNewAPI.getLoLAPI().getMatchAPI().getMatchList(EUROPE, puuId, ARAM, NORMAL, beginIndex, count, null, null);
            while (!matchIds.isEmpty()) {
                logger.info("Processing matchList {}-{} of user {}", beginIndex, beginIndex + count, nick);
                System.out.println("Processing matchList: " + nick + beginIndex);
                aramStats.addAll(processMatches(puuId, matchIds));
                beginIndex += count;
                matchIds = riotNewAPI.getLoLAPI().getMatchAPI().getMatchList(EUROPE, puuId, ARAM, NORMAL, beginIndex, count, null, null);
            }
        } catch (Exception ex) {
            logger.warn("Exception during processing match list of user: " + nick, ex);
        }
        logger.info("Processed {} games of user {}", aramStats.size(), nick);
//        cache.put(nick, aramStats);
        return aramStats;
    }

    private List<AramMatchSummonerInfo> processMatches(String puuId, List<String> matchIds) {
        List<AramMatchSummonerInfo> aramStats = new ArrayList<>();
        for (String matchId : matchIds) {
            try {
                Thread.sleep(50);
                logger.info("Processing match {}", matchId);
                System.out.println("Processing match " + matchId);
                LOLMatch match = getMatch(EUROPE, matchId)
                        .orElseThrow(() -> new IllegalStateException("Match with id: " + matchId + " not found"));
                MatchParticipant participant = match.getParticipants().stream()
                        .filter(p -> p.getPuuid().equals(puuId))
                        .findFirst()
                        .orElseThrow(() -> new IllegalStateException("Participant " + puuId + " not found in match " + matchId));
                long championId = participant.getChampionId();
                long gameDuration = match.getGameDuration();
                long dmgDealtToChamps = participant.getTotalDamageDealtToChampions();
                aramStats.add(new AramMatchSummonerInfo(championId, dmgDealtToChamps, gameDuration));
            } catch (Exception ex) {
                logger.warn("Match {} processing failed", matchId, ex);
            }
        }
        return aramStats;
    }

    private Optional<LOLMatch> getMatch(RegionShard regionShard, String matchId) {
        return retryer.callWithRetries(() -> riotNewAPI.getLoLAPI().getMatchAPI().getMatch(regionShard, matchId));
    }

    @Override
    public Optional<Summoner> getSummonerStats(String nick) {
        return retryer.callWithRetries(() -> riotNewAPI.getLoLAPI().getSummonerAPI().getSummonerByName(LeagueShard.EUN1, nick));
    }

    private long getChampionIdByName(String name) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(CHAMPION_INFO_LINK)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String result = response.body().string();
            String key = JsonPath.read(result, "$.data." + name + ".key");
            return Long.parseLong(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }


}
