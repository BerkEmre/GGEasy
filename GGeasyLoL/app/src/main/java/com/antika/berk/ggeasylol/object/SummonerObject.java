package com.antika.berk.ggeasylol.object;

public class SummonerObject {
    private String key, name;
    private int id, icon, lvl,accountID;

    public SummonerObject(String key, int id, String name, int icon, int lvl,int accountID)
    {
        this.key  = key;
        this.id   = id;
        this.name = name;
        this.icon = icon;
        this.lvl  = lvl;
        this.accountID  = accountID;
    }

    public String getKey() {return key;}
    public int getId()     {return id;}
    public String getName(){return name;}
    public int getIcon()   {return icon;}
    public int getLvl()    {return lvl;}
    public int getAccountID()    {return accountID;}
}
