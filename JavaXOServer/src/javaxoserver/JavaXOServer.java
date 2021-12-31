/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaxoserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import Entities.*;
import Utils.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            new RequestHandler(s);
            
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
    Socket s;
    Connection con;

//    static Vector<RequestHandler> clientsVector = new Vector<RequestHandler>();
//    clientsVector.add(this);

    public RequestHandler(Socket s) throws IOException {
        try {
            this.in = new DataInputStream(s.getInputStream());
            this.out = new DataOutputStream(s.getOutputStream());
            this.s = s;
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
            new Responce(200, "Done").sendJson(out);
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
                
                System.out.println("action: "+action);
          
                if(action.getCt() == UserCrud.class){
                    switch(action.getType()){
                        case Add:
                            Integer res = userCrud.add(new ObjectMapper().readValue(action.getObject(), User.class));
                            new Responce(200, res.toString()).sendJson(out);
                        break;
                        case GetAll:
                            ArrayList<User> users = userCrud.getAll();
                            new Responce(200, Responce.arrayToString(users)).sendJson(out);
                        break;
                        case Get:
                            User user = userCrud.get(action.getParams());
                            new Responce(200, user.toJson()).sendJson(out);
                        break;
                        case Update:
                            Integer updatedRow = userCrud.update(action.getParams(),new ObjectMapper().readValue(action.getObject(), User.class));
                            new Responce(200, updatedRow.toString()).sendJson(out);
                        break;
                        case Delete:
                            Integer deletedRow= userCrud.delete(action.getParams());
                            new Responce(200, deletedRow.toString()).sendJson(out);
                        break;
                    }
                }else if(action.getCt() == UserGameDetailsCrud.class){
                     switch(action.getType()){
                        case Add:
                            Integer addedRow = userGameDetailsCrud.add(new ObjectMapper().readValue(action.getObject(), UserGameDetails.class));
                            new Responce(200, addedRow.toString()).sendJson(out);
                        break;
                        case GetAll:
                            ArrayList<UserGameDetails> array = userGameDetailsCrud.getAll();
                            new Responce(200, Responce.arrayToString(array)).sendJson(out);
                        break;
                        case Get:
                            UserGameDetails userGameDetails = userGameDetailsCrud.get(action.getParams());
                            new Responce(200, userGameDetails.toJson()).sendJson(out);
                        break;
                        case Update:
                            Integer updatedRow = userGameDetailsCrud.update(action.getParams(),new ObjectMapper().readValue(action.getObject(), UserGameDetails.class));
                            new Responce(200, updatedRow.toString()).sendJson(out);
                            break;
                        case Delete:
                            Integer deletedRow =userGameDetailsCrud.delete(action.getParams());
                            new Responce(200, deletedRow.toString()).sendJson(out);
                        break;
                        case GetAllWithId:
                            ArrayList<UserGameDetails> arrayWithId =userGameDetailsCrud.getAllWithId(action.getParams());
                            new Responce(200, Responce.arrayToString(arrayWithId)).sendJson(out);
                        break;
                        case GetAllWithUesrName:
                           ArrayList<UserGameDetails> arrayWithUserName = userGameDetailsCrud.getAllWithUserName(action.getParams());
                            new Responce(200, Responce.arrayToString(arrayWithUserName)).sendJson(out);
                        break;
                     }
                }
                
            } 
            
            catch ( IOException ex) {
                try {
                    con.close();
                    s.close();
                    System.out.print("User quit");
                } catch (IOException|SQLException ex1) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex1);
                } 
                break;
            } catch (JSONException |SQLException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    new Responce(404, ex.toString()).sendJson(out);
                } catch (IOException ex1) {
                    Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } 
        }
    }

}
