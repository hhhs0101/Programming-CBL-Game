import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    
    GamePanel gamePanel = new GamePanel();
    GameOverPanel gameOverPanel = new GameOverPanel();

    GameFrame(){
        
        this.setTitle("Defense Game");

        JPanel startPanel = new JPanel();
        startPanel.setLayout(null);
        startPanel.setBackground(Color.WHITE);
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arail", Font.BOLD, 20));
        startButton.setBounds(200, 420, 200, 80);
        startPanel.add(startButton);

        JLabel label = new JLabel("Defense Game");
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setBounds(125, 100, 350, 50);
        startPanel.add(label);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame.this.remove(startPanel);
                GameFrame.this.add(gamePanel);
                GameFrame.this.revalidate();
                gamePanel.requestFocusInWindow();
                gamePanel.startGame();
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.add(startPanel);
        this.setVisible(true);

}
    /*void GameOverCount(int hp){

        if(hp == 0){

            GameFrame.this.remove(gamePanel);
            GameFrame.this.add(gameOverPanel);
        }


        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame.this.remove(startPanel);
                GameFrame.this.add(gamePanel);
                GameFrame.this.revalidate();
                gamePanel.requestFocusInWindow();
                gamePanel.startGame();
    } */
}

