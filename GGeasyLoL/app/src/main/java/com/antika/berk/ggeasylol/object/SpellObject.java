package com.antika.berk.ggeasylol.object;

import java.io.Serializable;

public class SpellObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String spellID;
    private String spellKey;
    private String spellName;

    public SpellObject() {
        super();
    }

    public SpellObject(String spellID, String spellKey, String spellName) {
        super();
        this.spellID   = spellID;
        this.spellKey  = spellKey;
        this.spellName = spellName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpellID() {
        return spellID;
    }

    public void setSpellID(String spellID) {
        this.spellID = spellID;
    }

    public String getSpellKey() {
        return spellKey;
    }

    public void setSpellKey(String spellKey) {
        this.spellKey = spellKey;
    }

    public String getSpellName() {
        return spellName;
    }

    public void setSpellName(String spellName) {
        this.spellName = spellName;
    }
}
