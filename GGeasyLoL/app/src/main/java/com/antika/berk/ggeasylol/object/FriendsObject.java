package com.antika.berk.ggeasylol.object;





public class FriendsObject {

    private String sihirdarAdi, sihirdarID,region,puan,icon,id,frame;

    public FriendsObject(String sihirdarAdi, String sihirdarID,String region,String puan,String icon,String id,String frame){
        this.sihirdarAdi   = sihirdarAdi;
        this.sihirdarID = sihirdarID;
        this.region = region;
        this.puan = puan;
        this.icon=icon;
        this.id=id;
        this.frame=frame;
    }

    public String getSihirdarAdi()  {return sihirdarAdi;}
    public String getSihirdarID(){return sihirdarID;}
    public String getRegion(){return region;}
    public String getPuan(){return puan;}
    public String getIcon(){return icon;}
    public String getId(){return id;}
    public String getFrame(){return frame;}





}
