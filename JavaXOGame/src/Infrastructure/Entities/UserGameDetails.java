/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infrastructure.Entities;

import java.util.Map;

/**
 *
 * @author A H M E D
 */

public class UserGameDetails {
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

 
    public Map<Integer, Integer> getRecord() {
        return record;
    }

    public void setRecord(Map<Integer, Integer> record) {
        this.record = record;
    }

    public Map<Integer, Integer> getGameBordBeforRecording() {
        return gameBordBeforRecording;
    }

    public void setGameBordBeforRecording(Map<Integer, Integer> gameBordBeforRecording) {
        this.gameBordBeforRecording = gameBordBeforRecording;
    }

    public Map<Integer, Integer> getGameBord() {
        return gameBord;
    }

    public void setGameBord(Map<Integer, Integer> gameBord) {
        this.gameBord = gameBord;
    }
    
    public PlayerDetail getPlayerOneDetails() {
        return playerOneDetails;
    }

    public void setPlayerOneDetails(PlayerDetail playerOneDetails) {
        this.playerOneDetails = playerOneDetails;
    }

    public PlayerDetail getPlayerTwoDetails() {
        return playerTwoDetails;
    }

    public void setPlayerTwoDetails(PlayerDetail playerTwoDetails) {
        this.playerTwoDetails = playerTwoDetails;
    }
    
    
    
    private GameModes gameMode;
    private GameDifficultyLvl gameDifficultyLvl;
    private PlayerDetail playerOneDetails;
    private PlayerDetail playerTwoDetails;
    private Map<Integer,Integer> record;
    private Map<Integer,Integer> gameBordBeforRecording;
    private Map<Integer,Integer> gameBord;

    
    
    
    
    class PlayerDetail{

        public User getPlayer() {
            return player;
        }

        public void setPlayer(User player) {
            this.player = player;
        }

        public PlayerState getPlayerState() {
            return playerState;
        }

        public void setPlayerState(PlayerState playerState) {
            this.playerState = playerState;
        }

        public PlayerSimbole getPlayerSample() {
            return playerSample;
        }

        public void setPlayerSample(PlayerSimbole playerSample) {
            this.playerSample = playerSample;
        }
        
        public PlayerDetail(User player, PlayerState playerState, PlayerSimbole playerSample) {
            this.player = player;
            this.playerState = playerState;
            this.playerSample = playerSample;
        }
        
        public PlayerDetail(User player, PlayerSimbole playerSample) {
            this.player = player;
            this.playerSample = playerSample;
            this.playerState = PlayerState.Draw;
        }
        
        private User player;
        private PlayerState playerState;
        private PlayerSimbole  playerSample;
    }
}
