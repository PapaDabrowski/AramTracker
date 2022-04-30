package com.example.aramtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aramtracker.leagueoflegends.LeagueOfLegendsAPI;
import com.example.aramtracker.leagueoflegends.LeagueOfLegendsApiImpl;
import com.example.aramtracker.properties.Props;

import org.json.JSONException;

import java.io.IOException;
import java.util.Optional;

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

        initViews();


//        LeagueOfLegendsAPI leagueOfLegendsAPI = new LeagueOfLegendsApiImpl(new Props(getApplicationContext()));
//        List<AramMatchSummonerInfo> stats = leagueOfLegendsAPI.getAramMatchInfosByNickAndChampion("Horniss", "Qiyana");
//        OptionalDouble avgDamage = stats.stream()
//                .mapToLong(AramMatchSummonerInfo::getTotalDamageDealtToChampions)
//                .average();
//        System.out.println("avgDamage: " + avgDamage);

    }

    public void initViews() {
        btnFind = findViewById(R.id.buttonFind);
        editTextName = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.test1);

    }

    public void onBtnClick (View view) throws JSONException, IOException {
        EditText editTextName = findViewById(R.id.editTextTextPersonName);
        UserStatistics user = new UserStatistics(editTextName.getText().toString());
        TextView t = findViewById(R.id.test1);
        t.setText(user.getNickname() + ": " + user.getAramMMR());
    }
}