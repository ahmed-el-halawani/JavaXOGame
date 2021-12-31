/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaxoserver;

import Entities.User;
import Entities.UserGameDetails;
import Utils.JsonAction;
import Utils.PlayerDetailsCrud;
import Utils.UserCrud;
import Utils.UserGameDetailsCrud;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author A H M E D
 */
public class JavaXOServer {
 ServerSocket serverSocket;

    public JavaXOServer() throws IOException, SQLException {
        serverSocket = new ServerSocket(5005);
        System.out.println("wiating client...");
        while (true) {
            Socket s = serverSocket.accept();
            System.out.println("hi Client how r u?");
            System.out.println("garsone handle him but him in ur eaise");
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            new RequestHandler(in,out);
            
        }
    }

    public static void main(String[] args) {
        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            new JavaXOServer();
        } catch (SQLException ex) {
            Logger.getLogger(JavaXOServer.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
            Logger.getLogger(JavaXOServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class Room{
    String roomId;
    Vector<RequestHandler> clientsVector = new Vector<RequestHandler>();
    
    public void run(JsonAction ja){
        
    }
}

class RequestHandler extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Connection con;

//    static Vector<RequestHandler> clientsVector = new Vector<RequestHandler>();
//    clientsVector.add(this);

    public RequestHandler(DataInputStream in,DataOutputStream out) {
        try {
            this.in = in;
            this.out = out;
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
            start();
        } catch (SQLException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        UserCrud userCrud = new UserCrud(in,out,con);
        PlayerDetailsCrud playerDetailsCrud = new PlayerDetailsCrud(in,out,con);
        UserGameDetailsCrud userGameDetailsCrud = new UserGameDetailsCrud(in,out,con,playerDetailsCrud);

        while (true) {
            try {
                String str = in.readUTF();
                System.out.println(str);
                JsonAction action = JsonAction.fromJson(str);
                
                if(action.getCt() == User.class){
                    System.out.println("here we are again");
                }
                
                
          
                if(action.getCt() == UserCrud.class){
                    switch(action.getType()){
                        case Add:
                            userCrud.add(new ObjectMapper().readValue(action.getObject(), User.class));
                        break;
                        case GetAll:
                            userCrud.getAll();
                        break;
                        case Get:
                            userCrud.get(action.getParams());
                        break;
                        case Update:
                            userCrud.update(action.getParams(),new ObjectMapper().readValue(action.getObject(), User.class));
                        break;
                        case Delete:
                            userCrud.delete(action.getParams());
                        break;
                    }
                }else if(action.getCt() == UserGameDetailsCrud.class){
                     switch(action.getType()){
                        case Add:
                           userGameDetailsCrud.add(new ObjectMapper().readValue(action.getObject(), UserGameDetails.class));
                        break;
                        case GetAll:
                            userGameDetailsCrud.getAll();
                        break;
                        case Get:
                            userGameDetailsCrud.get(action.getParams());
                        break;
                        case Update:
                            userGameDetailsCrud.update(action.getParams(),new ObjectMapper().readValue(action.getObject(), UserGameDetails.class));
                        break;
                        case Delete:
                            userGameDetailsCrud.delete(action.getParams());
                        break;
                        case GetAllWithId:
                            userGameDetailsCrud.getAllWithId(action.getParams());
                        break;
                        case GetAllWithUesrName:
                            userGameDetailsCrud.getAllWithUserName(action.getParams());
                        break;
                     }
                }
                
                
                System.out.println(action);
            } 
            
            catch (java.net.SocketException ex) {
//                clientsVector.remove(this);
                break;
            }catch (IOException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                
            } catch (JSONException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

}
