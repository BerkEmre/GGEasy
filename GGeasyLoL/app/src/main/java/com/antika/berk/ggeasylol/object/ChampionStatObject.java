package com.antika.berk.ggeasylol.object;


 public class ChampionStatObject {
 private String armor,armorperlevel,attackdamage,attackdamageperlevel,attackrange,hp,hpperlevel,hpregen,hpregenperlevel,
         movespeed,mp,mpperlevel,mpregen,mpregenperlevel,
         spellblock,spellblockperlevel;
 private int attack,defense,magic,difficulty;

 public ChampionStatObject(String armor,String armorperlevel,String attackdamage,String attackdamageperlevel,String attackrange
 ,String hp,String hpperlevel,String hpregen
 ,String hpregenperlevel,String movespeed,String mp,String mpperlevel,String mpregen,String mpregenperlevel,String spellblock
 ,String spellblockperlevel,int attack,int defense,int magic,int difficulty){
 this.armor=armor;
 this.armorperlevel=armorperlevel;
 this.attackdamage=attackdamage;
 this.attackdamageperlevel=attackdamageperlevel;
 this.attackrange=attackrange;




 this.hp=hp;
 this.hpperlevel=hpperlevel;
 this.hpregen=hpregen;
 this.hpregenperlevel=hpregenperlevel;
 this.movespeed=movespeed;
 this.mp=mp;
 this.mpperlevel=mpperlevel;
 this.mpregen=mpregen;
 this.mpregenperlevel=mpregenperlevel;
 this.spellblock=spellblock;
 this.spellblockperlevel=spellblockperlevel;
 this.attack=attack;
 this.defense=defense;
 this.magic=magic;
 this.difficulty=difficulty;

 }
 public String getArmor(){
 return armor;
 }
 public String getArmorperlevel(){
 return armorperlevel;
 }
 public String getAttackdamage(){
 return attackdamage;
 }
 public String getAttackdamageperlevel(){
 return attackdamageperlevel;
 }
 public String getAttackrange(){
 return attackrange;
 }
 public String getHp(){
 return hp;
 }
 public String getHpperlevel(){
 return hpperlevel;
 }
 public String getHpregen(){
 return hpregen;
 }
 public String getHpregenperlevel(){
 return hpregenperlevel;
 }
 public String getMovespeed(){
 return movespeed;
 }
 public String getMp(){
 return mp;
 }
 public String getMpperlevel(){
 return mpperlevel;
 }
 public String getMpregen(){
 return mpregen;
 }
 public String getMpregenperlevel(){
 return mpregenperlevel;
 }
 public String getSpellblock(){
 return spellblock;
 }
 public String getSpellblockperlevel(){
 return spellblockperlevel;
 }
 public int getAttack(){
 return attack;}
 public int getDefense(){
 return defense;
 }
 public int getMagic(){
 return magic;
 }
 public int getDifficulty(){
 return difficulty;
 }


 }