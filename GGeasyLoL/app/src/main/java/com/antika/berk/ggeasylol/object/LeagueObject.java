package com.antika.berk.ggeasylol.object;

/**
 * Created by berke on 1.12.2016.
 */

public class LeagueObject {
    private String queue, name, tier, division, miniSeriesprogress;
    private int leaguePoints, losses, wins, miniSeriestarget, miniSerieslosses, miniSerieswins;
    private boolean isFreshBlood, isHotStreak, isInactive, isVeteran;

    public LeagueObject(String queue, String name, String tier, String division,
                        int leaguePoints, int losses, int wins, boolean isFreshBlood,
                        boolean isHotStreak, boolean isInactive, boolean isVeteran,
                        String miniSeriesprogress, int miniSeriestarget, int miniSerieslosses,
                        int miniSerieswins){
        this.queue              = queue;
        this.name               = name;
        this.tier               = tier;
        this.division           = division;
        this.leaguePoints       = leaguePoints;
        this.losses             = losses;
        this.wins               = wins;
        this.isFreshBlood       = isFreshBlood;
        this.isHotStreak        = isHotStreak;
        this.isInactive         = isInactive;
        this.isVeteran          = isVeteran;
        this.miniSeriesprogress = miniSeriesprogress;
        this.miniSeriestarget   = miniSeriestarget;
        this.miniSerieslosses   = miniSerieslosses;
        this.miniSerieswins     = miniSerieswins;
    }

    public String getQueue()             {return queue;}
    public String getName()              {return name;}
    public String getTier()              {return tier;}
    public String getDivision()          {return division;}
    public int getLeaguePoints()         {return leaguePoints;}
    public int getLosses()               {return losses;}
    public int getWins()                 {return wins;}
    public boolean getFreshBlood()       {return isFreshBlood;}
    public boolean getHotStreak()        {return isHotStreak;}
    public boolean getInactive()         {return isInactive;}
    public boolean getVeteran()          {return isVeteran;}
    public String getMiniSeriesprogress(){return miniSeriesprogress;}
    public int getMiniSeriestarget()     {return miniSeriestarget;}
    public int getMiniSerieslosses()     {return miniSerieslosses;}
    public int getMiniSerieswins()       {return miniSerieswins;}
}
