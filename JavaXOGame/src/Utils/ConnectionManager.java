/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Responce;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    
    public enum socketType{
     game,data
    }
    
    private ConnectionManager(socketType type) throws IOException{
        soc = new Socket("127.0.0.1",5005);
        in = new DataInputStream(soc.getInputStream());
        out = new DataOutputStream(soc.getOutputStream());
        ObjectMapper om = new ObjectMapper();
        Responce res = om.readValue(in.readUTF(), Responce.class);
        if(res.getStatusCode()!=200){
            throw new IOException(res.getObject());
        }
    }
   
    
    public static ConnectionManager getInstance() throws IOException{
       return cm==null?new ConnectionManager(socketType.data):cm;
    }
    
    public static ConnectionManager createGameSocet() throws IOException{
       return new ConnectionManager(socketType.game);
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
