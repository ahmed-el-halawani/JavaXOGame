/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author A H M E D
 */

public class JsonAction{
    
    public static enum Types{
        Add,Get,GetAll,Update,Delete
    }

    public static JsonAction fromJson(String body) throws  JsonProcessingException{
           ObjectMapper obm = new ObjectMapper();
            return obm.readValue(body, JsonAction.class);
    }
    
    
    private String object;
    private Types type;
    private Class ct;
    private String params;

    public JsonAction(String object, Types type, Class ct,String params) {
        this.object = object;
        this.type = type;
        this.ct = ct;
        this.params = params;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
     
    
     
    
    public JsonAction(JsonAction j) {
        this.type = j.type;
    }

    @Override
    public String toString() {
        return "JsonAction{" + "object=" + object + ", type=" + type + ", ct=" + ct + '}';
    }

    public Class getCt() {
        return ct;
    }

    public void setCt(Class ct) {
        this.ct = ct;
    }
    
    

  
    

    public JsonAction() {
    }

    public String getObject() throws JsonProcessingException {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

   


    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    

}