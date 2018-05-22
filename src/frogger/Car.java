package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.Timer;

public class Car extends javax.swing.JComponent implements ActionListener {

    private BufferedImage car1, car2, car3, car4;
    private Timer timer = new Timer(10, this);
    private static List<BufferedImage> cars = new ArrayList();
    private static List<Point> coordinates = new ArrayList();
    private static List<Rectangle> rects = new ArrayList();
    private Clip audioSample;
    private InputStream audioStream, bufferedIn;
    private AudioInputStream audioInputStream;
    private String crashSound;

    int velocity = 2;

    public Car() {
        this.crashSound = "/frogger/resources/music/crash.wav";
        try {
            car1 = ImageIO.read(this.getClass().getResource("/frogger/resources/models/cars/car1.png"));
            car2 = ImageIO.read(this.getClass().getResource("/frogger/resources/models/cars/car2.png"));
            car3 = ImageIO.read(this.getClass().getResource("/frogger/resources/models/cars/car3.png"));
            car4 = ImageIO.read(this.getClass().getResource("/frogger/resources/models/cars/car4.png"));
        } catch (IOException e) {
        }
        initComponents();
    }

    public static List<Point> getCoordinates() {
        return coordinates;
    }

    public static List<BufferedImage> getCars() {
        return cars;
    }

    public static List<Rectangle> getRects() {
        return rects;
    }

    @Override
    public void paintComponent(Graphics g) {
        timer.start();
        Graphics2D g2d = (Graphics2D) g;
        Iterator iterator = cars.iterator();
        if (!cars.isEmpty()) {
            int i = 0;
            while (iterator.hasNext()) {
                BufferedImage bfi = (BufferedImage) iterator.next();
                Point xy = coordinates.get(i);
                g2d.drawImage(bfi, xy.x, xy.y, 90, 35, null);
                i++;
            }
        }

    }

    public void addCar(int x, int y, int skin) {
        switch (skin) {
            case 1:
                cars.add(car1);
                break;
            case 2:
                cars.add(car2);
                break;
            case 3:
                cars.add(car3);
                break;
            default:
                cars.add(car4);
                break;
        }
        coordinates.add(new Point(x, y));
        rects.add(new Rectangle(x, y, 90, 35));
        this.repaint();
    }

    public boolean detectCollision() {
        Rectangle frog = new Rectangle(Player.x, Player.y, 35, 35);
        return rects.stream().anyMatch((car) -> (frog.intersects(car)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(500, 580));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 591, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void crashSound() {
        try {
            audioStream = this.getClass().getResourceAsStream(crashSound);
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
            for (int i = 0; i < coordinates.size(); i++) {
                Point xy = coordinates.get(i);
                if (xy.x > 600) {
                    cars.remove(i);
                    coordinates.remove(i);
                    rects.remove(i);
                } else {
                    xy.x += velocity;
                    rects.get(i).x += velocity;
                }
            }
            repaint();
        } else {
            crashSound();
            Player.x = 225;
            Player.y = 505;
            repaint();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
