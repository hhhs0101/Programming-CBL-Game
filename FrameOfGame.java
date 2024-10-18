import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class FrameOfGame implements ActionListener {
    JButton button;
    JFrame frame;
    JPanel panel;
    JLabel label;
    Shield shield;
    Character character;
    MagicArrow magicArrow;
    int angle = 0;
    Random random;
    Ball ball;
    public int frameSize = 400;

    public FrameOfGame() {

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setTitle("Defense");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(frameSize, frameSize);

        button = new JButton("Start Game");
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBounds(160, 200, 80, 40);
        button.addActionListener(this);
        
        panel = new JPanel(null); 
        panel.setBackground(Color.WHITE);
        panel.add(button);

        label = new JLabel("Defense Game");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(100, 50, 300, 50);
        panel.add(label);


        shield = new Shield();
        shield.setBounds(0, 0, frameSize, frameSize);  
        shield.setVisible(false);
        panel.add(shield);

        character = new Character();
        character.setBounds(0, 0, frameSize, frameSize);
        character.setVisible(false);
        panel.add(character);

        magicArrow = new MagicArrow();
        magicArrow.setBounds(0, 0, frameSize, frameSize);
        magicArrow.setVisible(false); 
        panel.add(magicArrow);

        frame.add(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.setFocusable(true);
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w':
                        angle = 270;
                        shield.setAngle(angle);       
                        break;
                    case 'a':
                        angle = 180;
                        shield.setAngle(angle);       
                        break;
                    case 's':
                        angle = 90;
                        shield.setAngle(angle);       
                        break;
                    case 'd':
                        angle = 0;
                        shield.setAngle(angle);       
                        break;
                }
            }
        });
    }

    public void gameStageFrame() {
        
        shield.setVisible(true);
        character.setVisible(true); 
        magicArrow.setVisible(true);

        panel.revalidate();
        panel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            button.setVisible(false);
            label.setVisible(false);
            panel.repaint();
            gameStageFrame();
        }
    }

    public void newBall() {
        random = new Random();
		ball = new Ball((frameSize/2)-(20/2),random.nextInt(frameSize-20),20,20);
    }

    public static void main(String[] args) {
        new FrameOfGame();
    }
}
