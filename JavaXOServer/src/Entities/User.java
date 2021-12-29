/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A H M E D
 */

public class User extends BaseEntity{
    public static  enum UserType{
        Cpu,Account,Player
    }
    public static User fromJson(String body) throws  JsonProcessingException{
           ObjectMapper obm = new ObjectMapper();
            return obm.readValue(body, User.class);
        }
    

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toJson() throws JsonProcessingException {
        ObjectMapper obm = new ObjectMapper();
        
        return obm.writeValueAsString(this);
    }
    
    
    
   
    public User(String name, String userName, String password) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.userType = UserType.Account;
    }
    
    public User(String name) {
        this.name = name;
        this.userName = "";
        this.password = "";
        this.userType = UserType.Player;
    }
    
    public User() {
        this.name = "CPU";
        this.userName = "";
        this.password = "";
        this.userType = UserType.Cpu;
    }
    
    private User(String id,String name, String userName, String password,UserType userType) {
        super(id);
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", userName=" + userName + ", password=" + password + ", userType=" + userType + '}';
    }
    
    
    
    
    private String name;
    private String userName;
    private String password;
    private UserType userType;
    
}
