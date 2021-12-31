/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Responce;
import Interfaces.ICrud;
import Entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A H M E D
 */


public  class UserCrud implements ICrud<User>{
    private ObjectMapper obm = new ObjectMapper();
    DataOutputStream out;
    DataInputStream in;

    public UserCrud( DataInputStream in,DataOutputStream out) {
        this.out = out;
        this.in = in;
    }
    
    public UserCrud( ConnectionManager cm) {
        this.out = cm.out;
        this.in = cm.in;
    }
    
    @Override
    public synchronized int add(User entity) {
        System.out.println(entity);
        try {
            JsonAction jsonAction = new JsonAction(
                    entity.toJson(),
                    JsonAction.Types.Add,
                    this.getClass(),
                    ""
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            
            Responce res = obm.readValue(in.readUTF(), Responce.class);
            
            if(res.getStatusCode() == 200){
                return obm.readValue(res.getObject(), Integer.class);
            }else{
                throw new IOException(res.getObject());
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int update(String id, User entity) {
        System.out.print(entity);
        Map<String,String> m = new HashMap();
        m.put("id",id);
        try {
            JsonAction jsonAction = new JsonAction(
                    entity.toJson(),
                    JsonAction.Types.Update,
                    this.getClass(),
                    obm.writeValueAsString(m)
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            
            Responce res = obm.readValue(in.readUTF(), Responce.class);
            
            if(res.getStatusCode() == 200){
                return obm.readValue(res.getObject(), Integer.class);
            }else{
                throw new IOException(res.getObject());
            }
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized int delete(String id) {
        Map<String,String> m = new HashMap();
        m.put("id",id);
        try {
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.Delete,
                    this.getClass(),
                    obm.writeValueAsString(m)
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            
              
            Responce res = obm.readValue(in.readUTF(), Responce.class);
            
            if(res.getStatusCode() == 200){
                return obm.readValue(res.getObject(), Integer.class);
            }else{
                throw new IOException(res.getObject());
            }
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public synchronized User get(String id) {
        Map<String,String> m = new HashMap();
        m.put("id",id);
        try {
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.Get,
                    this.getClass(),
                    obm.writeValueAsString(m)
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            
            
            Responce res = obm.readValue(in.readUTF(), Responce.class);
            
            if(res.getStatusCode() == 200){
                if(res.getObject().equals("null")){
                    return null;
                }
            
                return obm.readValue(res.getObject(), User.class);
            }else{
                throw new IOException(res.getObject());
            }
            
            
            
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public synchronized ArrayList<User> getAll() {
         Map<String,String> m = new HashMap();
        try {
            final ObjectMapper mapper = new ObjectMapper();
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.GetAll,
                    this.getClass(),
                    ""
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, User.class);
            String j = in.readUTF();
            System.out.println(j);
            
            Responce res = obm.readValue(j, Responce.class);
            
            if(res.getStatusCode() == 200){
                return obm.readValue(res.getObject(), typeReference);
            }else{
                throw new IOException(res.getObject());
            }
            
         } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
   
}


