/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

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
import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A H M E D
 */


public class UserCrud{
    DataOutputStream out;
    DataInputStream in;
    Connection con;

    public UserCrud( DataInputStream in,DataOutputStream out,Connection con) {
        this.out = out;
        this.in = in;
        this.con= con;
    }
    
    public void add(User entity) throws JSONException, IOException, SQLException{
        System.out.print(entity);
         
        String id = UUID.randomUUID().toString();
        PreparedStatement query = con.prepareStatement("INSERT INTO USERDATA VALUES(?,?,?,?,?)");
        query.setString(1,id);
        query.setString(2,entity.getName());
        query.setString(3,entity.getUserName());
        query.setString(4,entity.getPassword());
        query.setString(5,entity.getUserType().name());

        out.writeInt(query.executeUpdate());
            
    }

    public void update(String idParam, User entity) throws JSONException, IOException, SQLException {
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

        out.writeInt(query.executeUpdate());
           
    }

    public void delete(String idParam)throws JSONException, IOException, SQLException {
           JSONObject jsonObject = new JSONObject(idParam);
        final String id = jsonObject.getString("id");
        PreparedStatement query = con.prepareStatement(
                "DELETE FROM  USERDATA WHERE ID =?"
        );

        query.setString(1,id);

        out.writeInt(query.executeUpdate());
      
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

    public void get(String idParam)throws JSONException, IOException, SQLException {
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        JSONObject jsonObject = new JSONObject(idParam);
        final String id = jsonObject.getString("id");


        User u = getWithId(con, id);
        if(u !=null){
            out.writeUTF(u.toJson());
        }else{
            out.writeUTF("null");
        }
    }

    public void getAll()throws JSONException, IOException, SQLException {
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();


     
            ArrayList<User> users = new ArrayList<>();
            String id = UUID.randomUUID().toString();
            PreparedStatement query = con.prepareStatement("SELECT * FROM USERDATA");
            ResultSet rs = query.executeQuery();

            while(rs.next()){
               users.add(User.fromResultSet(rs));
            }

             mapper.writeValue(out2,  users);

            final byte[] data = out2.toByteArray();
            System.out.println(new String(data));

            out.writeUTF(new String(data));
       
    }
}
