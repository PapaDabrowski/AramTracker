package com.example.aramtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aramtracker.leagueoflegends.LeagueOfLegendsAPI;
import com.example.aramtracker.leagueoflegends.LeagueOfLegendsApiImpl;
import com.example.aramtracker.properties.Props;

import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

import org.json.JSONException;
import java.io.IOException;
import java.util.Optional;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_main);

        LeagueOfLegendsAPI leagueOfLegendsAPI = new LeagueOfLegendsApiImpl(new Props(getApplicationContext()));
        Optional<Summoner> summoner = leagueOfLegendsAPI.getSummonerStats("Horniss");
        System.out.println("LEVEL: " + summoner.get().getSummonerLevel());

    }

    public void onBtnClick (View view) throws JSONException, IOException {
        EditText editTextName = findViewById(R.id.editTextTextPersonName);
        UserStatistics user = new UserStatistics(editTextName.getText().toString());
        TextView t = findViewById(R.id.test1);
        t.setText(user.getNickname() + ": " + user.getAramMMR());
    }
}