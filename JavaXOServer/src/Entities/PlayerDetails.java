/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.UserGameDetails.PlayerState;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author A H M E D
 */
public class PlayerDetails extends BaseEntity {

    public static PlayerDetails fromResultSet(ResultSet rs,User u) throws SQLException {
        return new PlayerDetails(
                rs.getString("ID"),
                u,
                PlayerState.valueOf(rs.getString("PLAYER_STATE")),
                UserGameDetails.PlayerSimbole.valueOf(rs.getString("PLAYER_SIMBOLE")),
                rs.getBoolean("IS_RECORDED")
        );
    }
    
     public static PlayerDetails fromJson(String body) throws  JsonProcessingException{
        ObjectMapper obm = new ObjectMapper();
        return obm.readValue(body, PlayerDetails.class);
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper obm = new ObjectMapper();
        
        return obm.writeValueAsString(this);
    }

    public UserGameDetails.PlayerSimbole getPlayerSimbole() {
        return PlayerSimbole;
    }

    public void setPlayerSimbole(UserGameDetails.PlayerSimbole PlayerSimbole) {
        this.PlayerSimbole = PlayerSimbole;
    }

    public boolean getIsRecorded() {
        return isRecorded;
    }

    public void setIsRecorded(boolean isRecorded) {
        this.isRecorded = isRecorded;
    }
    

    public User getPlayer() {
        return player;
    }

    public void setPlayer(User player) {
        this.player = player;
    }

    public UserGameDetails.PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(UserGameDetails.PlayerState playerState) {
        this.playerState = playerState;
    }

    public UserGameDetails.PlayerSimbole getPlayerSample() {
        return PlayerSimbole;
    }

    public void setPlayerSample(UserGameDetails.PlayerSimbole playerSample) {
        this.PlayerSimbole = playerSample;
    }

    public PlayerDetails(String id,User player, PlayerState playerState, UserGameDetails.PlayerSimbole PlayerSimbole, boolean isRecorded) {
        super(id);
        this.player = player;
        this.playerState = playerState;
        this.PlayerSimbole = PlayerSimbole;
        this.isRecorded = isRecorded;
    }


    public PlayerDetails(User player, UserGameDetails.PlayerSimbole playerSample) {
        this.player = player;
        this.PlayerSimbole = playerSample;
        this.playerState = UserGameDetails.PlayerState.Draw;
    }
        
        
        
    public PlayerDetails() {
    }
    

    
        @Override
        public String toString() {
            return "PlayerDetails{" + "player=" + player + ", playerState=" + playerState + ", PlayerSimbole=" + PlayerSimbole + '}';
        }
        
        private User player;
        private UserGameDetails.PlayerState playerState;
        private UserGameDetails.PlayerSimbole  PlayerSimbole;
        protected boolean isRecorded;
}
