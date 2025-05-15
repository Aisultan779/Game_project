package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    protected int x, y, width, height;

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public abstract void draw(Graphics g);
}
