package com.antika.berk.ggeasylol.object;

public class SummonerLottery {
    private String sumonnerName;
    private String summonerRegion;
    private String sumonnerIcon;
    int durum;


    public SummonerLottery( String sumonnerName, String summonerRegion, String sumonnerIcon,int durum) {
        this.sumonnerName = sumonnerName;
        this.summonerRegion  = summonerRegion;
        this.sumonnerIcon  = sumonnerIcon;
        this.durum  = durum;
    }




    public String getSumonnerName() {
        return sumonnerName;
    }

    public String getSummonerRegion() {
        return summonerRegion;
    }

    public String getSumonnerIcon() {
        return sumonnerIcon;
    }

    public int getDurum() {
        return durum;
    }
}