/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.MultiPlayer;

import Testing.*;
import Entities.GameRoom;
import Utils.GameRoomCrud;
import Entities.Responce;
import Entities.Responce.responceCodes;
import static UI.MultiPlayer.GameBordPanel.OSimbole;
import Utils.AppManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author A H M E D
 */
public final class GameRoomGui extends javax.swing.JFrame {

    
    private enum screens{
        waitingWithCode,leaver,game,waiting
    }
    
    JButton[] buttons;
    GameRoomCrud gamebord;
    AppManager appManager;
    GameRoomCrud.ListenersX listener;
    CardLayout cardLayout;
    GameBordPanel gamePanel;
    boolean isPlayerLeave = false;
    public GameRoomGui(GameRoomCrud gamebord) {
        initComponents();

        this.appManager = AppManager.getinstance();
        this.gamebord = gamebord;
        gamePanel = new GameBordPanel();
        
        game.add(gamePanel);
        buttons = gamePanel.buttons;
        
        Arrays.asList(buttons).forEach(this::buttonAction);
        
        setTitle("Game Bord Test");
        
        initListener();
        cardLayout = (CardLayout)cards.getLayout();
        cardLayout.show(cards, screens.waiting.name());
    }
    
    
    private void buttonAction(JButton button) { 
        button.addActionListener((evt) -> {
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
        });
        
    }  

      
    public void setMovieLisener(){
        gamebord.setMoveListenr(
            (String object) -> {
                EventQueue.invokeLater(()->{
                    gamePanel.TurnText.setText(
                        gamebord.gameRoom.currentTurn.getPlayer().getName()
                    );
                    gamePanel.setTurn(gamebord.gameRoom.currentTurn.getPlayerSimbole());
                    gamebord.gameRoom.getGameBord().forEach((t, u) -> {
                        gamePanel.setSimbole(buttons[t-1],u);
                    });
                });
            },
            (String object) -> {
                EventQueue.invokeLater(()->{
                    gamebord.gameRoom.getGameBord().forEach((t, u) -> {
                         gamePanel.setSimbole(buttons[t-1],u);
                    });
                    gamePanel.TurnText.setText(
                        gamebord.gameRoom.getPlayerStateWithId(appManager.getUser().getId()).name()
                    );
                    gameEnded();
                });
            },
            (String object) -> {
                EventQueue.invokeLater(()->{
                    gamebord.gameRoom.getGameBord().forEach((t, u) -> {
                         gamePanel.setSimbole(buttons[t-1],u);
                    });
                    gamePanel.TurnText.setText(
                        "Draw"
                    );
                    gameEnded();
                   
                    
                });
            },
            (String object) -> {
            EventQueue.invokeLater(()->{
                gamePanel.TurnText.setText(
                    object
                );
            });
            }
        );
    }
    
    
    public void gameEnded(){
        System.out.println("gameEnded gameEnded");
        recordGame();
        playAgain();
    }
    
    public void recordGame(){
         int answer = JOptionPane.showConfirmDialog(this, "do u want record game", "recording ", JOptionPane.YES_NO_OPTION);
                    System.out.println("answer: "+answer);
                    if(answer==0)
                        try {
                            gamebord.recordGame(appManager.getUser());
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(this, "some thing wrong happend cant record game right now", "recording Error", JOptionPane.WARNING_MESSAGE);
                            Logger.getLogger(GameRoomCrud.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    
    public void playAgain(){
        if(isPlayerLeave)
            return;
        int answer = JOptionPane.showConfirmDialog(this, "do u want play Again", "playAgain ", JOptionPane.YES_NO_OPTION);
            System.out.println("answer: "+answer);
            if(answer==0)
                try {
                    gamebord.playAgain(appManager.getUser());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "some thing wrong happend cant record game right now", "recording Error", JOptionPane.WARNING_MESSAGE);
                    Logger.getLogger(GameRoomCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public void initListener(){
        if(listener !=null) return;
        listener = new GameRoomCrud.ListenersX(
                    new GameRoomCrud.NotifierObject[]
                    {
                        new GameRoomCrud.NotifierObject(
                            (String object) -> {
                                JOptionPane.showMessageDialog(this, "waiting");
                            },
                            Responce.responceCodes.findGame
                        ),
                        
                        new GameRoomCrud.NotifierObject(
                        (String object) -> {
                            try {
                                gamebord.gameRoom = gamebord.obm.readValue(object, GameRoom.class);
                            } catch (JsonProcessingException ex) {
                                Logger.getLogger(GameRoomGui.class.getName()).log(Level.SEVERE, null, ex);
                            }
                              EventQueue.invokeLater(()->{
                                cardLayout.show(cards, screens.waitingWithCode.name());
                                GameRoomGui.this.code.setText(gamebord.gameRoom.code);
                            });
                        },
                        Responce.responceCodes.createGameRoom
                    ),
                        
                    
                    new GameRoomCrud.NotifierObject(
                        (String object) -> {
                              EventQueue.invokeLater(()->{
//                                messages.setText("create game with code Error "+object);
                                cardLayout.show(cards, screens.waitingWithCode.name());
                                GameRoomGui.this.code.setText(object);
                            });
                        },
                        Responce.responceCodes.createGameRoomError
                    ),
                        new GameRoomCrud.NotifierObject(
                            (String object) -> {
                                try {
                                 gamebord.gameRoom = gamebord.obm.readValue(object, GameRoom.class);
                                 
                                 EventQueue.invokeLater(()->{
                                     isPlayerLeave = false;
//                                    messages.setText("starting game "+gamebord.gameRoom.code);
                                    gamePanel.playerOne.setText(gamebord.gameRoom.getPlayerOneDetails().getPlayer().getName());
                                    gamePanel.playerTwo.setText(gamebord.gameRoom.getPlayerTwoDetails().getPlayer().getName());
                                    gamePanel.TurnText.setText(
                                        gamebord.gameRoom.currentTurn.getPlayer().getName()
                                    );
                                    gamePanel.setTurn(gamebord.gameRoom.currentTurn.getPlayerSimbole());
                                    gamePanel.resetButtons();
                                    
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
//                                    messages.setText("starting game");
                                });
                            },
                            Responce.responceCodes.startGameError
                        ),
                        
                        new GameRoomCrud.NotifierObject(
                        (String object) -> {
                            try {
                                gamebord.gameRoom = gamebord.obm.readValue(object, GameRoom.class);
                            } catch (JsonProcessingException ex) {
                                Logger.getLogger(GameRoomGui.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                             EventQueue.invokeLater(()->{
//                                    messages.setText("starting game "+gamebord.gameRoom.code);
                                    gamePanel.TurnText.setText(
                                        gamebord.gameRoom.currentTurn.getPlayer().getName()
                                    );
                                    gamePanel.setTurn(gamebord.gameRoom.currentTurn.getPlayerSimbole());
                                    gamePanel.resetButtons();
                                    
                                });
                        },
                        Responce.responceCodes.playAgain
                    ),
                        
                         new GameRoomCrud.NotifierObject(
                            (String object) -> {
                                if(appManager.getUser().getId()==object){
                                    System.out.println("waiting other player");
                                }else{
                                    System.out.println("other player want play again");
                                }
                            },
                            Responce.responceCodes.waitingPlayerTwoPlayAgain
                        ),
                        new GameRoomCrud.NotifierObject(
                        (String object) -> {
                            isPlayerLeave = true;
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
        waiting = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(900, 634));
        setMinimumSize(new java.awt.Dimension(900, 634));
        setPreferredSize(new java.awt.Dimension(900, 634));
        setResizable(false);

        cards.setBackground(new java.awt.Color(255, 255, 255));
        cards.setPreferredSize(new java.awt.Dimension(900, 600));
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
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
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
                .addContainerGap(197, Short.MAX_VALUE)
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
                    .addContainerGap(453, Short.MAX_VALUE)))
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
                .addGroup(leaverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leaverLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leaverLayout.createSequentialGroup()
                        .addGap(0, 170, Short.MAX_VALUE)
                        .addGroup(leaverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(leaverLayout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addGap(175, 175, 175))))
        );
        leaverLayout.setVerticalGroup(
            leaverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leaverLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(leaverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        cards.add(leaver, "leaver");

        game.setBackground(new java.awt.Color(255, 255, 255));
        game.setLayout(new java.awt.BorderLayout());
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
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 880, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        waitingLayout.setVerticalGroup(
            waitingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, waitingLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
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
        super.dispose();
        try {
            System.out.println("from dispose");
            gamebord.leaveGame(appManager.getUser());
            gamebord.isListing=false;
             try {
                gamebord.in.close();
            } catch (IOException ex1) {
                Logger.getLogger(GameRoomGui.class.getName()).log(Level.SEVERE, null, ex1);
            }
            try {
                gamebord.out.close();
            } catch (IOException ex1) {
                Logger.getLogger(GameRoomGui.class.getName()).log(Level.SEVERE, null, ex1);
            }
            gamebord.connection.dispose();
            
        } catch (IOException ex) {
            Logger.getLogger(GameRoomGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JPanel leaver;
    private javax.swing.JPanel waiting;
    private javax.swing.JPanel waitingWithCode;
    // End of variables declaration//GEN-END:variables
}
