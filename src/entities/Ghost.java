package entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

public class Ghost extends MovableEntity {
    private final Image image;
    private final Random rnd = new Random();
    private static final char[] DIRS = {'U','D','L','R'};
    private final int step;

    public Ghost(int x, int y, int size, Image img) {
        this.x      = x;
        this.y      = y;
        this.width  = this.height = size;
        this.step   = size / 4;
        this.image  = img;

        // стартовое случайное направление
        updateDirection(DIRS[rnd.nextInt(DIRS.length)]);
        initStartPosition();
    }

    /** Меняем speed внутри STEP */
    @Override
    public void updateDirection(char newDir) {
        switch (newDir) {
            case 'U': velocityX = 0;     velocityY = -step; break;
            case 'D': velocityX = 0;     velocityY =  step; break;
            case 'L': velocityX = -step; velocityY =  0;    break;
            case 'R': velocityX =  step; velocityY =  0;    break;
        }
        this.direction = newDir;
    }

    /** Вызывается каждый тик */
    @Override
    public void update(List<Entity> walls) {
        // меняем направление только на границах клеток
        if (x % width == 0 && y % height == 0) {
            // если впереди стена или захотели случайно повернуть
            if (!canMove(direction, walls) || rnd.nextInt(10) == 0) {
                char nd = DIRS[rnd.nextInt(DIRS.length)];
                updateDirection(nd);
            }
        }
        super.move(walls);
    }

    /** Проверка прохода в dir */
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
        g.drawImage(image, x, y, width, height, null);
    }

    /** При чисто пиксельном столкновении ничего не делаем */
    @Override
    protected void onCollision(Entity wall) { }
}
