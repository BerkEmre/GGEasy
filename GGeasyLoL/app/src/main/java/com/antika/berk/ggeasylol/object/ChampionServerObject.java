package com.antika.berk.ggeasylol.object;



public class ChampionServerObject {
    private String championID;
    private String championKey;
    private String championName;
    private String ip;
    private String rp;
    public ChampionServerObject(String championID,String championKey, String championName,String ip,String rp ) {

        this.championID    = championID;
        this.championKey   = championKey;
        this.championName  = championName;
        this.ip = ip;
        this.rp = rp;

    }
    public String getChampionID() {
        return championID;
    }
    public String getChampionKey() {
        return championKey;
    }
    public String getChampionName() {
        return championName;
    }
    public String getIp() {
        return ip;
    }
    public String getRp() {
        return rp;
    }
}
