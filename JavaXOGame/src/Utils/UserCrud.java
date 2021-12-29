/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

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


public class UserCrud implements ICrud<User>{
    private ObjectMapper obm = new ObjectMapper();
    DataOutputStream out;
    DataInputStream in;

    public UserCrud( DataInputStream in,DataOutputStream out) {
        this.out = out;
        this.in = in;
    }
    
    @Override
    public int add(User entity) {
        System.out.println(entity);
        try {
            JsonAction jsonAction = new JsonAction(
                    entity.toJson(),
                    JsonAction.Types.Add,
                    entity.getClass(),
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
    public int update(String id, User entity) {
        System.out.print(entity);
        Map<String,String> m = new HashMap();
        m.put("id",id);
        try {
            JsonAction jsonAction = new JsonAction(
                    entity.toJson(),
                    JsonAction.Types.Update,
                    entity.getClass(),
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
                    User.class,
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
    public User get(String id) {
        Map<String,String> m = new HashMap();
        m.put("id",id);
        try {
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.Get,
                    User.class,
                    obm.writeValueAsString(m)
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            return obm.readValue(in.readUTF(), User.class);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<User> getAll() {
         Map<String,String> m = new HashMap();
        try {
            final ObjectMapper mapper = new ObjectMapper();
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.GetAll,
                    User.class,
                    ""
            );
            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, User.class);
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
