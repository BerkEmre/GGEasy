package com.antika.berk.ggeasylol.Other;

/**
 * Created by berke on 14.11.2016.
 */

public class ChampionMaster {
    private String id, level, point;
    public ChampionMaster(String id, String level, String point)
    {
        this.id = id;
        this.level = level;
        this.point = point;
    }

    public String getId(){ return id; }
    public String getLevel(){ return level; }
    public String getPoint(){ return point; }
}
