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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        getAllUsers = new javax.swing.JButton();
        getAllUsers1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        getUser1 = new javax.swing.JButton();
        withUserId = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messages = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        getUser2 = new javax.swing.JButton();
        withUserId1 = new javax.swing.JTextField();
        userWithId = new javax.swing.JRadioButton();
        userWithName = new javax.swing.JRadioButton();
        createRoom = new javax.swing.JButton();
        setMove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));
        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 150));

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

        jPanel6.setPreferredSize(new java.awt.Dimension(258, 30));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        getUser2.setText("get all UserGamesData");
        getUser2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getUser2ActionPerformed(evt);
            }
        });
        jPanel6.add(getUser2);
        jPanel6.add(withUserId1);

        buttonGroup2.add(userWithId);
        userWithId.setMnemonic('1');
        userWithId.setSelected(true);
        userWithId.setText("user with id");
        jPanel6.add(userWithId);

        buttonGroup2.add(userWithName);
        userWithName.setMnemonic('2');
        userWithName.setText("user with name");
        userWithName.setToolTipText("");
        jPanel6.add(userWithName);

        createRoom.setText("createRoom");
        createRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRoomActionPerformed(evt);
            }
        });

        setMove.setText("setMove");
        setMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setMoveActionPerformed(evt);
            }
        });

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(setMove, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(getAllUsers1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(getAllUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createRoom)
                        .addComponent(setMove)))
                .addContainerGap())
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void getUser2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getUser2ActionPerformed
        User user = null;
        
        if(buttonGroup2.getSelection().getMnemonic()==userWithId.getMnemonic()){
            user = userCrud.get(withUserId1.getText());
        }else{
            user = userCrud.getWithUserName(withUserId1.getText());
        }
        System.out.println(user);
        if(user!=null)
            messages.setText(user.toString());
        else
            messages.setText("no user with this id or user name");
        
    }//GEN-LAST:event_getUser2ActionPerformed

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

    private void getAllUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getAllUsersActionPerformed
        ArrayList<User> users = userCrud.getAll();
        System.out.println(users);

        messages.setText(users.toString());
    }//GEN-LAST:event_getAllUsersActionPerformed

    private void createRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createRoomActionPerformed
        try {
            GameRoomCrud gamebord = new GameRoomCrud(cm.in,cm.out);
           gamebord .createGameRoom(
                new PlayerDetails(
                    new User(
                            "123123123",
                            "ahmed",
                            "ahmedgomaa",
                            "ahmed123"
                    ), 
                    UserGameDetails.PlayerSimbole.X
                )
            );
            messages.setText(
                gamebord.getCode()
            );
            
            
            gamebord.setMove(2);
            
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createRoomActionPerformed

    private void setMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setMoveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_setMoveActionPerformed

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
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton createRoom;
    private javax.swing.JButton getAllUsers;
    private javax.swing.JButton getAllUsers1;
    private javax.swing.JButton getUser1;
    private javax.swing.JButton getUser2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messages;
    private javax.swing.JButton setMove;
    private javax.swing.JRadioButton userWithId;
    private javax.swing.JRadioButton userWithName;
    private javax.swing.JTextField withUserId;
    private javax.swing.JTextField withUserId1;
    // End of variables declaration//GEN-END:variables
}
