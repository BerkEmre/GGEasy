package com.antika.berk.ggeasylol.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.SpellObject;
import com.antika.berk.ggeasylol.object.Sumonner;
import com.antika.berk.ggeasylol.object.UserObject;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME   = "GGeasyLoL";
    private static final String TABLE_CHAMPIONS = "champions";
    private static final String TABLE_SPELL     = "spell";
    private static final String TABLE_SUMONNER  = "sumonner";
    private static final String TABLE_MATCH     = "matchID";
    private static final String TABLE_USER      = "user";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 22);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_CHAMPIONS +
                "(" +
                "id INTEGER PRIMARY KEY," +
                "champion_id       TEXT," +
                "champion_name     TEXT," +
                "champion_key      TEXT," +
                "champion_title    TEXT"  +
                ")";
        db.execSQL(sql);

        sql = "CREATE TABLE " + TABLE_SPELL +
                "(" +
                "id INTEGER PRIMARY KEY," +
                "spell_id          TEXT," +
                "spell_key         TEXT," +
                "spell_name        TEXT"  +
                ")";
        db.execSQL(sql);

        sql = "CREATE TABLE " + TABLE_SUMONNER +
                "(" +
                "id INTEGER PRIMARY KEY," +
                "sumonner_id       TEXT," +
                "sumonner_name     TEXT," +
                "sumonner_key      TEXT," +
                "sumonner_icon     TEXT" +
                ")";
        db.execSQL(sql);

        sql = "CREATE TABLE " + TABLE_MATCH +
                "(" +
                "id INTEGER PRIMARY KEY," +
                "match_id          TEXT," +
                "gorev             TEXT" +
                ")";
        db.execSQL(sql);

        sql = "CREATE TABLE " + TABLE_USER +
                "(" +
                "id INTEGER PRIMARY KEY," +
                "region            TEXT," +
                "summonerID        TEXT," +
                "email             TEXT," +
                "sifre             TEXT" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAMPIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPELL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUMONNER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATCH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public void insertChampion(ChampionObject champion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("champion_id"   , champion.getChampionID()   );
        values.put("champion_name" , champion.getChampionName() );
        values.put("champion_key"  , champion.getChampionKey()  );
        values.put("champion_title", champion.getChampionTitle());
        db.insert(TABLE_CHAMPIONS, null, values);
        db.close();
    }

    public ChampionObject getChampion(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_CHAMPIONS, new String[]{
                "id", "champion_id", "champion_name", "champion_key", "champion_title"
        },null, null, null, null, null);

        while (cursor.moveToNext()) {
            if(cursor.getString(1).equals(ID))
            {
                ChampionObject champion = new ChampionObject();
                champion.setId(cursor.getInt(0)              );
                champion.setChampionID(cursor.getString(1)   );
                champion.setChampionName(cursor.getString(2) );
                champion.setChampionKey(cursor.getString(3)  );
                champion.setChampionTitle(cursor.getString(4));
                return champion;
            }
        }
        return null;
    }

    public void insertSpell(SpellObject spell) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("spell_id"   , spell.getSpellID()  );
        values.put("spell_name" , spell.getSpellName());
        values.put("spell_key"  , spell.getSpellKey() );
        db.insert(TABLE_SPELL, null, values);
        db.close();
    }

    public SpellObject getSpell(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SPELL, new String[]{
                "id", "spell_id", "spell_name", "spell_key"
        },null, null, null, null, null);

        while (cursor.moveToNext()) {
            if(cursor.getString(1).equals(ID))
            {
                SpellObject spell = new SpellObject();
                spell.setId(cursor.getInt(0)          );
                spell.setSpellID(cursor.getString(1)  );
                spell.setSpellName(cursor.getString(2));
                spell.setSpellKey(cursor.getString(3) );
                return spell;
            }
        }
        return null;
    }

    public void insertSumonner(Sumonner sumonner) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("sumonner_id"   , sumonner.getSumonnerID()   );
        values.put("sumonner_name" , sumonner.getSumonnerName() );
        values.put("sumonner_key"  , sumonner.getSumonnerKey()  );
        values.put("sumonner_icon" , sumonner.getSumonnerIcon());
        db.insert(TABLE_SUMONNER, null, values);
        db.close();
    }

    public Sumonner getSumonner(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SUMONNER, new String[]{
                "id", "sumonner_id", "sumonner_name", "sumonner_key", "sumonner_icon"
        },null, null, null, null, null);

        while (cursor.moveToNext()) {
            if(cursor.getString(1).equals(ID))
            {
                Sumonner sumonner = new Sumonner();
                sumonner.setId(cursor.getInt(0)             );
                sumonner.setSumonnerID(cursor.getString(1)  );
                sumonner.setSumonnerName(cursor.getString(2));
                sumonner.setSumonnerKey(cursor.getString(3) );
                sumonner.setSumonnerIcon(cursor.getString(4));
                return sumonner;
            }
        }
        return null;
    }

    public List<Sumonner> getAllSunmonners() {
        List<Sumonner> sumonners = new ArrayList<Sumonner>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_SUMONNER, new String[]{
                "id", "sumonner_id", "sumonner_name", "sumonner_key", "sumonner_icon"
        }, null, null, null, null, null);

        while (cursor.moveToNext()) {
            Sumonner sumonner = new Sumonner();
            sumonner.setId(cursor.getInt(0));
            sumonner.setSumonnerID(cursor.getString(1)  );
            sumonner.setSumonnerName(cursor.getString(2));
            sumonner.setSumonnerKey(cursor.getString(3) );
            sumonner.setSumonnerIcon(cursor.getString(4));
            sumonners.add(sumonner);
        }

        return sumonners;
    }

    public void insertMatch (String id, String gorev) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_MATCH + " WHERE gorev = '" + gorev + "'");

        ContentValues values = new ContentValues();
        values.put("match_id",id);
        values.put("gorev",gorev);
        db.insert(TABLE_MATCH, null, values);
        db.close();
    }

    public String getMatch(String gorev) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_MATCH, new String[]{
                "id", "match_id", "gorev"
        },null, null, null, null, null);
        String match_id = "";
        while (cursor.moveToNext()) {
            if(gorev.equals(cursor.getString(2)))
                match_id=cursor.getString(1);
        }
        return match_id;
    }

    public void insertUser (String email, String sifre, String region, String summonerID) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_USER);

        ContentValues values = new ContentValues();
        values.put("region",region);
        values.put("summonerID",summonerID);
        values.put("email",email);
        values.put("sifre",sifre);
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public UserObject getUser() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[]{
        "id", "region", "summonerID", "email", "sifre"},null, null, null, null, null);

        UserObject uo = new UserObject("","","","");

        while (cursor.moveToNext()) {
            uo = new UserObject(cursor.getString(3), cursor.getString(4), cursor.getString(1), cursor.getString(2));
            return uo;
        }
        return uo;
    }
}
