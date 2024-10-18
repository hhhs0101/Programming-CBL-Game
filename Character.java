
import java.awt.*;
import javax.swing.*;

public class Character extends JComponent {
    public int circleRadius = 20;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawCharacter(g);
    }

    public void drawCharacter(Graphics g) {
        
        int centerX = getWidth() / 2;
        int centerY = centerX;

        g.setColor(Color.RED);
        g.fillOval(centerX - 10, centerY - 10, circleRadius, circleRadius);

    }
}