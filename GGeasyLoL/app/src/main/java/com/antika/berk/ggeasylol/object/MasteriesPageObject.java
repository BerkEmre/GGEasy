package com.antika.berk.ggeasylol.object;

import java.util.List;


public class MasteriesPageObject {
    private List<MasterObject> masteries;
    private int id;
    private String name;

    public MasteriesPageObject(List<MasterObject> masteries, int id, String name) {
        this.masteries = masteries;
        this.id        = id;
        this.name      = name;
    }

    public List<MasterObject> getMasteries(){return masteries;}
    public int getId(){return id;}
    public String getName(){return name;}
}
