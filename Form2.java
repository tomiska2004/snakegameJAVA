import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Form2 extends JFrame {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private static final int BOX_WIDTH = 50;
    private static final int BOX_HEIGHT = 20;
    private static final int CIRCLE_RADIUS = 15;
private int v=0;
    private int boxX = (WIDTH - BOX_WIDTH) / 2;
    private int circleX;
    private int circleY = 0;

    public Form2() {
        setTitle("Catch the Circle Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_LEFT && boxX > 0) {
                    boxX -= 10;
                } else if (keyCode == KeyEvent.VK_RIGHT && boxX < WIDTH - BOX_WIDTH) {
                    boxX += 10;
                }
                repaint();
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        Timer timer = new Timer(175, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                circleY += 10;

                if (circleY > HEIGHT - CIRCLE_RADIUS) {
                    checkCollision();
                }
                if (v == 1) {
                    ((Timer) e.getSource()).stop();

                    dispose();
                }
                repaint();
            }
        });
        timer.start();
    }

    private void checkCollision() {
        int boxCenterX = boxX + BOX_WIDTH / 2;
        int circleCenterX = circleX + CIRCLE_RADIUS;
        int boxTopY = HEIGHT - BOX_HEIGHT;
        int boxBottomY = HEIGHT;


        if (circleCenterX >= boxX && circleCenterX <= boxX + BOX_WIDTH && circleY + CIRCLE_RADIUS >= boxTopY && circleY <= boxBottomY) {

            circleX = (int) (Math.random() * (WIDTH - CIRCLE_RADIUS));
            circleY = 0;
        } else {

            System.out.println("Game Over!");
           v=1;
           dispose();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);


        g.setColor(Color.BLUE);
        g.fillRect(boxX, HEIGHT - BOX_HEIGHT, BOX_WIDTH, BOX_HEIGHT);

        g.setColor(Color.YELLOW);
        g.fillOval(circleX, circleY, CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Form2 game = new Form2();
                game.setVisible(true);
            }
        });
    }
}
