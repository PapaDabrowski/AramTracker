package com.example.aramtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;
import org.json.JSONException;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        UserStatistics user = null;
        try {
            user = new UserStatistics("horniss");
            TextView t = (TextView) findViewById(R.id.test1);
            t.setText(user.getNickname() + ": " + user.getAramMMR());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}