package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;

import entities.Food;
import entities.MovableEntity;
import entities.PacMan;

public class ScoreBoard {
    private int score;
    private int lives;
    private final int tileSize;
    private boolean gameOver;

    public ScoreBoard(int tileSize) {
        this.tileSize = tileSize;
        reset();
    }

    /** Сбрасывает очки, жизни и флаг gameOver */
    public void reset(){
        score    = 0;
        lives    = 3;
        gameOver = false;
    }

    public void draw(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, tileSize / 2));
        g.setColor(Color.WHITE);
        g.drawString("Lives: " + lives + "   Score: " + score,
                tileSize/4, tileSize/2);
    }

    /**
     * Проверяет столкновения.
     * @return true, если это последняя (третья) смерть и нужно Game Over.
     */
    public boolean checkCollisions(
            PacMan pacMan,
            List<MovableEntity> ghosts,
            List<Food> foods
    ) {
        // 1) столкновение с призраками
        for (MovableEntity ghost : ghosts) {
            if (pacMan.getBounds().intersects(ghost.getBounds())) {
                lives--;
                if (lives > 0) {
                    // ещё есть жизни — сбрасываем на старт
                    pacMan.resetPosition();
                    ghosts.forEach(MovableEntity::resetPosition);
                } else {
                    // жизней не осталось — игра окончена
                    gameOver = true;
                    return true;
                }
                break;
            }
        }

        // 2) съедание еды
        Iterator<Food> it = foods.iterator();
        while (it.hasNext()) {
            Food f = it.next();
            if (pacMan.getBounds().intersects(f.getBounds())) {
                score += 10;
                it.remove();
            }
        }

        return false;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
