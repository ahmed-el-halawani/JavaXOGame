/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.GameRoom.gameRoomResponce;
import Entities.PlayerDetails;
import Entities.UserGameDetails;
import Interfaces.IGameRoom;
import Utils.JsonAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.reflect.Array.set;
import java.util.Set;

/**
 *
 * @author A H M E D
 */
public class GameRoomCrud {
    private String code;
    private final ObjectMapper obm = new ObjectMapper();
    private GameRoom gameRoom = null;
    DataOutputStream out;
    DataInputStream in;

    public GameRoomCrud(DataInputStream in,DataOutputStream out) {
        this.out = out;
        this.in = in;
    }
    
    public void createGameRoom(PlayerDetails playerOneDetails) throws JsonProcessingException, IOException {
          System.out.println(playerOneDetails);
       
            JsonAction jsonAction = new JsonAction(
                    playerOneDetails.toJson(),
                    JsonAction.Types.createGameRoom,
                    GameRoom.class,
                    ""
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            Responce res = obm.readValue(in.readUTF(), Responce.class);
                        System.out.println(res);

            if(res.getStatusCode() == 200){
                this.code = res.getObject();
            }else{
                throw new IOException(res.getObject());
            }
    }

    public void findGameRoom(PlayerDetails playerOneDetails) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCode() {
        return this.code;
    }

    public PlayerDetails getCurrentTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isMyTurn(String userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setMove(Integer position) throws JsonProcessingException, IOException {
       
            JsonAction jsonAction = new JsonAction(
                    position.toString(),
                    JsonAction.Types.setMove,
                    GameRoom.class,
                    code
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            Responce res = obm.readValue(in.readUTF(), Responce.class);
            System.out.println("Game Room");
            System.out.println(res);

            if(res.getStatusCode() == 200){
                this.gameRoom = obm.readValue(res.getObject(), GameRoom.class);
            }else if(res.getStatusCode() == 300){
                System.out.print(gameRoomResponce.valueOf(res.getObject()));
            }else{
                throw new IOException(res.getObject());
            }
        }


    public String setPlayerTwo(PlayerDetails playerTwoDetails) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String startRecordingForUser(String userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
