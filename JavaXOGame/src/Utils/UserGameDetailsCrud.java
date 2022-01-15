/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.Responce;
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

/**
 *
 * @author A H M E D
 */
public 
class UserGameDetailsCrud implements ICrud<UserGameDetails>{
        private final ObjectMapper obm = new ObjectMapper();

DataOutputStream out;
    DataInputStream in;

    public UserGameDetailsCrud(DataInputStream in,DataOutputStream out) {
        this.out = out;
        this.in = in;
    }
    @Override
    public int add(UserGameDetails entity) throws JsonProcessingException, IOException{
//      System.out.println(entity);
       
            JsonAction jsonAction = new JsonAction(
                    entity.toJson(),
                    JsonAction.Types.Add,
                    ""
            );
//            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            Responce res = obm.readValue(in.readUTF(), Responce.class);
//            System.out.println(res);

            if(res.getStatusCode() == Responce.responceCodes.Done){
                return obm.readValue(res.getObject(), Integer.class);
            }else{
                throw new IOException(res.getObject());
            }
        
    }

    @Override
    public int update(String id, UserGameDetails entity)throws JsonProcessingException, IOException {
         System.out.print(entity);
        Map<String,String> m = new HashMap();
        m.put("id",id);
      
            JsonAction jsonAction = new JsonAction(
                    entity.toJson(),
                    JsonAction.Types.Update,
                    
                    obm.writeValueAsString(m)
            );
//            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            Responce res = obm.readValue(in.readUTF(), Responce.class);
//                        System.out.println(res);

            if(res.getStatusCode() == Responce.responceCodes.Done){
                return obm.readValue(res.getObject(), Integer.class);
            }else{
                throw new IOException(res.getObject());
            }
      
    }

    @Override
    public int delete(String id) throws JsonProcessingException, IOException{
    Map<String,String> m = new HashMap();
        m.put("id",id);
       
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.Delete,
                    
                    obm.writeValueAsString(m)
            );
//            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            Responce res = obm.readValue(in.readUTF(), Responce.class);
//                        System.out.println(res);

            if(res.getStatusCode() == Responce.responceCodes.Done){
                return obm.readValue(res.getObject(), Integer.class);
            }else{
                throw new IOException(res.getObject());
            }
     
    }

    @Override
    public UserGameDetails get(String id)throws JsonProcessingException, IOException {
        Map<String,String> m = new HashMap();
        m.put("id",id);
        
        JsonAction jsonAction = new JsonAction(
                "",
                JsonAction.Types.Get,
               
                obm.writeValueAsString(m)
        );
//        System.out.println(obm.writeValueAsString(jsonAction));
        out.writeUTF(obm.writeValueAsString(jsonAction));
        Responce res = obm.readValue(in.readUTF(), Responce.class);
//                    System.out.println(res);

        if(res.getStatusCode() == Responce.responceCodes.Done){
            if(!res.getObject().equals("null"))
                return obm.readValue(res.getObject(), UserGameDetails.class);
            else
                return null;
        }else{
            throw new IOException(res.getObject());
        }
    }

    @Override
    public ArrayList<UserGameDetails> getAll()throws JsonProcessingException, IOException  {
      Map<String,String> m = new HashMap();
            final ObjectMapper mapper = new ObjectMapper();
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.GetAll,
                   
                    ""
            );
//            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, UserGameDetails.class);
            
            Responce res = obm.readValue(in.readUTF(), Responce.class);
//                        System.out.println(res);
                        
//            System.out.println(res);

            if(res.getStatusCode() == Responce.responceCodes.Done){
                return obm.readValue(res.getObject(), typeReference);
            }else{
                throw new IOException(res.getObject());
            }
            
      
    }
    
    public ArrayList<UserGameDetails> getAllWithId(String id)throws JsonProcessingException, IOException {
       Map<String,String> m = new HashMap();
        m.put("id",id);
            final ObjectMapper mapper = new ObjectMapper();
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.GetAllWithId,
                    
                    obm.writeValueAsString(m)
            );
//            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, UserGameDetails.class);
            
            Responce res = obm.readValue(in.readUTF(), Responce.class);
//            System.out.println(res);
            
            if(res.getStatusCode() == Responce.responceCodes.Done){
                return obm.readValue(res.getObject(), typeReference);
            }else{
                throw new IOException(res.getObject());
            }
            
    }
    
    public ArrayList<UserGameDetails> getAllWithUserName(String userName) throws JsonProcessingException, IOException {
       Map<String,String> m = new HashMap();
        m.put("userName",userName);
            final ObjectMapper mapper = new ObjectMapper();
            JsonAction jsonAction = new JsonAction(
                    "",
                    JsonAction.Types.GetAllWithUesrName,
                    
                    obm.writeValueAsString(m)
            );
//            System.out.println(obm.writeValueAsString(jsonAction));
            out.writeUTF(obm.writeValueAsString(jsonAction));
            CollectionType typeReference = TypeFactory.defaultInstance().constructCollectionType(List.class, UserGameDetails.class);
            Responce res = obm.readValue(in.readUTF(), Responce.class);
//                        System.out.println(res);

            if(res.getStatusCode() == Responce.responceCodes.Done){
                return obm.readValue(res.getObject(), typeReference);
            }else{
                throw new IOException(res.getObject());
            }
        
    }
   

}
