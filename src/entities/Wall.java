package entities;

import java.awt.Graphics;
import java.awt.Image;

public class Wall extends Entity {
    private final Image image;

    public Wall(int x, int y, int width, int height, Image image) {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }
}
