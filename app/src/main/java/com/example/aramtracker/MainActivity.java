package com.example.aramtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.aramtracker.fragments.FavoriteFragment;
import com.example.aramtracker.fragments.SearchFragment;
import com.example.aramtracker.leagueoflegends.LeagueOfLegendsAPI;
import com.example.aramtracker.leagueoflegends.LeagueOfLegendsApiImpl;
import com.example.aramtracker.leagueoflegends.MatchMakingRatingApiImpl;
import com.example.aramtracker.leagueoflegends.data.AramMatchSummonerInfo;
import com.example.aramtracker.leagueoflegends.data.AramSummonerInfo;
import com.example.aramtracker.properties.Props;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;

import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                MatchMakingRatingApiImpl whatIsMyMMR = new MatchMakingRatingApiImpl();
//
//                switch (item.getItemId()) {
//                    case R.id.nav_search :
//                        selectorFragment = new SearchFragment();
//                        String nick = "Bodziuguard";
//                        try {
//                            AramSummonerInfo summonerInfo = whatIsMyMMR.getSummonerInfoByNick(nick).orElseThrow(() -> new IllegalStateException("Error for user " + nick));
//                            Toast.makeText(MainActivity.this, summonerInfo.getNickname() + ": " + summonerInfo.getClosestRank(), Toast.LENGTH_SHORT).show();
//                        } catch (IllegalStateException e) {
//                            e.printStackTrace();
//                            Toast.makeText(MainActivity.this, "No stats for user: " + nick, Toast.LENGTH_SHORT).show();
//                        }
//                        break;
//                    case R.id.nav_favorites:
//                        selectorFragment = new FavoriteFragment();
//                        LeagueOfLegendsAPI leagueOfLegendsAPI = new LeagueOfLegendsApiImpl(new Props(getApplicationContext()));
//                        Map<Integer, List<String>> currentGameParticipants = leagueOfLegendsAPI.getCurrentGameParticipantsByNick("Duke Alimony");
//                        for (Integer team: currentGameParticipants.keySet()) {
////                            String key = team.toString();
////                            String value = currentGameParticipants.get(team).toString();
//                            for (String name: currentGameParticipants.get(team)) {
//                                try {
//                                    AramSummonerInfo summonerInfo = whatIsMyMMR.getSummonerInfoByNick(name).orElseThrow(() -> new IllegalStateException("Error for user " + name));
//                                    Toast.makeText(MainActivity.this, summonerInfo.getNickname() + ": " + summonerInfo.getClosestRank(), Toast.LENGTH_SHORT).show();
//                                } catch (IllegalStateException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                        break;
//                    default:
//                        break;
//
//                }
//                if (selectorFragment != null) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectorFragment).commit();
//                }
//                return true;
//            }
//        });
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();
//
//
//    }


    }


}