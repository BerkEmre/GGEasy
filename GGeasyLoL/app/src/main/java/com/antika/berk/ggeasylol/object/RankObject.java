package com.antika.berk.ggeasylol.object;



public class RankObject {

    private String sihirdarAdi, sihirdarID,region,puan,icon,rank,frame;

    public RankObject(String sihirdarAdi, String sihirdarID,String region,String puan,String icon,String rank,String frame){
        this.sihirdarAdi   = sihirdarAdi;
        this.sihirdarID = sihirdarID;
        this.region = region;
        this.puan = puan;
        this.icon=icon;
        this.rank=rank;
        this.frame=frame;
    }

    public String getSihirdarAdi()  {return sihirdarAdi;}
    public String getSihirdarID(){return sihirdarID;}
    public String getRegion(){return region;}
    public String getPuan(){return puan;}
    public String getIcon(){return icon;}
    public String getRank(){return rank;}
    public String getFrame(){return frame;}






}
