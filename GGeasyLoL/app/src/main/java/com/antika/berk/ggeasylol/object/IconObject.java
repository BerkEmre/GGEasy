package com.antika.berk.ggeasylol.object;



public class IconObject {

    private  String id,name,puan;




    public IconObject(String id,String name,String puan){
        this.id=id;
        this.name=name;
        this.puan=puan;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getPuan(){
        return puan;
    }
}
