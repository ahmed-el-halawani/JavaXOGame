/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A H M E D
 */
public class ConnectionManager {
    private static ConnectionManager cm;
    public Socket soc;
    public DataOutputStream out;
    public DataInputStream in;
    
    private ConnectionManager(){
        try {
            soc = new Socket("127.0.0.1",5005);
            in = new DataInputStream(soc.getInputStream());
            out = new DataOutputStream(soc.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ConnectionManager getInstance(){
       return cm==null?new ConnectionManager():cm;
    }
    
    public void dispose(){
        try {
            soc.close();
            in.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
