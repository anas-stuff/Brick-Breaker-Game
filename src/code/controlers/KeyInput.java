package code.controlers;

import code.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    private Game game;

    public KeyInput(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D
                || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (game.getPlayer().getPlayerX() >= 600) {
                game.getPlayer().setPlayerX(600);
            } else {
                game.setGameStarted(true);
                game.getPlayer().moveRight();
            }
            System.out.println("Right");
        }
        if (e.getKeyCode() == KeyEvent.VK_A
                || e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (game.getPlayer().getPlayerX() < 10) {
                game.getPlayer().setPlayerX(10);
            } else {
                game.setGameStarted(true);
                game.getPlayer().moveLeft();
            }
            System.out.println("Left");
        }

        // Restart game
        if ((e.getKeyCode() == KeyEvent.VK_ENTER
            || e.getKeyCode() == KeyEvent.VK_SPACE) && !game.isGameStarted()) {
                game.reInit();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
