package com.antika.berk.ggeasylol.object;



public class MissionTeamObject {

    private boolean winner;
    private boolean firstBlood;
    private boolean firstTower;
    private boolean firstInhibitor;
    private boolean firstBaron;
    private boolean firstDragon;
    private int towerKills;
    private int inhibitorKills;
    private int baronKills;
    private int dragonKills;


    public MissionTeamObject(boolean winner,boolean firstBlood,boolean firstTower, boolean firstInhibitor,boolean firstBaron,
                             boolean firstDragon,int towerKills,int inhibitorKills,int baronKills,int dragonKills){
        this.winner=winner;
        this.firstBlood=firstBlood;
        this.firstTower=firstTower;
        this.firstInhibitor=firstInhibitor;
        this.firstBaron=firstBaron;
        this.firstDragon=firstDragon;
        this.towerKills=towerKills;
        this.inhibitorKills=inhibitorKills;
        this.baronKills=baronKills;
        this.dragonKills=dragonKills;

    }
    public boolean isWinner(){
        return winner;
    }
    public boolean isFirstBlood(){
        return firstBlood;
    }
    public boolean isFirstTower(){
        return firstTower;
    }
    public boolean isFirstInhibitor(){
        return firstInhibitor;
    }
    public boolean isFirstBaron(){
        return firstBaron;
    }
    public boolean isFirstDragon(){
        return firstDragon;
    }
    public int getTowerKills(){
        return towerKills;
    }
    public int getInhibitorKills(){
        return inhibitorKills;
    }
    public int getBaronKills(){
        return baronKills;
    }
    public int getDragonKills(){
        return dragonKills;
    }

}
