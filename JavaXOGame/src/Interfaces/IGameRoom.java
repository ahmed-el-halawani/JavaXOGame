/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.PlayerDetails;

/**
 *
 * @author A H M E D
 */
public interface IGameRoom {

    boolean canJoin();

    void createGameRoom(PlayerDetails playerOneDetails);

    void findGameRoom(PlayerDetails playerOneDetails);

    String getCode();

    PlayerDetails getCurrentTurn();

    boolean isIsFinding();

    boolean isMyTurn(String userId);

    void setCode(String code);

    void setCurrentTurn(PlayerDetails currentTurn);

    void setIsFinding(boolean isFinding);

    String setMove(String userId, Integer position);

    int setPlayerTwo(PlayerDetails playerTwoDetails);

    String startRecordingForUser(String userId);
    
}
