package com.antika.berk.ggeasylol.object;

public class LotteryObject {
    private String ID, name, odul, img, end_date, winnderID, status;

    public LotteryObject(String ID, String name, String odul, String img, String end_date,
                         String winnderID, String status){
        this.ID        = ID;
        this.name      = name;
        this.odul      = odul;
        this.img       = img;
        this.end_date  = end_date;
        this.winnderID = winnderID;
        this.status    = status;
    }

    public String getID(){return ID;}
    public void setID(String ID){this.ID = ID;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getOdul(){return odul;}
    public void setOdul(String odul){this.odul = odul;}

    public String getImg(){return img;}
    public void setImg(String img){this.img = img;}

    public String getEnd_date(){return end_date;}
    public void setEnd_date(String end_date){this.end_date = end_date;}

    public String getWinnderID(){return winnderID;}
    public void setWinnderID(String winnderID){this.winnderID = winnderID;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}
}
