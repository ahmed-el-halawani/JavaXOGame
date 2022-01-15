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
    
    public static AppManager getInstance(){
        if(appManager ==null )
        {
            appManager = new AppManager();
//            appManager.setUser(new User(
//                    "2033e557-fb4d-4297-a48b-0a971140593e",
//                    "ahmed34",
//                    "ahmedGomaa244",
//                    "123123123",
//                    User.UserType.Account
//                ));
        }
            
                
        return appManager;
    }
    
    private AppManager(){}

    @Override
    public String toString() {
        return "AppManager{" + "user=" + user + '}';
    }
    
    
    
    private static AppManager appManager;
    private User user;

}
