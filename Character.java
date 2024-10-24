import java.awt.*;


class Character extends Rectangle {
    Character(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, width, height);
    }
}