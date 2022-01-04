/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    public static UserGameDetails fromResultSet(ResultSet rs,PlayerDetails playerOne,PlayerDetails playerTwo) throws SQLException, JsonProcessingException {
        ObjectMapper obm = new ObjectMapper();
        
        MapType typeReference = TypeFactory.defaultInstance().constructMapType(Map.class, Integer.class, PlayerSimbole.class);

        return new UserGameDetails(
            rs.getString("ID"),
            GameModes.valueOf(rs.getString("GAMEMODE")),
            GameDifficultyLvl.valueOf(rs.getString("GAMEDIFFICULTYLVL")),
            playerOne,
            playerTwo,
            obm.readValue(rs.getString("GAMEBORD"), typeReference)
        );
    }
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
                new PlayerDetails(UUID.randomUUID().toString(),
                        new User("ahmed", "mo", "123123"),
                        UserGameDetails.PlayerState.Loser, 
                        PlayerSimbole.X,
                        false
                ),
                new PlayerDetails(UUID.randomUUID().toString(),
                        new User("ahmed2", "moo", "123123"),
                        UserGameDetails.PlayerState.Winner,
                        PlayerSimbole.O,
                        false
                ),
                new LinkedHashMap()
        );
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

    public Map<Integer, PlayerSimbole> getGameBord() {
        return gameBord;
    }

    public void setGameBord(LinkedHashMap<Integer, PlayerSimbole> gameBord) {
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
    
     public boolean isPlayerPartWithId(String id){
         return this.getPlayerOneDetails().getPlayer().getId().equals(id)||
                this.getPlayerTwoDetails().getPlayer().getId().equals(id);
    }
    
    public boolean isPlayerPartWithUserName(String userName){
        return this.getPlayerOneDetails().getPlayer().getUserName().equals(userName)||
                this.getPlayerTwoDetails().getPlayer().getUserName().equals(userName);
    }
    
    public PlayerDetails getPlayerWithId(String id){
        if(this.getPlayerOneDetails()!=null &&this.getPlayerOneDetails().getPlayer().getId().equals(id)){
                return this.getPlayerOneDetails();
        }else if (this.getPlayerTwoDetails()!=null && this.getPlayerTwoDetails().getPlayer().getId().equals(id)){
            return this.getPlayerTwoDetails();
        }
        return null;
    }
    
    

    public UserGameDetails(
            String id,
            GameModes gameMode, 
            GameDifficultyLvl gameDifficultyLvl, 
            PlayerDetails playerOneDetails, 
            PlayerDetails playerTwoDetails, 
            LinkedHashMap<Integer, PlayerSimbole> gameBord
    ) {
        super(id);
        this.gameMode = gameMode;
        this.gameDifficultyLvl = gameDifficultyLvl;
        this.playerOneDetails = playerOneDetails;
        this.playerTwoDetails = playerTwoDetails;
        this.gameBord = gameBord;

    }
    
    public UserGameDetails(
            GameModes gameMode, 
            GameDifficultyLvl gameDifficultyLvl, 
            PlayerDetails playerOneDetails, 
            PlayerDetails playerTwoDetails, 
            LinkedHashMap<Integer, PlayerSimbole> gameBord
    ) {
        this.gameMode = gameMode;
        this.gameDifficultyLvl = gameDifficultyLvl;
        this.playerOneDetails = playerOneDetails;
        this.playerTwoDetails = playerTwoDetails;
        this.gameBord = gameBord;

    }

    public UserGameDetails(
            GameModes gameMode,
            GameDifficultyLvl gameDifficultyLvl,
            PlayerDetails playerOneDetails, 
            LinkedHashMap<Integer, PlayerSimbole> gameBord,
            boolean isRecorded
    ) {
        this.gameMode = gameMode;
        this.gameDifficultyLvl = gameDifficultyLvl;
        this.gameBord = gameBord;
        this.playerOneDetails = playerOneDetails;
        PlayerSimbole x =  playerOneDetails.getPlayerSimbole();
        this.playerTwoDetails = 
                new PlayerDetails(
                    new User(),
                    x == PlayerSimbole.X?  
                            PlayerSimbole.O: 
                            PlayerSimbole.X
                );
    }
    
    
    
    public UserGameDetails(
            GameModes gameMode,
            PlayerDetails playerOneDetails
    ) {
        this.gameMode = gameMode;
        this.gameDifficultyLvl = gameDifficultyLvl.Intermediate;
        this.playerOneDetails = playerOneDetails;
        gameBord = new LinkedHashMap();
    }
    
    
    public UserGameDetails(
            GameModes gameMode,
            PlayerDetails playerOneDetails,
            PlayerDetails playerTwoDetails
    ) {
        this.gameMode = gameMode;
        this.gameDifficultyLvl = gameDifficultyLvl.Intermediate;
        this.playerOneDetails = playerOneDetails;
         this.playerTwoDetails = playerTwoDetails;
        gameBord = new LinkedHashMap();
    }
    
     public UserGameDetails() {
        gameBord = new LinkedHashMap();
    }
    
    
    
    
    @Override
    public String toString() {
        return "UserGameDetails{" + "gameMode=" + gameMode + ", gameDifficultyLvl=" + gameDifficultyLvl + ", playerOneDetails=" + playerOneDetails + ", playerTwoDetails=" + playerTwoDetails + ", gameBord=" + gameBord + '}';
    }
    
    protected GameModes gameMode;
    protected GameDifficultyLvl gameDifficultyLvl;
    protected PlayerDetails playerOneDetails;
    protected PlayerDetails playerTwoDetails;
    protected LinkedHashMap<Integer,PlayerSimbole> gameBord;
    
}
