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
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A H M E D
 */


public class UserCrud implements ICrud<User>{
    DataOutputStream out;
    DataInputStream in;

    public UserCrud( DataInputStream in,DataOutputStream out) {
        this.out = out;
        this.in = in;
    }
    
    @Override
    public void add(User entity) throws JSONException, IOException {
        System.out.print(entity);
            out.writeInt(1);
        
    }

    @Override
    public void update(String id, User entity) throws JSONException, IOException {
        
            JSONObject jsonObject = new JSONObject(id);
            System.out.println(jsonObject.getString("id"));
            System.out.print("id: "+id+"entity: "+entity);
        
            out.writeInt(1);
        
    }

    @Override
    public void delete(String id)throws JSONException, IOException {
            JSONObject jsonObject = new JSONObject(id);
            System.out.println(jsonObject.getString("id"));
            out.writeInt(1);
      
    }

    @Override
    public void get(String id)throws JSONException, IOException  {
        System.out.print("id: "+id);
            JSONObject jsonObject = new JSONObject(id);
            System.out.println(jsonObject.getString("id"));
            
            out.writeUTF(new User("ahmed","ahmed","gomaa").toJson());
       
    }

    @Override
    public void getAll()throws JSONException, IOException {
            final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            final ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(out2,  new ArrayList(Arrays.asList(new User[]{new User("ahmed","ahmed","gomaa"),new User("ahmed2","ahmed2","gomaa2")})));

            final byte[] data = out2.toByteArray();
            System.out.println(new String(data));
    
            out.writeUTF(new String(data));
    }
}
