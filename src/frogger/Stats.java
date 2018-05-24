package frogger;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.Timer;

public class Stats extends JComponent implements ActionListener {

    Timer timer = new Timer(400, this);
    private int win = 0;

    public Stats() {
        setVisible(true);
    }

    public void addWin() {
        win++;
    }

    @Override
    public void paintComponent(Graphics g) {
        timer.start();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
        g2d.drawString("Wins: " + win, 10, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (Player.win) {
            addWin();
        }
        repaint();
    }

}
