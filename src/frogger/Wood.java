package frogger;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.Timer;

public class Wood extends javax.swing.JComponent implements ActionListener {

    private BufferedImage wood;
    private Timer timer = new Timer(12, this);
    private static List<BufferedImage> woodImages = new ArrayList();
    private static List<Point> woodCoordinates = new ArrayList();
    private static List<Rectangle> woodRects = new ArrayList();
    private int velocity = 2;
    private Clip audioSample;
    private InputStream audioStream, bufferedIn;
    private AudioInputStream audioInputStream;
    private String waterSound;

    public Wood() {
        this.waterSound = "/frogger/resources/music/splash.wav";
        try {
            wood = ImageIO.read(this.getClass().getResource("/frogger/resources/models/wood/wood.png"));
        } catch (IOException e) {
        }
        initComponents();
    }

    static public List<BufferedImage> getWoods() {
        return woodImages;
    }

    static public List<Point> getCoordinates() {
        return woodCoordinates;
    }

    static public List<Rectangle> getRects() {
        return woodRects;
    }

    public void addWood(int x, int y) {
        woodImages.add(wood);
        woodCoordinates.add(new Point(x, y));
        woodRects.add(new Rectangle(x, y, 90, 35));
        this.repaint();
    }

    public boolean detectCollision() {
        if (Player.y == 205 || Player.y == 155) {
            Rectangle frog = new Rectangle(Player.x, Player.y, 35, 35);
            return !woodRects.stream().anyMatch((wood) -> (frog.intersects(wood)));
        }
        return false;
    }

    @Override
    public void paintComponent(Graphics g) {
        timer.start();
        Graphics2D g2d = (Graphics2D) g;
        Iterator iterator = woodImages.iterator();
        if (!woodImages.isEmpty()) {
            int i = 0;
            while (iterator.hasNext()) {
                BufferedImage bfi = (BufferedImage) iterator.next();
                Point xy = woodCoordinates.get(i);
                g2d.drawImage(bfi, xy.x, xy.y, 90, 35, null);
                i++;
            }
        }
    }

    public void waterSound() {
        try {
            audioStream = this.getClass().getResourceAsStream(waterSound);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!detectCollision()) {
            for (int i = 0; i < woodCoordinates.size(); i++) {
                Point xy = woodCoordinates.get(i);
                if (xy.x > 500) {
                    woodImages.remove(i);
                    woodCoordinates.remove(i);
                    woodRects.remove(i);
                } else {
                    xy.x += velocity;
                    woodRects.get(i).x += velocity;
                }
            }
        } else {
            waterSound();
            Player.x = 225;
            Player.y = 505;
            repaint();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(80, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
