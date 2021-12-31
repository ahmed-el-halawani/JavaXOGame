/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Entities.*;
import Utils.*;
import java.util.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A H M E D
 */
public final class MainTest extends javax.swing.JFrame {
    
    
    ConnectionManager cm;
    UserCrud userCrud;
    UserGameDetailsCrud userGameDetailsCrud;
    public MainTest() {
        initComponents();
        setTitle("Main Test");
        try {
            cm = ConnectionManager.getInstance();
            userGameDetailsCrud = new UserGameDetailsCrud(cm.in,cm.out);
            userCrud = new UserCrud(cm.in,cm.out);
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        getUser = new javax.swing.JButton();
        id = new javax.swing.JTextField();
        getAllUsers = new javax.swing.JButton();
        getAllUsers1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        getUser1 = new javax.swing.JButton();
        withUserId = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messages = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 500));

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 150));

        jPanel2.setPreferredSize(new java.awt.Dimension(258, 30));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        getUser.setText("get user");
        getUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getUserActionPerformed(evt);
            }
        });
        jPanel2.add(getUser);
        jPanel2.add(id);

        getAllUsers.setText("getAllUsers");
        getAllUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getAllUsersActionPerformed(evt);
            }
        });

        getAllUsers1.setText("get All UserGamesData");
        getAllUsers1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getAllUsers1ActionPerformed(evt);
            }
        });

        jPanel5.setPreferredSize(new java.awt.Dimension(258, 30));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        getUser1.setText("get all UserGamesData");
        getUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getUser1ActionPerformed(evt);
            }
        });
        jPanel5.add(getUser1);
        jPanel5.add(withUserId);

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setMnemonic('1');
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("with user id");
        jPanel5.add(jRadioButton1);

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setMnemonic('2');
        jRadioButton2.setText("with user name");
        jRadioButton2.setToolTipText("");
        jPanel5.add(jRadioButton2);

        messages.setColumns(20);
        messages.setRows(5);
        jScrollPane1.setViewportView(messages);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(getAllUsers1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getAllUsers, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(getAllUsers1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getAllUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getAllUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getAllUsersActionPerformed
        ArrayList<User> users = userCrud.getAll();
        System.out.println(users);
        
        messages.setText(users.toString());
    }//GEN-LAST:event_getAllUsersActionPerformed

    private void getUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getUserActionPerformed
        User user =userCrud.get(id.getText());
        System.out.println(user);
        messages.setText(user.toString());
    }//GEN-LAST:event_getUserActionPerformed

    private void getAllUsers1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getAllUsers1ActionPerformed
                try {
        ArrayList<UserGameDetails> userGameDetails;
            userGameDetails = userGameDetailsCrud.getAll();
        
        System.out.println(userGameDetails);
        messages.setText(userGameDetails.toString());
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_getAllUsers1ActionPerformed

    private void getUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getUser1ActionPerformed
        try {
            System.out.println(buttonGroup1.getSelection().getMnemonic());
            
                        
            ArrayList<UserGameDetails> userGameDetails;
            if(buttonGroup1.getSelection().getMnemonic()==jRadioButton1.getMnemonic()){
                userGameDetails = userGameDetailsCrud.getAllWithId(withUserId.getText().trim());
            }else{
                userGameDetails = userGameDetailsCrud.getAllWithUserName(withUserId.getText());
            }
        
            System.out.println(userGameDetails);
            messages.setText(userGameDetails.toString());
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_getUser1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MainTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainTest.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainTest().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton getAllUsers;
    private javax.swing.JButton getAllUsers1;
    private javax.swing.JButton getUser;
    private javax.swing.JButton getUser1;
    private javax.swing.JTextField id;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messages;
    private javax.swing.JTextField withUserId;
    // End of variables declaration//GEN-END:variables
}
