/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.UserGameDetails;
import Interfaces.ICrud;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A H M E D
 */
public 
class UserGameDetailsCrud implements ICrud<Entities.UserGameDetails>{
        private final ObjectMapper obm = new ObjectMapper();

DataOutputStream out;
    DataInputStream in;

    public UserGameDetailsCrud(DataInputStream in,DataOutputStream out) {
        this.out = out;
        this.in = in;
    }
    @Override
    public int add(UserGameDetails entity) {
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
            return in.readInt();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int update(String id, UserGameDetails entity) {
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
            return in.readInt();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public int delete(String id) {
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
            return in.readInt();
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public UserGameDetails get(String id) {
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
            return obm.readValue(in.readUTF(), UserGameDetails.class);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<UserGameDetails> getAll() {
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
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, UserGameDetails.class);
            String j = in.readUTF();
            return obm.readValue(j, typeReference);
         } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<UserGameDetails> getAllWithId(String id) {
       Map<String,String> m = new HashMap();
        m.put("id",id);
        try {
            final ObjectMapper mapper = new ObjectMapper();
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.GetAllWithId,
                    this.getClass(),
                    obm.writeValueAsString(m)
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, UserGameDetails.class);
            String j = in.readUTF();
            return obm.readValue(j, typeReference);
         } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<UserGameDetails> getAllWithUserName(String userName) {
       Map<String,String> m = new HashMap();
        m.put("userName",userName);
        try {
            final ObjectMapper mapper = new ObjectMapper();
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.GetAllWithUesrName,
                    this.getClass(),
                    obm.writeValueAsString(m)
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, UserGameDetails.class);
            String j = in.readUTF();
            return obm.readValue(j, typeReference);
         } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
   

}
