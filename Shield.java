import java.awt.*;
import javax.swing.*;

public class Shield extends JComponent {
   
    public double circleRadius = 20;

    private int angle = 0;
    
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D s = (Graphics2D) g; 
        int rectWidth = 10;
        int rectHeight = 40;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        s.translate(centerX, centerY);
        double radian = Math.toRadians(angle);
        int offsetX = (int) (circleRadius * Math.cos(radian));
        int offsetY = (int) (circleRadius * Math.sin(radian));
        s.translate(offsetX, offsetY);
        s.rotate(radian);
        s.setColor(Color.BLACK);
        s.fillRect(-rectWidth / 2, -rectHeight / 2, 10, 40);
    }
}