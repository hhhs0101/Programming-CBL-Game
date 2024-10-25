import java.awt.*;
import javax.swing.*;

public class Heart {
    
    Image heartImage;

    public Heart() {
        heartImage = new ImageIcon("Heart.png").getImage();
    }

    public void drawHeart(Graphics g, int hp) {
        int heartWidth = 100;
        int heartHeight = 100;
        int lifeSpace = 10;

        for (int i = 0; i < hp; i++) {
            g.drawImage(heartImage, 500 + (i * lifeSpace), -40, heartWidth, heartHeight, null);
        }
    }
}