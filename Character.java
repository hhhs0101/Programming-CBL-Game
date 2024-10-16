import java.awt.*;
import javax.swing.*;



public class Character extends JComponent{

    public int circleRadius = 20;
    
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(0, 0, 40, 40);
    }

}
