/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BounsGames;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author A H M E D
 */


public class SnackGame extends javax.swing.JFrame {

    private int newX;
    private int newY;
    private int lastCode;
    private boolean updateKey;
    private boolean isGameOver;
    private Snack s;
    
    public SnackGame() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public void newSnack(){
          s = new Snack(
            20,
            game.getX(),
            game.getX()+game.getWidth()+5,
            game.getY(),
            game.getY()+game.getHeight()+5
        );
    }
    
    public void start(){
        newX = 1;
        newY = 0;
        lastCode=-1;
        updateKey=false;
        isGameOver = false;
        newSnack();
        
        new Thread(()->{
            while(!isGameOver){
                try {
                    Thread.sleep(100);
                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(SnackGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
        }).start();
    }
    
    
    
    final class Snack{
        int size;
        int xStart;
        int xEnd ;
        int yStart ;
        int yEnd ;
        Point eat = null;
        Point lastTails = null;
        List<Point> snackBody = new ArrayList<>();
        List<Integer> xAxis;
        List<Integer> yAxis;
        Snack(int size,int xStart,int xEnd,int yStart,int yEnd){
            this.xAxis = new ArrayList<>();
            this.yAxis = new ArrayList<>();
            
            for (int i = 0; i < xEnd-xStart; i+=size) 
                xAxis.add(i);
            
            for (int i = 0; i < yEnd-yStart; i+=size) 
                yAxis.add(i);
            
            this.xStart = xStart;
            this.xEnd = xEnd;
            this.yStart = yStart;
            this.yEnd = yEnd;
            
            this.size = size;
            for (int i = 0; i < 1; i++) {
                snackBody.add(new Point(xStart+(i*size),yStart));
            }
            randomEat();
        }
        
        public void randomEat(){
            Random rnd = new Random();
            boolean notGetEat = true;
            int x = 0 ;
            int y = 0;
            while(notGetEat){
                 x = rnd.nextInt(xAxis.size());
                 y = rnd.nextInt(yAxis.size());
                
                notGetEat = false;
                for (int i = 0; i < snackBody.size(); i++) 
                    if(snackBody.get(i).x == xAxis.get(x) && snackBody.get(i).y == yAxis.get(y))
                        notGetEat=true;
            }
            
            eat = new Point(xAxis.get(x)+xStart, yAxis.get(y)+yStart);
        }
        
        
        public void move(int newX,int newY){
            lastTails = new Point(snackBody.get(0).x,snackBody.get(0).y);
            for (int i = 0; i < snackBody.size(); i++) {
                if(i == snackBody.size()-1){
                    snackBody.get(i).x = snackBody.get(snackBody.size()-1).x+(int)(size*newX);
                    snackBody.get(i).y = snackBody.get(snackBody.size()-1).y+(int)(size*newY);
                }else{
                    snackBody.get(i).x = snackBody.get(i+1).x;
                    snackBody.get(i).y = snackBody.get(i+1).y;
                }
            }
        }
        
        public void eatIt(){
            if(snackBody.get(snackBody.size()-1).x == eat.x && snackBody.get(snackBody.size()-1).y == eat.y)
            {
                snackBody.add(0,lastTails);
                randomEat();
                Score.setText(""+(snackBody.size()*10));
            }
        }
        
        public boolean isGameOver(){
            for (int i = 0; i < snackBody.size()-1; i++) 
                if(snackBody.get(i).x == snackBody.get(snackBody.size()-1).x && snackBody.get(i).y == snackBody.get(snackBody.size()-1).y)
                    return true;
            return false;
        }
        
        
        public void donthitBounds(){
             if(snackBody.get(snackBody.size()-1).x>=xEnd){
                snackBody.get(snackBody.size()-1).x = xStart;
            }else if(snackBody.get(snackBody.size()-1).x<xStart){
                snackBody.get(snackBody.size()-1).x = xEnd;
            }else if(snackBody.get(snackBody.size()-1).y>=yEnd){
                snackBody.get(snackBody.size()-1).y = yStart;
            }else if(snackBody.get(snackBody.size()-1).y<yStart){
                snackBody.get(snackBody.size()-1).y = yEnd;
            }
        }
        
        public void drawSnack(Graphics g){
            snackBody.forEach((p) -> {
                g.fillRect(p.x, p.y, size-5, size-5);
            });
        }
        
        public void drawEat(Graphics g){
            g.fillRect(eat.x, eat.y, size, size);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        game = new javax.swing.JPanel();
        draggeer = new javax.swing.JPanel();
        Score = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        game.setBackground(new java.awt.Color(255, 255, 255));
        game.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout gameLayout = new javax.swing.GroupLayout(game);
        game.setLayout(gameLayout);
        gameLayout.setHorizontalGroup(
            gameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        gameLayout.setVerticalGroup(
            gameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );

        jPanel3.add(game, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 395, 395));

        draggeer.setBackground(new java.awt.Color(0, 0, 0));
        draggeer.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                draggeerMouseDragged(evt);
            }
        });
        draggeer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                draggeerMousePressed(evt);
            }
        });

        Score.setFont(new java.awt.Font("Stencil Std", 0, 24)); // NOI18N
        Score.setForeground(java.awt.Color.white);
        Score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Score.setText("0");

        jLabel2.setFont(new java.awt.Font("Stencil Std", 0, 24)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Your Score: ");

        jLabel3.setFont(new java.awt.Font("Stencil Std", 0, 24)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout draggeerLayout = new javax.swing.GroupLayout(draggeer);
        draggeer.setLayout(draggeerLayout);
        draggeerLayout.setHorizontalGroup(
            draggeerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(draggeerLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(Score, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, draggeerLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        draggeerLayout.setVerticalGroup(
            draggeerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, draggeerLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(draggeerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Score, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3.add(draggeer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 110));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode()==lastCode)
            return;
        if(!updateKey)
            return;
        
        switch (evt.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(lastCode == KeyEvent.VK_DOWN)
                    return;
                newX = 0;
                newY = -1;
                break;
            case KeyEvent.VK_DOWN:
                if(lastCode == KeyEvent.VK_UP)
                    return;
                newX = 0;
                newY = 1;
                break;
            case KeyEvent.VK_RIGHT:
                if(lastCode == KeyEvent.VK_LEFT)
                    return;
                newX = 1;
                newY = 0;
                break;
            case KeyEvent.VK_LEFT:
                if(lastCode == KeyEvent.VK_RIGHT)
                    return;
                newX = -1;
                newY = 0;
                break;
            default:
                break;
        }
        lastCode = evt.getKeyCode();
        updateKey = false;
    }//GEN-LAST:event_formKeyPressed

    private void draggeerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_draggeerMousePressed
       lastClick = evt.getPoint();
    }//GEN-LAST:event_draggeerMousePressed

    private void draggeerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_draggeerMouseDragged
        setLocation(evt.getXOnScreen()-lastClick.x,evt.getYOnScreen()-lastClick.y);
    }//GEN-LAST:event_draggeerMouseDragged

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    Point lastClick;
    
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        
        s.drawEat(g);
        
        s.drawSnack(g);
        
        s.move(newX,newY);
        
        s.eatIt();
        
        s.donthitBounds();
        
        if(SnackGame.this.s.isGameOver())
        {
            isGameOver = true;
            int answer = JOptionPane.showConfirmDialog(this, "do u want play again", "play again", JOptionPane.YES_NO_OPTION);
            if(answer == 0)
                 start();
            else
                dispose();
            return;
        }
        
        updateKey=true;
    }

    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SnackGame s = new SnackGame();
            s.setVisible(true);
            s.start();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Score;
    private javax.swing.JPanel draggeer;
    private javax.swing.JPanel game;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
