import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import entities.Entity;
import entities.Food;
import entities.MovableEntity;
import entities.PacMan;
import input.InputHandler;
import ui.ScoreBoard;

public class GamePanel extends JPanel implements ActionListener {
    private static final int ROWS = 21, COLS = 19, TILE = 32;
    private Timer timer;
    private List<Entity> walls;
    private List<Food> foods;
    private List<MovableEntity> ghosts;
    private PacMan pacMan;
    private ScoreBoard scoreBoard;

    public GamePanel() {
        setPreferredSize(new Dimension(COLS * TILE, ROWS * TILE));
        setBackground(Color.BLACK);

        MapLoader loader = new MapLoader(getMap(), TILE);
        walls   = loader.getWalls();
        foods   = loader.getFoods();
        ghosts  = loader.getGhosts();
        pacMan  = loader.getPacMan();

        scoreBoard = new ScoreBoard(TILE);
        addKeyListener(new InputHandler(pacMan));
        setFocusable(true);
        requestFocusInWindow();
    }

    public void startGame() {
        timer = new Timer(60, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. игровое поле
        walls.forEach(w -> w.draw(g));
        foods.forEach(f -> f.draw(g));
        ghosts.forEach(gst -> gst.draw(g));
        pacMan.draw(g);
        scoreBoard.draw(g);

        // 2. Если игра окончена — выводим «GAME OVER»
        if (scoreBoard.isGameOver()) {
            String msg = "GAME OVER";

            // устанавливаем шрифт
            Font font = new Font("Arial", Font.BOLD, 48);
            g.setFont(font);
            g.setColor(Color.RED);

            // получаем размеры текста
            FontMetrics fm = g.getFontMetrics(font);
            int textWidth  = fm.stringWidth(msg);
            int textHeight = fm.getHeight();

            // вычисляем точку (x,y) для центрирования
            int x = (getWidth()  - textWidth)  / 2;
            int y = (getHeight() - textHeight) / 2 + fm.getAscent();

            g.drawString(msg, x, y);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!scoreBoard.isGameOver()) {
            pacMan.update(walls);
            ghosts.forEach(g -> g.update(walls));
            scoreBoard.checkCollisions(pacMan, ghosts, foods);
        }
        repaint();
    }

    private String[] getMap() {
        return new String[] {
                "XXXXXXXXXXXXXXXXXXX",
                "X        X        X",
                "X XX XXX X XXX XX X",
                "X                 X",
                "X XX X XXXXX X XX X",
                "X    X       X    X",
                "XXXX XXXX XXXX XXXX",
                "OOOX X       X XOOO",
                "XXXX  X r  X   XXXX",
                "X       bpo       X",
                "XXXX X X   X X XXXX",
                "OOOX X       X XOOO",
                "XXXX X XXXXX X XXXX",
                "X        X        X",
                "X XX XXX X XXX XX X",
                "X  X     P     X  X",
                "XX X X XXXXX X X XX",
                "X    X   X   X    X",
                "X XXXXXX X XXXXXX X",
                "X                 X",
                "XXXXXXXXXXXXXXXXXXX"
        };
    }
}
