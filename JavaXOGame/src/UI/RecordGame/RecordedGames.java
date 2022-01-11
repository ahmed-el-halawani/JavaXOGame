/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.RecordGame;

import Entities.PlayerDetails;
import Entities.User;
import Entities.UserGameDetails;
import Utils.AppManager;
import Utils.ConnectionManager;
import Utils.UserGameDetailsCrud;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

/**
 *
 * @author A H M E D
 */
public class RecordedGames extends javax.swing.JFrame {

    
    AppManager appManager;
    UserGameDetailsCrud userGameDetailsCrud;
    ConnectionManager cm;
    JFrame backFrame;
    public RecordedGames(JFrame backFrame) {
        this.backFrame = backFrame;
        initComponents();
        try {
        appManager = AppManager.getinstance();
     
        cm = ConnectionManager.getInstance();
        
        userGameDetailsCrud= new UserGameDetailsCrud(cm.in,cm.out);
        init();
        } catch (IOException ex) {
            Logger.getLogger(RecordedGames.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void replay(UserGameDetails userGameDetails){
        Replay r = new Replay(userGameDetails);
        r.setVisible(true);
        r.start();
    }
    
    public void init() throws IOException{
        
        ArrayList<UserGameDetails> userGamesDetails = userGameDetailsCrud.getAllWithId(appManager.getUser().getId());

        for (Iterator<UserGameDetails> iterator = userGamesDetails.iterator(); iterator.hasNext();) {
            UserGameDetails next = iterator.next();
            PlayerDetails p = next.getPlayerWithId(appManager.getUser().getId());
            if(!p.getIsRecorded())
                continue;
                
            RecordedRow rr = new RecordedRow(p.getPlayerState(),next.playerOneDetails,next.playerTwoDetails);
            rr.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e); 
                    replay(next);
                }
            });
            recordsList.add(rr);
        }
        
    }
    
    
    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void hide() {
        super.hide(); 
        backFrame.setVisible(true);
    }
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        recordsList = new javax.swing.JPanel();

        setMinimumSize(new java.awt.Dimension(900, 386));

        jPanel1.setBackground(new java.awt.Color(102, 0, 204));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(102, 0, 204));

        recordsList.setBackground(new java.awt.Color(0, 0, 0));
        recordsList.setMinimumSize(new java.awt.Dimension(900, 0));
        recordsList.setPreferredSize(new java.awt.Dimension(900, 100));
        recordsList.setLayout(new javax.swing.BoxLayout(recordsList, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(recordsList);

        jPanel1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(RecordedGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecordedGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecordedGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecordedGames.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RecordedGames(new JFrame()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel recordsList;
    // End of variables declaration//GEN-END:variables
}