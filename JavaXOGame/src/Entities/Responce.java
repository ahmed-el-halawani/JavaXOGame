/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Utils.JsonAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author A H M E D
 */
public class Responce {
    
    public static enum responceCodes{
        Done,
        ConnectionApproved,message,sendObject,
        SQLConnectionError,
        refreshGame,startGame,setMove,createGameRoom,findGame,findGameWithCode,Draw,Winner,
        error,setMoveError,createGameRoomError,findGameError,findGameWithCodeError,startGameError,
        LeaveGameRoom,LeaveGameRoomError,playAgain,waitingPlayerTwoPlayAgain
    }
    
    
    
    public static Responce fromJson(String body) throws  JsonProcessingException{
        return new ObjectMapper().readValue(body, Responce.class);
        
    }
    
    public String toJson() throws  JsonProcessingException{
        return new ObjectMapper().writeValueAsString(this);
    }
    
    public static <T> String arrayToString(ArrayList<T> array) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        mapper.writeValue(out2,  array);
        final byte[] data = out2.toByteArray();
        return new String(data);
    }
    
    public void sendJson(DataOutputStream out) throws JsonProcessingException, IOException{
        out.writeUTF(this.toJson());
    }
    
     public responceCodes getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(responceCodes statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "Responce{" + "statusCode=" + statusCode + ", object=" + object + '}';
    }
   
    public Responce() {
    }

    public Responce(responceCodes statusCode, String object) {
        this.statusCode = statusCode;
        this.object = object;
    }
    
    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    private responceCodes statusCode;
    private String object;

}
