/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author A H M E D
 */
public class Player {
    public Socket s;
    public DataInputStream in;
    public DataOutputStream out;
    public User user;
    public Player(User user,Socket s) throws IOException{
        this.user = user;
        this.s = s;
        this.in = new DataInputStream(s.getInputStream());
        this.out = new DataOutputStream(s.getOutputStream());
    }
}
