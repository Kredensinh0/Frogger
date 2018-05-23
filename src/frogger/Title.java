package frogger;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Title extends JComponent implements MouseListener, ActionListener {

    private BufferedImage title1, title2;
    private int disp = 0, titleChange = 0;
    Timer timer = new Timer(400, this);

    public Title() {
        addMouseListener(this);
        try {
            this.title1 = ImageIO.read(this.getClass().getResource("/frogger/resources/title1.jpg"));
            this.title2 = ImageIO.read(this.getClass().getResource("/frogger/resources/title2.jpg"));
        } catch (IOException ex) {
        }
        setVisible(true);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        timer.start();
        Graphics2D g2d = (Graphics2D) g;
        if (disp == 0) {
            if (titleChange == 0) {
                g2d.drawImage(title1, 0, 0, null);
            } else {
                g2d.drawImage(title2, 0, 0, null);
            }
        } else {
            g2d.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setVisible(false);
        Player.canMove = 1;
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        titleChange++;
        if (titleChange == 2) {
            titleChange = 0;
        }
    }
}
