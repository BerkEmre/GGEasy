package com.antika.berk.ggeasylol.object;

public class ChampionMasterObject {
    private int championPoints, championPointsUntilNextLevel, championLevel, tokensEarned,
            championId, championPointsSinceLastLevel, lastPlayTime;
    private boolean chestGranted;

    public ChampionMasterObject(int championPoints, int championPointsUntilNextLevel, int championLevel,
                                int tokensEarned, int championId, int championPointsSinceLastLevel,
                                int lastPlayTime,boolean chestGranted){
        this.championPoints               = championPoints;
        this.championPointsUntilNextLevel = championPointsUntilNextLevel;
        this.championLevel                = championLevel;
        this.tokensEarned                 = tokensEarned;
        this.championId                   = championId;
        this.championPointsSinceLastLevel = championPointsSinceLastLevel;
        this.lastPlayTime                 = lastPlayTime;
        this.chestGranted                 = chestGranted;
    }

    public int      getChampionPoints(){return championPoints;}
    public int      getChampionPointsUntilNextLevel() {return championPointsUntilNextLevel;}
    public int      getChampionLevel()                {return championLevel;}
    public int       getTokensEarned()                {return tokensEarned;}
    public int      getChampionId()                   {return championId;}
    public int      getChampionPointsSinceLastLevel() {return championPointsSinceLastLevel;}
    public int      getLastPlayTime()                 {return lastPlayTime;}
    public boolean  isChestGranted()                  {return chestGranted;}

}
