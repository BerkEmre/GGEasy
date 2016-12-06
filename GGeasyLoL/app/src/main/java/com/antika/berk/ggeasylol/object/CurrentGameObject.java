package com.antika.berk.ggeasylol.object;

import java.util.List;

/**
 * Created by berke on 1.12.2016.
 */

public class CurrentGameObject {
    List<ParticipantObject> participants;
    private int gameLength, mapId, gameId;
    private String gameMode, gameType;

    public CurrentGameObject(int gameLength, int mapId, int gameId, String gameMode,
                             String gameType, List<ParticipantObject> participants){
        this.participants = participants;
        this.gameLength   = gameLength;
        this.mapId        = mapId;
        this.gameId       = gameId;
        this.gameMode     = gameMode;
        this.gameType     = gameType;
    }

    public List<ParticipantObject> getParticipants(){return participants;}
    public int getGameLength()                      {return gameLength;}
    public int getMapId()                           {return mapId;}
    public int getGameId()                          {return gameId;}
    public String getGameMode()                     {return gameMode;}
    public String getGameType()                     {return gameType;}
}
