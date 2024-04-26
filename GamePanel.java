import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.*;


public class GamePanel extends JPanel implements ActionListener{

    private  final int scwi = 1400;
    private  final int sche = 800;
    private  final int testsize = 50;
    private  final int jateksize = (scwi * sche)/(testsize * testsize);
    private int delay;
    private  int x[] = new int[jateksize];
    private  int y[] = new int[jateksize];
    private int bodyParts = 3;
    private int almadb;
    private int appleX;
    private int appleY;
    private char direction = 'R';
    private boolean running = false;
    private Timer timer;
    private Random random;
    private Clip backgroundSound;
    private Clip effectSound;

    GamePanel( int delay){
        random = new Random();
        this.setPreferredSize(new Dimension(scwi, sche));
        this.setBackground(Color.gray);
        this.delay=delay;
        initializeSounds();
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    private void initializeSounds() {
        try {

            AudioInputStream backgroundStream = AudioSystem.getAudioInputStream(new File("snakehiss2.wav"));
            backgroundSound = AudioSystem.getClip();
            backgroundSound.open(backgroundStream);


            AudioInputStream effectStream = AudioSystem.getAudioInputStream(new File("snakeatt.wav"));
            effectSound = AudioSystem.getClip();
            effectSound.open(effectStream);

        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
    private void playBackgroundSound() {
        if (!backgroundSound.isRunning()) {
            backgroundSound.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    private void stopBackgroundSound() {
        if (backgroundSound.isRunning()) {
            backgroundSound.stop();
        }
    }

    private void playEffectSound() {
        effectSound.setFramePosition(0);
        effectSound.start();
    }

    public void startGame() {
        newApple();
        playBackgroundSound();
        running = true;
        timer = new Timer(delay,this);
        timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(running) {


            g.setColor(Color.red);
            g.fillOval(appleX, appleY, testsize, testsize);

            for(int i = 0; i< bodyParts;i++) {
                if(i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], testsize, testsize);
                }
                else {
                    g.setColor(new Color(45,180,0));
                    g.fillRect(x[i], y[i], testsize, testsize);
                }
            }
            g.setColor(Color.red);
            g.setFont( new Font("Times New Roman",Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+ almadb, (scwi - metrics.stringWidth("Score: "+ almadb))/2, g.getFont().getSize());
        }
        else {
            gameOver(g);
        }
    }

    public void newApple(){
        appleX = random.nextInt((int)(scwi / testsize))* testsize;
        appleY = random.nextInt((int)(sche / testsize))* testsize;
    }
    public void move(){
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction) {
            case 'U':
                y[0] = y[0] - testsize;
                break;
            case 'D':
                y[0] = y[0] + testsize;
                break;
            case 'L':
                x[0] = x[0] - testsize;
                break;
            case 'R':
                x[0] = x[0] + testsize;
                break;
        }

    }
    public void checkApple() {
        if((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            almadb++;
            playEffectSound();
            newApple();
        }
    }
    public void checkCollisions() {

        for(int i = bodyParts;i>0;i--) {
            if((x[0] == x[i])&& (y[0] == y[i])) {
                running = false;
            }
        }
        //bal
        if(x[0] < 0) {
            running = false;
        }
        //jobb
        if(x[0] > scwi) {
            running = false;
        }
        //felso
        if(y[0] < 0) {
            running = false;
        }
        //also
        if(y[0] > sche) {
            running = false;
        }

        if(!running) {
            timer.stop();

            if(delay>50) {
                stopBackgroundSound();
                new GameFrame(delay - 10);
            }else{
                stopBackgroundSound();
                new GameFrame(200);
            }
        }
    }



    public void gameOver(Graphics g) {



        g.setFont( new Font("Times New Roman",Font.BOLD, 50));g.setColor(Color.red);
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: "+ almadb, (scwi - metrics1.stringWidth("Score: "+ almadb))/2, g.getFont().getSize());



        g.setFont( new Font("Times New Roman",Font.BOLD, 100));g.setColor(Color.red);
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (scwi - metrics2.stringWidth("Game Over"))/2, sche /2);//
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}