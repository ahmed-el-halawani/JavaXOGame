/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.RecordGame;

import Entities.UserGameDetails;
import Entities.UserGameDetails.PlayerSimbole;
import UI.MultiPlayer.GameBordPanel;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A H M E D
 */
public class Replay extends javax.swing.JFrame {

    /**
     * Creates new form Replay
     */
    
    GameBordPanel gamePanel;
    UserGameDetails userGameDetails;
    public Replay(UserGameDetails userGameDetails) {
        initComponents();
        
        this.userGameDetails = userGameDetails;
        gamePanel = new GameBordPanel();
        add(gamePanel);
        
        gamePanel.playerOne.setText(userGameDetails.playerOneDetails.getPlayer().getName());
        gamePanel.playerTwo.setText(userGameDetails.playerTwoDetails.getPlayer().getName());
        
    }
    
    public void start(){
        new Thread(()->{
            for (Map.Entry<Integer, PlayerSimbole> entry : userGameDetails.gameBord.entrySet()) {
                Integer key = entry.getKey();
                PlayerSimbole value = entry.getValue();
                try {
                    gamePanel.setTurn(value);
                    Thread.sleep(1500);
                    gamePanel.setSimbole(gamePanel.buttons[key-1], value);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Replay.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));
        setResizable(false);

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
