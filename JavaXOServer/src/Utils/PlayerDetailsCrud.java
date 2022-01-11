package Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import Entities.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import org.json.*;

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
Connection con;
    public PlayerDetailsCrud( DataInputStream in,DataOutputStream out,Connection con) {
        this.out = out;
        this.in = in;
        this.con= con;

    }
    
    public PlayerDetails add(PlayerDetails entity) throws JSONException, IOException, SQLException {
        System.out.print(entity);
        
        
                String id = UUID.randomUUID().toString();
                entity.setId(id);
                System.out.println("PlayerDetails add");
                System.out.println(entity);

                PreparedStatement query = con.prepareStatement("INSERT INTO PLAYERDETAILS VALUES(?,?,?,?,?,?)");
                query.setString(1,entity.getPlayerState().name());
                query.setString(2,entity.getPlayerSimbole().name());
                if(entity.getIsAccount())
                    query.setString(3,entity.getPlayer().getId());
                else
                    query.setString(3,entity.getPlayer().getName());
                
                query.setString(4,entity.getId());
                query.setBoolean(5,entity.getIsRecorded());
                query.setBoolean(6,entity.getIsAccount());
                

                if(query.executeUpdate()!=0){
                    return entity;
                }
           
        return null;
    }
    
    public PlayerDetails update(String idParam, PlayerDetails entity) throws JSONException, IOException, SQLException {
        JSONObject jsonObject = new JSONObject(idParam);
        final String id = jsonObject.getString("id");
        entity.setId(id);
        PreparedStatement query = con.prepareStatement(
                "UPDATE PLAYERDETAILS "
                + "SET "
                + "PLAYER_STATE = ?,"
                + "PLAYER_SIMBOLE = ?,"
                + "USER_ID = ?,"
                + "WHERE ID=?"
        );

        query.setString(1,entity.getPlayerState().name());
        query.setString(2,entity.getPlayerSimbole().name());
        query.setString(3,entity.getPlayer().getId());
        query.setString(4,entity.getId());
        if(query.executeUpdate()!=0){
            return entity;
        }
           
         return null;   
        
    }

    public int delete(String idParam)throws JSONException, IOException, SQLException {
        JSONObject jsonObject = new JSONObject(idParam);
        final String id = jsonObject.getString("id");
        
        PreparedStatement query = con.prepareStatement(
                "DELETE FROM  PLAYERDETAILS WHERE ID =?"
        );

        query.setString(1,id);

        return query.executeUpdate();
    }

    public PlayerDetails get(String id)throws JSONException, IOException, SQLException  {
          final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            final ObjectMapper mapper = new ObjectMapper();
            
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement query = con.prepareStatement("SELECT * FROM PLAYERDETAILS WHERE ID=?");
        query.setString(1,id);
        ResultSet rs = query.executeQuery();

        if(rs.next()){
            User user;
            if(rs.getBoolean("IS_ACCOUNT"))
                user= new UserCrud(con).getWithId(con,rs.getString("USER_ID"));
            else
                user= new User(rs.getString("USER_ID"));
            return PlayerDetails.fromResultSet(rs,user);
        }

           
        return null;
    }

    public ArrayList<PlayerDetails> getAll()throws JSONException, IOException, SQLException {
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        ArrayList<PlayerDetails>  playersDetails = new ArrayList<>();
        String id = UUID.randomUUID().toString();
        PreparedStatement query = con.prepareStatement("SELECT * FROM PLAYERDETAILS");
        ResultSet rs = query.executeQuery();

        while(rs.next()){
            User user;
            if(rs.getBoolean("IS_ACCOUNT"))
                user= new UserCrud(con).getWithId(con,rs.getString("USER_ID"));
            else
                user= new User(rs.getString("USER_ID"));
           playersDetails.add(PlayerDetails.fromResultSet(rs,user));
        }
        
        return playersDetails;
    }
}

