package com.example.aramtracker.leagueoflegends;

import com.example.aramtracker.leagueoflegends.data.AramMatchSummonerInfo;
import com.example.aramtracker.leagueoflegends.retryer.SynchronizedRetryer;
import com.example.aramtracker.properties.Props;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;

import java.util.*;

public class LeagueOfLegendsApiImpl implements LeagueOfLegendsAPI{

//    private final static String CHAMPION_INFO_LINK = "https://ddragon.leagueoflegends.com/cdn/12.8.1/data/en_US/champion.json";
//    private final static Set<Integer> ALL_SEASONS = Set.of(1,2,3,4,5,6,7,8,9,10,11,12,13);
//    private final static int ANY_GAME_TIME = -1;
//    private static Map<String, List<AramMatchSummonerInfo>> cache = new HashMap<>();
//    private final Props props;
    private final RiotApi riotApi;
    private final SynchronizedRetryer retryer;

    public LeagueOfLegendsApiImpl(Props props) {
//        this.props = props;
        this.retryer = new SynchronizedRetryer();
        this.riotApi = new RiotApi(new ApiConfig()
                .setRequestTimeout(10000)
                .setKey(props.getLolApiKey()));
    }

    @Override
    public List<AramMatchSummonerInfo> getAramMatchInfosByChampion(String champion) {
        return null;
    }

    @Override
    public List<AramMatchSummonerInfo> getAramMatchInfosByNickAndChampion(String nick, String champion) {
        return null;
    }

    @Override
    public Optional<Summoner> getSummonerStats(String nick) {
        // TODO: parametrize platform
        return retryer.callWithRetries(() -> riotApi.getSummonerByName(Platform.EUNE, nick));
    }

}
