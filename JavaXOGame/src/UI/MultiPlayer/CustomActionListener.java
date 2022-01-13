/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.MultiPlayer;

import Entities.UserGameDetails;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A H M E D
 */

  

// Someone who says "Hello"
public class CustomActionListener {
   
 
    private List<Runnable> listeners = new ArrayList<Runnable>();

  
    public void addListener(Runnable runner) {
        listeners.add(runner);
    }
  
     
    public void sayHello() {
        for (Runnable hl : listeners)
            hl.run();
    }
  
}