package com.antika.berk.ggeasylol.object;


public class RozetObject {
    private String gorevAdi;
    private String gorevAdedi;

    public RozetObject(String gorevAdi, String gorevAdedi){
        this.gorevAdi   = gorevAdi;
        this.gorevAdedi = gorevAdedi;
    }

    public String getGorevAdi()  {return gorevAdi;}
    public String getGorevAdedi(){return gorevAdedi;}
}
