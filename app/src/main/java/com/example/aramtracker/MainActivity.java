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
import com.example.aramtracker.leagueoflegends.data.AramMatchSummonerInfo;
import com.example.aramtracker.properties.Props;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONException;
import java.io.IOException;
import java.util.List;
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

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_search :
                        selectorFragment = new SearchFragment();
//                        startActivity(new Intent(MainActivity.this, SearchActivity.class));
                        break;
                    case R.id.nav_favorites:
                        selectorFragment = new FavoriteFragment();
                        LeagueOfLegendsAPI leagueOfLegendsAPI = new LeagueOfLegendsApiImpl(new Props(getApplicationContext()));
                        List<AramMatchSummonerInfo> stats = leagueOfLegendsAPI.getAramMatchInfosByNickAndChampion("Horniss", "Qiyana");
                        OptionalDouble avgDamage = stats.stream()
                                .mapToLong(AramMatchSummonerInfo::getTotalDamageDealtToChampions)
                                .average();
                        Toast.makeText(MainActivity.this, "avgDamage: " + avgDamage, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;

                }
                if (selectorFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectorFragment).commit();
                }
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFragment()).commit();


    }


}