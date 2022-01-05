/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testing;

import Entities.GameRoom;
import Entities.GameRoomCrud;
import Entities.Responce;
import Entities.Responce.responceCodes;
import Utils.AppManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author A H M E D
 */
public final class WaitingScrean extends javax.swing.JFrame {

    /**
     * Creates new form WaitingScrean
     */
    
    private enum screens{
        waitingWithCode,leaver,game,waiting
    }
    
    JButton[] buttons;
    GameRoomCrud gamebord;
    AppManager appManager;
    GameRoomCrud.ListenersX listener;
    CardLayout cardLayout;
    public WaitingScrean(GameRoomCrud gamebord) {
        this.appManager = AppManager.getinstance();
        this.gamebord = gamebord;
        
        initComponents();
        setTitle("Game Bord Test");
        buttons = new JButton[]{b1,b2,b3,b4,b5,b6,b7,b8,b9};
        initListener();
        cardLayout = (CardLayout)cards.getLayout();
        cardLayout.show(cards, screens.waiting.name());
    }

      
    public void setMovieLisener(){
        gamebord.setMoveListenr(
            (String object) -> {
                EventQueue.invokeLater(()->{
                    messages.setText(
                        gamebord.gameRoom.currentPosition.toString()+
                        "::"+
                        gamebord.gameRoom.currentTurn.getPlayerSimbole().name()+
                        ":: is My Turn :"+
                        gamebord.gameRoom.currentTurn.getPlayer().getId().equals(appManager.getUser().getId())
                    );
                    gamebord.gameRoom.getGameBord().forEach((t, u) -> {
                        buttons[t-1].setText(u.name());
                    });
                });
            },
            (String object) -> {
                EventQueue.invokeLater(()->{
                    messages.setText(gamebord.gameRoom.getPlayerStateWithId(appManager.getUser().getId()).name());
                    gamebord.gameRoom.getGameBord().forEach((t, u) -> {
                        buttons[t-1].setText(u.name());
                    });
                });
            },
            (String object) -> {
                EventQueue.invokeLater(()->{
                    messages.setText("Draw");
                    gamebord.gameRoom.getGameBord().forEach((t, u) -> {
                        buttons[t-1].setText(u.name());
                    });
                });
            },
            (String object) -> {
            EventQueue.invokeLater(()->{
                messages.setText(object);
            });
            }
        );
    }
    
    public void initListener(){
        if(listener !=null) return;
        listener = new GameRoomCrud.ListenersX(
                    new GameRoomCrud.NotifierObject[]
                    {
                        new GameRoomCrud.NotifierObject(
                        (String object) -> {
                             gamebord.code = object;
                              EventQueue.invokeLater(()->{
                                messages.setText("create game with code "+object);
                                cardLayout.show(cards, screens.waitingWithCode.name());
                                WaitingScrean.this.code.setText(object);
                            });
                        },
                        Responce.responceCodes.createGameRoom
                    ),
                        
                    
                    new GameRoomCrud.NotifierObject(
                        (String object) -> {
                              EventQueue.invokeLater(()->{
                                messages.setText("create game with code Error "+object);
                                cardLayout.show(cards, screens.waitingWithCode.name());
                                WaitingScrean.this.code.setText(object);
                            });
                        },
                        Responce.responceCodes.createGameRoomError
                    ),
                        new GameRoomCrud.NotifierObject(
                            (String object) -> {
                                try {
                                 gamebord.gameRoom = gamebord.obm.readValue(object, GameRoom.class);
                                 
                                 EventQueue.invokeLater(()->{
                                    messages.setText("starting game "+gamebord.gameRoom.code);
                                    
                                     for (JButton button : buttons) {
                                         button.setText("");
                                     }
                                    
                                    cardLayout.show(cards, screens.game.name());
                                });
                                } catch (JsonProcessingException ex) {
                                 Logger.getLogger(GameRoomCrud.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            },
                            Responce.responceCodes.startGame
                        ),
                        new GameRoomCrud.NotifierObject(
                            (String object) -> {
                                 EventQueue.invokeLater(()->{
                                    messages.setText("starting game");
                                });
                            },
                            Responce.responceCodes.startGameError
                        ),
                        new GameRoomCrud.NotifierObject(
                        (String object) -> {
                            EventQueue.invokeLater(()->{
                                cardLayout.show(cards, screens.leaver.name());
                            });
                        },
                        responceCodes.LeaveGameRoom
                    ),
                    },
                false
                );
        
        gamebord.setListener(listener);
        setMovieLisener();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cards = new javax.swing.JPanel();
        waitingWithCode = new javax.swing.JPanel();
        code = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        copyCode = new javax.swing.JButton();
        leaver = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        game = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messages = new javax.swing.JTextArea();
        waiting = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        cards.setBackground(new java.awt.Color(255, 255, 255));
        cards.setLayout(new java.awt.CardLayout());

        waitingWithCode.setBackground(new java.awt.Color(255, 255, 255));

        code.setFont(new java.awt.Font("Tekton Pro Ext", 0, 36)); // NOI18N
        code.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        code.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Tekton Pro Ext", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Code");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Testing/waiting.gif"))); // NOI18N

        copyCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        copyCode.setText("Copy");
        copyCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyCodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout waitingWithCodeLayout = new javax.swing.GroupLayout(waitingWithCode);
        waitingWithCode.setLayout(waitingWithCodeLayout);
        waitingWithCodeLayout.setHorizontalGroup(
            waitingWithCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, waitingWithCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(waitingWithCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(waitingWithCodeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(waitingWithCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(waitingWithCodeLayout.createSequentialGroup()
                            .addComponent(copyCode, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(code, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        waitingWithCodeLayout.setVerticalGroup(
            waitingWithCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, waitingWithCodeLayout.createSequentialGroup()
                .addContainerGap(231, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(86, 86, 86))
            .addGroup(waitingWithCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(waitingWithCodeLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(waitingWithCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(code, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                        .addComponent(copyCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap(487, Short.MAX_VALUE)))
        );

        cards.add(waitingWithCode, "waitingWithCode");

        leaver.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tekton Pro Ext", 0, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("your frind leave u alone");

        jButton2.setText("Quit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Testing/leaver.jpg"))); // NOI18N

        jButton3.setText("Back To Code");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leaverLayout = new javax.swing.GroupLayout(leaver);
        leaver.setLayout(leaverLayout);
        leaverLayout.setHorizontalGroup(
            leaverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaverLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leaverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, leaverLayout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leaverLayout.setVerticalGroup(
            leaverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(leaverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cards.add(leaver, "leaver");

        game.setBackground(new java.awt.Color(255, 255, 255));
        game.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new java.awt.GridLayout(3, 3));

        b1.setFont(new java.awt.Font("Adobe Arabic", 0, 120)); // NOI18N
        b1.setName("1"); // NOI18N
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel4.add(b1);

        b2.setFont(new java.awt.Font("Adobe Arabic", 0, 120)); // NOI18N
        b2.setName("2"); // NOI18N
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel4.add(b2);

        b3.setFont(new java.awt.Font("Adobe Arabic", 0, 120)); // NOI18N
        b3.setName("3"); // NOI18N
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel4.add(b3);

        b4.setFont(new java.awt.Font("Adobe Arabic", 0, 120)); // NOI18N
        b4.setName("4"); // NOI18N
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel4.add(b4);

        b5.setFont(new java.awt.Font("Adobe Arabic", 0, 100)); // NOI18N
        b5.setName("5"); // NOI18N
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel4.add(b5);

        b6.setFont(new java.awt.Font("Adobe Arabic", 0, 120)); // NOI18N
        b6.setName("6"); // NOI18N
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel4.add(b6);

        b7.setFont(new java.awt.Font("Adobe Arabic", 0, 120)); // NOI18N
        b7.setName("7"); // NOI18N
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel4.add(b7);

        b8.setFont(new java.awt.Font("Adobe Arabic", 0, 120)); // NOI18N
        b8.setName("8"); // NOI18N
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel4.add(b8);

        b9.setFont(new java.awt.Font("Adobe Arabic", 0, 120)); // NOI18N
        b9.setName("9"); // NOI18N
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel4.add(b9);

        game.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, -2, 560, 440));

        messages.setColumns(20);
        messages.setRows(5);
        jScrollPane1.setViewportView(messages);

        game.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 540, 150));

        cards.add(game, "game");

        waiting.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tekton Pro Ext", 0, 36)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Waiting");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Testing/waiting.gif"))); // NOI18N

        javax.swing.GroupLayout waitingLayout = new javax.swing.GroupLayout(waiting);
        waiting.setLayout(waitingLayout);
        waitingLayout.setHorizontalGroup(
            waitingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(waitingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(waitingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        waitingLayout.setVerticalGroup(
            waitingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, waitingLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(86, 86, 86))
        );

        cards.add(waiting, "waiting");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cards, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        try {
            if(!gamebord.isReadyToPlay()) return;
            if(gamebord.gameRoom._getGameSate()==GameRoom.GameState.draw||gamebord.gameRoom._getGameSate()==GameRoom.GameState.winner) return;

            System.out.println(appManager);
            System.out.println(gamebord.gameRoom.currentTurn.getPlayer().getId());

            if(appManager.getUser().getId().equals(gamebord.gameRoom.currentTurn.getPlayer().getId()))
            gamebord.setMove(
                Integer.valueOf(((JButton)evt.getSource()).getName())
            );
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_b5ActionPerformed

    private void copyCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyCodeActionPerformed
        StringSelection stringSelection = new StringSelection(code.getText());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
    }//GEN-LAST:event_copyCodeActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        code.setText(gamebord.gameRoom.code);
        cardLayout.show(cards, screens.waitingWithCode.name());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    @Override
    public void dispose() {
        try {
            System.out.println("from dispose");
            gamebord.leaveGame(appManager.getUser().getId());
            gamebord.isListing=false;
             try {
                gamebord.in.close();
            } catch (IOException ex1) {
                Logger.getLogger(WaitingScrean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            try {
                gamebord.out.close();
            } catch (IOException ex1) {
                Logger.getLogger(WaitingScrean.class.getName()).log(Level.SEVERE, null, ex1);
            }
            gamebord.connection.dispose();
            
        } catch (IOException ex) {
            Logger.getLogger(WaitingScrean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        super.dispose();
        
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JPanel cards;
    private javax.swing.JLabel code;
    private javax.swing.JButton copyCode;
    private javax.swing.JPanel game;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel leaver;
    private javax.swing.JTextArea messages;
    private javax.swing.JPanel waiting;
    private javax.swing.JPanel waitingWithCode;
    // End of variables declaration//GEN-END:variables
}
