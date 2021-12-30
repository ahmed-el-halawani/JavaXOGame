/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaxoserver;

import Entities.User;
import Entities.UserGameDetails;
import Utils.JsonAction;
import Utils.UserCrud;
import Utils.UserGameDetailsCrud;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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

    public JavaXOServer() throws IOException {
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
            new JavaXOServer();
        } catch (IOException ex) {
            Logger.getLogger(JavaXOServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class RequestHandler extends Thread {
    DataInputStream in;
    DataOutputStream out;
//    static Vector<RequestHandler> clientsVector = new Vector<RequestHandler>();
//    clientsVector.add(this);

    public RequestHandler(Socket cs) throws IOException {
        in = new DataInputStream(cs.getInputStream());
        out = new DataOutputStream(cs.getOutputStream());
        start();
    }

    public void run() {
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
                            (new UserCrud(in,out)).add(new ObjectMapper().readValue(action.getObject(), User.class));
                        break;
                        case GetAll:
                            new UserCrud(in,out).getAll();
                        break;
                        case Get:
                            new UserCrud(in,out).get(action.getParams());
                        break;
                        case Update:
                            new UserCrud(in,out).update(action.getParams(),new ObjectMapper().readValue(action.getObject(), User.class));
                        break;
                        case Delete:
                            new UserCrud(in,out).delete(action.getParams());
                        break;
                    }
                }else if(action.getCt() == UserGameDetailsCrud.class){
                     switch(action.getType()){
                        case Add:
                           (new UserGameDetailsCrud(in,out)).add(new ObjectMapper().readValue(action.getObject(), UserGameDetails.class));
                        break;
                        case GetAll:
                            new UserGameDetailsCrud(in,out).getAll();
                        break;
                        case Get:
                            new UserGameDetailsCrud(in,out).get(action.getParams());
                        break;
                        case Update:
                            new UserGameDetailsCrud(in,out).update(action.getParams(),new ObjectMapper().readValue(action.getObject(), UserGameDetails.class));
                        break;
                        case Delete:
                            new UserGameDetailsCrud(in,out).delete(action.getParams());
                        break;
                        case GetAllWithId:
                            new UserGameDetailsCrud(in,out).getAllWithId(action.getParams());
                        break;
                        case GetAllWithUesrName:
                            new UserGameDetailsCrud(in,out).getAllWithUserName(action.getParams());
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
            } 
        }
    }

}
