/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A H M E D
 */

public class UserGameDetails extends BaseEntity {
    public static enum PlayerState{
        Winner,Loser,Draw
    }
    
    public static enum PlayerSimbole{
        X,O
    }
    
    public static enum GameModes{
        Single,VsPc,Multi
    }
    public static enum GameDifficultyLvl{
        Easy,Medium,Intermediate
    }

    
       public static UserGameDetails dumyObject(){
        return new UserGameDetails(
                GameModes.Single,
                GameDifficultyLvl.Medium,
                new PlayerDetails(new User("ahmed", "mo", "123123"), PlayerSimbole.X),
                 new PlayerDetails(new User("ahmed2", "mo", "123123"), PlayerSimbole.O),
                new HashMap(),
                new HashMap(),
                new HashMap(),
                false);
    }

    private static Map<Integer,PlayerSimbole> toBordMap(Map<Integer, String> map){
        Map<Integer,PlayerSimbole> m = new HashMap();
        if(map == null) return m;
        map.forEach((Integer key,String value)->{
            m.put(key, PlayerSimbole.valueOf(value));
        });
        return m;
    }
    public static UserGameDetails fromJson(String body) throws JSONException, JsonProcessingException{
        ObjectMapper obm = new ObjectMapper();
        return obm.readValue(body, UserGameDetails.class);
    }
    
    public static Map<Integer,String> mapToJson( Map<Integer,UserGameDetails.PlayerSimbole> map){
        Map<Integer,String> m = new HashMap();
        if(map == null) return m;
        map.forEach((Integer key,UserGameDetails.PlayerSimbole value)->{
            m.put(key, value.name());
        });
        return m;
    }
    
    
    public UserGameDetails() {
    }
    
    
    public String toJson() throws JsonProcessingException {
        ObjectMapper obm = new ObjectMapper();
        
        return obm.writeValueAsString(this);
    }
    

    
    public GameModes getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameModes gameMode) {
        this.gameMode = gameMode;
    }

    public GameDifficultyLvl getGameDifficultyLvl() {
        return gameDifficultyLvl;
    }

    public void setGameDifficultyLvl(GameDifficultyLvl gameDifficultyLvl) {
        this.gameDifficultyLvl = gameDifficultyLvl;
    }

 
    public Map<Integer, PlayerSimbole> getRecord() {
        return record;
    }

    public void setRecord(Map<Integer, PlayerSimbole> record) {
        this.record = record;
    }

    public Map<Integer, PlayerSimbole> getGameBordBeforRecording() {
        return gameBordBeforRecording;
    }

    public void setGameBordBeforRecording(Map<Integer, PlayerSimbole> gameBordBeforRecording) {
        this.gameBordBeforRecording = gameBordBeforRecording;
    }

    public Map<Integer, PlayerSimbole> getGameBord() {
        return gameBord;
    }

    public void setGameBord(Map<Integer, PlayerSimbole> gameBord) {
        this.gameBord = gameBord;
    }
    
    public PlayerDetails getPlayerOneDetails() {
        return playerOneDetails;
    }

    public void setPlayerOneDetails(PlayerDetails playerOneDetails) {
        this.playerOneDetails = playerOneDetails;
    }

    public PlayerDetails getPlayerTwoDetails() {
        return playerTwoDetails;
    }

    public void setPlayerTwoDetails(PlayerDetails playerTwoDetails) {
        this.playerTwoDetails = playerTwoDetails;
    }

    public UserGameDetails(
            String id,
            GameModes gameMode, 
            GameDifficultyLvl gameDifficultyLvl, 
            PlayerDetails playerOneDetails, 
            PlayerDetails playerTwoDetails, 
            Map<Integer, PlayerSimbole> record,
            Map<Integer, PlayerSimbole> gameBordBeforRecording, 
            Map<Integer, PlayerSimbole> gameBord,
            boolean isRecorded
    ) {
        super(id);
        this.gameMode = gameMode;
        this.gameDifficultyLvl = gameDifficultyLvl;
        this.playerOneDetails = playerOneDetails;
        this.playerTwoDetails = playerTwoDetails;
        this.record = record;
        this.gameBordBeforRecording = gameBordBeforRecording;
        this.gameBord = gameBord;
        this.isRecorded = isRecorded;

    }
    
    public UserGameDetails(
            GameModes gameMode, 
            GameDifficultyLvl gameDifficultyLvl, 
            PlayerDetails playerOneDetails, 
            PlayerDetails playerTwoDetails, 
            Map<Integer, PlayerSimbole> record,
            Map<Integer, PlayerSimbole> gameBordBeforRecording, 
            Map<Integer, PlayerSimbole> gameBord,
            boolean isRecorded
    ) {
        this.gameMode = gameMode;
        this.gameDifficultyLvl = gameDifficultyLvl;
        this.playerOneDetails = playerOneDetails;
        this.playerTwoDetails = playerTwoDetails;
        this.record = record;
        this.gameBordBeforRecording = gameBordBeforRecording;
        this.gameBord = gameBord;
        this.isRecorded = isRecorded;

    }

    public UserGameDetails(
            GameModes gameMode,
            GameDifficultyLvl gameDifficultyLvl,
            PlayerDetails playerOneDetails, 
            Map<Integer, PlayerSimbole> gameBord,
            boolean isRecorded
    ) {
        this.gameMode = gameMode;
        this.gameDifficultyLvl = gameDifficultyLvl;
        this.gameBord = gameBord;
        this.record = new HashMap();
        this.isRecorded = isRecorded;
        this.playerOneDetails = playerOneDetails;
        PlayerSimbole x =  playerOneDetails.getPlayerSample();
        this.playerTwoDetails = 
                new PlayerDetails(
                    new User(),
                    x == PlayerSimbole.X?  
                            PlayerSimbole.O: 
                            PlayerSimbole.X
                );
    }
    
    
    
    @Override
    public String toString() {
        return "UserGameDetails{" + "gameMode=" + gameMode + ", gameDifficultyLvl=" + gameDifficultyLvl + ", playerOneDetails=" + playerOneDetails + ", playerTwoDetails=" + playerTwoDetails + ", record=" + record + ", gameBordBeforRecording=" + gameBordBeforRecording + ", gameBord=" + gameBord + ", isRecorded=" + isRecorded + '}';
    }
    
    
    
    
    private GameModes gameMode;
    private GameDifficultyLvl gameDifficultyLvl;
    private PlayerDetails playerOneDetails;
    private PlayerDetails playerTwoDetails;
    private Map<Integer,PlayerSimbole> record;
    private Map<Integer,PlayerSimbole> gameBordBeforRecording;
    private Map<Integer,PlayerSimbole> gameBord;
    private boolean isRecorded;

    
   }
