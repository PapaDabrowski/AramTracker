package com.example.aramtracker;

import java.io.IOException;

public class UserStatistics {
    public final String nickname;
    public String data;
    public UserStatistics(String nickname) throws IOException {
        this.nickname = nickname;
        try {
            this.data = GetApi.getFromAram(nickname);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public String getNickname()
    {
        return "https://eune.whatismymmr.com/api/v1/summoner?name=" + this.nickname;
    }
}
