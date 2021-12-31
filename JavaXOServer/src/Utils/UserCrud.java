/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.sql.*;
import java.util.*;
import org.json.*;

/**
 *
 * @author A H M E D
 */


public class UserCrud{
    
    public int add(User entity) throws JSONException, IOException, SQLException{
        System.out.print(entity);
         
        String id = UUID.randomUUID().toString();
        PreparedStatement query = con.prepareStatement("INSERT INTO USERDATA VALUES(?,?,?,?,?)");
        query.setString(1,id);
        query.setString(2,entity.getName());
        query.setString(3,entity.getUserName());
        query.setString(4,entity.getPassword());
        query.setString(5,entity.getUserType().name());

//        out.writeInt();
        return query.executeUpdate();
            
    }

    public int update(String idParam, User entity) throws JSONException, IOException, SQLException {
        JSONObject jsonObject = new JSONObject(idParam);
        final String id = jsonObject.getString("id");
        
        PreparedStatement query = con.prepareStatement(
                "UPDATE USERDATA "
                + "SET "
                + "NAME = ?,"
                + "USERNAME = ?,"
                + "PASSWORD = ?,"
                + "USERTYPE = ? "
                + "WHERE ID=?"
        );

        query.setString(1,entity.getName());
        query.setString(2,entity.getUserName());
        query.setString(3,entity.getPassword());
        query.setString(4,entity.getUserType().name());
        query.setString(5,id);

//        out.writeInt(query.executeUpdate());
        return query.executeUpdate();
           
    }

    public int delete(String idParam)throws JSONException, IOException, SQLException {
           JSONObject jsonObject = new JSONObject(idParam);
        final String id = jsonObject.getString("id");
        PreparedStatement query = con.prepareStatement(
                "DELETE FROM  USERDATA WHERE ID =?"
        );

        query.setString(1,id);

//        out.writeInt(query.executeUpdate());
        return query.executeUpdate();
      
    }
    
    public User getWithId(Connection con,String id) throws JSONException, IOException, SQLException{
            final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            final ObjectMapper mapper = new ObjectMapper();

            ArrayList<User> users = new ArrayList<>();
            PreparedStatement query = con.prepareStatement("SELECT * FROM USERDATA WHERE ID=?");
            query.setString(1,id);
            ResultSet rs = query.executeQuery();
            if(rs.next()){
                return User.fromResultSet(rs);
            }else{
                 return null;
            }

}

    public User get(String params)throws JSONException, IOException, SQLException {
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        final ArrayList<User> users = new ArrayList<>();
        final ObjectMapper mapper = new ObjectMapper();
        final JSONObject jsonObject = new JSONObject(params);
        final String id = jsonObject.getString("id");

        PreparedStatement query = con.prepareStatement("SELECT * FROM USERDATA WHERE ID=?");
        query.setString(1,id);
        ResultSet rs = query.executeQuery();
        if(rs.next()){
            return User.fromResultSet(rs);
        }else{
             return null;
        }
    }
    
    public User getWithUserName(String params)throws JSONException, IOException, SQLException {
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        final ArrayList<User> users = new ArrayList<>();
        final ObjectMapper mapper = new ObjectMapper();
        final JSONObject jsonObject = new JSONObject(params);
        final String userName = jsonObject.getString("userName");

        PreparedStatement query = con.prepareStatement("SELECT * FROM USERDATA WHERE USERNAME=?");
        query.setString(1,userName);
        ResultSet rs = query.executeQuery();
        if(rs.next()){
            return User.fromResultSet(rs);
        }else{
             return null;
        }
    }

    public ArrayList<User> getAll()throws JSONException, IOException, SQLException {
        final ArrayList<User> users = new ArrayList<>();
        
        PreparedStatement query = null;
        ResultSet rs = null;
     
        query = con.prepareStatement("SELECT * FROM USERDATA");
        rs = query.executeQuery();

        while(rs.next()){
           users.add(User.fromResultSet(rs));
        }
        return users;
    }
    
    
    public UserCrud( DataInputStream in,DataOutputStream out,Connection con) {
        this.out = out;
        this.in = in;
        this.con= con;
        this.mapper = new ObjectMapper();
    }
    
    final private DataOutputStream out;
    final private DataInputStream in;
    final private Connection con;
    final private ObjectMapper mapper;

}
