package com.antika.berk.ggeasylol.object;

public class ChallengeObject {

    private String sihirdarAdi1, sihirdarID1,region1,puan1,icon1,id,sihirdarAdi2, sihirdarID2,region2,puan2,icon2,status,gorev,winner;

    public ChallengeObject(String sihirdarAdi1, String sihirdarID1,String region1,String puan1,String icon1,String id,String sihirdarAdi2, String sihirdarID2,String region2,String puan2,String icon2,String status,String gorev,String winner){
        this.sihirdarAdi1   = sihirdarAdi1;
        this.sihirdarID1 = sihirdarID1;
        this.region1 = region1;
        this.puan1 = puan1;
        this.icon1=icon1;
        this.id=id;
        this.sihirdarAdi2   = sihirdarAdi2;
        this.sihirdarID2 = sihirdarID2;
        this.region2 = region2;
        this.puan2 = puan2;
        this.icon2=icon2;
        this.status=status;
        this.gorev=gorev;
        this.winner=winner;
    }

    public String getSihirdarAdi1()  {return sihirdarAdi1;}
    public String getSihirdarID1(){return sihirdarID1;}
    public String getRegion1(){return region1;}
    public String getPuan1(){return puan1;}
    public String getIcon1(){return icon1;}
    public String getId(){return id;}
    public String getSihirdarAdi2()  {return sihirdarAdi2;}
    public String getSihirdarID2(){return sihirdarID2;}
    public String getRegion2(){return region2;}
    public String getPuan2(){return puan2;}
    public String getIcon2(){return icon2;}
    public String getStatus(){return status;}
    public String getGorev(){return gorev;}
    public String getWinner(){return winner;}






}