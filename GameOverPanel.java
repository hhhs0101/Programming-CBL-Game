import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOverPanel extends JPanel{
    
JButton replayButton = new JButton("Replay Game");

    GameOverPanel(){

        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setFont(new Font("Arail", Font.BOLD, 20));
        replayButton.setBounds(200, 420, 200, 80);
        this.add(replayButton);

        JLabel label = new JLabel("Game Over");
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setBounds(125, 100, 350, 50);
        this.add(label);
    }

    void setReplayButtonListener(ActionListener listener) {
        replayButton.addActionListener(listener);
    }

    
} 
