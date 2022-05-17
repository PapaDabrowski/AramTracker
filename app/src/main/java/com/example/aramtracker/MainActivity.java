package com.example.aramtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aramtracker.leagueoflegends.MatchMakingRatingApiImpl;
import com.example.aramtracker.leagueoflegends.data.AramSummonerInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

import no.stelar7.api.r4j.pojo.lol.summoner.Summoner;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nickNameTextView = (TextView)findViewById(R.id.nickName);
        TextView temp = (TextView)findViewById(R.id.lol2);
        ImageView rankView = (ImageView)findViewById(R.id.rank);


        Intent i = getIntent();
        String nickName = i.getStringExtra("playerName");
        String server = i.getStringExtra("playerServer");
        nickNameTextView.setText(nickName);

        MatchMakingRatingApiImpl whatIsMyMMR = new MatchMakingRatingApiImpl();
        try {
           AramSummonerInfo summonerInfo = whatIsMyMMR.getSummonerInfoByNick(nickName, server.toLowerCase(Locale.ROOT)).orElseThrow(() -> new IllegalStateException("Error for user " + nickName));
           String imageUri = "@drawable/" + parseRank(summonerInfo.getClosestRank());

           int imageResource = getResources().getIdentifier(imageUri, null, getPackageName());
           Drawable res = getResources().getDrawable(imageResource);
           rankView.setImageDrawable(res);
           temp.setText(summonerInfo.getClosestRank());

           //summonerInfo.

        } catch (IllegalStateException e) {
           e.printStackTrace();
           Toast.makeText(MainActivity.this, "No stats for user: " + nickName, Toast.LENGTH_SHORT).show();
           super.onBackPressed();
        }
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

    private String parseRank(String rank)
    {
        String arr[] = rank.split(" ", 2);

        String baseRank = arr[0];
        String stage = arr[1];

        if("challenger".equalsIgnoreCase(baseRank))
            return "challenger";
        if("grandmaster".equalsIgnoreCase(baseRank))
            return "grandmaster";
        if("master".equalsIgnoreCase(baseRank))
            return "master";
        if("diamond".equalsIgnoreCase(baseRank))
            return "diamond";
        if("platinum".equalsIgnoreCase(baseRank))
            return "platinum";
        if("gold".equalsIgnoreCase(baseRank))
            return "gold";
        if("silver".equalsIgnoreCase(baseRank))
            return "silver";
        if("bronze".equalsIgnoreCase(baseRank))
            return "bronze";
        if("iron".equalsIgnoreCase(baseRank))
            return "iron";
        return "";
    }


}