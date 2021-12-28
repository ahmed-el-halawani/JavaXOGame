/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaxoserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    DataInputStream dis;
    DataOutputStream ps;
    static Vector<RequestHandler> clientsVector = new Vector<RequestHandler>();

    public RequestHandler(Socket cs) throws IOException {
        dis = new DataInputStream(cs.getInputStream());
        ps = new DataOutputStream(cs.getOutputStream());
        clientsVector.add(this);
        start();
    }

    public void run() {
        while (true) {
            String str="no message";
            try {
                str = dis.readUTF();
                System.out.println(str);
            } 
            
            catch (java.net.SocketException ex) {
                clientsVector.remove(this);
                break;
            }catch (IOException ex) {
                Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
    }

}
