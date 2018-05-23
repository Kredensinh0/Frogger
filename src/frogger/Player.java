package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class Player extends javax.swing.JComponent implements KeyListener {

    public static boolean win = false;
    private BufferedImage playerInPlace, playerLeft, playerRight, playerTop, playerDown;
    public static int x = 225, y = 505, size = 35;
    private int skinChange = 0; // 0 - normal, 1 - facing top
    private Clip audioSample;
    private InputStream audioStream, bufferedIn;
    private AudioInputStream audioInputStream;
    private String jumpSound;
    public static int canMove = 0; // 0 - can't, 1 - can

    public Player() {
        setFocusable(true);
        addKeyListener(this);
        this.jumpSound = "/frogger/resources/music/jump.wav";
        try {
            playerInPlace = ImageIO.read(this.getClass().getResource("/frogger/resources/models/player/frog_in_place.png"));
            playerTop = ImageIO.read(this.getClass().getResource("/frogger/resources/models/player/frog_jumping_top.png"));
            playerDown = ImageIO.read(this.getClass().getResource("/frogger/resources/models/player/frog_jumping_down.png"));
            playerRight = ImageIO.read(this.getClass().getResource("/frogger/resources/models/player/frog_jumping_right.png"));
            playerLeft = ImageIO.read(this.getClass().getResource("/frogger/resources/models/player/frog_jumping_left.png"));
        } catch (IOException e) {
        }
        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (playerInPlace != null) {
            switch (skinChange) {
                case 1:
                    g2d.drawImage(playerRight, x, y, null);
                    break;
                case 2:
                    g2d.drawImage(playerLeft, x, y, null);
                    break;
                case 3:
                    g2d.drawImage(playerTop, x, y, null);
                    break;
                case 4:
                    g2d.drawImage(playerDown, x, y, null);
                    break;
                default:
                    g2d.drawImage(playerInPlace, x, y, null);
                    break;
            }
        }
    }

    public void jumpSound() {
        try {
            audioStream = this.getClass().getResourceAsStream(jumpSound);
            audioSample = AudioSystem.getClip();
            bufferedIn = new BufferedInputStream(audioStream);
            audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
            audioSample.open(audioInputStream);
            FloatControl volume = (FloatControl) audioSample.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-20.0f);
            audioSample.start();
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }

    public void detectWin() {
        if (y == 5) {
            win = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (canMove == 1) {
            detectWin();
            int key = e.getKeyCode();
            jumpSound();
            switch (key) {
                case KeyEvent.VK_UP:
                    skinChange = 3;
                    if (y > 5) {
                        y -= 50;
                        detectWin();
                    }
                    repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    skinChange = 4;
                    if (y < 500) {
                        y += 50;
                    }
                    repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    skinChange = 1;
                    if (x < 400) {
                        x += 50;
                    }
                    repaint();
                    break;
                case KeyEvent.VK_LEFT:
                    skinChange = 2;
                    if (x > 25) {
                        x -= 50;
                    }
                    repaint();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        skinChange = 0;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMaximumSize(null);
        setPreferredSize(new java.awt.Dimension(500, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 399, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
