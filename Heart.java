import java.awt.*;
import javax.swing.*;

public class Heart {
    
    Image heartImage;

    // Constructor to initialize the heart image
    public Heart(int initialHp) {
        heartImage = new ImageIcon("Heart.png").getImage();
    }

    // Method to draw hearts representing the player's current hp
    public void drawHeart(Graphics g, int hp) {
        int heartWidth = 100;
        int heartHeight = 100;
        int lifeSpace = 10;

        for (int i = 0; i < hp; i++) {
            g.drawImage(heartImage, 500 + (i * lifeSpace), -40, heartWidth, heartHeight, null);
        }
    }
}