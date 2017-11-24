package com.antika.berk.ggeasylol.object;

public class MatchHistoryObject {
    private boolean winner;
    private int champLevel;
    private int kills;
    private int deaths;
    private int assists;
    private int champion;
    private int gameMode;
    private int spell1;
    private int spell2;
    private int minionsKilled;
    private int neutralMinionsKilled;
    private int goldEarned;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int item6;
    private int item7;


    public MatchHistoryObject(boolean winner, int champLevel, int kills, int deaths, int assists, int champion, int gameMode, int spell1,
                              int spell2, int minionsKilled, int neutralMinionsKilled, int goldEarned, int item1,
                              int item2, int item3, int item4, int item5, int item6, int item7) {

        this.winner = winner;
        this.champLevel = champLevel;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.champion = champion;
        this.gameMode = gameMode;
        this.spell1 = spell1;
        this.spell2 = spell2;
        this.minionsKilled = minionsKilled;
        this.neutralMinionsKilled = neutralMinionsKilled;
        this.goldEarned = goldEarned;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.item5 = item5;
        this.item6 = item6;
        this.item7 = item7;

    }

    public boolean isWinner() {
        return winner;
    }

    public int getChampLevel() {
        return champLevel;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getChampion() {
        return champion;
    }

    public int getGameMode() {
        return gameMode;
    }

    public int getSpell1() {
        return spell1;
    }

    public int getSpell2() {
        return spell2;
    }

    public int getMinionsKilled() {
        return minionsKilled;
    }

    public int getNeutralMinionsKilled() {
        return neutralMinionsKilled;
    }

    public int getGoldEarned() {
        return goldEarned;
    }

    public int getItem1() {
        return item1;
    }

    public int getItem2() {
        return item2;
    }

    public int getItem3() {
        return item3;
    }

    public int getItem4() {
        return item4;
    }

    public int getItem5() {
        return item5;
    }

    public int getItem6() {
        return item6;
    }

    public int getItem7() {
        return item7;
    }
}
