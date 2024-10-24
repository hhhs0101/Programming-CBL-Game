import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameOverPanel extends JPanel{
    
    JButton replayButton;

    GameOverPanel(){

        this.setLayout(null);
        this.setBackground(Color.WHITE);

        replayButton = new JButton("Replay Game");
        replayButton.setFont(new Font("Arial", Font.BOLD, 20));
        replayButton.setBounds(200, 420, 200, 80);
        this.add(replayButton);

        JLabel label = new JLabel("Game Over");
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setBounds(165, 100, 270, 50);
        this.add(label);
    }

    void setReplayButtonListener(ActionListener listener) {
        replayButton.addActionListener(listener);
    }

    
} 
