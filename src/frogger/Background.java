package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Background extends javax.swing.JComponent implements ActionListener {

    private BufferedImage background1, background2;
    private Timer timer = new Timer(500, this);
    int skinChange = 0;

    public Background() {
        try {
            background1 = ImageIO.read(this.getClass().getResource("/frogger/resources/background1.jpg"));
            background2 = ImageIO.read(this.getClass().getResource("/frogger/resources/background2.jpg"));
        } catch (IOException e) {
        }
        initComponents();
    }

    @Override
    public void paintComponent(Graphics g) {
        timer.start();
        Graphics2D g2d = (Graphics2D) g;
        if (background1 != null && background2 != null) {
            if (skinChange == 0) {
                g2d.drawImage(background1, 0, 0, this);
            } else {
                g2d.drawImage(background2, 0, 0, this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        skinChange++;
        if (skinChange == 2) {
            skinChange = 0;
        }
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setMinimumSize(new java.awt.Dimension(500, 550));
        setPreferredSize(new java.awt.Dimension(500, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
