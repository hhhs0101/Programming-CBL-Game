import java.awt.*;
import javax.swing.*;

class Shield extends JComponent {
    public int circleRadius = 20;
    private int angle = 0;  

    public void setAngle(int angle) {
        this.angle = angle;  
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawRotatedShield(g);
    }

    public void drawRotatedShield(Graphics g) {
        Graphics2D s = (Graphics2D) g;
        int shieldWidth = 10;
        int shieldHeight = 40;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        double radian = Math.toRadians(angle);
        int offsetX = (int) (circleRadius * Math.cos(radian));
        int offsetY = (int) (circleRadius * Math.sin(radian));
        s.translate(centerX, centerY);
        s.translate(offsetX, offsetY); 
        s.rotate(radian);
        s.setColor(Color.BLACK);
        s.drawRect(-shieldWidth / 2, -shieldHeight / 2, shieldWidth, shieldHeight);  
    }

    public boolean intersects(Rectangle arrow) {
        int shieldCenterX = getWidth() / 2 + (int) (circleRadius * Math.cos(Math.toRadians(angle)));
        int shieldCenterY = getHeight() / 2 
            + (int) (circleRadius * Math.sin(Math.toRadians(angle)));
        int shieldWidth = 10;
        int shieldHeight = 10;

        Rectangle shieldBounds = new Rectangle(shieldCenterX - shieldWidth / 2,
            shieldCenterY - shieldHeight / 2, shieldWidth, shieldHeight);
        return shieldBounds.intersects(arrow);
    }
}
