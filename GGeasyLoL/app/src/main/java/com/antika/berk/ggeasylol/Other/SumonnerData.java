package com.antika.berk.ggeasylol.Other;

/**
 * Created by berke on 13.11.2016.
 */

public class SumonnerData {
    private String sumonner_id, sumonner_name, sumonner_region, sumonner_icon, league, league_division, league_lose, league_win,
            league_point, stat_kill, stat_dead, stat_asist, stat_played, stat_win, stat_lost;

    private String[] champion_id, champion_level, champion_points, stat_champion_id, stat_champion_kill, stat_champion_dead,
            stat_champion_asist, stat_champion_played, stat_champion_win, stat_champion_lost;

    public SumonnerData(String sumonner_id,String sumonner_name,String sumonner_region,String sumonner_icon,String league,
                        String league_division,String league_lose,String league_win,String league_point,String stat_kill,
                        String stat_dead,String stat_asist,String stat_played,String stat_win,String stat_lost,

                        String[] champion_id,String[] champion_level,String[] champion_points,String[] stat_champion_id,
                        String[] stat_champion_kill,String[] stat_champion_dead,String[] stat_champion_asist,
                        String[] stat_champion_played,String[] stat_champion_win,String[] stat_champion_lost)
    {
        this.sumonner_id     = sumonner_id;
        this.sumonner_name   = sumonner_name;
        this.sumonner_region = sumonner_region;
        this.sumonner_icon   = sumonner_icon;
        this.league          = league;
        this.league_division = league_division;
        this.league_lose     = league_lose;
        this.league_win      = league_win;
        this.league_point    = league_point;
        this.stat_kill       = stat_kill;
        this.stat_dead       = stat_dead;
        this.stat_asist      = stat_asist;
        this.stat_played     = stat_played;
        this.stat_win        = stat_win;
        this.stat_lost       = stat_lost;

        this.champion_id          = champion_id;
        this.champion_level       = champion_level;
        this.champion_points      = champion_points;
        this.stat_champion_id     = stat_champion_id;
        this.stat_champion_kill   = stat_champion_kill;
        this.stat_champion_dead   = stat_champion_dead;
        this.stat_champion_asist  = stat_champion_asist;
        this.stat_champion_played = stat_champion_played;
        this.stat_champion_win    = stat_champion_win;
        this.stat_champion_lost   = stat_champion_lost;
    }

    public String getSumonner_id(){
        return sumonner_id;
    }
    public String getSumonner_name(){
        return sumonner_name;
    }
    public String getSumonner_region(){
        return sumonner_region;
    }
    public String getSumonner_icon(){
        return sumonner_icon;
    }
    public String getLeague(){
        return league;
    }
    public String getLeague_division(){
        return league_division;
    }
    public String getLeague_lose(){
        return league_lose;
    }
    public String getLeague_win(){
        return league_win;
    }
    public String getLeague_point(){
        return league_point;
    }
    public String getStat_kill(){
        return stat_kill;
    }
    public String getStat_dead(){
        return stat_dead;
    }
    public String getStat_asist(){
        return stat_asist;
    }
    public String getStat_played(){
        return stat_played;
    }
    public String getStat_win(){
        return stat_win;
    }
    public String getStat_lost(){
        return stat_lost;
    }

    public String[] getChampion_id() { return champion_id; }
    public String[] getChampion_level() { return champion_level; }
    public String[] getChampion_points() { return champion_points; }
    public String[] getStat_champion_id() { return stat_champion_id; }
    public String[] getStat_champion_kill() { return stat_champion_kill; }
    public String[] getStat_champion_dead() { return stat_champion_dead; }
    public String[] getStat_champion_asist() { return stat_champion_asist; }
    public String[] getStat_champion_played() { return stat_champion_played; }
    public String[] getStat_champion_win() { return stat_champion_win; }
    public String[] getStat_champion_lost() { return stat_champion_lost; }
}
