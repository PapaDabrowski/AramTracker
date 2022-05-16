package com.example.aramtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aramtracker.leagueoflegends.LeagueOfLegendsAPI;
import com.example.aramtracker.leagueoflegends.LeagueOfLegendsApiImpl;
import com.example.aramtracker.leagueoflegends.MatchMakingRatingAPI;
import com.example.aramtracker.leagueoflegends.MatchMakingRatingApiImpl;
import com.example.aramtracker.leagueoflegends.data.AramMatchSummonerInfo;
import com.example.aramtracker.leagueoflegends.data.AramSummonerInfo;
import com.example.aramtracker.properties.Props;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import org.json.JSONException;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;

import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;

public class SearchActivity extends AppCompatActivity {

    private Button btnFind;
    private EditText editTextName;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_search);

        //DISPLAY
//        MatchMakingRatingApiImpl whatIsMyMMR = new MatchMakingRatingApiImpl();
//        String nick = "Horniss";
//        try {
//            AramSummonerInfo summonerInfo = whatIsMyMMR.getSummonerInfoByNick(nick).orElseThrow(() -> new IllegalStateException("Error for user " + nick));
//            Toast.makeText(SearchActivity.this, summonerInfo.getNickname() + ": " + summonerInfo.getClosestRank(), Toast.LENGTH_SHORT).show();
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//            Toast.makeText(SearchActivity.this, "No stats for user: " + nick, Toast.LENGTH_SHORT).show();
//        }


        // DISPLAY LIVE GAME PLAYERS MMR
//        MatchMakingRatingApiImpl whatIsMyMMR = new MatchMakingRatingApiImpl();
//        LeagueOfLegendsApiImpl leagueOfLegendsAPI = new LeagueOfLegendsApiImpl(new Props(getApplicationContext()));
//        Map<Integer, List<String>> currentGameParticipants = leagueOfLegendsAPI.getCurrentGameParticipantsByNick("Christian Díor");
//        for (Integer team : currentGameParticipants.keySet()) {
////                            String key = team.toString();
////                            String value = currentGameParticipants.get(team).toString();
//            for (String name : Objects.requireNonNull(currentGameParticipants.get(team))) {
//                try {
//                    AramSummonerInfo summonerInfo = whatIsMyMMR.getSummonerInfoByNick(name).orElseThrow(() -> new IllegalStateException("Error for user " + name));
//                    Toast.makeText(SearchActivity.this, summonerInfo.getNickname() + ": " + summonerInfo.getAramMMR(), Toast.LENGTH_SHORT).show();
//                } catch (IllegalStateException e) {
////                    e.printStackTrace();
//                    Toast.makeText(SearchActivity.this, "No info", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }


        // AVG DMG FOR PLAYER AND CHAMPION
//        String nick = "koncaty3K";
//        String champion = "Sona";
//        LeagueOfLegendsAPI leagueOfLegendsAPI = new LeagueOfLegendsApiImpl(new Props(getApplicationContext()));
//        List<AramMatchSummonerInfo> stats = leagueOfLegendsAPI.getAramMatchInfosByNickAndChampion(nick, champion);
//        OptionalDouble avgDamage = stats.stream()
//                .mapToLong(AramMatchSummonerInfo::getTotalDamageDealtToChampions)
//                .average();
//        Toast.makeText(SearchActivity.this, "User:  " + nick + " | Champion: " + champion + " AVG DMG: " + avgDamage , Toast.LENGTH_SHORT).show();
//
    }
}