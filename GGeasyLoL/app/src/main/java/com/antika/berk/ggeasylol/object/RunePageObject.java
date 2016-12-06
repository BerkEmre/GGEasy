package com.antika.berk.ggeasylol.object;

import java.util.List;

/**
 * Created by berke on 30.11.2016.
 */

public class RunePageObject {
    private List<RuneObject> runes;
    private int id;
    private String name;

    public RunePageObject(List<RuneObject> runes, int id, String name){
        this.runes = runes;
        this.id    = id;
        this.name  = name;
    }

    public List<RuneObject> getRunes(){return runes;}
    public int getId()                {return id;}
    public String getName()           {return name;}
}
