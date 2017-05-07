package com.antika.berk.ggeasylol.object;


public class ParticipantObject {
    private int spell1Id, spell2Id, profileIconId, championId, teamId, summonerId;
    private String summonerName;

    public ParticipantObject( int spell1Id,
                             int  spell2Id,int  profileIconId,int  championId,int  teamId,
                             int  summonerId, String summonerName){
        this.spell1Id = spell1Id;
        this.spell2Id = spell2Id;
        this.profileIconId = profileIconId;
        this.championId = championId;
        this.teamId = teamId;
        this.summonerId = summonerId;
        this.summonerName = summonerName;
    }

    public int getSpell1Id(){return spell1Id;}
    public int getSpell2Id(){return spell2Id;}
    public int getProfileIconId(){return profileIconId;}
    public int getChampionId(){return championId;}
    public int getTeamId(){return teamId;}
    public int getSummonerId(){return summonerId;}
    public String getSummonerName(){return summonerName;}
}
