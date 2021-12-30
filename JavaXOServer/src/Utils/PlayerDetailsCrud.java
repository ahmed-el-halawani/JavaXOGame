package Utils;

import Entities.PlayerDetails;
import Entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author A H M E D
 */
public class PlayerDetailsCrud{
DataOutputStream out;
    DataInputStream in;

    public PlayerDetailsCrud( DataInputStream in,DataOutputStream out) {
        this.out = out;
        this.in = in;
    }
    
    public PlayerDetails add(PlayerDetails entity) throws JSONException, IOException {
        System.out.print(entity);
        
        
        Connection con = null;
         try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
                String id = UUID.randomUUID().toString();
                entity.setId(id);
                PreparedStatement query = con.prepareStatement("INSERT INTO PLAYERDETAILS VALUES(?,?,?,?)");
                query.setString(1,entity.getPlayerState().name());
                query.setString(2,entity.getPlayerSample().name());
                query.setString(3,entity.getPlayer().getId());
                query.setString(4,entity.getId());

                if(query.executeUpdate()!=0){
                    return entity;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        return null;
    }

    public PlayerDetails update(String idParam, PlayerDetails entity) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject(idParam);
        final String id = jsonObject.getString("id");
        entity.setId(id);
        Connection con = null;
         try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
                PreparedStatement query = con.prepareStatement(
                        "UPDATE PLAYERDETAILS "
                        + "SET "
                        + "PLAYER_STATE = ?,"
                        + "PLAYER_SIMBOLE = ?,"
                        + "USER_ID = ?,"
                        + "WHERE ID=?"
                );
                
                query.setString(1,entity.getPlayerState().name());
                query.setString(2,entity.getPlayerSample().name());
                query.setString(3,entity.getPlayer().getId());
                query.setString(4,entity.getId());
                if(query.executeUpdate()!=0){
                    return entity;
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
             try {
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
               }
         }
            
         return null;   
        
    }

    public int delete(String idParam)throws JSONException, IOException {
           JSONObject jsonObject = new JSONObject(idParam);
        final String id = jsonObject.getString("id");
        
            Connection con = null;
         try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
                PreparedStatement query = con.prepareStatement(
                        "DELETE FROM  PLAYERDETAILS WHERE ID =?"
                );
                
                query.setString(1,id);

                return query.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
               try {
                   con.close();
               } catch (SQLException ex) {
                   Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
               }
         }
          
         return -1;   
    }

    public PlayerDetails get(String id)throws JSONException, IOException  {
          final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            final ObjectMapper mapper = new ObjectMapper();
            
            Connection con = null;
            
            try {
                con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
                ArrayList<User> users = new ArrayList<>();
                PreparedStatement query = con.prepareStatement("SELECT * FROM PLAYERDETAILS WHERE ID=?");
                query.setString(1,id);
                ResultSet rs = query.executeQuery();
                
                if(rs.next()){
                    User user = new UserCrud(in, out).getWithId(con,rs.getString("USER_ID"));
                    return PlayerDetails.fromResultSet(rs,user);
                }

            } catch (SQLException ex) {
                Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
    }

    public ArrayList<PlayerDetails> getAll() {
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/javaOXDatabase","javaProject","javaProject");
            ArrayList<PlayerDetails>  playersDetails = new ArrayList<>();
            String id = UUID.randomUUID().toString();
            PreparedStatement query = con.prepareStatement("SELECT * FROM PLAYERDETAILS");
            ResultSet rs = query.executeQuery();

            while(rs.next()){
               User user = new UserCrud(in, out).getWithId(con,rs.getString("USER_ID"));
               playersDetails.add(PlayerDetails.fromResultSet(rs,user));
            }

             return playersDetails;
        } catch (SQLException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return null;
    }
}

