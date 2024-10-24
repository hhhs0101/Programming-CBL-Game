import java.awt.*;
import java.awt.event.*;
import java.util.*;
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

    int panelWidth = 600;
    int panelHeight = 600;
    int centerX = panelWidth / 2;
    int centerY = panelHeight / 2;
    int stage = 0;
    int hp = 5;


    GamePanel() {
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setLayout(null);
        stageLabel = new JLabel("Stage:" + stage);
        stageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        stageLabel.setBounds(0, 0, 100, 24);
        this.add(stageLabel);
        this.addKeyListener(new KeyListener());

        arrows = new ArrayList<>();
        random = new Random();

        character = new Character(centerX - 10, centerY - 10, 20, 20);
        shield = new Shield();
        shield.setBounds(0, 0, panelWidth, panelHeight);
        this.setLayout(null);
        this.add(shield);

        timer = new Timer(30, this);
    }

    public void startGame() {

        timer.start();

        arrowTimer = new Timer(1000, e -> spawnArrow());
        stopTimer = new Timer(10000, e -> arrowTimer.stop());
        stageTimer = new Timer(14000, e -> nextStage());
        arrowTimer.start();
        stopTimer.start();
        stageTimer.start();
              
    }
    
    public void nextStage(){
        if (hp > 0) {
            arrowTimer.stop();
            stopTimer.stop();
            stageTimer.stop();
            stage++;
            this.remove(stageLabel);
            stageLabel = new JLabel("Stage:" + stage);
            stageLabel.setFont(new Font("Arial", Font.BOLD, 20));
            stageLabel.setBounds(0, 0, 100, 24);
            this.add(stageLabel);
            startGame();
        } else {
            System.out.println("Game Over");
        }
    }

    public void spawnArrow() {
        int position = random.nextInt(4); 
        int x = 0;
        int y = 0;
        int rotation = 0;

        switch (position) {
            case 0: 
                x = centerX - 2;
                y = 0;
                rotation = 0;
                break;
            case 1: 
                x = centerX - 2;
                y = panelHeight - 5;
                rotation = 180;
                break;
            case 2: 
                x = 0;
                y = centerY - 5;
                rotation = 90;
                break;
            case 3: 
                x = panelWidth - 2;
                y = centerY - 5;
                rotation = 270;
                break;
        }

        boolean magic = random.nextInt(100) < 20; 
        RotatableArrow arrow;
        if (magic) {
            arrow = new MagicArrow(x, y, 4, 10, rotation);
        } else {
            arrow = new NorArrow(x, y, 4, 10, rotation);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator<RotatableArrow> iterator = arrows.iterator();
        while (iterator.hasNext()) {
            RotatableArrow arrow = iterator.next();
            moveArrow(arrow);
            
            if (arrow instanceof MagicArrow) {
                if (arrow.intersects(character)) {
                    iterator.remove(); 
                    System.out.println("Magic arrow collides with character");
                    continue;
                }
                if (shield.intersects(arrow)) {
                    iterator.remove(); 
                    System.out.println("Magic arrow collides with shield "  + hp);
                    hp--;
                }
            } else if (arrow instanceof NorArrow) {
                if (arrow.intersects(character)) {
                    iterator.remove(); 
                    System.out.println("Normal arrow collides with character " + hp);
                    hp--;
                    continue;
                }
                if (shield.intersects(arrow)) {
                    iterator.remove(); 
                    System.out.println("Normal arrow collides with shield");
                }
            }

        }
        repaint();
    }
    
    public void moveArrow(Rectangle arrow) {
        int deltaX = centerX - arrow.x;
        int deltaY = centerY - arrow.y;
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
}

