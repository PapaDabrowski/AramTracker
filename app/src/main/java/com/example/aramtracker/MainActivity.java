package com.example.aramtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserStatistics user = null;
        try {
            user = new UserStatistics("horniss");
            TextView t = (TextView) findViewById(R.id.test1);
            t.setText(user.getNickname());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}