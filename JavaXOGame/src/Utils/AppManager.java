/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.User;

/**
 *
 * @author A H M E D
 */
public class AppManager {

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public static AppManager getinstance(){
        return appManager==null?new AppManager():appManager;
    }
    
    private AppManager(){
        
    }
    
    private static AppManager appManager;
    private User user;

}
