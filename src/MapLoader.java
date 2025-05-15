import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import Factory.EntityFactory;
import entities.Entity;
import entities.Food;
import entities.MovableEntity;
import entities.PacMan;

public class MapLoader {
    private final List<Entity> walls   = new ArrayList<>();
    private final List<Food> foods     = new ArrayList<>();
    private final List<MovableEntity> ghosts = new ArrayList<>();
    private final PacMan pacMan;

    public MapLoader(String[] tileMap, int tileSize) {
        EntityFactory factory = new EntityFactory(tileSize);
        Random rnd = new Random();
        PacMan tmpPac = null;

        for (int r = 0; r < tileMap.length; r++) {
            String row = tileMap[r];
            for (int c = 0; c < row.length(); c++) {
                char ch = row.charAt(c);
                int x = c * tileSize;
                int y = r * tileSize;

                switch (ch) {
                    // и X — это непроходимая стена
                    case 'X':
                        walls.add(factory.createWall(x, y));
                        break;

                    // пробел — еда
                    case ' ':
                        foods.add(factory.createFood(x + tileSize/2 - 2,
                                y + tileSize/2 - 2));
                        break;

                    // P — Pac-Man
                    case 'P':
                        tmpPac = factory.createPacMan(x, y);
                        break;

                    // b, o, p, r — призраки разных цветов
                    case 'b': case 'o': case 'p': case 'r':
                        ghosts.add(factory.createGhost(ch, x, y));
                        break;

                    // всё остальное игнорируем
                    default:
                        break;
                }
            }
        }

        this.pacMan = tmpPac;

        // Задаём каждому призраку случайное начальное направление
        char[] dirs = {'U','D','L','R'};
        for (MovableEntity g : ghosts) {
            g.updateDirection(dirs[rnd.nextInt(dirs.length)]);
        }
    }

    public List<Entity> getWalls()   { return walls; }
    public List<Food> getFoods()     { return foods; }
    public List<MovableEntity> getGhosts() { return ghosts; }
    public PacMan getPacMan()        { return pacMan; }
}
