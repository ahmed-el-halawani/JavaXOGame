/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimax;

import Entities.UserGameDetails;
import UI.MultiPlayer.GameBordPanel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;

/**
 *
 * @author A H M E D
 */
public class NewJFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    boolean isMyTurn;
    ArrayList<JButton> current;
    Game game;
    boolean isGameEnd;
    GameBordPanel gameBordPanel;
    public NewJFrame() {
        initComponents();
        gameBordPanel = new GameBordPanel();
        jPanel1.add(gameBordPanel);
        current = new ArrayList(Arrays.asList(gameBordPanel.buttons));
        
        current.forEach((b)->b.addActionListener((e) -> {
            btn6ActionPerformed(e);
        }));
        
        game = new Game(Game.EASY);
        isMyTurn = true;
        isGameEnd = false;
    }
    
    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {                                     
        if(!isMyTurn || ((JButton)evt.getSource()).getText()!="" || isGameEnd)
            return;
        
        
        gameBordPanel.setSimbole(((JButton)evt.getSource()),UserGameDetails.PlayerSimbole.X);
        isMyTurn = false;
        
        if(game.winner(current, Game.X))
        {
            System.out.println("win");
            isGameEnd = true;
            return;
        }
        
        gameBordPanel.setSimbole(game.update(current),UserGameDetails.PlayerSimbole.O);
        isMyTurn = true;
    }                                    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(200, 200));
        jPanel1.setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
