package com.antika.berk.ggeasylol.object;



public class BuildObject {

    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private String item6;
    private String name;
    private String icon;
    private String puan;
    private String id;
    private String championID;
    private String eksi;
    private String frame;




    public BuildObject(String item1,String item2,String item3,String item4,String item5,String item6,String name,String icon,String puan
            ,String id,String championID,String eksi,String frame){
        this.item1 =item1;
        this.item2 =item2;
        this.item3 =item3;
        this.item4 =item4;
        this.item5 =item5;
        this.item6 =item6;
        this.name =name;
        this.icon =icon;
        this.puan =puan;
        this.id =id;
        this.championID =championID;
        this.eksi =eksi;
        this.frame =frame;

    }
    public String getItem1(){
        return item1;
    }
    public String getItem2(){
        return item2;
    }
    public String getItem3(){
        return item3;
    }
    public String getItem4(){
        return item4;
    }
    public String getItem5(){
        return item5;
    }
    public String getItem6(){
        return item6;
    }
    public String getName(){
        return name;
    }
    public String getIcon(){
        return icon;
    }
    public String getPuan(){
        return puan;
    }
    public String getId(){
        return id;
    }
    public String getChampionID(){
        return championID;
    }
    public String getEksi(){
        return eksi;
    }
    public String getFrame(){
        return frame;
    }






}
