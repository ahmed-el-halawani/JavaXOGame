/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.helperPanels;

import java.awt.EventQueue;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author A H M E D
 */
public class RunnerFrame extends javax.swing.JFrame {
    public static final String winVideo = "/Utils/videos/winnerrr.mp4";
    public static final String loseVideo = "/Utils/videos/winnerrr.mp4";
    Point p;

    /**
     * Creates new form RunnerFrame
     */
    public RunnerFrame(String winVideo) {
        initComponents();
        this.add(new WinVideoPanel(winVideo){
            @Override
            public void startDrag(int x, int y) {
                super.startDrag(x, y);
                p = new Point(x,y);
            }

            @Override
            public void onDrag(int x, int y) {
                super.onDrag(x, y);
                RunnerFrame.this.setLocation(x - p.x, y - p.y);
            }

            @Override
            public void onClose() {
                super.onClose(); 
                RunnerFrame.this.dispose();
                RunnerFrame.this.onClose();
            }

            @Override
            public void onBack() {
                super.onBack(); //To change body of generated methods, choose Tools | Templates.
                RunnerFrame.this.dispose();
                RunnerFrame.this.onClose();
            }

            @Override
            public void onPlayAgain() {
                RunnerFrame.this.dispose();
                RunnerFrame.this.onPlayAgain();
            }

            @Override
            public void onRecord() {
                super.onRecord(); //To change body of generated methods, choose Tools | Templates.
                RunnerFrame.this.onRecord();
            
            }
            
            
        });
    }
    
    public void onPlayAgain(){};
    
    public void onRecord(){};
    
    public void onClose(){};
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setPreferredSize(new java.awt.Dimension(900, 600));

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
            java.util.logging.Logger.getLogger(RunnerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RunnerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RunnerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RunnerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RunnerFrame(winVideo).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
