package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import entities.PacMan;

public class InputHandler implements KeyListener {
    private final PacMan pacMan;

    public InputHandler(PacMan pacMan) {
        this.pacMan = pacMan;
    }

    @Override public void keyTyped(KeyEvent e)  { }
    @Override public void keyPressed(KeyEvent e){ }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:    pacMan.setDesiredDirection('U'); break;
            case KeyEvent.VK_DOWN:  pacMan.setDesiredDirection('D'); break;
            case KeyEvent.VK_LEFT:  pacMan.setDesiredDirection('L'); break;
            case KeyEvent.VK_RIGHT: pacMan.setDesiredDirection('R'); break;
        }
    }
}
