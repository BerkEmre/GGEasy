package com.antika.berk.ggeasylol.object;


public class ChampionSkillObject {
    private String skillName;
    private String description;
    private String image;

    public ChampionSkillObject(String skillName,String description,String image){
        this.skillName=skillName;
        this.description=description;
        this.image=image;
    }
    public String getSkillName(){
        return skillName;
    }
    public String getDescription(){
        return description;
    }
    public String getImage(){
        return image;
    }
}


