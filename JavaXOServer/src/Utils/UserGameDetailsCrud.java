/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.User;
import Entities.UserGameDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A H M E D
 */
public 
class UserGameDetailsCrud implements ICrud<UserGameDetails>{
DataOutputStream out;
    DataInputStream in;

    public UserGameDetailsCrud(DataInputStream in,DataOutputStream out) {
        this.out = out;
        this.in = in;
    }
    @Override
    public void add(UserGameDetails entity) throws JSONException, IOException{
        System.out.print(entity);
        out.writeInt(1);
    }

    @Override
    public void update(String id, Entities.UserGameDetails entity) throws JSONException, IOException {
       
            JSONObject jsonObject = new JSONObject(id);
            String id2 = jsonObject.getString("id");
            System.out.print("id: "+id2+"entity: "+entity);
        
            out.writeInt(1);
        
    }

    @Override
    public void delete(String id) throws JSONException, IOException{
            JSONObject jsonObject = new JSONObject(id);
            System.out.println(jsonObject.getString("id"));
            out.writeInt(1);
        
    }

    @Override
    public void get(String id) throws JSONException, IOException{
         System.out.print("id: "+id);
            JSONObject jsonObject = new JSONObject(id);
            System.out.println(jsonObject.getString("id"));
            
            out.writeUTF(UserGameDetails.dumyObject().toJson());
       
    }

    @Override
    public void getAll() throws JSONException, IOException{
            final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            final ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(out2,  new ArrayList(Arrays.asList(new UserGameDetails[]{UserGameDetails.dumyObject(),UserGameDetails.dumyObject()})));

            final byte[] data = out2.toByteArray();
            System.out.println(new String(data));
    
            out.writeUTF(new String(data));
         
    }
    
    
    public void getAllWithId(String id) throws JSONException, IOException {
         System.out.print("id: "+id);
        JSONObject jsonObject = new JSONObject(id);
        System.out.println(jsonObject.getString("id"));
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(out2,  new ArrayList(Arrays.asList(new UserGameDetails[]{UserGameDetails.dumyObject(),UserGameDetails.dumyObject()})));

        final byte[] data = out2.toByteArray();
        System.out.println(new String(data));

        out.writeUTF(new String(data));
      
    }
    
    
    public void getAllWithUserName(String userName) throws JSONException, IOException {
         System.out.print("userName: "+userName);
        JSONObject jsonObject = new JSONObject(userName);
        System.out.println(jsonObject.getString("userName"));
        final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(out2,  new ArrayList(Arrays.asList(new UserGameDetails[]{UserGameDetails.dumyObject(),UserGameDetails.dumyObject()})));

        final byte[] data = out2.toByteArray();
        System.out.println(new String(data));

        out.writeUTF(new String(data));

    }
   

}
