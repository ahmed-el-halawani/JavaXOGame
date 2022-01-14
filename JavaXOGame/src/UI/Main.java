/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Testing.MainTest;
import UI.Authentication.Login;
import Utils.AppManager;
import Utils.ConnectionManager;
import java.awt.EventQueue;
import java.awt.Point;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author A H M E D
 */

public final class Main extends JFrame {
    

    public Main() {
        initComponents();
        this.setVisible(true);
        setLocationRelativeTo(null);
        new Thread(()->{
            try {
                cm = ConnectionManager.getInstance();
                appManager = AppManager.getInstance();
                EventQueue.invokeLater(()->{
                    this.setVisible(false);
                    new Login().setVisible(true);
                });
            } catch (IOException ex) {
                EventQueue.invokeLater(()->{
                    loading.setVisible(false);
                    JOptionPane.showMessageDialog(this, ex.getMessage()+"\n press ok to quit", "Server Error", JOptionPane.OK_OPTION);
                    System.exit(0);
                });
               
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        loading = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(900, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        loading.setMaximumSize(new java.awt.Dimension(900, 600));
        loading.setMinimumSize(new java.awt.Dimension(900, 600));
        loading.setPreferredSize(new java.awt.Dimension(900, 600));
        loading.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Stencil Std", 0, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LOADING...");
        loading.add(jLabel3);
        jLabel3.setBounds(250, 210, 430, 210);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/homee_ccexpress.jpeg"))); // NOI18N
        loading.add(jLabel2);
        jLabel2.setBounds(0, 0, 900, 600);

        getContentPane().add(loading);
        loading.setBounds(0, 0, 900, 600);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/error_1.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 900, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
        
    }

    @Override
    public void disable() {
        super.disable(); //To change body of generated methods, choose Tools | Templates.
        cm.dispose();
    }
    
    
    private ConnectionManager cm;
    private AppManager appManager;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel loading;
    // End of variables declaration//GEN-END:variables
}
