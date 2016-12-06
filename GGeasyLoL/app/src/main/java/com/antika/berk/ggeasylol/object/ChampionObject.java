package com.antika.berk.ggeasylol.object;

import java.io.Serializable;

public class ChampionObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String championID;
    private String championKey;
    private String championName;
    private String championTitle;

    public ChampionObject() {
        super();
    }

    public ChampionObject(String championKey, String championName, String championTitle, String championID) {
        super();
        this.championKey   = championKey;
        this.championName  = championName;
        this.championTitle = championTitle;
        this.championID    = championID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChampionKey() {
        return championKey;
    }

    public void setChampionKey(String championKey) {
        this.championKey = championKey;
    }

    public String getChampionName() {
        return championName;
    }

    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public String getChampionTitle() {
        return championTitle;
    }

    public void setChampionTitle(String championTitle) {
        this.championTitle = championTitle;
    }

    public String getChampionID() {
        return championID;
    }

    public void setChampionID(String championID) {
        this.championID = championID;
    }
}
