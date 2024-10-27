import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
    Timer timer;
    Timer arrowTimer;
    Timer stopTimer;
    Timer stageTimer;
    ArrayList<RotatableArrow> arrows;
    Character character;
    Shield shield;
    Random random;
    JLabel stageLabel;
    Heart heart;

    int panelWidth = 600;
    int panelHeight = 600;
    int centerX = panelWidth / 2;
    int centerY = panelHeight / 2;
    int stage = 0;
    int hp = 5;
    int arrowWidth = 8;
    int arrowHeight = 20;
    boolean isGameOver = false; // New variable to track if the game is over
    Clip collisionClip; // New Clip variable for collision sound

    GamePanel() {
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
        stageLabel = new JLabel("Stage:" + (stage + 1));
        stageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        stageLabel.setBounds(0, 0, 100, 24);
        this.add(stageLabel);
        this.addKeyListener(new KeyListener());

        arrows = new ArrayList<>();
        random = new Random();

        heart = new Heart();

        character = new Character(centerX - 10, centerY - 10, 20, 20);
        shield = new Shield();
        shield.setBounds(0, 0, panelWidth, panelHeight);
        this.setLayout(null);
        this.add(shield);

        timer = new Timer(30, this);
    }

    public void startGame() {
        isGameOver = false; // Reset game over status on game start
        timer.start();

        int frequency = 700 - 50 * stage;

        arrowTimer = new Timer(frequency, e -> spawnArrow());
        stopTimer = new Timer(10000, e -> arrowTimer.stop());
        stageTimer = new Timer(14000, e -> nextStage());
        arrowTimer.start();
        stopTimer.start();
        stageTimer.start();
    }

    public void nextStage() {
        if (hp > 0) {
            arrowTimer.stop();
            stopTimer.stop();
            stageTimer.stop();

            // Update the stage label immediately
            stage++;
            this.remove(stageLabel);
            stageLabel = new JLabel("Stage:" + (stage + 1));
            stageLabel.setFont(new Font("Arial", Font.BOLD, 20));
            stageLabel.setBounds(0, 0, 100, 24);
            this.add(stageLabel);
            this.repaint(); // Ensure the label update is displayed immediately

            // Play the next stage sound with a 2-second delay before starting the next stage
            playSound("sounds/nextstage.wav", 2000);

            // Use a Timer to delay the start of the next stage
            Timer delayTimer = new Timer(2000, e -> {
                startGame(); // Start the game after sound delay
            });
            delayTimer.setRepeats(false); // Only run the timer once
            delayTimer.start();
        } else {
            gameOver();
        }
    }

    public void spawnArrow() {
        int position = random.nextInt(4);
        int x = 0;
        int y = 0;
        int rotation = 0;


        switch (position) {
            case 0:
                x = centerX - arrowWidth / 2;
                y = 0;
                rotation = 0;
                break;
            case 1:
                x = centerX - arrowWidth / 2;
                y = panelHeight - arrowHeight / 2;
                rotation = 180;
                break;
            case 2:
                x = 0;
                y = centerY - arrowHeight / 2;
                rotation = 90;
                break;
            case 3:
                x = panelWidth - arrowWidth / 2;
                y = centerY - arrowHeight / 2;
                rotation = 270;
                break;
        }

        boolean magic = random.nextInt(100) < 20 + 2 * stage;
        RotatableArrow arrow;
        if (magic) {
            arrow = new MagicArrow(x, y, arrowWidth, arrowHeight, rotation);
        } else {
            arrow = new NorArrow(x, y, arrowWidth, arrowHeight, rotation);
        }
        arrows.add(arrow);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        character.draw((Graphics2D) g);

        for (RotatableArrow arrow : arrows) {
            arrow.draw(g);
        }

        heart.drawHeart(g, hp);
    }

    public void playSound(String filePath, int duration) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            Timer stopTimer = new Timer(duration, e -> {
                clip.stop();
                clip.close();
            });
            stopTimer.setRepeats(false);
            stopTimer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playCollisionSound() {
        try {
            if (collisionClip != null && collisionClip.isActive()) {
                return; // Avoid overlapping by checking if the collision sound is already playing
            }
            File soundFile = new File("sounds/collision.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            collisionClip = AudioSystem.getClip();
            collisionClip.open(audioStream);
            collisionClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameOver) return; // Prevent further actions if game is over

        Iterator<RotatableArrow> iterator = arrows.iterator();
        while (iterator.hasNext()) {
            RotatableArrow arrow = iterator.next();
            moveArrow(arrow);

            if (arrow.intersects(character)) {
                playCollisionSound();
                iterator.remove();
                if (arrow instanceof NorArrow) {
                    hp--;
                }
                continue;
            }
            if (shield.intersects(arrow)) {
                playCollisionSound();
                iterator.remove();
                if (arrow instanceof MagicArrow) {
                    hp--;
                }
            }
        }
        if (hp <= 0 && !isGameOver) { // Play game over sound only once
            playSound("sounds/gameover.wav", 3000);
            gameOver();
        }
        repaint();
    }

    public void moveArrow(Rectangle arrow) {
        int deltaX = centerX - arrow.x - arrowWidth / 2;
        int deltaY = centerY - arrow.y - arrowHeight / 2;
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        int speed = 10 + 2 * stage;

        int moveX = (int) (speed * (deltaX / distance));
        int moveY = (int) (speed * (deltaY / distance));

        arrow.x += moveX;
        arrow.y += moveY;
    }

    private class KeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyChar()) {
                case 'w':
                    shield.setAngle(270);
                    break;
                case 'a':
                    shield.setAngle(180);
                    break;
                case 's':
                    shield.setAngle(90);
                    break;
                case 'd':
                    shield.setAngle(0);
                    break;
            }
        }
    }

    public void gameOver() {
        isGameOver = true; // Set game over status
        JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parent != null) {
            parent.remove(this);
            GameOverPanel gameOverPanel = new GameOverPanel();
            gameOverPanel.setReplayButtonListener(e -> {
                parent.remove(gameOverPanel);
                GamePanel newGamePanel = new GamePanel();
                parent.add(newGamePanel);
                newGamePanel.requestFocusInWindow();
                parent.revalidate();
                parent.repaint();
                newGamePanel.startGame();
            });
            parent.add(gameOverPanel);
            parent.revalidate();
            parent.repaint();
        }
    }
}
