package com.antika.berk.ggeasylol.helper;

import android.util.Log;

import com.antika.berk.ggeasylol.Other.ChampionMaster;
import com.antika.berk.ggeasylol.object.ChampionMasterObject;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.CurrentGameObject;
import com.antika.berk.ggeasylol.object.LeagueObject;
import com.antika.berk.ggeasylol.object.MasterObject;
import com.antika.berk.ggeasylol.object.MasteriesPageObject;
import com.antika.berk.ggeasylol.object.ParticipantObject;
import com.antika.berk.ggeasylol.object.RankedStatObject;
import com.antika.berk.ggeasylol.object.RuneObject;
import com.antika.berk.ggeasylol.object.RunePageObject;
import com.antika.berk.ggeasylol.object.SpellObject;
import com.antika.berk.ggeasylol.object.SummaryStat;
import com.antika.berk.ggeasylol.object.SummonerObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RiotApiHelper {
    public String apiKey = "yourApiKey";
    //Get summoner object with summoner name
    public SummonerObject getSumonner(String summonerName, String region) {
        SummonerObject sumonner;

        JSONObject sumonnerObject;

        String JSONString = readURL("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4/" +
                    "summoner/by-name/" + summonerName + "?api_key=" + apiKey);

        try{sumonnerObject = new JSONObject(JSONString);
            Iterator<?> keys = sumonnerObject.keys();
            String key = (String)keys.next();
            sumonnerObject = sumonnerObject.getJSONObject(key);
            sumonner = new SummonerObject(key, sumonnerObject.getInt("id"),
                    sumonnerObject.getString("name"), sumonnerObject.getInt("profileIconId"),
                    sumonnerObject.getInt("summonerLevel"));
            return sumonner;
        }
        catch (Exception e){Log.e("Hata",
                "Boş değer döndü SumonnerName: " + summonerName + " Region: " + region + " API Key: " + apiKey +
                        "\n" + e.toString());
            return null; }
    }
    //Get summoner object with summoner ID but key is null
    public SummonerObject getSumonner(int summonerID, String region) {
        SummonerObject sumonner;

        JSONObject sumonnerObject;

        String JSONString = readURL("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4" +
                "/summoner/" + summonerID + "?api_key=" + apiKey);


        try{sumonnerObject = new JSONObject(JSONString);}
        catch (Exception e){Log.e("Hata",
                "Boş değer döndü SummonerID: " + summonerID + " Region: " + region + " API Key: " + apiKey +
                        "\n" + e.toString());
            return null; }

        Iterator<?> keys = sumonnerObject.keys();
        String key = (String)keys.next();
        try { sumonnerObject = sumonnerObject.getJSONObject(key);
            sumonner = new SummonerObject(null, sumonnerObject.getInt("id"),
                    sumonnerObject.getString("name"), sumonnerObject.getInt("profileIconId"),
                    sumonnerObject.getInt("summonerLevel"));
            return sumonner;
        } catch (JSONException e) { Log.e("Hata", e.toString()); }
        return null;
    }
    //Get masteries pages array with summonerid
    public List<MasteriesPageObject> getSummonerMasteries(int summonerID, String region) {
        List<MasteriesPageObject> masteriesPages = new ArrayList<MasteriesPageObject>();
        List<MasterObject> masteries = new ArrayList<MasterObject>();

        JSONObject master, masters;
        JSONArray masterArray, mastersArray;

        String JSONString = readURL("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4/summoner/"
                + summonerID + "/masteries?api_key=" + apiKey);

        try {
            master = new JSONObject(JSONString);
            master = master.getJSONObject(Integer.toString(summonerID));

            masterArray = master.getJSONArray("pages");
            for (int i = 0; i < masterArray.length(); i++){
                masters = masterArray.getJSONObject(i);

                masteries.clear();
                mastersArray = masters.getJSONArray("masteries");
                for (int j = 0; j < mastersArray.length(); j++){
                    masteries.add(new MasterObject(mastersArray.getJSONObject(j).getInt("id"), mastersArray.getJSONObject(j).getInt("rank")));
                }
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
    public List<RunePageObject> getSummonerRunes(int summonerID, String region){
        List<RunePageObject> runePages = new ArrayList<RunePageObject>();
        List<RuneObject> runes = new ArrayList<RuneObject>();

        JSONObject obje1, obje2;
        JSONArray array1, array2;

        String JSONString = readURL("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.4/summoner/"
                + summonerID + "/runes?api_key=" + apiKey);

        try{
            obje1 = new JSONObject(JSONString);
            obje1 = obje1.getJSONObject(Integer.toString(summonerID));
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
    //Get ranked stat for summoner champion id "0" is all champs stats
    public List<RankedStatObject> getRankedStat(int summonerID, String region, String season){
        List<RankedStatObject> stats = new ArrayList<RankedStatObject>();

        JSONObject obje1, obje2;
        JSONArray array1;

        String JSONString = readURL("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.3/" +
                "stats/by-summoner/" + summonerID + "/ranked?season=" + season + "&api_key=" + apiKey);

        try {
            obje1 = new JSONObject(JSONString);
            array1 = obje1.getJSONArray("champions");

            for (int i = 0; i < array1.length(); i++){
                obje2 = array1.getJSONObject(i);

                int totalDeathsPerSession, totalSessionsPlayed, totalDamageTaken,
                        totalQuadraKills, totalTripleKills, totalMinionKills, maxChampionsKilled,
                        totalDoubleKills, totalPhysicalDamageDealt, totalChampionKills, totalAssists,
                        mostChampionKillsPerSession, totalDamageDealt, totalFirstBlood, totalSessionsLost,
                        totalSessionsWon, totalMagicDamageDealt, totalGoldEarned, totalPentaKills,
                        totalTurretsKilled, mostSpellsCast, maxNumDeaths, totalUnrealKills;

                try{totalDeathsPerSession = obje2.getJSONObject("stats").getInt("totalDeathsPerSession");}
                catch (Exception e){totalDeathsPerSession = 0;}
                try{totalSessionsPlayed = obje2.getJSONObject("stats").getInt("totalSessionsPlayed");}
                catch (Exception e){totalSessionsPlayed = 0;}
                try{totalDamageTaken = obje2.getJSONObject("stats").getInt("totalDamageTaken");}
                catch (Exception e){totalDamageTaken = 0;}
                try{totalQuadraKills = obje2.getJSONObject("stats").getInt("totalQuadraKills");}
                catch (Exception e){totalQuadraKills = 0;}
                try{totalTripleKills = obje2.getJSONObject("stats").getInt("totalTripleKills");}
                catch (Exception e){totalTripleKills = 0;}
                try{totalMinionKills = obje2.getJSONObject("stats").getInt("totalMinionKills");}
                catch (Exception e){totalMinionKills = 0;}
                try{maxChampionsKilled = obje2.getJSONObject("stats").getInt("maxChampionsKilled");}
                catch (Exception e){maxChampionsKilled = 0;}
                try{totalDoubleKills = obje2.getJSONObject("stats").getInt("totalDoubleKills");}
                catch (Exception e){totalDoubleKills = 0;}
                try{totalPhysicalDamageDealt = obje2.getJSONObject("stats").getInt("totalPhysicalDamageDealt");}
                catch (Exception e){totalPhysicalDamageDealt = 0;}
                try{totalChampionKills = obje2.getJSONObject("stats").getInt("totalChampionKills");}
                catch (Exception e){totalChampionKills = 0;}
                try{totalAssists = obje2.getJSONObject("stats").getInt("totalAssists");}
                catch (Exception e){totalAssists = 0;}
                try{mostChampionKillsPerSession = obje2.getJSONObject("stats").getInt("mostChampionKillsPerSession");}
                catch (Exception e){mostChampionKillsPerSession = 0;}
                try{totalDamageDealt = obje2.getJSONObject("stats").getInt("totalDamageDealt");}
                catch (Exception e){totalDamageDealt = 0;}
                try{totalFirstBlood = obje2.getJSONObject("stats").getInt("totalFirstBlood");}
                catch (Exception e){totalFirstBlood = 0;}
                try{totalSessionsLost = obje2.getJSONObject("stats").getInt("totalSessionsLost");}
                catch (Exception e){totalSessionsLost = 0;}
                try{totalSessionsWon = obje2.getJSONObject("stats").getInt("totalSessionsWon");}
                catch (Exception e){totalSessionsWon = 0;}
                try{totalMagicDamageDealt = obje2.getJSONObject("stats").getInt("totalMagicDamageDealt");}
                catch (Exception e){totalMagicDamageDealt = 0;}
                try{totalGoldEarned = obje2.getJSONObject("stats").getInt("totalGoldEarned");}
                catch (Exception e){totalGoldEarned = 0;}
                try{totalPentaKills = obje2.getJSONObject("stats").getInt("totalPentaKills");}
                catch (Exception e){totalPentaKills = 0;}
                try{totalTurretsKilled = obje2.getJSONObject("stats").getInt("totalTurretsKilled");}
                catch (Exception e){totalTurretsKilled = 0;}
                try{mostSpellsCast = obje2.getJSONObject("stats").getInt("mostSpellsCast");}
                catch (Exception e){mostSpellsCast = 0;}
                try{maxNumDeaths = obje2.getJSONObject("stats").getInt("maxNumDeaths");}
                catch (Exception e){maxNumDeaths = 0;}
                try{totalUnrealKills = obje2.getJSONObject("stats").getInt("totalUnrealKills");}
                catch (Exception e){totalUnrealKills = 0;}

                stats.add(new RankedStatObject(obje2.getInt("id"),
                        totalDeathsPerSession, totalSessionsPlayed, totalDamageTaken, totalQuadraKills,
                        totalTripleKills, totalMinionKills, maxChampionsKilled, totalDoubleKills,
                        totalPhysicalDamageDealt, totalChampionKills, totalAssists,
                        mostChampionKillsPerSession, totalDamageDealt, totalFirstBlood, totalSessionsLost,
                        totalSessionsWon, totalMagicDamageDealt, totalGoldEarned, totalPentaKills,
                        totalTurretsKilled, mostSpellsCast, maxNumDeaths, totalUnrealKills));
            }
            return stats;
        }catch (Exception e){ Log.e("Hata", e.toString());};
        return null;
    }
    //Get ranked stat for summoner champion id "0" is all champs stats for last season
    public List<RankedStatObject> getRankedStat(int summonerID, String region){
        List<RankedStatObject> stats = new ArrayList<RankedStatObject>();

        JSONObject obje1, obje2;
        JSONArray array1;

        String JSONString = readURL("https://" + region + ".api.pvp.net/api/lol/" + region +
                "/v1.3/stats/by-summoner/" + summonerID + "/ranked?api_key=" + apiKey);

        try {
            obje1 = new JSONObject(JSONString);
            array1 = obje1.getJSONArray("champions");

            for (int i = 0; i < array1.length(); i++){
                obje2 = array1.getJSONObject(i);

                int totalDeathsPerSession, totalSessionsPlayed, totalDamageTaken,
                        totalQuadraKills, totalTripleKills, totalMinionKills, maxChampionsKilled,
                        totalDoubleKills, totalPhysicalDamageDealt, totalChampionKills, totalAssists,
                        mostChampionKillsPerSession, totalDamageDealt, totalFirstBlood, totalSessionsLost,
                        totalSessionsWon, totalMagicDamageDealt, totalGoldEarned, totalPentaKills,
                        totalTurretsKilled, mostSpellsCast, maxNumDeaths, totalUnrealKills;

                try{totalDeathsPerSession = obje2.getJSONObject("stats").getInt("totalDeathsPerSession");}
                catch (Exception e){totalDeathsPerSession = 0;}
                try{totalSessionsPlayed = obje2.getJSONObject("stats").getInt("totalSessionsPlayed");}
                catch (Exception e){totalSessionsPlayed = 0;}
                try{totalDamageTaken = obje2.getJSONObject("stats").getInt("totalDamageTaken");}
                catch (Exception e){totalDamageTaken = 0;}
                try{totalQuadraKills = obje2.getJSONObject("stats").getInt("totalQuadraKills");}
                catch (Exception e){totalQuadraKills = 0;}
                try{totalTripleKills = obje2.getJSONObject("stats").getInt("totalTripleKills");}
                catch (Exception e){totalTripleKills = 0;}
                try{totalMinionKills = obje2.getJSONObject("stats").getInt("totalMinionKills");}
                catch (Exception e){totalMinionKills = 0;}
                try{maxChampionsKilled = obje2.getJSONObject("stats").getInt("maxChampionsKilled");}
                catch (Exception e){maxChampionsKilled = 0;}
                try{totalDoubleKills = obje2.getJSONObject("stats").getInt("totalDoubleKills");}
                catch (Exception e){totalDoubleKills = 0;}
                try{totalPhysicalDamageDealt = obje2.getJSONObject("stats").getInt("totalPhysicalDamageDealt");}
                catch (Exception e){totalPhysicalDamageDealt = 0;}
                try{totalChampionKills = obje2.getJSONObject("stats").getInt("totalChampionKills");}
                catch (Exception e){totalChampionKills = 0;}
                try{totalAssists = obje2.getJSONObject("stats").getInt("totalAssists");}
                catch (Exception e){totalAssists = 0;}
                try{mostChampionKillsPerSession = obje2.getJSONObject("stats").getInt("mostChampionKillsPerSession");}
                catch (Exception e){mostChampionKillsPerSession = 0;}
                try{totalDamageDealt = obje2.getJSONObject("stats").getInt("totalDamageDealt");}
                catch (Exception e){totalDamageDealt = 0;}
                try{totalFirstBlood = obje2.getJSONObject("stats").getInt("totalFirstBlood");}
                catch (Exception e){totalFirstBlood = 0;}
                try{totalSessionsLost = obje2.getJSONObject("stats").getInt("totalSessionsLost");}
                catch (Exception e){totalSessionsLost = 0;}
                try{totalSessionsWon = obje2.getJSONObject("stats").getInt("totalSessionsWon");}
                catch (Exception e){totalSessionsWon = 0;}
                try{totalMagicDamageDealt = obje2.getJSONObject("stats").getInt("totalMagicDamageDealt");}
                catch (Exception e){totalMagicDamageDealt = 0;}
                try{totalGoldEarned = obje2.getJSONObject("stats").getInt("totalGoldEarned");}
                catch (Exception e){totalGoldEarned = 0;}
                try{totalPentaKills = obje2.getJSONObject("stats").getInt("totalPentaKills");}
                catch (Exception e){totalPentaKills = 0;}
                try{totalTurretsKilled = obje2.getJSONObject("stats").getInt("totalTurretsKilled");}
                catch (Exception e){totalTurretsKilled = 0;}
                try{mostSpellsCast = obje2.getJSONObject("stats").getInt("mostSpellsCast");}
                catch (Exception e){mostSpellsCast = 0;}
                try{maxNumDeaths = obje2.getJSONObject("stats").getInt("maxNumDeaths");}
                catch (Exception e){maxNumDeaths = 0;}
                try{totalUnrealKills = obje2.getJSONObject("stats").getInt("totalUnrealKills");}
                catch (Exception e){totalUnrealKills = 0;}

                stats.add(new RankedStatObject(obje2.getInt("id"),
                        totalDeathsPerSession, totalSessionsPlayed, totalDamageTaken, totalQuadraKills,
                        totalTripleKills, totalMinionKills, maxChampionsKilled, totalDoubleKills,
                        totalPhysicalDamageDealt, totalChampionKills, totalAssists,
                        mostChampionKillsPerSession, totalDamageDealt, totalFirstBlood, totalSessionsLost,
                        totalSessionsWon, totalMagicDamageDealt, totalGoldEarned, totalPentaKills,
                        totalTurretsKilled, mostSpellsCast, maxNumDeaths, totalUnrealKills));
            }
            return stats;
        }catch (Exception e){ Log.e("Hata", e.toString());};
        return null;
    }
    //get summary stat
    public List<SummaryStat> getSummaryStat(int summonerID, String region, String season){
        List<SummaryStat> stats = new ArrayList<SummaryStat>();

        JSONObject obje1, obje2;
        JSONArray array1;

        String JSONString = readURL("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.3/" +
                "stats/by-summoner/" + summonerID + "/summary?season=" + season + "&api_key=" + apiKey);

        try{
            obje1 = new JSONObject(JSONString);
            array1 = obje1.getJSONArray("playerStatSummaries");

            for (int i = 0; i < array1.length(); i++){
                int losses, win, totalNeutralMinionsKilled, totalMinionKills, totalChampionKills,
                        totalAssists, totalTurretsKilled;

                obje2 = array1.getJSONObject(i);

                try {losses = obje2.getInt("losses");}catch (Exception e){losses = 0;}
                try {win = obje2.getInt("wins");}catch (Exception e){win = 0;}
                try {totalNeutralMinionsKilled = obje2.getJSONObject("aggregatedStats").getInt("totalNeutralMinionsKilled");}
                catch (Exception e){totalNeutralMinionsKilled = 0;}
                try {totalMinionKills = obje2.getJSONObject("aggregatedStats").getInt("totalMinionKills");}
                catch (Exception e){totalMinionKills = 0;}
                try {totalChampionKills = obje2.getJSONObject("aggregatedStats").getInt("totalChampionKills");}
                catch (Exception e){totalChampionKills = 0;}
                try {totalAssists = obje2.getJSONObject("aggregatedStats").getInt("totalAssists");}
                catch (Exception e){totalAssists = 0;}
                try {totalTurretsKilled = obje2.getJSONObject("aggregatedStats").getInt("totalTurretsKilled");}
                catch (Exception e){totalTurretsKilled = 0;}

                stats.add(new SummaryStat(obje2.getString("playerStatSummaryType"), losses, win,
                        totalNeutralMinionsKilled, totalMinionKills, totalChampionKills,
                        totalAssists, totalTurretsKilled));
            }
            return stats;
        }catch (Exception e){Log.e("Hata", e.toString());}

        return null;
    }
    //get summary stat for last season
    public List<SummaryStat> getSummaryStat(int summonerID, String region){
        List<SummaryStat> stats = new ArrayList<SummaryStat>();

        JSONObject obje1, obje2;
        JSONArray array1;

        String JSONString = readURL("https://" + region + ".api.pvp.net/api/lol/" + region + "/v1.3/" +
                "stats/by-summoner/" + summonerID + "/summary?api_key=" + apiKey);

        try{
            obje1 = new JSONObject(JSONString);
            array1 = obje1.getJSONArray("playerStatSummaries");

            for (int i = 0; i < array1.length(); i++){
                int losses, win, totalNeutralMinionsKilled, totalMinionKills, totalChampionKills,
                        totalAssists, totalTurretsKilled;

                obje2 = array1.getJSONObject(i);

                try {losses = obje2.getInt("losses");}catch (Exception e){losses = 0;}
                try {win = obje2.getInt("wins");}catch (Exception e){win = 0;}
                try {totalNeutralMinionsKilled = obje2.getJSONObject("aggregatedStats").getInt("totalNeutralMinionsKilled");}
                catch (Exception e){totalNeutralMinionsKilled = 0;}
                try {totalMinionKills = obje2.getJSONObject("aggregatedStats").getInt("totalMinionKills");}
                catch (Exception e){totalMinionKills = 0;}
                try {totalChampionKills = obje2.getJSONObject("aggregatedStats").getInt("totalChampionKills");}
                catch (Exception e){totalChampionKills = 0;}
                try {totalAssists = obje2.getJSONObject("aggregatedStats").getInt("totalAssists");}
                catch (Exception e){totalAssists = 0;}
                try {totalTurretsKilled = obje2.getJSONObject("aggregatedStats").getInt("totalTurretsKilled");}
                catch (Exception e){totalTurretsKilled = 0;}

                stats.add(new SummaryStat(obje2.getString("playerStatSummaryType"), losses, win,
                        totalNeutralMinionsKilled, totalMinionKills, totalChampionKills,
                        totalAssists, totalTurretsKilled));
            }
            return stats;
        }catch (Exception e){Log.e("Hata", e.toString());}

        return null;
    }
    //get current match data
    public CurrentGameObject getCurrentMatch(int summonerID, String region){
        CurrentGameObject cgo;
        List<ParticipantObject> participants = new ArrayList<ParticipantObject>();
        List<MasterObject> masteries = new ArrayList<MasterObject>();
        List<RuneObject> runes = new ArrayList<RuneObject>();

        JSONObject obje1, obje2;
        JSONArray array1, array2;

        String JSONString = readURL("https://" + region + ".api.pvp.net/observer-mode/rest/" +
                "consumer/getSpectatorGameInfo/" + regionToPlatform(region) + "/" + summonerID +
                "?api_key=" + apiKey);

        try{
            obje1 = new JSONObject(JSONString);
            array1 = obje1.getJSONArray("participants");
            for (int i = 0; i < array1.length(); i++){
                obje2 = array1.getJSONObject(i);

                array2 = obje2.getJSONArray("masteries");
                masteries.clear();
                for (int j = 0; j < array2.length(); j++){
                    masteries.add(new MasterObject(array2.getJSONObject(j).getInt("masteryId"),
                            array2.getJSONObject(j).getInt("rank")));
                }

                array2 = obje2.getJSONArray("runes");
                runes.clear();
                for (int j = 0; j < array2.length(); j++){
                    runes.add(new RuneObject(array2.getJSONObject(j).getInt("runeId"),
                            array2.getJSONObject(j).getInt("count")));
                }

                participants.add(new ParticipantObject(masteries, runes,
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
                    obje1.getString("gameMode"),
                    obje1.getString("gameType"),
                    participants);
            return cgo;
        }catch (Exception e){Log.e("Hata", e.toString());}

        return null;
    }
    //get sumonner leagues stats
    public List<LeagueObject> getSummonerLeague(int summonerID, String region){
        List<LeagueObject> leagues = new ArrayList<LeagueObject>();
        JSONObject obje1;
        JSONArray array1;

        String JSONString = readURL("https://" + region + ".api.pvp.net/api/lol/" + region +
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
    public ChampionObject getStaticChampion(int championID, String region){
        ChampionObject co;
        JSONObject obje1;

        String JSONString = readURL("https://global.api.pvp.net/api/lol/static-data/" + region +
                "/v1.2/champion/" + championID + "?api_key=" + apiKey);
        try{
            obje1 = new JSONObject(JSONString);
            co = new ChampionObject(obje1.getString("key"), obje1.getString("name"),
                    obje1.getString("title"), Integer.toString(obje1.getInt("id")));

            return co;
        }catch (Exception e){Log.e("Hata", e.toString());}
        return null;
    }
    //get spell object from id
    public SpellObject getStaticSpell(int spellID, String region){
        SpellObject so;
        JSONObject obje1;

        String JSONString = readURL("https://global.api.pvp.net/api/lol/static-data/" + region +
                "/v1.2/summoner-spell/" + spellID + "?api_key=" + apiKey);
        try{
            obje1 = new JSONObject(JSONString);
            so = new SpellObject(Integer.toString(obje1.getInt("id")), obje1.getString("key"), obje1.getString("name"));

            return so;
        }catch (Exception e){Log.e("Hata", e.toString());}
        return null;
    }
    //getChampion masteries
    public List<ChampionMasterObject> getChampionMasteries(int summonerID, String region){
        List<ChampionMasterObject> masteries = new ArrayList<ChampionMasterObject>();

        String JSONString = readURL("https://" + region + ".api.pvp.net/championmastery/location/" +
                regionToPlatform(region) + "/player/" + summonerID + "/champions?api_key=" + apiKey);

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
                        obje1.getInt("lastPlayTime")));
            }
            return masteries;
        }catch (Exception e){Log.e("Hata", e.toString());}
        return null;
    }
    //get static champion data
    public List<ChampionObject> getChampionStaticData(){
        List<ChampionObject> champions = new ArrayList<ChampionObject>();
        String JSONString = readURL("https://global.api.pvp.net/api/lol/static-data/tr/v1.2/champion?api_key=" + apiKey);

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
    public List<Integer> getChampionFreeToPlay(String region){
        List<Integer> chapions=new ArrayList<Integer>();
        String JSONString= readURL("https://tr.api.pvp.net/api/lol/tr/v1.2/champion?api_key="+apiKey);
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

    private String regionToPlatform(String region) {
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
    private String readURL(String link) {
        URL u = null;
        try {
            String new_link = link.replace(" ", "");
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
}
