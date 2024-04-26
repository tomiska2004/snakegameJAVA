import javax.swing.JFrame;

public class GameFrame extends JFrame{

    GameFrame(int delay){

        this.add(new GamePanel(delay));
        this.setTitle("SnakeGame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }
}