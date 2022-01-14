/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.GameRoom;
import Entities.PlayerDetails;
import Entities.Responce;
import Entities.Responce.responceCodes;
import Entities.User;
import Entities.UserGameDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A H M E D
 */
public class GameRoomCrud {
    

    
    
    public GameRoomCrud(DataInputStream in,DataOutputStream out,ConnectionManager connection) {
        this.out = out;
        this.in = in;
        this.connection =connection;
    }
    
    public GameRoomCrud(ConnectionManager connection) {
        this.out = connection.out;
        this.in = connection.in;
        this.connection =connection;
    }
    
    public void createGameRoom(User user) throws JsonProcessingException, IOException {
          System.out.println(user);
       
            JsonAction jsonAction = new JsonAction(
                    user.toJson(),
                    JsonAction.Types.createGameRoom,
                    ""
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
    }

    public void findGameRoom(User user) throws JsonProcessingException, IOException {
        JsonAction jsonAction = new JsonAction(
            user.toJson(),
            JsonAction.Types.findGameRoom,
            ""
        );
        System.out.println(obm.writeValueAsString(jsonAction));
        out.writeUTF(obm.writeValueAsString(jsonAction));

        
    }
    
    public void leaveGame(User user) throws JsonProcessingException, IOException{
        JsonAction jsonAction = new JsonAction(
                user.getId(),
                JsonAction.Types.LeaveGameRoom,
                gameRoom.code
        );
        System.out.println(obm.writeValueAsString(jsonAction));
        out.writeUTF(obm.writeValueAsString(jsonAction));
    }
    
    public void recordGame(User user) throws JsonProcessingException, IOException{
        JsonAction jsonAction = new JsonAction(
                user.getId(),
                JsonAction.Types.StartRecordingForUser,
                gameRoom.code
        );

        System.out.println(obm.writeValueAsString(jsonAction));
        out.writeUTF(obm.writeValueAsString(jsonAction));
    }
    
    public void saveGame(UserGameDetails userGameDetails) throws JsonProcessingException, IOException{
        JsonAction jsonAction = new JsonAction(
                userGameDetails.toJson(),
                JsonAction.Types.SaveGame,
                gameRoom.code
        );
        System.out.println(obm.writeValueAsString(jsonAction));
        out.writeUTF(obm.writeValueAsString(jsonAction));
    }
    
    public void playAgain(User user) throws JsonProcessingException, IOException{
        JsonAction jsonAction = new JsonAction(
                user.getId(),
                JsonAction.Types.playAgain,
                gameRoom.code
        );
        System.out.println(obm.writeValueAsString(jsonAction));
        out.writeUTF(obm.writeValueAsString(jsonAction));
    }
    
    public void findGameRoomWithCode(User user,String code) throws JsonProcessingException, IOException {
        System.out.println(user);
        JsonAction jsonAction = new JsonAction(
                user.toJson(),
                JsonAction.Types.findGameRoomWithCode,
                code
        );
        System.out.println(obm.writeValueAsString(jsonAction));
        out.writeUTF(obm.writeValueAsString(jsonAction));

    }
    
    public boolean isReadyToPlay(){
        return gameRoom!=null && gameRoom.playerOneDetails!=null && gameRoom.playerTwoDetails!=null;
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
                    gameRoom.code
            );
            out.writeUTF(obm.writeValueAsString(jsonAction));
        }
    
    public ListenersX listener;
    
    public void removeMoveListener(){
        if(listener==null){
            return;
        }
        listeners.remove(listener);
        listener = null;
    }
    
    
    public void setMoveListenr(INotifayer recive,INotifayer win,INotifayer draw,INotifayer error){
        if(listener!=null){
            return;
        }
        listener = new ListenersX(
                    new NotifierObject[]
                    {
                        new NotifierObject(
                            (String object) -> {
                                try {
                                 gameRoom = obm.readValue(object, GameRoom.class);
                                 recive.notif(object);
                                } catch (JsonProcessingException ex) {
                                 Logger.getLogger(GameRoomCrud.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            },
                            responceCodes.setMove
                        ),
                        
                         new NotifierObject(
                            (String object) -> {
                                try {
                                 gameRoom = obm.readValue(object, GameRoom.class);
                                 win.notif(object);
                                } catch (JsonProcessingException ex) {
                                 Logger.getLogger(GameRoomCrud.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            },
                            responceCodes.Winner
                        ),
//                        
                        new NotifierObject(
                            (String object) -> {
                                try {
                                 gameRoom = obm.readValue(object, GameRoom.class);
                                 draw.notif(object);
                                } catch (JsonProcessingException ex) {
                                 Logger.getLogger(GameRoomCrud.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            },
                            responceCodes.Draw
                        ),
                        
                       
//                        
                        new NotifierObject(
                            (String object) -> {
                                 error.notif(object);
                            },
                            responceCodes.setMoveError
                        )
                    },
                false
                );
        
        setListener(listener);
    }
    
    public String setPlayerTwo(PlayerDetails playerTwoDetails) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String startRecordingForUser(String userId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setThread(Thread th){
         if(this.th== null){
            this.th = th;
            th.start();
        }
    }
    
    public void setListener(ListenersX listener){
        listeners.add(listener);
        if(th==null){
             th = new Thread(()->{
                while(isListing){
                    Vector<ListenersX> iterator2 = new Vector(Arrays.asList(listeners.toArray()));
                   
                    try {
                        String r = in.readUTF();
                        System.out.println("setListener");
                        System.out.println(r);
                        Responce res = obm.readValue(r, Responce.class);
                        
                        for(ListenersX next : iterator2) {
                            next.doIt(res.getObject(),res.getStatusCode());
                            if(next.isFinish)
                                listeners.remove(next);
                        }
                       
                    } catch (IOException ex) {
                        if(isListing)
                            Logger.getLogger(GameRoomCrud.class.getName()).log(Level.SEVERE, null, ex);
                        else
                            System.out.println("room closed successfuly");
                    }
                }
            });
            
            th.start();
        }
    }
    
    public Vector<ListenersX> listeners = new Vector<>();
    
    public static class NotifierObject{
        public INotifayer action;
        public responceCodes code;
        
        public NotifierObject(INotifayer action,responceCodes code){
            this.action = action;
            this.code = code;
        }
    }
    
    public static class ListenersX{
        public boolean isOneTime;
        public int reciveCode;
        public int erroCode;
        
        public ArrayList<NotifierObject> notifiers = new ArrayList();
        
        public boolean isFinish = false;
        public ListenersX(NotifierObject notifier){
            notifiers.add(notifier);
            this.isOneTime = true;
        }
        
        public ListenersX(NotifierObject[] notifier){
            notifiers.addAll(Arrays.asList(notifier));
            this.isOneTime = true;
        }
        
        public ListenersX(NotifierObject notifier,boolean isOneTime){
            notifiers.add(notifier);
            this.isOneTime = isOneTime;
        }
        
        public ListenersX(NotifierObject[] notifier,boolean isOneTime){
            notifiers.addAll(Arrays.asList(notifier) );
            this.isOneTime = isOneTime;
        }
        
        
        public NotifierObject containsCode(responceCodes code){
            for (NotifierObject notifier : notifiers) {
                if(notifier.code == code)
                    return notifier;
            }
            return null;
        }
        
        
        public void doIt(String object,responceCodes code) {
            NotifierObject notifi = containsCode(code);
            if(notifi!=null)
            {
                notifi.action.notif(object);
                if(isOneTime){
                    isFinish = true;
                }
            }
        }
    }
    
    public interface INotifayer{
        public void notif(String object);
    }
    
    private Thread th;
    
    public void dispose(){
        running = false;
    }
    
    public boolean running = true;
    public GameRoom gameRoom = null;
    public final DataOutputStream out;
    public final DataInputStream in;
    public boolean isListing = true;
    public final ConnectionManager connection;
    public final ObjectMapper obm = new ObjectMapper();
}
