/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.UserGameDetailsCrud;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author A H M E D
 */
public final class GameRoom extends UserGameDetails  {
    
    public static Vector<GameRoom> gameRooms = new Vector<>();

    public static enum gameRoomResponce{
        StartGame,FindingGame,WaitingPlayerTwo,
        WrongPlace,youAreAlreadyIn,
        NoGameRoomRightNow,NoGameRoomWithThisCode,GameRoomIsFull,PlayerLeave
    }
    
    public static enum GameState{
        playing,draw,winner
    }
    
    public static String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
                sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
    
    @Override
    public String toJson() throws JsonProcessingException {
        ObjectMapper obm = new ObjectMapper();
        return obm.writeValueAsString(this);
    }
    
    public static GameRoom getGameRoom(String code){
        for (GameRoom next : gameRooms) {
            System.out.println(next);
            if(next.isEqualCode(code))
                return next;
        }
        return null;
    }
    
    private boolean isContainsCode(String code){
        for (GameRoom gameRoom : gameRooms) {
            if(gameRoom.code.equals(code))
                return true;
        }
        return false;
    }
    
    private void addCode(){
        String mcode;
            while(true){
                mcode= generateRandomPassword(5);
                if(!isContainsCode(mcode))
                    break;
            }
            
        this.code = mcode;
    }
    
    public gameRoomResponce setPlayerTwo(Player p){    
        
        if(getPlayerWithId(p.user.getId())!=null){
            return gameRoomResponce.youAreAlreadyIn;
        }
        
        if(!canJoin()){
            return gameRoomResponce.GameRoomIsFull;
        }
        
        this.players.add(p);

         if(playerOneDetails==null){
            this.playerOneDetails = new PlayerDetails(
                p.user,
                PlayerSimbole.X
            );
        }else{
            this.playerTwoDetails = new PlayerDetails(
                p.user,
                PlayerSimbole.O
            );
        }
            
        currentTurn = this.playerOneDetails;
        nextTurn = this.playerTwoDetails;
        return null;
    }
    
    public gameRoomResponce Playerleave(String playerId){
        Player p = null;
        
        for (Player player : players) 
            if(player.user.getId().equals(playerId)){
                p = player;
                break;
            }
        if(p!=null){
            this.players.remove(p);
        }
        
        if(playerOneDetails!=null && playerOneDetails.getPlayer().getId().equals(playerId)){
            playerOneDetails = null;
            currentTurn = playerTwoDetails;

        }else{
            playerTwoDetails = null;
            currentTurn = playerOneDetails;
        }
        
        nextTurn = null;
        gameBord.clear();
        return null;
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
    
//    public void saveGame(UserGameDetails userGameDetails){
//        new UserGameDetailsCrud();
//    }
    
    public GameRoom(Player p){
        super(
            GameModes.Multi, 
            new PlayerDetails(
                p.user,
                PlayerSimbole.X
            )
        );
        this.players = new Vector<>();
        
        this.players.add(p);
        
        addCode();
        this.currentTurn = playerOneDetails;
        gameRooms.add(this);
    }
    
    public  GameRoom(Player p1,Player p2){
        super(
            GameModes.Multi, 
            new PlayerDetails(
                p1.user,
                PlayerSimbole.X
            ),
            new PlayerDetails(
                p2.user,
                PlayerSimbole.O
            )
        );
        this.players = new Vector<>();
        
        this.players.add(p1);
        this.players.add(p2);
        
        addCode();
        currentTurn = this.playerOneDetails;
        nextTurn = this.playerTwoDetails;
        gameRooms.add(this);
    }
    
    public void randomGameRoom(PlayerDetails playerOneDetails){
        this.playerOneDetails = playerOneDetails;
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
        return playerTwoDetails==null || playerOneDetails==null;
    }
    
    public boolean isMyTurn(String userId){
        return (userId == null ? currentTurn.getPlayer().getId() == null : userId.equals(currentTurn.getPlayer().getId()));
    }
    
    public String startRecordingForUser(String userId){
        if(this.getPlayerWithId(userId).getIsRecorded())  
                return "already recording";
        this.getPlayerWithId(userId).setIsRecorded(true);
        return null;
    }
    
    
    public void notifySockets(Responce.responceCodes code,String object) throws IOException{
        for (Iterator<Player> iterator = players.iterator(); iterator.hasNext();) {
            Player next = iterator.next();
            new Responce(code, object).sendJson(next.out);
        }
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
    
    public boolean hasPlayer(){
        return !players.isEmpty();
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
     
    private Vector<Player> players;
    private PlayerDetails currentTurn;
    private PlayerDetails nextTurn;
    private String code = "";
    private Integer currentPosition;
}
