import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form1 extends JFrame {
    public Form1() {
        initComponents();
    }

    private void initComponents() {
        label1 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        label1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 36));
        label1.setText("Játék");

        button1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 25));
        button1.setText("Pálya: 1");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 25));
        button2.setText("Pálya: 2");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

                layout.setHorizontalGroup(
                        layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(404, 404, 404)
                                        .addComponent(label1))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(129, 129, 129)
                                        .addComponent(button1, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(129, 129, 129)
                                        .addComponent(button2, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE))
                );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(label1)
                                .addGap(168, 168, 168)
                                .addComponent(button1, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                .addGap(226, 226, 226)
                                .addComponent(button2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108)
                                .addContainerGap(159, Short.MAX_VALUE))
        );

        pack();
        setSize(1000, 900);
    }

    private void button1ActionPerformed(ActionEvent evt) {
        new GameFrame(200);
    }

    private void button2ActionPerformed(ActionEvent evt) {
        Form2 game = new Form2();
        game.setVisible(true);
    }



    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form1().setVisible(true);
            }
        });
    }


    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

}
