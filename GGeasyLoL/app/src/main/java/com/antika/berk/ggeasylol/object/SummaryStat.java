package com.antika.berk.ggeasylol.object;

/**
 * Created by berke on 1.12.2016.
 */

public class SummaryStat {
    private String type;
    private int losses, win, totalNeutralMinionsKilled, totalMinionKills, totalChampionKills,
            totalAssists, totalTurretsKilled;

    public SummaryStat(String type, int losses, int win, int totalNeutralMinionsKilled,
                       int totalMinionKills, int totalChampionKills, int totalAssists,
                       int totalTurretsKilled){
        this.type                      = type;
        this.losses                    = losses;
        this.win                       = win;
        this.totalNeutralMinionsKilled = totalNeutralMinionsKilled;
        this.totalMinionKills          = totalMinionKills;
        this.totalChampionKills        = totalChampionKills;
        this.totalAssists              = totalAssists;
        this.totalTurretsKilled        = totalTurretsKilled;
    }

    public String getType()                  {return type;}
    public int getLosses()                   {return losses;}
    public int getWin()                      {return win;}
    public int getTotalNeutralMinionsKilled(){return totalNeutralMinionsKilled;}
    public int getTotalMinionKills()         {return totalMinionKills;}
    public int getTotalChampionKills()       {return totalChampionKills;}
    public int getTotalAssists()             {return totalAssists;}
    public int getTotalTurretsKilled()       {return totalTurretsKilled;}
}
