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
    public int add(UserGameDetails entity) {
        System.out.print(entity);
        try {
            out.writeInt(1);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int update(String id, Entities.UserGameDetails entity) {
       try {
            JSONObject jsonObject = new JSONObject(id);
            String id2 = jsonObject.getString("id");
            System.out.print("id: "+id2+"entity: "+entity);
        
            out.writeInt(1);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;  
    }

    @Override
    public int delete(String id) {
         try {
            JSONObject jsonObject = new JSONObject(id);
            System.out.println(jsonObject.getString("id"));
            out.writeInt(1);
        } catch (JSONException ex) { 
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public UserGameDetails get(String id) {
         System.out.print("id: "+id);
         try {
            JSONObject jsonObject = new JSONObject(id);
            System.out.println(jsonObject.getString("id"));
            
            out.writeUTF(UserGameDetails.dumyObject().toJson());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Entities.UserGameDetails> getAll() {
      try {
            final ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            final ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(out2,  new ArrayList(Arrays.asList(new UserGameDetails[]{UserGameDetails.dumyObject(),UserGameDetails.dumyObject()})));

            final byte[] data = out2.toByteArray();
            System.out.println(new String(data));
    
            out.writeUTF(new String(data));
         } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   

}
