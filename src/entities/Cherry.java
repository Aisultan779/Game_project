package entities;

import java.awt.Graphics;
import java.awt.Image;

public class Cherry extends Entity {
    private final Image image;

    public Cherry(int x, int y, int size, Image img) {
        this.x = x; this.y = y;
        this.width = this.height = size;
        this.image = img;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
