package com.example.aramtracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UserStatistics {
    public final String nickname;
    public final String aramMMR;
    public String data;

    public UserStatistics(String nickname) throws IOException, JSONException {
        this.nickname = nickname;
        this.data = GetApi.getFromAram(nickname);
        this.aramMMR = processAramMMR(this.data);
//        try {
//            this.data = GetApi.getFromAram(nickname);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }

    public String getNickname()
    {
        return nickname;
    }

    public String getAramMMR()
    {
        return aramMMR;
    }

    public String processAramMMR(String data) throws JSONException {
        JSONObject aram = (new JSONObject(data)).getJSONObject("ARAM");
        String aramMMR = aram.getString("avg");

        return aramMMR;
    }
}
