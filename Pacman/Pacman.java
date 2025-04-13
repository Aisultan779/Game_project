import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;
public class PacMan extends JPanel {
    private int rowCount = 21;
    private int columnCount = 19;
    private int tileSize = 32;
    private int boardWidth = columnCount * tileSize;
    private int boardHeight = rowCount * tileSize;

    private Image wallImage;
    private Image blueGhostImage;
    private Image orangeGhostImage;
    private Image redGhostImage;
    private Image pinkGhostImage;

    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image pacmanRightImage;

    PacMan(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.BLACK);
        wallImage = newImageIcon(getClass().getResource("./wall.png")).getImage();
        blueGhostImage = newImageIcon(getClass().getResource("./blueGhost.png")).getImage();
        redGhostImage = newImageIcon(getClass().getResource("./redGhost.png")).getImage();
        orangeGhostImage = newImageIcon(getClass().getResource("./orangeGhost.png")).getImage();
        pinkGhostImage = newImageIcon(getClass().getResource("./pinkGhost.png")).getImage();

        pacmanUpImage = newImageIcon(getClass().getResource("./pacmanUp.png")).getImage();
        pacmanDownImage = newImageIcon(getClass().getResource("./pacmanDown.png")).getImage();
        pacmanLeftImage = newImageIcon(getClass().getResource("./pacmanLeft.png")).getImage();
        pacmanRightImage = newImageIcon(getClass().getResource("./pacmanRight.png")).getImage();






    }
}
