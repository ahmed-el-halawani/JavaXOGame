/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.GameRoom.gameRoomResponce;
import Utils.JsonAction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A H M E D
 */
public class GameRoomCrud {
    

    public boolean isReadyToPlay(){
    return gameRoom!=null && gameRoom.playerOneDetails!=null && gameRoom.playerTwoDetails!=null;
    }
    
    public GameRoomCrud(DataInputStream in,DataOutputStream out) {
        this.out = out;
        this.in = in;
    }
    
    public void createGameRoom(User user,INotifayer recive,INotifayer error) throws JsonProcessingException, IOException {
          System.out.println(user);
       
            JsonAction jsonAction = new JsonAction(
                    user.toJson(),
                    JsonAction.Types.createGameRoom,
                    GameRoom.class,
                    ""
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            
            setListener(
                    new ListenersX(new NotifierObject[]{
                        new NotifierObject(
                            (String object) -> {
                                 this.code = object;
                                 recive.notif(object);
                            },
                            Responce.createGameRoom
                        ),
                        new NotifierObject(
                            (String object) -> {
                                 error.notif(object);
                            },
                            Responce.createGameRoomError
                        )
                    }
                )
            );
                    
    }

    public void findGameRoom(User user,INotifayer recive,INotifayer error) throws JsonProcessingException, IOException {
        System.out.println(user);
       
        JsonAction jsonAction = new JsonAction(
            user.toJson(),
            JsonAction.Types.findGameRoom,
            GameRoom.class,
            ""
        );
        System.out.println(obm.writeValueAsString(jsonAction));
        out.writeUTF(obm.writeValueAsString(jsonAction));

        setListener(
                new ListenersX(new NotifierObject[]
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
                        Responce.findGame
                    ),
                    new NotifierObject(
                        (String object) -> {
                             error.notif(object);
                        },
                        Responce.findGameError
                    )
                }
            )
        );
    }
    
    public void findGameRoomWithCode(User user,String code,INotifayer recive,INotifayer error) throws JsonProcessingException, IOException {
        System.out.println(user);
        JsonAction jsonAction = new JsonAction(
                user.toJson(),
                JsonAction.Types.findGameRoomWithCode,
                GameRoom.class,
                code
        );
        System.out.println(obm.writeValueAsString(jsonAction));
        out.writeUTF(obm.writeValueAsString(jsonAction));
//        
//        setListener(
//                new ListenersX(new NotifierObject[]
//                {
//                    new NotifierObject(
//                        (String object) -> {
//                            try {
//                             gameRoom = obm.readValue(object, GameRoom.class);
//                             recive.notif(object);
//                            } catch (JsonProcessingException ex) {
//                             Logger.getLogger(GameRoomCrud.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//                        },
//                        Responce.findGameWithCode
//                    ),
//                    new NotifierObject(
//                        (String object) -> {
//                             error.notif(object);
//                        },
//                        Responce.findGameWithCodeError
//                    )
//                }
//            )
//        );
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
    
    public void setMoveListenr(INotifayer recive,INotifayer error){
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
                            Responce.setMove
                        ),
                        new NotifierObject(
                            (String object) -> {
                                 error.notif(object);
                            },
                            Responce.setMoveError
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
                while(running){
                    try {
                        String r = in.readUTF();
                        System.out.println("setListener");
                        System.out.println(r);
                        Responce res = obm.readValue(r, Responce.class);
                        
                        for (Iterator<ListenersX> iterator = listeners.iterator(); iterator.hasNext();) {
                            ListenersX next = iterator.next();
                            next.doIt(res.getObject(),res.getStatusCode());
//                            if(next.isFinish){
//                                listeners.remove(next);
//                            }
                        }
                       
                    } catch (IOException ex) {
                        Logger.getLogger(GameRoomCrud.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            
            th.start();
        }
    }
    
    public Vector<ListenersX> listeners = new Vector<>();
    
    public static class NotifierObject{
        public INotifayer action;
        public int code;
        
        public NotifierObject(INotifayer action,int code){
            this.action = action;
            this.code = code;
        }
    }
    
    public static class ListenersX{
        public INotifayer refresh;
        public INotifayer error;
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
            this.isOneTime = true;
        }
        
        public ListenersX(NotifierObject[] notifier,boolean isOneTime){
            notifiers.addAll(Arrays.asList(notifier) );
            this.isOneTime = true;
        }
        
        
        public NotifierObject containsCode(int code){
            for (NotifierObject notifier : notifiers) {
                if(notifier.code == code)
                    return notifier;
            }
            return null;
        }
        
        
        public void doIt(String object,int code) {
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

    public String code;
    public final ObjectMapper obm = new ObjectMapper();
    public  GameRoom gameRoom = null;
    public DataOutputStream out;
    public DataInputStream in;
}
