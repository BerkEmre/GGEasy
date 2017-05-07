package com.antika.berk.ggeasylol.object;

public class ItemObject {
    private int  id;
    private String name;


    public ItemObject(int id,String name){
        this.id=id;
        this.name=name;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
}