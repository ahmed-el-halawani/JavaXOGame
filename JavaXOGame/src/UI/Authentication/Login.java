/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Authentication;

/**
 *
 * @author hossam
 */

import Entities.User;
import Testing.MainTest;
import Utils.ConnectionManager;
import Utils.UserCrud;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login extends javax.swing.JFrame {
    
    ConnectionManager cm;
    UserCrud userCrud;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        
        jLabel9.setVisible(false);

        addPlaceHolderStyle(jTextField1);
        addPlaceHolderStyle(jPasswordField1);
        
        try {
            cm = ConnectionManager.getInstance();
            userCrud = new UserCrud(cm.in,cm.out);
        } catch (IOException ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void addPlaceHolderStyle(JTextField textField){
        Font font = textField.getFont();
        font = font.deriveFont(Font.BOLD);
        textField.setFont(font);
        textField.setForeground(Color.LIGHT_GRAY);
    }
    
    public void removePlaceHolderStyle(JTextField textField){
        Font font = textField.getFont();
        font = font.deriveFont(Font.PLAIN | Font.BOLD);
        textField.setFont(font);
        textField.setForeground(Color.LIGHT_GRAY);
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        signupBtn = new javax.swing.JButton();

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(74, 31, 61));
        jLabel6.setText("_");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel1.setBackground(new java.awt.Color(74, 31, 61));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Authentication/logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel10)
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(75, 75, 75))
        );

        jPanel3.setBackground(new java.awt.Color(186, 79, 84));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setBackground(new java.awt.Color(186, 79, 84));
        jTextField1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setText("Username");
        jTextField1.setBorder(null);
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 113, 200, 20));

        jPasswordField1.setBackground(new java.awt.Color(186, 79, 84));
        jPasswordField1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(204, 204, 204));
        jPasswordField1.setText("Password");
        jPasswordField1.setBorder(null);
        jPasswordField1.setEchoChar('\u0000');
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusLost(evt);
            }
        });
        jPasswordField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPasswordField1MouseClicked(evt);
            }
        });
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        jPanel3.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(78, 163, 200, 20));

        jSeparator1.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 200, 10));

        jSeparator2.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator2.setForeground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 200, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Authentication/username.png"))); // NOI18N
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 30, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UI/Authentication/password.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 30, 40));

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Forget Password?");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 120, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(74, 31, 61));
        jLabel7.setText("_");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, -30, 20, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(74, 31, 61));
        jLabel8.setText("X");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 20, 20));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(74, 31, 61));
        jLabel9.setText("M");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 20, 20));

        loginBtn.setBackground(new java.awt.Color(186, 79, 84));
        loginBtn.setForeground(new java.awt.Color(204, 204, 204));
        loginBtn.setText("Log In");
        loginBtn.setBorder(null);
        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtnMouseExited(evt);
            }
        });
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        jPanel3.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 90, 30));

        signupBtn.setBackground(new java.awt.Color(186, 79, 84));
        signupBtn.setForeground(new java.awt.Color(204, 204, 204));
        signupBtn.setText("Sign Up");
        signupBtn.setBorder(null);
        signupBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupBtnMouseExited(evt);
            }
        });
        signupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupBtnActionPerformed(evt);
            }
        });
        jPanel3.add(signupBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 90, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        //jTextField1.setText("");
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    private void jPasswordField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPasswordField1MouseClicked
        
    }//GEN-LAST:event_jPasswordField1MouseClicked

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        if(jTextField1.getText().equals("Username")){
            jTextField1.setText(null);
            jTextField1.requestFocus();
            removePlaceHolderStyle(jTextField1);
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        if(jPasswordField1.getText().equals("Password")){
            jPasswordField1.setText(null);
            jPasswordField1.requestFocus();
            jPasswordField1.setEchoChar('\u2022');
            removePlaceHolderStyle(jPasswordField1);

        }
    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if(jTextField1.getText().length() == 0){
            addPlaceHolderStyle(jTextField1);
            jTextField1.setText("Username");
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost
        if(jPasswordField1.getText().length() == 0){
            addPlaceHolderStyle(jPasswordField1);
            jPasswordField1.setText("Password");
            jPasswordField1.setEchoChar('\u0000');
        }
    }//GEN-LAST:event_jPasswordField1FocusLost

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.requestFocusInWindow();
    }//GEN-LAST:event_formWindowGainedFocus

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void loginBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnMouseEntered
        loginBtn.setForeground(new Color(74,31,61));
    }//GEN-LAST:event_loginBtnMouseEntered

    private void loginBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBtnMouseExited
        loginBtn.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_loginBtnMouseExited

    private void signupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupBtnActionPerformed
        Register reg = new Register();
                reg.setVisible(true);
                reg.pack();
                reg.setLocationRelativeTo(null);
                this.dispose();
    }//GEN-LAST:event_signupBtnActionPerformed

    private void signupBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupBtnMouseEntered
        signupBtn.setForeground(new Color(74,31,61));
    }//GEN-LAST:event_signupBtnMouseEntered

    private void signupBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupBtnMouseExited
        signupBtn.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_signupBtnMouseExited

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        
        
        String userName = jTextField1.getText(); 
        String password = String.valueOf(jPasswordField1.getPassword());
        
           
           User u = userCrud.getWithUserName(userName);
           
           if(u != null && u.getPassword().equals(password)){
               
               // change Menu_Form with next screen class(Main)
               Menu_Form form = new Menu_Form();
                form.setVisible(true);
                form.pack();
                form.setLocationRelativeTo(null);
                this.dispose();
                
            }else{
                JOptionPane.showMessageDialog(null,"Invalid Username / Password", "Login Error",2);
            }
        
        
    }//GEN-LAST:event_loginBtnActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5MouseClicked

    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton signupBtn;
    // End of variables declaration//GEN-END:variables
}
