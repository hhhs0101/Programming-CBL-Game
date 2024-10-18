import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MagicArrow extends JComponent implements ArrowsInteraction{
  
    private int arrowWidth = 4;
    private int arrowHeight = 10;
    private int frameSize;
    private int arrowVelo = 10;
    int arrowX = frameSize / 2 - 2;
    int arrowY = frameSize / 2 - 5;


    public void getFrameSize(int frameSize) {
        this.frameSize = frameSize;  
    }

    @Override
    public void meetChar() {

    }

    @Override 
    public void meetShield() {


    }

    public void norArrowVisual(Graphics g) {


        g.setColor(Color.BLUE);
        g.fillRect(frameSize / 2 - 2, frameSize / 2 - 5, arrowWidth, arrowHeight);  
    }

    public void arrowAni() {

        Timer timer = new Timer(30, new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                veloOnArrow();
                repaint();
            }
        });

        timer.start(); 
    }

    public void veloOnArrow() {
        arrowX -= arrowVelo;
    }
    
    
}