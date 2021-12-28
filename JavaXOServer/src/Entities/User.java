/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import org.json.JSONObject;

/**
 *
 * @author A H M E D
 */

public class User {
    public static  enum UserType{
        Cpu,Account,Player
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
    
    public String toJson(){
        return "";
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
    
    private String name;
    private String userName;
    private String password;
    private UserType userType;
    
}
