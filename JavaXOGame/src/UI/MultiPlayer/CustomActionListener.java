/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.MultiPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author A H M E D
 */

  

// Someone who says "Hello"
public class CustomActionListener {
    public static interface HelloListener {
        void someoneSaidHello();
    }

    
    private List<HelloListener> listeners = new ArrayList<HelloListener>();

    public void addListener(HelloListener toAdd) {
        listeners.add(toAdd);
    }

     
    public void sayHello() {
        System.out.println("Hello!!");
        for (HelloListener hl : listeners)
            hl.someoneSaidHello();
    }
}