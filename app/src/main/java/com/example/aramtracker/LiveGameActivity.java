package com.example.aramtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.aramtracker.leagueoflegends.LeagueOfLegendsApiImpl;
import com.example.aramtracker.leagueoflegends.MatchMakingRatingApiImpl;
import com.example.aramtracker.leagueoflegends.data.AramSummonerInfo;
import com.example.aramtracker.properties.Props;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LiveGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_game);

        MatchMakingRatingApiImpl whatIsMyMMR = new MatchMakingRatingApiImpl();
        LeagueOfLegendsApiImpl leagueOfLegendsAPI = new LeagueOfLegendsApiImpl(new Props(getApplicationContext()));
        Map<Integer, List<String>> currentGameParticipants = leagueOfLegendsAPI.getCurrentGameParticipantsByNick("Christian Díor");
        for (Integer team : currentGameParticipants.keySet()) {
                            String key = team.toString();
                            String value = currentGameParticipants.get(team).toString();
          for (String name : Objects.requireNonNull(currentGameParticipants.get(team))) {
             // try {
                  //AramSummonerInfo summonerInfo = whatIsMyMMR.getSummonerInfoByNick(name).orElseThrow(() -> new IllegalStateException("Error for user " + name));
                  //Toast.makeText(LiveGameActivity.this, summonerInfo.getNickname() + ": " + summonerInfo.getAramMMR(), Toast.LENGTH_SHORT).show();
           //   } catch (IllegalStateException e) {
                   // e.printStackTrace();
                    //Toast.makeText(LiveGameActivity.this, "No info", Toast.LENGTH_SHORT).show();
              //  }
            }
        }
    }
}