/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Entities.*;
import Entities.Responce.responceCodes;
import Utils.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.awt.EventQueue;
import java.util.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ListDataListener;

/**
 *
 * @author A H M E D
 */
public final class MainTest extends javax.swing.JFrame {
    
    
    ConnectionManager cm;
    
    UserCrud userCrud;
    UserGameDetailsCrud userGameDetailsCrud;
    AppManager appManager;
    
    
    
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

// Someone interested in "Hello" events
class Responder implements HelloListener {
    @Override
    public void someoneSaidHello() {
        System.out.println("Hello there...");
    }
}

    public MainTest() {
       
        initComponents();
        
        setTitle("Main Test");
        try {
            cm = ConnectionManager.getInstance();
            userGameDetailsCrud = new UserGameDetailsCrud(cm.in,cm.out);
            userCrud = new UserCrud(cm.in,cm.out);
             appManager = AppManager.getinstance();
            
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
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
        findGame = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        code = new javax.swing.JTextField();
        IAmReady = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setMinimumSize(new java.awt.Dimension(200, 200));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 600));

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

        findGame.setText("findGameWithCode");
        findGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findGameActionPerformed(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridLayout(3, 3));

        b1.setFont(new java.awt.Font("Tekton Pro Cond", 0, 120)); // NOI18N
        b1.setName("1"); // NOI18N
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel2.add(b1);
        b1.getAccessibleContext().setAccessibleName("b1");

        b2.setFont(new java.awt.Font("Tekton Pro Cond", 0, 120)); // NOI18N
        b2.setName("2"); // NOI18N
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel2.add(b2);
        b2.getAccessibleContext().setAccessibleName("b2");

        b3.setFont(new java.awt.Font("Tekton Pro Cond", 0, 120)); // NOI18N
        b3.setName("3"); // NOI18N
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel2.add(b3);

        b4.setFont(new java.awt.Font("Tekton Pro Cond", 0, 120)); // NOI18N
        b4.setName("4"); // NOI18N
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel2.add(b4);

        b5.setFont(new java.awt.Font("Tekton Pro Cond", 0, 120)); // NOI18N
        b5.setName("5"); // NOI18N
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel2.add(b5);

        b6.setFont(new java.awt.Font("Tekton Pro Cond", 0, 120)); // NOI18N
        b6.setName("6"); // NOI18N
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel2.add(b6);

        b7.setFont(new java.awt.Font("Tekton Pro Cond", 0, 120)); // NOI18N
        b7.setName("7"); // NOI18N
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel2.add(b7);

        b8.setFont(new java.awt.Font("Tekton Pro Cond", 0, 120)); // NOI18N
        b8.setName("8"); // NOI18N
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel2.add(b8);

        b9.setFont(new java.awt.Font("Tekton Pro Cond", 0, 120)); // NOI18N
        b9.setName("9"); // NOI18N
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel2.add(b9);

        code.setText("Code");

        IAmReady.setText("Ready");
        IAmReady.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IAmReadyActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton3);
        jRadioButton3.setText("user 1");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup3.add(jRadioButton4);
        jRadioButton4.setText("user 3");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(findGame, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(getAllUsers1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(getAllUsers, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                                            .addComponent(code))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(createRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(IAmReady, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jRadioButton3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton4)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(getAllUsers1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(getAllUsers)
                        .addComponent(createRoom)
                        .addComponent(IAmReady)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(findGame)
                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addContainerGap(14, Short.MAX_VALUE))
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

    User u = null;
    private void createRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createRoomActionPerformed
        try {
            ConnectionManager cm4 = ConnectionManager.createGameSocet();
            GameRoomCrud gamebord = new GameRoomCrud(cm4.in,cm4.out,cm4);
            gamebord.createGameRoom(appManager.getUser());
            JFrame game = new WaitingScrean(gamebord);
            game.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createRoomActionPerformed
//
//    public void setMovieLisener(){
//        gamebord.setMoveListenr(
//            (String object) -> {
//                
//                EventQueue.invokeLater(()->{
//                    messages.setText(
//                        gamebord.gameRoom.currentPosition.toString()+
//                        "::"+
//                        gamebord.gameRoom.currentTurn.getPlayerSimbole().name()
//                    );
//                    gamebord.gameRoom.getGameBord().forEach((t, u) -> {
//                        buttons[t-1].setText(u.name().toString());
//                    });
//                });
//            },
//                (String object) -> {
//                EventQueue.invokeLater(()->{
//                    messages.setText(gamebord.gameRoom.getPlayerStateWithId(u.getId()).name());
//                    gamebord.gameRoom.getGameBord().forEach((t, u) -> {
//                        buttons[t-1].setText(u.name().toString());
//                    });
//                });
//            },
//                (String object) -> {
//                EventQueue.invokeLater(()->{
//                    messages.setText("Draw");
//                    gamebord.gameRoom.getGameBord().forEach((t, u) -> {
//                        buttons[t-1].setText(u.name());
//                    });
//                });
//            },
//            (String object) -> {
//            EventQueue.invokeLater(()->{
//                messages.setText(object);
//            });
//            }
//        );
//    }
//    
//      public GameRoomCrud.ListenersX listener;
//    
//    public void setStartGame(){
//        if(listener !=null) return;
//        listener = new GameRoomCrud.ListenersX(
//                    new GameRoomCrud.NotifierObject[]
//                    {
//                        new GameRoomCrud.NotifierObject(
//                            (String object) -> {
//                                try {
//                                 gamebord.gameRoom = gamebord.obm.readValue(object, GameRoom.class);
//                                 
//                                 EventQueue.invokeLater(()->{
//                                    messages.setText("starting game "+gamebord.gameRoom.code);
//                                });
//                                } catch (JsonProcessingException ex) {
//                                 Logger.getLogger(GameRoomCrud.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            },
//                            responceCodes.startGame
//                        ),
//                        new GameRoomCrud.NotifierObject(
//                            (String object) -> {
//                                 EventQueue.invokeLater(()->{
//                                    messages.setText("starting game");
//                                });
//                            },
//                            responceCodes.startGameError
//                        )
//                    },
//                false
//                );
//        
//        gamebord.setListener(listener);
//        setMovieLisener();
//    }
//    
    private void findGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findGameActionPerformed
         try {
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
                                messages.setText(object);
                            });
                        },
                        responceCodes.findGameWithCodeError
                    ),
                        
                        new GameRoomCrud.NotifierObject(
                        (String object) -> {
                            initiater.sayHello();
                        },
                        responceCodes.findGameWithCode
                    ),
                    },
                false
                ));
             
            
            
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_findGameActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
//         try {
//             if(!gamebord.isReadyToPlay()) return;
//             if(gamebord.gameRoom._getGameSate()==GameRoom.GameState.draw||gamebord.gameRoom._getGameSate()==GameRoom.GameState.winner) return;
//             if(u.getId().equals(gamebord.gameRoom.currentTurn.getPlayer().getId()))
//                gamebord.setMove(
//                    Integer.valueOf(((JButton)evt.getSource()).getName())
//                );
//        } catch (IOException ex) {
//            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_b2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        appManager.setUser(new User(
                        "12475136-e31f-4ace-9a31-a8f2f445653f",
                        "ahmed34",
                        "ahmedGomaa24334",
                        "123123123",
                        User.UserType.Account
                    )
                );
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        appManager.setUser(new User(
                    "2033e557-fb4d-4297-a48b-0a971140593e",
                    "ahmed34",
                    "ahmedGomaa244",
                    "123123123",
                    User.UserType.Account
                ));
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void IAmReadyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IAmReadyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IAmReadyActionPerformed

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
    private javax.swing.JButton IAmReady;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JTextField code;
    private javax.swing.JButton createRoom;
    private javax.swing.JButton findGame;
    private javax.swing.JButton getAllUsers;
    private javax.swing.JButton getAllUsers1;
    private javax.swing.JButton getUser1;
    private javax.swing.JButton getUser2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea messages;
    private javax.swing.JRadioButton userWithId;
    private javax.swing.JRadioButton userWithName;
    private javax.swing.JTextField withUserId;
    private javax.swing.JTextField withUserId1;
    // End of variables declaration//GEN-END:variables
}
