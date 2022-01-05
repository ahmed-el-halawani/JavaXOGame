/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.MultiPlayer;

import Entities.GameRoomCrud;
import Entities.Responce;
import Entities.User;
import Testing.MainTest;
import Testing.WaitingScrean;
import Utils.AppManager;
import Utils.ConnectionManager;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author A H M E D
 */
public class MultiMain extends javax.swing.JFrame {

    /**
     * Creates new form TestStart
     */
    
    
    interface HelloListener {
    void someoneSaidHello();
}

// Someone who says "Hello"
class Initiater {
    private List<HelloListener> listeners = new ArrayList<HelloListener>();

    public void addListener(HelloListener toAdd) {
        listeners.add(toAdd);
    }

     
    public void sayHello() {
        System.out.println("Hello!!");

        // Notify everybody that may be interested.
        for (HelloListener hl : listeners)
            hl.someoneSaidHello();
    }
}

    
    AppManager appManager;
    JFrame backFrame;
    private final String CODE_HINT = "Enter Code";
    public MultiMain(JFrame backFrame) {
        initComponents();
        setLocationRelativeTo(null);
        this.backFrame = backFrame;
        appManager = AppManager.getinstance();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        findGameWithCode1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        createGameRoom = new javax.swing.JButton();
        randomGame = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        code = new javax.swing.JTextField();
        findGameWithCode = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        findGameWithCode1.setBackground(new java.awt.Color(255, 255, 255));
        findGameWithCode1.setFont(new java.awt.Font("Lithos Pro Regular", 1, 24)); // NOI18N
        findGameWithCode1.setForeground(new java.awt.Color(67, 16, 162));
        findGameWithCode1.setText("Back");
        findGameWithCode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findGameWithCode1ActionPerformed(evt);
            }
        });
        jPanel2.add(findGameWithCode1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(78, 27, 171));

        createGameRoom.setBackground(new java.awt.Color(255, 255, 255));
        createGameRoom.setFont(new java.awt.Font("Lithos Pro Regular", 1, 24)); // NOI18N
        createGameRoom.setForeground(new java.awt.Color(67, 16, 162));
        createGameRoom.setText("Create Room");
        createGameRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGameRoomActionPerformed(evt);
            }
        });

        randomGame.setBackground(new java.awt.Color(255, 255, 255));
        randomGame.setFont(new java.awt.Font("Lithos Pro Regular", 1, 24)); // NOI18N
        randomGame.setForeground(new java.awt.Color(67, 16, 162));
        randomGame.setText("Random Game");

        jPanel4.setBackground(new java.awt.Color(78, 27, 171));

        code.setFont(new java.awt.Font("Lithos Pro Regular", 1, 24)); // NOI18N
        code.setForeground(new java.awt.Color(67, 16, 162));
        code.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        code.setText("Enter Code");
        code.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                codeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                codeFocusLost(evt);
            }
        });

        findGameWithCode.setBackground(new java.awt.Color(255, 255, 255));
        findGameWithCode.setFont(new java.awt.Font("Lithos Pro Regular", 1, 24)); // NOI18N
        findGameWithCode.setForeground(new java.awt.Color(67, 16, 162));
        findGameWithCode.setText("Let Me In");
        findGameWithCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findGameWithCodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(findGameWithCode, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(code))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(findGameWithCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 515, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createGameRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(randomGame, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(createGameRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(randomGame, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/MultiPlayer/background.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 897, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createGameRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGameRoomActionPerformed
        try {
            ConnectionManager cm4 = ConnectionManager.createGameSocet();
            GameRoomCrud gamebord = new GameRoomCrud(cm4.in,cm4.out,cm4);
            gamebord.createGameRoom(appManager.getUser());
            JFrame game = new WaitingScrean(gamebord);
            game.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createGameRoomActionPerformed

    private void codeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_codeFocusGained
        if(code.getText().equals(CODE_HINT)){
            code.setText("");
        }
    }//GEN-LAST:event_codeFocusGained

    private void codeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_codeFocusLost
        if(code.getText().equals("")){
            code.setText(CODE_HINT);
        }
    }//GEN-LAST:event_codeFocusLost

    private void findGameWithCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findGameWithCodeActionPerformed
        try {
            appManager.setUser(new User(
                "12475136-e31f-4ace-9a31-a8f2f445653f",
                "ahmed34",
                "ahmedGomaa24334",
                "123123123",
                User.UserType.Account
            )
        );

        ConnectionManager cm4 = ConnectionManager.createGameSocet();
        GameRoomCrud gamebord = new GameRoomCrud(cm4.in,cm4.out,cm4);
        gamebord .findGameRoomWithCode(appManager.getUser(),code.getText());

        Initiater initiater = new Initiater();

        initiater.addListener(()->{
            new WaitingScrean(gamebord).setVisible(true);
        });

        gamebord.setListener(new GameRoomCrud.ListenersX(
            new GameRoomCrud.NotifierObject[]
            {
                new GameRoomCrud.NotifierObject(
                    (String object) -> {
                        EventQueue.invokeLater(()->{
                            JOptionPane.showMessageDialog(this, object, "Finding game", JOptionPane.WARNING_MESSAGE);
                        });
                    },
                    Responce.responceCodes.findGameWithCodeError
                ),

                new GameRoomCrud.NotifierObject(
                    (String object) -> {
                        initiater.sayHello();
                    },
                    Responce.responceCodes.findGameWithCode
                ),
            },
            false
        )
        );

        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_findGameWithCodeActionPerformed

    private void findGameWithCode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findGameWithCode1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_findGameWithCode1ActionPerformed

    public static void main(String args[]) {
    
    }

    @Override
    public void dispose() {
        super.dispose();
        backFrame.setVisible(true);
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField code;
    private javax.swing.JButton createGameRoom;
    private javax.swing.JButton findGameWithCode;
    private javax.swing.JButton findGameWithCode1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton randomGame;
    // End of variables declaration//GEN-END:variables
}
