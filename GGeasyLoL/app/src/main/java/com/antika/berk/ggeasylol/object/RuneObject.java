package com.antika.berk.ggeasylol.object;

/**
 * Created by berke on 30.11.2016.
 */

public class RuneObject {
    private int id, slot;

    public RuneObject(int id, int slot){
        this.id   = id;
        this.slot = slot;
    }

    public int getId()  {return id;}
    public int getSlot(){return slot;}
}
