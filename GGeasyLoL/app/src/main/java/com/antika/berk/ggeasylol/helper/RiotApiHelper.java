package com.antika.berk.ggeasylol.helper;

import android.content.Context;
import android.util.Log;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.CurrentGameObject;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.MasterObject;
import com.antika.berk.ggeasylol.object.MasteriesPageObject;
import com.antika.berk.ggeasylol.object.MatchIdObject;
import com.antika.berk.ggeasylol.object.MissionObject;
import com.antika.berk.ggeasylol.object.MissionTeamObject;
import com.antika.berk.ggeasylol.object.ParticipantObject;
import com.antika.berk.ggeasylol.object.RankedStatObject;
import com.antika.berk.ggeasylol.object.RozetObject;
import com.antika.berk.ggeasylol.object.RuneObject;
import com.antika.berk.ggeasylol.object.RunePageObject;
import com.antika.berk.ggeasylol.object.SpellObject;
import com.antika.berk.ggeasylol.object.SummaryStat;
import com.antika.berk.ggeasylol.object.SummonerObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RiotApiHelper {
    public String apiKey   = "RGAPI-a088eafc-3507-43ea-b419-cb0f0acac8f7";
    public String version  = "7.9.1";
    public int iconSize    =  22;
    //Get summoner object with summoner name
    //V-3 YAPILDI
    public SummonerObject getSumonner(String summonerName, String region) {
        SummonerObject sumonner;

        JSONObject sumonnerObject;

        String JSONString = readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/summoner/v3/summoners/by-name/"+convertToUTF8(summonerName)+"?api_key="+apiKey);

        try{sumonnerObject = new JSONObject(JSONString);
            sumonner = new SummonerObject(null, sumonnerObject.getInt("id"),
                    sumonnerObject.getString("name"), sumonnerObject.getInt("profileIconId"),
                    sumonnerObject.getInt("summonerLevel"),sumonnerObject.getInt("accountId"));
            return sumonner;
        }
        catch (Exception e){Log.e("Hata",
                "Boş değer döndü SumonnerName: " + summonerName + " Region: " + region + " API Key: " + apiKey +
                        "\n" + e.toString());
            return null; }
    }
    //Get summoner object with summoner ID but key is null
    //V-3 YAPILDI
    public SummonerObject getSumonner(int summonerID, String region) {
        SummonerObject sumonner;

        JSONObject sumonnerObject;

        String JSONString = readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/summoner/v3/summoners/"+summonerID+"?api_key=" + apiKey);


        try{sumonnerObject = new JSONObject(JSONString);}
        catch (Exception e){Log.e("Hata",
                "Boş değer döndü SummonerID: " + summonerID + " Region: " + region + " API Key: " + apiKey +
                        "\n" + e.toString());
            return null; }
        try {       sumonner = new SummonerObject(null, sumonnerObject.getInt("id"),
                    sumonnerObject.getString("name"), sumonnerObject.getInt("profileIconId"),
                    sumonnerObject.getInt("summonerLevel"),sumonnerObject.getInt("accountId"));
            return sumonner;
        } catch (JSONException e) { Log.e("Hata", e.toString()); }
        return null;
    }
    //Get masteries pages array with summonerid
    //V-3 YAPILDI
    public List<MasteriesPageObject> getSummonerMasteries(int summonerID, String region) {
        List<MasteriesPageObject> masteriesPages = new ArrayList<MasteriesPageObject>();
        List<MasterObject> masteries = new ArrayList<MasterObject>();

        JSONObject master, masters;
        JSONArray masterArray;

        String JSONString = readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/summoner/v3/summoners/"+summonerID+"?api_key=" + apiKey);

        try {
            master = new JSONObject(JSONString);
            masterArray = master.getJSONArray("pages");
            for (int i = 0; i < masterArray.length(); i++){
                masters = masterArray.getJSONObject(i);
                masteriesPages.add(new MasteriesPageObject(masteries, masters.getInt("id"), masters.getString("name")));
            }
            return masteriesPages;
        }
        catch (Exception e) {
            Log.e("Hata", e.toString());
        }

        return null;
    }
    //Get rune pages with sumonnerid
    // V-3 YAPILDI
    public List<RunePageObject> getSummonerRunes(int summonerID, String region){
        List<RunePageObject> runePages = new ArrayList<RunePageObject>();
        List<RuneObject> runes = new ArrayList<RuneObject>();

        JSONObject obje1, obje2;
        JSONArray array1, array2;

        String JSONString = readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/platform/v3/runes/by-summoner/"+summonerID+"?api_key=" + apiKey);

        try{
            obje1 = new JSONObject(JSONString);
            array1 = obje1.getJSONArray("pages");

            for (int i = 0; i < array1.length(); i++){
                obje2 = array1.getJSONObject(i);
                runes.clear();
                array2 = obje2.getJSONArray("slots");
                for (int j = 0; j < array2.length(); j++){
                    runes.add(new RuneObject(array2.getJSONObject(j).getInt("runeId"), array2.getJSONObject(j).getInt("runeSlotId")));
                }

                runePages.add(new RunePageObject(runes, obje2.getInt("id"), obje2.getString("name")));
            }
            return runePages;
        }catch (Exception e){Log.e("Hata", e.toString());}
        return null;
    }
    //get current match data
    //V-3 YAPILDI
    public List<ChampionMasterObject> getMasteries(int summonerID, String region,int championID){
        List<ChampionMasterObject> masterObjects = new ArrayList<ChampionMasterObject>();

        try{
            masterObjects.clear();
            String data=readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/champion-mastery/v3/champion-masteries/by-summoner/"+summonerID+"/by-champion/"+championID+"?api_key="+apiKey);
            JSONObject master = new JSONObject(data);
            masterObjects.add(new ChampionMasterObject(master.getInt("championPoints"), master.getInt("championPointsUntilNextLevel"), master.getInt("championLevel"),
                    master.getInt("tokensEarned"), master.getInt("championId"), master.getInt("championPointsSinceLastLevel"),
                    master.getInt("lastPlayTime"), master.getBoolean("chestGranted")));
        }
        catch (Exception e){
            masterObjects.add(new ChampionMasterObject(0, 0, 0,
                    0, 0, 0,
                    0, false));
        }

        return masterObjects;
    }
    public CurrentGameObject getCurrentMatch(int summonerID, String region){
        CurrentGameObject cgo;
        List<ParticipantObject> participants = new ArrayList<ParticipantObject>();
        List<ChampionMasterObject> masteries = new ArrayList<ChampionMasterObject>();
        List<RuneObject> runes = new ArrayList<RuneObject>();

        JSONObject obje1, obje2;
        JSONArray array1;

        String JSONString = readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/spectator/v3/active-games/by-summoner/"+summonerID+"?api_key=" + apiKey);
        try{
            obje1 = new JSONObject(JSONString);
            array1 = obje1.getJSONArray("participants");
            for (int i = 0; i < array1.length(); i++){
                obje2 = array1.getJSONObject(i);








                participants.add(new ParticipantObject(
                        obje2.getInt("spell1Id"),
                        obje2.getInt("spell2Id"),
                        obje2.getInt("profileIconId"),
                        obje2.getInt("championId"),
                        obje2.getInt("teamId"),
                        obje2.getInt("summonerId"),
                        obje2.getString("summonerName")));
            }
            cgo = new CurrentGameObject(obje1.getInt("gameLength"),
                    obje1.getInt("mapId"),
                    obje1.getInt("gameId"),
                    obje1.getInt("gameQueueConfigId"),
                    obje1.getString("gameMode"),
                    obje1.getString("gameType"),
                    participants);
            return cgo;
        }catch (Exception e){Log.e("Hata", e.toString());}

        return null;
    }
    //get sumonner leagues stats
    //V-3 BULAMADIM
    public List<LeagueObject> getSummonerLeague(int summonerID, String region){
        List<LeagueObject> leagues = new ArrayList<LeagueObject>();
        JSONObject obje1;
        JSONArray array1;

        String JSONString = readURL("https://" + region.toLowerCase() + ".api.pvp.net/api/lol/" + region.toLowerCase() +
                "/v2.5/league/by-summoner/" + summonerID + "/entry?api_key=" + apiKey);

        try {
            obje1 = new JSONObject(JSONString);
            array1 = obje1.getJSONArray(Integer.toString(summonerID));

            int miniSeriestarget, miniSerieslosses, miniSerieswins;
            String miniSeriesprogress;

            for (int i = 0; i < array1.length(); i++){
                try{miniSeriesprogress = array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getJSONObject("miniSeries").getString("progress");}
                catch (Exception e){miniSeriesprogress = "";}
                try{miniSeriestarget = array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getJSONObject("miniSeries").getInt("target");}
                catch (Exception e){miniSeriestarget = 0;}
                try{miniSerieslosses = array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getJSONObject("miniSeries").getInt("losses");}
                catch (Exception e){miniSerieslosses = 0;}
                try{miniSerieswins = array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getJSONObject("miniSeries").getInt("wins");}
                catch (Exception e){miniSerieswins = 0;}

                leagues.add(new LeagueObject(array1.getJSONObject(i).getString("queue"),
                        array1.getJSONObject(i).getString("name"),
                        array1.getJSONObject(i).getString("tier"),
                        array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getString("division"),
                        array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getInt("leaguePoints"),
                        array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getInt("losses"),
                        array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getInt("wins"),
                        array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getBoolean("isFreshBlood"),
                        array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getBoolean("isHotStreak"),
                        array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getBoolean("isInactive"),
                        array1.getJSONObject(i).getJSONArray("entries").getJSONObject(0).getBoolean("isVeteran"),
                        miniSeriesprogress,
                        miniSeriestarget,
                        miniSerieslosses,
                        miniSerieswins));
            }
            return leagues;
        }catch (Exception e){Log.e("Hata", e.toString());}

        return null;
    }
    //get champion object from id
    //V-3 YAPILDI
    public ChampionObject getStaticChampion(int championID, String region,Context context){
        ChampionObject co;
        JSONObject obje1;

        String JSONString = readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/static-data/v3/champions/"+championID+"?locale="+context.getString(R.string.language2)+"&api_key=" + apiKey);
        try{
            obje1 = new JSONObject(JSONString);
            co = new ChampionObject(obje1.getString("key"), obje1.getString("name"),
                    obje1.getString("title"), Integer.toString(obje1.getInt("id")));

            return co;
        }catch (Exception e){Log.e("Hata", e.toString());}
        return null;
    }
    //get spell object from id
    //V-3 YAPILDI
    public SpellObject getStaticSpell(int spellID, String region){
        SpellObject so;
        JSONObject obje1;

        String JSONString = readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/static-data/v3/summoner-spells/"+spellID+"?api_key=" + apiKey);
        try{
            obje1 = new JSONObject(JSONString);
            so = new SpellObject(Integer.toString(obje1.getInt("id")), obje1.getString("key"), obje1.getString("name"));

            return so;
        }catch (Exception e){Log.e("Hata", e.toString());}
        return null;
    }

    //getChampion masteries
    //V-3 YAPILDI
    public List<ChampionMasterObject> getChampionMasteries(int summonerID, String region){
        List<ChampionMasterObject> masteries = new ArrayList<ChampionMasterObject>();

        String JSONString = readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/champion-mastery/v3/champion-masteries/by-summoner/"+summonerID+"?api_key=" + apiKey);

        JSONObject obje1;
        JSONArray array1;

        try{
            array1 = new JSONArray(JSONString);
            for (int i = 0; i < array1.length(); i++){
                obje1 = array1.getJSONObject(i);

                masteries.add(new ChampionMasterObject(obje1.getInt("championPoints"),
                        obje1.getInt("championPointsUntilNextLevel"),
                        obje1.getInt("championLevel"),
                        obje1.getInt("tokensEarned"),
                        obje1.getInt("championId"),
                        obje1.getInt("championPointsSinceLastLevel"),
                        obje1.getInt("lastPlayTime"),
                        obje1.getBoolean("chestGranted")));
            }
            return masteries;
        }catch (Exception e){Log.e("Hata", e.toString());}
        return null;
    }
    //get static champion data
    //V-3 YAPILDI
    public List<ChampionObject> getChampionStaticData(Context context){
        List<ChampionObject> champions = new ArrayList<ChampionObject>();
        String JSONString = readURL("https://"+regionToPlatform(context.getString(R.string.language)).toLowerCase()+".api.riotgames.com/lol/static-data/v3/champions?locale="+context.getString(R.string.language2)+"&api_key="+ apiKey);

        JSONObject obje1, obje2, obje3;

        try{
            obje1 = new JSONObject(JSONString);
            obje2 = obje1.getJSONObject("data");

            Iterator<?> keys = obje2.keys();
            while (keys.hasNext()){
                obje3 = obje2.getJSONObject((String) keys.next());

                champions.add(new ChampionObject(obje3.getString("key"),
                        obje3.getString("name"),
                        obje3.getString("title"),
                        Integer.toString(obje3.getInt("id"))));
            }
            return champions;
        }catch (Exception e){Log.e("Hata", e.toString());}
        return null;
    }
    //get champion freeToPlay
    //V-3 YAPILDI
    public List<Integer> getChampionFreeToPlay(String region){
        List<Integer> chapions=new ArrayList<Integer>();
        String JSONString= readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/platform/v3/champions?api_key="+apiKey);
        JSONObject obj1,obj2;
        JSONArray array1;
        try {
            obj1=new JSONObject(JSONString);
            array1= obj1.getJSONArray("champions");
            for(int i=0;i<array1.length();i++){
                obj2=array1.getJSONObject(i);
                if(obj2.getBoolean("freeToPlay")==true)
                    chapions.add(obj2.getInt("id"));

            }
            return chapions;

        }
        catch (Exception e){
            Log.e("HATA",e.toString());
        }
        return null;
    }
    //get Team Stats
    //V-3 YAPILDI
    public MissionTeamObject getTeam(String matchID,String region,int sihirdarID){

        String data=readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/match/v3/matches/"+matchID+"?api_key="+apiKey);
        MissionTeamObject teamObjects;
        try {
            JSONObject obj1=new JSONObject(data);
            JSONArray array=obj1.getJSONArray("participantIdentities");
            int y=0;
            for (int i=0;i<array.length();i++){
                JSONObject object=array.getJSONObject(i);
                JSONObject object1=object.getJSONObject("player");
                int id=object1.getInt("summonerId");
                if(id==sihirdarID){
                    y=i;
                    break;
                }
            }
            if(y<5){
                JSONArray array1=obj1.getJSONArray("teams");
                JSONObject obj2=array1.getJSONObject(0);
                teamObjects=new MissionTeamObject(obj2.getString("win"),obj2.getBoolean("firstBlood"),obj2.getBoolean("firstTower"),obj2.getBoolean("firstInhibitor"),obj2.getBoolean("firstBaron"),obj2.getBoolean("firstDragon"),obj2.getInt("towerKills"),obj2.getInt("inhibitorKills"),obj2.getInt("baronKills"),obj2.getInt("dragonKills"));
            }
            else {
                JSONArray array1=obj1.getJSONArray("teams");
                JSONObject obj2=array1.getJSONObject(1);
                teamObjects=new MissionTeamObject(obj2.getString("win"),obj2.getBoolean("firstBlood"),obj2.getBoolean("firstTower"),obj2.getBoolean("firstInhibitor"),obj2.getBoolean("firstBaron"),obj2.getBoolean("firstDragon"),obj2.getInt("towerKills"),obj2.getInt("inhibitorKills"),obj2.getInt("baronKills"),obj2.getInt("dragonKills"));

            }

            return teamObjects;
        }
        catch (JSONException e) {
            Log.e("Hata", e.toString());

            return null;
        }
    }
    //get Summoner Stats
    //V-3 YAPILDI
    public MissionObject getMatch(String matchID, String region, int sihirdarID){
        String data=readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/match/v3/matches/"+matchID+"?api_key="+apiKey);
        MissionObject missionObject;
        try {
            JSONObject obj1=new JSONObject(data);
            JSONArray array=obj1.getJSONArray("participantIdentities");
            int y=0;
            for (int i=0;i<array.length();i++){
                JSONObject object=array.getJSONObject(i);
                JSONObject object1=object.getJSONObject("player");
                int id=object1.getInt("summonerId");
                if(id==sihirdarID){
                    y=i;
                    break;
                }
            }
            JSONArray array1=obj1.getJSONArray("participants");
            JSONObject obj2=array1.getJSONObject(y);
            JSONObject obj3=obj2.getJSONObject("stats");
            missionObject=new MissionObject(obj3.getBoolean("win"),obj3.getInt("champLevel"),obj3.getInt("kills"),obj3.getInt("doubleKills"),obj3.getInt("tripleKills"),
                    obj3.getInt("quadraKills"),obj3.getInt("pentaKills"),obj3.getInt("deaths"),obj3.getInt("assists"),obj3.getInt("totalMinionsKilled"),obj3.getInt("neutralMinionsKilled"),
                    obj3.getInt("goldEarned"),obj3.getInt("turretKills"),obj3.getInt("wardsPlaced"),obj3.getInt("wardsKilled"),obj3.getInt("largestMultiKill"),obj3.getInt("totalDamageDealtToChampions"));

            return missionObject;
        }
        catch (JSONException e) {
            Log.e("Hata", e.toString());

            return null;
        }
    }

    public String getPuan(String emmail,String sifre) {
        String data=readURL("http://ggeasylol.com/api/check_user.php?Mail="+emmail+"&Sifre="+sifre);
        try {
            JSONArray array=new JSONArray(data);
            JSONObject object=array.getJSONObject(0);



            return object.getString("Puan");
        }
        catch (Exception e){
            return "HATA";
        }


    }
    public List<RozetObject> getRozet(String mail){
        List<RozetObject> rozets = new ArrayList<RozetObject>();
        String data=readURL("http://ggeasylol.com/api/get_gorev.php?Mail="+mail);
        try {
            JSONArray array = new JSONArray(data);
            for(int i=0;i<array.length();i++){
                JSONObject object=array.getJSONObject(i);
                rozets.add(new RozetObject(object.getString("mission"),object.getString("adet")));
            }
            return rozets;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    //V-3 YAPILDI
    public MatchIdObject getMatchID(int accountID, String region){
        String data=readURL("https://"+regionToPlatform(region).toLowerCase()+".api.riotgames.com/lol/match/v3/matchlists/by-account/"+accountID+"?endIndex=1&beginIndex=0&api_key="+apiKey);
        MatchIdObject matchIdObject;

        try {
            JSONObject object=new JSONObject(data);
            JSONArray array=object.getJSONArray("matches");
            JSONObject object1=array.getJSONObject(0);
            matchIdObject=new MatchIdObject(object1.getString("gameId"));


            return matchIdObject;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }
    public String regionToPlatform(String region) {
        switch (region)
        {
            case "BR"  : return "BR1" ;
            case "EUNE": return "EUN1";
            case "EUW" : return "EUW1";
            case "JP"  : return "JP1" ;
            case "KR"  : return "KR"  ;
            case "LAN" : return "LA1" ;
            case "LAS" : return "LA2" ;
            case "NA"  : return "NA1" ;
            case "OCE" : return "OC1" ;
            case "TR"  : return "TR1" ;
            case "RU"  : return "RU"  ;
            case "PBE" : return "PBE1";
        }
        return null;
    }

    public int iconTable(int y) {

        int[]x={R.drawable.icon0,R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4,R.drawable.icon5,
                R.drawable.icon6,R.drawable.icon7,R.drawable.icon8,R.drawable.icon9,R.drawable.icon10,R.drawable.icon11,R.drawable.icon12,
                R.drawable.icon13,R.drawable.icon14,R.drawable.icon15,R.drawable.icon16,R.drawable.icon17,R.drawable.icon18,R.drawable.icon19,R.drawable.icon20,
                R.drawable.icon21

        };

        return x[y];
    }



    public String readURL(String link) {
        URL u = null;
        try {
            String new_link = link.replace(" ", "");
            Log.e("URL", new_link);
            u = new URL(new_link);
            URLConnection conn = u.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuffer buffer = new StringBuffer();

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                buffer.append(inputLine);

            in.close();

            return buffer.toString();
        }
        catch (Exception e) {
            Log.e("Hata",
                "İnternet sorunu" +
                        "\n" + e.toString());
            return null;
        }
    }
    private String convertToUTF8(String s) {
        Charset.forName("UTF-8").encode(s);
        return s;
    }
}
