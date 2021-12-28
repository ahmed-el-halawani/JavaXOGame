/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A H M E D
 */

public class User {
    public static  enum UserType{
        Cpu,Account,Player
    }
    
    public static User fromJson(JSONObject json) throws JSONException{
        return 
            new User(
                json.getString("name"),
                json.getString("userName"),
                json.getString("password"),
                UserType.valueOf(
                    json.getString("userType")
                )
            );
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
    
    public String toJson() throws JSONException{
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", name);
        jsonObj.put("userName", userName);
        jsonObj.put("password", password);
        jsonObj.put("userType", userType.name());
        
        return jsonObj.toString();
    }
    
    @Override
    public String toString(){
        return "name: "+name+"\npassword: "+password+"\nusername: "+userName+"\nuserType: "+userType.name();
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
    
    private User(String name, String userName, String password,UserType userType) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }
    
    private String name;
    private String userName;
    private String password;
    private UserType userType;
    
}
