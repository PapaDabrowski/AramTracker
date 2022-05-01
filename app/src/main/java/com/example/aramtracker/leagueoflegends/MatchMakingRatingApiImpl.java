package com.example.aramtracker.leagueoflegends;

import android.util.Log;

import com.example.aramtracker.leagueoflegends.data.AramSummonerInfo;
import com.jayway.jsonpath.JsonPath;

import java.util.Optional;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MatchMakingRatingApiImpl implements MatchMakingRatingAPI{

    private final String PARTIAL_LINK = ".whatismymmr.com/api/v1/summoner?name=";
    private final String PLATFORM = "eune";

    @Override
    public Optional<AramSummonerInfo> getSummonerInfoByNick(String nick) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://" + PLATFORM + PARTIAL_LINK + nick)
                .build();

        Log.i("MMRInfo", "Requesting whatismymmr for summoner " + nick);
        try {
            Response response = client.newCall(request).execute();
            String result = response.body().string();
            String closestRank = JsonPath.read(result, "$.ARAM.closestRank");
            int aramMMR = JsonPath.read(result, "$.ARAM.avg");
            int standardDeviation = JsonPath.read(result, "$.ARAM.err");
            double percentile = JsonPath.read(result, "$.ARAM.percentile");
            AramSummonerInfo summonerInfo = new AramSummonerInfo(nick, closestRank, aramMMR, standardDeviation, percentile);
            return Optional.ofNullable(summonerInfo);
        } catch (Exception ex) {
            Log.w("MMRInfo", String.format("Whatismymmr for summoner %s failed", nick), ex);
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
