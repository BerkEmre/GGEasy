package com.antika.berk.ggeasylol.object;


public class ChampionSkinObject {
    private String skinName;
    private int num;
    private String key;

    public ChampionSkinObject(String skinName,int num,String key){
        this.skinName=skinName;
        this.num=num;
        this.key=key;
    }
    public String getSkinName(){
        return skinName;
    }
    public int getNum(){
        return num;
    }
    public String getKey(){
        return key;
    }
}
