package frogger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.Timer;
import java.util.concurrent.ThreadLocalRandom;
import javax.sound.sampled.*;

public class Frogger extends javax.swing.JFrame implements ActionListener {

    private Timer timer = new Timer(700, this);
    private final String backgroundMusic, winSound;
    private Clip audioSample;
    private InputStream audioStream, bufferedIn;
    private AudioInputStream audioInputStream;

    public Frogger() {
        this.winSound = "/frogger/resources/music/win.wav";
        this.backgroundMusic = "/frogger/resources/music/backgroundMusic.wav";
        initComponents();
        this.setLocationRelativeTo(null);
        timer.start();
        try {
            audioSample = AudioSystem.getClip();
            audioStream = this.getClass().getResourceAsStream(backgroundMusic);
            bufferedIn = new BufferedInputStream(audioStream);
            audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
            audioSample.open(audioInputStream);
            FloatControl volume = (FloatControl) audioSample.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-30.0f);
            if (audioSample.isOpen()) {
                audioSample.loop(Clip.LOOP_CONTINUOUSLY);
                audioSample.start();
            }
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!Player.win) {
            int xCar = (int) ((Math.random() * 1) - 150);
            int carLine = (int) ((Math.random() * 9));
            while (carLine == 3 || carLine == 4 || carLine == 2) {
                carLine = (int) ((Math.random() * 9));
            }
            int carY = 55 + (carLine * 50);
            int skin = (int) ((Math.random() * 5) - 1);
            carTest.addCar(xCar, carY, skin);

            int woodLine = (int) ((Math.random() * 4));
            while (woodLine == 0 || woodLine == 1) {
                woodLine = (int) ((Math.random() * 4));
            }

            int xWood = ThreadLocalRandom.current().nextInt(-180, -90 + 1);

            int woodY = 55 + (woodLine * 50);
            wood1.addWood(xWood, woodY);
        } else {
            Player.win = false;
            Player.x = 225;
            Player.y = 505;
            //if there was a new level 
//            Car.getCoordinates().clear();
//            Car.getCars().clear();
//            Car.getRects().clear();
//            Wood.getCoordinates().clear();
//            Wood.getWoods().clear();
//            Wood.getRects().clear();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new frogger.Background();
        carTest = new frogger.Car();
        wood1 = new frogger.Wood();
        player1 = new frogger.Player();
        stats2 = new frogger.Stats();
        title2 = new frogger.Title();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Frogger");
        setMinimumSize(null);
        setName("frame"); // NOI18N
        setResizable(false);

        stats2.add(title2);
        title2.setBounds(0, 0, 500, 550);

        javax.swing.GroupLayout player1Layout = new javax.swing.GroupLayout(player1);
        player1.setLayout(player1Layout);
        player1Layout.setHorizontalGroup(
            player1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stats2, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        player1Layout.setVerticalGroup(
            player1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stats2, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout wood1Layout = new javax.swing.GroupLayout(wood1);
        wood1.setLayout(wood1Layout);
        wood1Layout.setHorizontalGroup(
            wood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wood1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(player1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        wood1Layout.setVerticalGroup(
            wood1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wood1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(player1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout carTestLayout = new javax.swing.GroupLayout(carTest);
        carTest.setLayout(carTestLayout);
        carTestLayout.setHorizontalGroup(
            carTestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wood1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        carTestLayout.setVerticalGroup(
            carTestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wood1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(carTest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addComponent(carTest, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            java.util.logging.Logger.getLogger(Frogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frogger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frogger().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private frogger.Background background1;
    private frogger.Car carTest;
    private frogger.Player player1;
    private frogger.Stats stats2;
    private frogger.Title title2;
    private frogger.Wood wood1;
    // End of variables declaration//GEN-END:variables

}
