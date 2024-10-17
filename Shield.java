import java.awt.*;
import javax.swing.*;

public class Shield extends JComponent {
    
    public int circleRadius = 20;
    private int angle = 0;  

    public void setAngle(int angle) {
        this.angle = angle;  
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawRotatedRectangle(g);
    }

    public void drawRotatedRectangle(Graphics g) {
        Graphics2D s = (Graphics2D) g;
        int rectWidth = 10;
        int rectHeight = 40;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;


        double radian = Math.toRadians(angle);
        int offsetX = (int) (circleRadius * Math.cos(radian));
        int offsetY = (int) (circleRadius * Math.sin(radian));
        s.translate(centerX, centerY);
        s.translate(offsetX, offsetY); 
        s.rotate(radian);
        s.setColor(Color.BLACK);
        s.fillRect(-rectWidth / 2, -rectHeight / 2, rectWidth, rectHeight);  
    }
}