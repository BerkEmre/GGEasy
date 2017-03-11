package com.antika.berk.ggeasylol.object;

import com.antika.berk.ggeasylol.Other.Mission;

import java.lang.reflect.Method;



public class MissionObject {
    private boolean winner;
    private int champLevel;
    private int kills;
    private int doubleKills;
    private int tripleKills;
    private int quadraKills;
    private int pentaKills;
    private int deaths;
    private int assists;
    private int minionsKilled;
    private int neutralMinionsKilled;
    private int goldEarned;
    private int towerKills;
    private int wardsPlaced;
    private int wardsKilled;
    private int largestMultiKill;
    private int totalDamageDealtToChampions;


    public MissionObject(boolean winner,int champLevel,int kills,int doubleKills,int tripleKills,int quadraKills,int pentaKills,
                         int deaths,int assists,int minionsKilled,int neutralMinionsKilled,int goldEarned,int towerKills,int wardsPlaced,
                         int wardsKilled, int largestMultiKill,int totalDamageDealtToChampions){

        this.winner=winner;
        this.champLevel=champLevel;
        this.kills=kills;
        this.doubleKills=doubleKills;
        this.tripleKills=tripleKills;
        this.quadraKills=quadraKills;
        this.pentaKills=pentaKills;
        this.deaths=deaths;
        this.assists=assists;
        this.minionsKilled=minionsKilled;
        this.neutralMinionsKilled=neutralMinionsKilled;
        this.goldEarned=goldEarned;
        this.towerKills=towerKills;
        this.wardsPlaced=wardsPlaced;
        this.wardsKilled=wardsKilled;
        this.largestMultiKill=largestMultiKill;
        this.totalDamageDealtToChampions=totalDamageDealtToChampions;

    }

    public boolean isWinner(){
        return winner;
    }
    public int getChampLevel(){
        return champLevel;
    }
    public int getKills(){
        return kills;
    }
    public int getDoubleKills(){
        return doubleKills;
    }
    public int getTripleKills(){
        return tripleKills;
    }
    public int getQuadraKills(){
        return quadraKills;
    }
    public int getPentaKills(){
        return pentaKills;
    }
    public int getDeaths(){
        return deaths;
    }
    public int getAssists(){
        return assists;
    }
    public int getMinionsKilled(){
        return minionsKilled;
    }
    public int getNeutralMinionsKilled(){
        return neutralMinionsKilled;
    }
    public int getGoldEarned(){
        return goldEarned;
    }
    public int getTowerKills(){
        return towerKills;
    }
    public int getWardsPlaced(){
        return wardsPlaced;
    }
    public int getWardsKilled(){
        return wardsKilled;
    }
    public int getLargestMultiKill(){
        return largestMultiKill;
    }
    public int getTotalDamageDealtToChampions(){
        return totalDamageDealtToChampions;
    }



}

