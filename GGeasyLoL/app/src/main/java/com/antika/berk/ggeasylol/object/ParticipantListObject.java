package com.antika.berk.ggeasylol.object;

public class ParticipantListObject {
    private String isim,
            league = "",
            league_division = "",
            league_progress = "";
    private  int master,
            master_icon;
    private int team, championID,spell1,
            spell2, lost,league_point;

    public ParticipantListObject(String isim, int team, int championID, int spell1, int spell2,
                                 String league, String league_division, int league_point, String league_progress,int master,int master_icon) {
        super();
        this.isim = isim;
        this.team = team;
        this.championID = championID;
        this.spell1 = spell1;
        this.spell2 = spell2;
        this.league = league;
        this.league_division = league_division;
        this.league_point = league_point;
        this.league_progress = league_progress;
        this.master=master;
        this.master_icon=master_icon;
    }

    @Override
    public String toString() {
        return isim;
    }

    public String getIsim() {
        return isim;
    }

    public int getMaster() {
        return master;
    }

    public int getMaster_icon() {
        return master_icon;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public int getChampionID() {
        return championID;
    }

    public void setChampionID(int championID) {
        this.championID = championID;
    }

    public int getSpell1() {
        return spell1;
    }

    public void setSpell1(int spell1) {
        this.spell1 = spell1;
    }

    public int getSpell2() {
        return spell2;
    }

    public void setSpell2(int spell2) {
        this.spell2 = spell2;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getLeague_division() {
        return league_division;
    }

    public void setLeague_division(String league_division) {
        this.league_division = league_division;
    }

    public int getLeague_point() {
        return league_point;
    }

    public void setLeague_point(int league_point) {
        this.league_point = league_point;
    }

    public String getLeague_progress() {
        return league_progress;
    }

    public void setLeague_progress(String league_progress) {
        this.league_progress = league_progress;
    }
}
