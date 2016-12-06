package com.antika.berk.ggeasylol.object;

/**
 * Created by berke on 30.11.2016.
 */

public class RankedStatObject {
    private int championID, totalDeathsPerSession, totalSessionsPlayed, totalDamageTaken,
            totalQuadraKills, totalTripleKills, totalMinionKills, maxChampionsKilled,
            totalDoubleKills, totalPhysicalDamageDealt, totalChampionKills, totalAssists,
            mostChampionKillsPerSession, totalDamageDealt, totalFirstBlood, totalSessionsLost,
            totalSessionsWon, totalMagicDamageDealt, totalGoldEarned, totalPentaKills,
            totalTurretsKilled, mostSpellsCast, maxNumDeaths, totalUnrealKills;

    public RankedStatObject(int championID, int totalDeathsPerSession, int totalSessionsPlayed,
                            int totalDamageTaken, int totalQuadraKills, int totalTripleKills,
                            int totalMinionKills, int maxChampionsKilled, int totalDoubleKills,
                            int totalPhysicalDamageDealt, int totalChampionKills, int totalAssists,
                            int mostChampionKillsPerSession, int totalDamageDealt,
                            int totalFirstBlood, int totalSessionsLost, int totalSessionsWon,
                            int totalMagicDamageDealt, int totalGoldEarned, int totalPentaKills,
                            int totalTurretsKilled, int mostSpellsCast, int maxNumDeaths,
                            int totalUnrealKills) {
        this.championID                  = championID;
        this.totalDeathsPerSession       = totalDeathsPerSession;
        this.totalSessionsPlayed         = totalSessionsPlayed;
        this.totalDamageTaken            = totalDamageTaken;
        this.totalQuadraKills            = totalQuadraKills;
        this.totalTripleKills            = totalTripleKills;
        this.totalMinionKills            = totalMinionKills;
        this.maxChampionsKilled          = maxChampionsKilled;
        this.totalDoubleKills            = totalDoubleKills;
        this.totalPhysicalDamageDealt    = totalPhysicalDamageDealt;
        this.totalChampionKills          = totalChampionKills;
        this.totalAssists                = totalAssists;
        this.mostChampionKillsPerSession = mostChampionKillsPerSession;
        this.totalDamageDealt            = totalDamageDealt;
        this.totalFirstBlood             = totalFirstBlood;
        this.totalSessionsLost           = totalSessionsLost;
        this.totalSessionsWon            = totalSessionsWon;
        this.totalMagicDamageDealt       = totalMagicDamageDealt;
        this.totalGoldEarned             = totalGoldEarned;
        this.totalPentaKills             = totalPentaKills;
        this.totalTurretsKilled          = totalTurretsKilled;
        this.mostSpellsCast              = mostSpellsCast;
        this.maxNumDeaths                = maxNumDeaths;
        this.totalUnrealKills            = totalUnrealKills;
    }

    public int getChampionID()                 { return championID;}
    public int getTotalDeathsPerSession()      { return totalDeathsPerSession;}
    public int getTotalSessionsPlayed()        { return totalSessionsPlayed;}
    public int getTotalDamageTaken()           { return totalDamageTaken;}
    public int getTotalQuadraKills()           { return totalQuadraKills;}
    public int getTotalTripleKills()           { return totalTripleKills;}
    public int getTotalMinionKills()           { return totalMinionKills;}
    public int getMaxChampionsKilled()         { return maxChampionsKilled;}
    public int getTotalDoubleKills()           { return totalDoubleKills;}
    public int getTotalPhysicalDamageDealt()   { return totalPhysicalDamageDealt;}
    public int getTotalChampionKills()         { return totalChampionKills;}
    public int getTotalAssists()               { return totalAssists;}
    public int getMostChampionKillsPerSession(){ return mostChampionKillsPerSession;}
    public int getTotalDamageDealt()           { return totalDamageDealt;}
    public int getTotalFirstBlood()            { return totalFirstBlood;}
    public int getTotalSessionsLost()          { return totalSessionsLost;}
    public int getTotalSessionsWon()           { return totalSessionsWon;}
    public int getTotalMagicDamageDealt()      { return totalMagicDamageDealt;}
    public int getTotalGoldEarned()            { return totalGoldEarned;}
    public int getTotalPentaKills()            { return totalPentaKills;}
    public int getTotalTurretsKilled()         { return totalTurretsKilled;}
    public int getMostSpellsCast()             { return mostSpellsCast;}
    public int getMaxNumDeaths()               { return maxNumDeaths;}
    public int getTotalUnrealKills()           { return totalUnrealKills;}
}
