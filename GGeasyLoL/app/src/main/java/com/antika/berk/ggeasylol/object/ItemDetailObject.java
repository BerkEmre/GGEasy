package com.antika.berk.ggeasylol.object;


import java.util.List;

public class ItemDetailObject {
    private String id;
    private String name;
    private String  gold;
    private String aciklama;
    private List<Item2Object> from;
    private List<Item2Object> to;


    public ItemDetailObject(String id,String name,String gold,String aciklama,List<Item2Object>from,List<Item2Object>to){
        this.id =id;
        this.name=name;
        this.gold=gold;
        this.aciklama=aciklama;
        this.from=from;
        this.to=to;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getGold(){
        return gold;
    }
    public String getAciklama(){
        return aciklama;
    }
    public List<Item2Object>getFrom(){return from;}
    public List<Item2Object>getTo(){return to;}
}
