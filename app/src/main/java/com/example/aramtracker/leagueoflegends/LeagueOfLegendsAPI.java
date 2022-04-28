package com.example.aramtracker.leagueoflegends;

import com.example.aramtracker.leagueoflegends.data.AramMatchSummonerInfo;

import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

import java.util.List;
import java.util.Optional;

public interface LeagueOfLegendsAPI {

    List<AramMatchSummonerInfo> getAramMatchInfosByChampion(String champion);

    List<AramMatchSummonerInfo> getAramMatchInfosByNickAndChampion(String nick, String champion);

    Optional<Summoner> getSummonerStats(String nick);
}
