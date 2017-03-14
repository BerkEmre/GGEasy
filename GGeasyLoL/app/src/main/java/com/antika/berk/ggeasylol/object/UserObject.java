package com.antika.berk.ggeasylol.object;

/**
 * Created by berke on 14.03.2017.
 */

public class UserObject {
    private String email, sifre, region, summonerID;

    public UserObject(String email, String sifre, String region, String summonerID)
    {
        this.email = email;
        this.sifre = sifre;
        this.region = region;
        this.summonerID = summonerID;
    }

    public String getEmail() {return email;}
    public String getSifre(){return sifre;}
    public String getRegion(){return region;}
    public String getSummonerID(){return summonerID;}
    public void setEmail(String email){ this.email = email;}
    public void setSifre(String sifre){ this.sifre = sifre;}
    public void setRegion(String region){ this.region = region;}
    public void setSummonerID(String summonerID){ this.summonerID = summonerID;}
}
