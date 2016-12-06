package com.antika.berk.ggeasylol.object;

public class Sumonner {
    private static final long serialVersionUID = 1L;
    private int id;
    private String sumonnerID;
    private String sumonnerName;
    private String sumonnerKey;
    private String sumonnerIcon;

    public Sumonner() {
        super();
    }

    public Sumonner(String sumonnerID, String sumonnerName, String sumonnerKey, String sumonnerIcon) {
        super();
        this.sumonnerID   = sumonnerID;
        this.sumonnerName = sumonnerName;
        this.sumonnerKey  = sumonnerKey;
        this.sumonnerIcon  = sumonnerIcon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSumonnerID() {
        return sumonnerID;
    }

    public void setSumonnerID(String sumonnerID) {
        this.sumonnerID = sumonnerID;
    }

    public String getSumonnerName() {
        return sumonnerName;
    }

    public void setSumonnerName(String sumonnerName) {
        this.sumonnerName = sumonnerName;
    }

    public String getSumonnerKey() {
        return sumonnerKey;
    }

    public void setSumonnerKey(String sumonnerKey) {
        this.sumonnerKey = sumonnerKey;
    }

    public String getSumonnerIcon() {
        return sumonnerIcon;
    }

    public void setSumonnerIcon(String sumonnerIcon) {
        this.sumonnerIcon = sumonnerIcon;
    }
}
