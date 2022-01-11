/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BounsGames;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import static java.awt.image.ImageObserver.HEIGHT;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author A H M E D
 */
public class FlappyBird extends javax.swing.JFrame {
    public static final String birdPath = "/BounsGames/bird.png";
    public static final String topBarrier = "/BounsGames/topBarrier.png";
    public static final String bottomBarrier = "/BounsGames/bottomBarrier.png";
    
    public static final int freeSpace = 160;
    public static final int spaceBetweenTwoBarriers = 170;
    

    private ArrayList<Barrier> barriers = new ArrayList<>();

    public boolean newBarrier(int width,int spaceBetweenTwoBarriers){
        return  barriers.isEmpty()||
                barriers.get(barriers.size()-1)
                    .currentLocation.x<width-spaceBetweenTwoBarriers;
    }
     
    
    class Bird{
        public static final float acceleration = 1f;
        public static final float velocity = -8;
        
        
        
        public void freeMove(){
            currentVelocity+=acceleration;
        }
        
        public void newLocation(){
            currentLocation.y+=currentVelocity;
            if(currentLocation.y>getHeight()-34)
                gameOver();
        }
        
        public void hit(){
            currentVelocity = velocity;
        }
        
        public void draw(Graphics g){
            g.drawImage(birdImage, currentLocation.x, currentLocation.y, 20, 20,FlappyBird.this);
        }
        
        public void update(Graphics g){
            newLocation();
            draw(g);
            freeMove();
        }
        
        public boolean isHitGround(){
            return currentLocation.y>=getHeight();
        }
        
        Bird(Point currentLocation){
            birdImage = new ImageIcon(getClass().getResource(birdPath)).getImage();
            this.currentLocation = currentLocation;
        }
        
        protected float currentVelocity = 1;
        protected Point currentLocation;
        protected int size = 20;
        private Image birdImage;
    }
   
    private final class Barrier{

        
        public boolean isPass = false;
        
        public void newLocation(){
            currentLocation.x+=barrierVelocity;
        }
        
        public void draw(Graphics g){
            g.drawImage(
                    topBarrierImage, 
                    currentLocation.x, 
                    playArea.getY(), width,
                    topBarrierImage.getHeight(FlappyBird.this),
                    FlappyBird.this
            );
            
            g.drawImage(
                    bottomBarrierImage, 
                    currentLocation.x, 
                    bottom, 
                    width, 
                    topBarrierImage.getHeight(FlappyBird.this),
                    FlappyBird.this
            );
        }
        
        public void update(Graphics g){
            newLocation();
            draw(g);
            remove();
        }
        
        public void remove(){
            if(currentLocation.x<-width)
                barriers.remove(this);
        }
        
        public boolean isHitBarrer(Bird b){
            return ((b.currentLocation.x+b.size>= currentLocation.x&&b.currentLocation.x<= currentLocation.x+width) && (b.currentLocation.y+b.size>=bottom||b.currentLocation.y<=top+playArea.getY()));
        }
        
        public boolean checkIsPass(Bird b){
            if(isPass)
                return false;
            
            if(b.currentLocation.x>currentLocation.x+width){
                isPass = true;
                return true;
            }
            
            return false;
        }
        
        
        Barrier(){
            currentLocation = new Point(getWidth(),0);
            top = rad.nextInt((int)(playArea.getHeight()-spaceBetweenTwoBarriers));
            bottom = top+freeSpace;
            
            this.topBarrierImage = new ImageIcon(getClass().getResource(topBarrier)).getImage();
            width = topBarrierImage.getWidth(FlappyBird.this);

            this.topBarrierImage = createImage(new FilteredImageSource(
                        this.topBarrierImage.getSource(),
                        new CropImageFilter(
                            0, 
                            topBarrierImage.getHeight(FlappyBird.this)-top, 
                            topBarrierImage.getWidth(FlappyBird.this),
                            topBarrierImage.getHeight(FlappyBird.this)
                        )
                )
            );
            
            this.bottomBarrierImage = new ImageIcon(getClass().getResource(bottomBarrier)).getImage();

            barriers.add(this);
            
        };
        
        protected Point currentLocation;
        protected int width;
        protected int top;
        protected int bottom;
        protected Point buttomBarrier;
        private Image topBarrierImage;
        private Image bottomBarrierImage;
        private Random rad = new Random();
    }   
    
    public void gameOver(){
        isGameOver = true;
        gameOverLabel.setText("GAME OVER");
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playArea = new javax.swing.JPanel();
        ground = new javax.swing.JPanel();
        draggeer = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        gameOverLabel = new javax.swing.JLabel();
        score = new javax.swing.JLabel();
        gameOverLabel2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(606, 378));
        setMinimumSize(new java.awt.Dimension(606, 378));
        setUndecorated(true);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        playArea.setBackground(new java.awt.Color(0, 153, 255));
        playArea.setToolTipText("");

        ground.setBackground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout groundLayout = new javax.swing.GroupLayout(ground);
        ground.setLayout(groundLayout);
        groundLayout.setHorizontalGroup(
            groundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );
        groundLayout.setVerticalGroup(
            groundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout playAreaLayout = new javax.swing.GroupLayout(playArea);
        playArea.setLayout(playAreaLayout);
        playAreaLayout.setHorizontalGroup(
            playAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        playAreaLayout.setVerticalGroup(
            playAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, playAreaLayout.createSequentialGroup()
                .addGap(0, 286, Short.MAX_VALUE)
                .addComponent(ground, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(playArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 58, -1, -1));

        draggeer.setBackground(new java.awt.Color(0, 0, 0));
        draggeer.setPreferredSize(new java.awt.Dimension(606, 58));
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

        jLabel3.setFont(new java.awt.Font("Stencil Std", 0, 24)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        gameOverLabel.setFont(new java.awt.Font("Rosewood Std Regular", 0, 36)); // NOI18N
        gameOverLabel.setForeground(new java.awt.Color(255, 255, 255));
        gameOverLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameOverLabel.setText("GAME OVER");

        score.setFont(new java.awt.Font("Rosewood Std Regular", 0, 36)); // NOI18N
        score.setForeground(new java.awt.Color(255, 255, 255));
        score.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        score.setText("0");

        gameOverLabel2.setFont(new java.awt.Font("Rosewood Std Regular", 0, 36)); // NOI18N
        gameOverLabel2.setForeground(new java.awt.Color(255, 255, 255));
        gameOverLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameOverLabel2.setText("Score: ");

        javax.swing.GroupLayout draggeerLayout = new javax.swing.GroupLayout(draggeer);
        draggeer.setLayout(draggeerLayout);
        draggeerLayout.setHorizontalGroup(
            draggeerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, draggeerLayout.createSequentialGroup()
                .addComponent(gameOverLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameOverLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        draggeerLayout.setVerticalGroup(
            draggeerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, draggeerLayout.createSequentialGroup()
                .addGroup(draggeerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gameOverLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gameOverLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(draggeer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_SPACE){
            b.hit();
        }
    }//GEN-LAST:event_formKeyPressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void draggeerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_draggeerMouseDragged
        setLocation(evt.getXOnScreen()-lastClick.x,evt.getYOnScreen()-lastClick.y);
    }//GEN-LAST:event_draggeerMouseDragged

    private void draggeerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_draggeerMousePressed
        lastClick = evt.getPoint();
    }//GEN-LAST:event_draggeerMousePressed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FlappyBird f = new FlappyBird();
                f.setVisible(true);
                f.start();
            }
        });
    }
    
    
    public void start(){
        barrierVelocity = -3;
        gameScore = 0;
        barriers.clear();
        b = new Bird(new Point(200,getHeight()/2));
        gameOverLabel.setText("");
        isGameOver = false;
        
        new Thread(()->{
            while(!isGameOver){
                try {
                    Thread.sleep(30);
                    repaint();
                    ArrayList<Barrier> copyOfBarriers = new ArrayList<>(barriers);

                    if(newBarrier(getWidth(),spaceBetweenTwoBarriers))
                        new Barrier();
                    
                    for (Barrier barrier : copyOfBarriers) 
                        if(barrier.checkIsPass(b))
                           gameScore++;
                    
                    System.out.println(gameScore%5);
                    if(gameScore!=0 && gameScore%5==0)
                    {
                        gameScore+=1;
                        barrierVelocity-=1;
                    }

                    EventQueue.invokeLater(()->{
                        score.setText(""+(gameScore*12));
                    });

                    for (Barrier barrier : copyOfBarriers) 
                        if(barrier.isHitBarrer(b))
                            {
                                gameOver();
                                break;
                            }
                    
                   
                    
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(FlappyBird.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            int answer = JOptionPane.showConfirmDialog(this, "do u want play again", "play Again ", JOptionPane.YES_NO_OPTION);
            if(answer == JOptionPane.YES_OPTION)
                 start();
            else
                dispose();
            return;
        }).start();
        
        
    }
    
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        b.update(g);
        
        ArrayList<Barrier> copyOfBarriers = new ArrayList<>(barriers);

        for (Barrier barrier : copyOfBarriers) 
            barrier.update(g);
        
        
    }
    
    public FlappyBird() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    private float barrierVelocity = -3;
    private Point lastClick;
    private Bird b;
    private boolean isGameOver=false;
    private Integer gameScore = 0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel draggeer;
    private javax.swing.JLabel gameOverLabel;
    private javax.swing.JLabel gameOverLabel2;
    private javax.swing.JPanel ground;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel playArea;
    private javax.swing.JLabel score;
    // End of variables declaration//GEN-END:variables
}
