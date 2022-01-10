/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.MultiPlayer;

import Entities.UserGameDetails;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameBordPanel extends JPanel {

    public static final String XSimbole ="/UI/Board/xCol2.png";
    public static final String OSimbole = "/UI/Board/oCol2.png";
    public static final String XWinSimbole = "/UI/Board/xWin.png";
    public static final String OWinSimbole = "/UI/Board/oWin.png";
    public static final String selectedtPlayer = "/UI/Board/playerBSelected.png";
    public static final String Player = "/UI/Board/playerB.png";
    public static final String initButtonBackground = "/UI/Board/background.png";
    
    public void setSimbole(JButton btn,UserGameDetails.PlayerSimbole simbole){
        if(simbole.equals(UserGameDetails.PlayerSimbole.X))
            btn.setIcon(new ImageIcon(getClass().getResource(XSimbole))); 
        else
            btn.setIcon(new ImageIcon(getClass().getResource(OSimbole))); 
    }
    
    public void resetButtons(){
        for (JButton button : buttons) {
            button.setIcon(new ImageIcon(getClass().getResource(initButtonBackground)));
        }
    }
    public void setTurn(UserGameDetails.PlayerSimbole simbole){
        if(simbole.equals(UserGameDetails.PlayerSimbole.X)){
            jLabel3.setIcon(new ImageIcon(getClass().getResource(selectedtPlayer)));
            jLabel4.setIcon(new ImageIcon(getClass().getResource(Player)));
        }else{
            jLabel4.setIcon(new ImageIcon(getClass().getResource(selectedtPlayer)));
            jLabel3.setIcon(new ImageIcon(getClass().getResource(Player)));
        }
    }
    
    public JButton[] buttons;
    
    public GameBordPanel() {
        initComponents();
        
        buttons = new JButton[]{btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9};
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        TurnText = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        playerTwo = new javax.swing.JLabel();
        playerOne = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/background.png"))); // NOI18N
        btn1.setName("1"); // NOI18N
        jPanel1.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 146, 108, 93));

        btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/background.png"))); // NOI18N
        btn2.setName("2"); // NOI18N
        jPanel1.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 146, 110, 93));

        btn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/background.png"))); // NOI18N
        btn3.setBorder(null);
        btn3.setBorderPainted(false);
        btn3.setName("3"); // NOI18N
        jPanel1.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 146, 105, 93));

        btn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/background.png"))); // NOI18N
        btn7.setName("7"); // NOI18N
        jPanel1.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 355, 108, 93));

        btn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/background.png"))); // NOI18N
        btn8.setName("8"); // NOI18N
        jPanel1.add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 355, 110, 93));

        btn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/background.png"))); // NOI18N
        btn9.setName("9"); // NOI18N
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn9btn7ActionPerformed(evt);
            }
        });
        jPanel1.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 355, 105, 93));

        btn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/background.png"))); // NOI18N
        btn4.setName("4"); // NOI18N
        jPanel1.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 250, 108, 93));

        btn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/background.png"))); // NOI18N
        btn5.setBorder(null);
        btn5.setName("5"); // NOI18N
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
        jPanel1.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 250, 110, 93));

        btn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/background.png"))); // NOI18N
        btn6.setName("6"); // NOI18N
        jPanel1.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 250, 105, 93));

        TurnText.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        TurnText.setForeground(new java.awt.Color(255, 255, 0));
        TurnText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(TurnText, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 220, 60));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/oPng.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 320, 140, 60));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/XPngg.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 320, 150, 60));

        playerTwo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        playerTwo.setForeground(new java.awt.Color(204, 204, 204));
        playerTwo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerTwo.setText("NickNaame");
        jPanel1.add(playerTwo, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 270, 140, 30));

        playerOne.setBackground(java.awt.Color.white);
        playerOne.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        playerOne.setForeground(new java.awt.Color(204, 204, 204));
        playerOne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerOne.setText("NickNaame");
        jPanel1.add(playerOne, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 270, 130, 30));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Player 2");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 240, 140, 20));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Player 1");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 240, 130, 20));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/playerB.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 150, 190));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/playerB.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 200, 150, 190));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/test7.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 130, 380, 330));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Board/background.png"))); // NOI18N
        jLabel1.setRequestFocusEnabled(false);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btn9btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9btn7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn9btn7ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel TurnText;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel playerOne;
    public javax.swing.JLabel playerTwo;
    // End of variables declaration//GEN-END:variables
}
