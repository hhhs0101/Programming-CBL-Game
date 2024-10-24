import java.awt.*;

abstract class RotatableArrow extends Rectangle {
    int rotation;

    RotatableArrow(int x, int y, int width, int height, int rotation) {
        super(x, y, width, height);
        this.rotation = rotation;
    }

    public abstract void draw(Graphics g);
}

class MagicArrow extends RotatableArrow {
    MagicArrow(int x, int y, int width, int height, int rotation) {
        super(x, y, width, height, rotation);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);
        g2d.rotate(Math.toRadians(rotation), x + width / 2.0, y + height / 2.0);
        g2d.fillRect(x, y, width, height);
        g2d.rotate(-Math.toRadians(rotation), x + width / 2.0, y + height / 2.0);
    }
}

class NorArrow extends RotatableArrow {
    NorArrow(int x, int y, int width, int height, int rotation) {
        super(x, y, width, height, rotation);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GREEN);
        g2d.rotate(Math.toRadians(rotation), x + width / 2.0, y + height / 2.0);
        g2d.fillRect(x, y, width, height);
        g2d.rotate(-Math.toRadians(rotation), x + width / 2.0, y + height / 2.0);
    }
}