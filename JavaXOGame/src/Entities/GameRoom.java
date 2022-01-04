/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.PlayerDetails;
import Entities.UserGameDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author A H M E D
 */
public class GameRoom extends UserGameDetails  {
    public static Vector<GameRoom> gameRooms = new Vector<>();

    public static enum gameRoomResponce{
        StartGame,FindingGame,WaitingPlayerTwo,
        WrongPlace,
        NoGameRoomRightNow,NoGameRoomWithThisCode,GameRoomIsFull,PlayerLeave
    }
    
    public String toJson() throws JsonProcessingException {
        ObjectMapper obm = new ObjectMapper();
        return obm.writeValueAsString(this);
    }
    
    public static GameRoom getGameRoom(String code){
        for (Iterator<GameRoom> iterator = gameRooms.iterator(); iterator.hasNext();) {
            GameRoom next = iterator.next();
            if(next.isEqualCode(code))
                return next;
        }
        return null;
    }
    
    public gameRoomResponce setPlayerTwo(PlayerDetails playerTwoDetails){
        if(canJoin()){
            this.playerTwoDetails = playerTwoDetails;
             return null;
        }
        return gameRoomResponce.GameRoomIsFull;
    }
    
    public gameRoomResponce setMove(Integer position){
        if(gameBord.keySet().contains(position))
            return gameRoomResponce.WrongPlace;
        
        gameBord.put(position, currentTurn.getPlayerSimbole());
        currentPosition = position;
        
        PlayerDetails temp = currentTurn; 
        currentTurn = nextTurn;
        nextTurn = temp;
        
        return null;
    }
    
    public GameRoom(PlayerDetails playerOneDetails){
        this.playerOneDetails = this.playerOneDetails;
        code = "abcd";
    }
    
    public GameRoom(){
    }
    
    public GameRoom(PlayerDetails playerOneDetails,PlayerDetails playerTwoDetails){
        this.playerOneDetails = this.playerOneDetails;
        this.playerTwoDetails = this.playerTwoDetails;
    }
    
    public void createGameRoom(){
        
        
    }
    
    public void randomGameRoom(PlayerDetails playerOneDetails){
        this.playerOneDetails = this.playerOneDetails;
    }
    
    public PlayerDetails getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(PlayerDetails currentTurn) {
        this.currentTurn = currentTurn;
    }

    public String getCode() {
        return code;
    }
    
    public boolean isEqualCode(String code) {
        return this.code.equals(code);
    }

    public void setCode(String code) {
        this.code = code;
    }   

   
    
    public boolean canJoin(){
        return playerTwoDetails==null;
    }
    
    public boolean isMyTurn(String userId){
        return userId==currentTurn.getPlayer().getId();
    }
    
    public String startRecordingForUser(String userId){
        if(this.getPlayerWithId(userId).getIsRecorded())  
                return "already recording";
        this.getPlayerWithId(userId).setIsRecorded(true);
        return null;
    }

    @Override
    public String toString() {
        return super.toString()+"\nGameRoom{" + "currentTurn=" + currentTurn + ", nextTurn=" + nextTurn + ", code=" + code + ", currentPosition=" + currentPosition + '}';
    }

    public PlayerDetails getNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(PlayerDetails nextTurn) {
        this.nextTurn = nextTurn;
    }

    public Integer getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Integer currentPosition) {
        this.currentPosition = currentPosition;
    }
    
    public GameState _getGameSate(){
        for(int i =1;i<=6;i+=3)
            if(gameBord.get(i)!=null && gameBord.get(i).equals(gameBord.get(i+1)) &&gameBord.get(i+1).equals(gameBord.get(i+2)))
            {
                setWinnerWithSimbole(gameBord.get(i));
                return GameState.winner;
            }
        
        for(int i =1;i<=6;i++)
            if(gameBord.get(i)!=null && gameBord.get(i).equals(gameBord.get(i+3)) &&gameBord.get(i+3) .equals(gameBord.get(i+6)))
            {
                setWinnerWithSimbole(gameBord.get(i));
                return GameState.winner;
            }
        
        if(gameBord.get(1)!=null && gameBord.get(1).equals(gameBord.get(5)) &&gameBord.get(5).equals(gameBord.get(9)))
            {
                setWinnerWithSimbole(gameBord.get(1));
                return GameState.winner;
            }
        
        if(gameBord.get(3)!=null && gameBord.get(3).equals(gameBord.get(5)) &&gameBord.get(5).equals(gameBord.get(7)))
            {
                setWinnerWithSimbole(gameBord.get(3));
                return GameState.winner;
            }
        
        if(gameBord.size() == 9)
            return GameState.draw;
        
        return GameState.playing;
    }
    
    
      private void setWinnerWithSimbole(PlayerSimbole playerSimbole){
         if(playerOneDetails.getPlayerSimbole().equals(playerSimbole)){
             playerOneDetails.setPlayerState(PlayerState.Winner);
             playerTwoDetails.setPlayerState(PlayerState.Loser);
         }else{
            playerOneDetails.setPlayerState(PlayerState.Loser);
            playerTwoDetails.setPlayerState(PlayerState.Winner);
         }
    }
    
    public static enum GameState{
        playing,draw,winner
    }
    
    public PlayerDetails currentTurn;
    public PlayerDetails nextTurn;
    public String code = "";
    public Integer currentPosition;
}
