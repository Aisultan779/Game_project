package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

public class PacMan extends MovableEntity {
    private final Image imgUp, imgDown, imgLeft, imgRight;
    private Image currentImage;
    private char desiredDirection;
    private final int step;

    public PacMan(int x, int y, int size,
                  Image imgUp, Image imgDown,
                  Image imgLeft, Image imgRight) {
        this.x      = x;
        this.y      = y;
        this.width  = this.height = size;
        this.step   = size / 4;

        this.imgUp    = imgUp;
        this.imgDown  = imgDown;
        this.imgLeft  = imgLeft;
        this.imgRight = imgRight;

        this.currentImage     = imgRight;
        this.desiredDirection = 'R';
        this.velocityX = this.velocityY = 0;

        initStartPosition();  // сохраняем startX, startY
    }

    /** Вызывается из InputHandler при нажатии стрелок */
    public void setDesiredDirection(char dir) {
        this.desiredDirection = dir;
    }

    /** Game loop вызывает update каждый тик */
    @Override
    public void update(List<Entity> walls) {
        // Смена направления только ровно на границах клеток
        if (x % width == 0 && y % height == 0) {
            if (canMove(desiredDirection, walls)) {
                updateDirection(desiredDirection);
            } else if (!canMove(direction, walls)) {
                // некуда идти — стоим
                velocityX = velocityY = 0;
                return;
            }
        }
        // движение по установленной скорости
        super.move(walls);
    }

    /** Меняем speed и sprite */
    @Override
    public void updateDirection(char newDir) {
        switch (newDir) {
            case 'U': velocityX = 0;     velocityY = -step; break;
            case 'D': velocityX = 0;     velocityY =  step; break;
            case 'L': velocityX = -step; velocityY =  0;    break;
            case 'R': velocityX =  step; velocityY =  0;    break;
        }
        this.direction = newDir;

        switch (newDir) {
            case 'U': currentImage = imgUp;    break;
            case 'D': currentImage = imgDown;  break;
            case 'L': currentImage = imgLeft;  break;
            case 'R': currentImage = imgRight; break;
        }
    }

    /** Проверка, не врежемся ли в стену */
    private boolean canMove(char dir, List<Entity> walls) {
        int nx = x + (dir == 'L' ? -step : dir == 'R' ? step : 0);
        int ny = y + (dir == 'U' ? -step : dir == 'D' ? step : 0);
        Rectangle next = new Rectangle(nx, ny, width, height);

        for (Entity w : walls) {
            if (next.intersects(w.getBounds())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(currentImage, x, y, width, height, null);
    }

    /** Ничего не делаем при пиксельном столкновении */
    @Override
    protected void onCollision(Entity wall) { }
}
