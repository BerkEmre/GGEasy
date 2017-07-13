package com.antika.berk.ggeasylol.object;

import java.util.List;



public class RunePageObject {
    private String aciklama;
    private String name;
    private int adet;
    private String  image;

    public RunePageObject(String aciklama, String name,int adet,String image){
        this.aciklama = aciklama;
        this.name  = name;
        this.adet  = adet;
        this.image  = image;
    }

    public String getAciklama(){return aciklama;}
    public String getName()           {return name;}
    public int getAdet()           {return adet;}
    public String getImage()           {return image;}


}
