package Factory;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import entities.Wall;
import entities.Food;
import entities.PacMan;
import entities.Ghost;

public class EntityFactory {
    private final int tileSize;
    private final Image wallImg, pacUp, pacDown, pacLeft, pacRight;
    private final Image blueGhost, orangeGhost, pinkGhost, redGhost;

    public EntityFactory(int tileSize) {
        this.tileSize = tileSize;
        wallImg     = load("wall.png");
        pacUp       = load("pacmanUp.png");
        pacDown     = load("pacmanDown.png");
        pacLeft     = load("pacmanLeft.png");
        pacRight    = load("pacmanRight.png");
        blueGhost   = load("blueGhost.png");
        orangeGhost = load("orangeGhost.png");
        pinkGhost   = load("pinkGhost.png");
        redGhost    = load("redGhost.png");
    }

    private Image load(String fileName) {
        try {

            return ImageIO.read(new File("photos/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Wall createWall(int x, int y) {
        return new Wall(x, y, tileSize, tileSize, wallImg);
    }
    public Food createFood(int x, int y) {
        return new Food(x, y, 4, 4);
    }
    public PacMan createPacMan(int x, int y) {
        return new PacMan(x, y, tileSize, pacUp, pacDown, pacLeft, pacRight);
    }
    public Ghost createGhost(char type, int x, int y) {
        Image img;
        switch (type) {
            case 'b': img = blueGhost;   break;
            case 'o': img = orangeGhost; break;
            case 'p': img = pinkGhost;   break;
            case 'r': img = redGhost;    break;
            default : img = blueGhost;   break;
        }
        return new Ghost(x, y, tileSize, img);
    }
}
