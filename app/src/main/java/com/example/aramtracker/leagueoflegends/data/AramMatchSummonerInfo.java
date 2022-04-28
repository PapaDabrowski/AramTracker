package com.example.aramtracker.leagueoflegends.data;

public class AramMatchSummonerInfo {
    private final long championId;
    private final long totalDamageDealtToChampions;
    private final long gameDuration;

    public AramMatchSummonerInfo(long championId, long totalDamageDealtToChampions, long gameDuration) {
        this.championId = championId;
        this.totalDamageDealtToChampions = totalDamageDealtToChampions;
        this.gameDuration = gameDuration;
    }

    public long getChampionId() {
        return championId;
    }

    public long getTotalDamageDealtToChampions() {
        return totalDamageDealtToChampions;
    }

    public long getGameDuration() {
        return gameDuration;
    }
}
