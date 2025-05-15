package entities;

import java.awt.Graphics;
import java.util.List;

public abstract class MovableEntity extends Entity {
    protected int velocityX, velocityY;
    protected char direction;

    // Новые поля для старта
    protected int startX, startY;

    /** Вызывается в update() для движения */
    public void move(List<Entity> walls) {
        x += velocityX;
        y += velocityY;
        for (Entity w : walls) {
            if (getBounds().intersects(w.getBounds())) {
                x -= velocityX;
                y -= velocityY;
                onCollision(w);
                break;
            }
        }
    }

    /** Если нужно — переопределяйте; по умолчанию ничего не делаем */
    protected void onCollision(Entity wall) { }

    /** Метод для старта/рестарта на исходной позиции */
    public void resetPosition() {
        this.x = startX;
        this.y = startY;
        this.velocityX = this.velocityY = 0;
    }

    protected void initStartPosition() {
        this.startX = this.x;
        this.startY = this.y;
    }

    public abstract void update(List<Entity> walls);

    public abstract void updateDirection(char newDir);

    @Override
    public abstract void draw(Graphics g);
}
