package code;

import code.controlers.KeyInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JPanel implements ActionListener {
    private int score;
    private boolean gameStarted; // true if game is started
    private boolean win; // true if win
    private Timer timer; // timer for game
    private int delay; // delay of timer
    private Player player; // player
    private Ball ball; // ball
    private MapGenerator mapGenerator; // map generator
    private int totalBricks; // total number of bricks
    private boolean gameOver; // true if game is over
    private String gameName = "Brick Breaker Game";

    public Game() {
        // set up game
        initGame();
        // Crate a window
        new Window(gameName, 700, 600, this);

        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        addKeyListener(new KeyInput(this));

        // start timer
        timer.start();
    }

    private void initGame() {
        gameStarted = false;
        gameOver = false;
        win = false;
        score = 0;
        // map generator
        mapGenerator = new MapGenerator(3, 7);
        // player
        player = new Player("", 310);
        // ball
        ball = new Ball((int)(Math.random() * (600 - 1) + 1),
                (int)(Math.random() * (player.getPlayerX() + 20 - mapGenerator.getMapHeight() + 50) + mapGenerator.getMapHeight() + 50),
                -1, -2);
        // bricks
        totalBricks = 21;
        // timer
        delay = 8;
        // create timer
        timer = new Timer(delay, this);
    }

    public void reInit() {
        initGame();
    }

    @Override
    public void paint(Graphics g) {
        // draw background
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, 692, 592);
        // border
        g.setColor(Color.YELLOW);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);

        // draw map
        mapGenerator.draw((Graphics2D) g);

        // draw player
        g.setColor(Color.GREEN);
        g.fillRect(player.getPlayerX(), 550, 100, 8);

        // draw ball
        g.setColor(Color.YELLOW);
        g.fillOval(ball.getBallX(), ball.getBallY(), 20, 20);

        // draw score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Score: " + score, 5, 25);

        // draw game over
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Game Over, Your Score: " + score, 100, 300);
            g.drawString("Press Enter to Replay", 150, 350);
        }

        // draw win
        if (win) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("You Win, Your Score: " + score, 100, 300);
            g.drawString("Press Enter to Replay", 150, 350);
        }
        // dispose of graphics
        g.dispose();

    }

    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // start timer
        timer.start();

        // move ball
        if (gameStarted) {
            // if ball intersects with player
            if (new Rectangle(ball.getBallX(), ball.getBallY(), 20, 20)
                    .intersects(new Rectangle(player.getPlayerX(), 550, 100, 8))) {
                ball.setBallYDirection(-ball.getBallYDirection());
            }
            // if ball intersects with bricks
            loo: for (int i = 0; i < mapGenerator.getMap().length; i++) {
                for (int j = 0; j < mapGenerator.getMap()[0].length; j++) {
                    if (mapGenerator.getMap()[i][j] > 0) {
                        int brickX = j * mapGenerator.getBlockWidth() + 80;
                        int brickY = i * mapGenerator.getBlockHeight() + 50;

                        // if ball intersects with brick
                        if (new Rectangle(ball.getBallX(), ball.getBallY(), 20, 20)
                                .intersects(new Rectangle(brickX, brickY, mapGenerator.getBlockWidth(), mapGenerator.getBlockHeight()))) {
                            mapGenerator.getMap()[i][j] = 0;
                            totalBricks--;
                            score += 5;

                            if (ball.getBallX() + 19 <= brickX
                                    || ball.getBallX() + 1 >= brickX + mapGenerator.getBlockWidth()) {
                                ball.setBallXDirection(-ball.getBallXDirection());
                            } else {
                                ball.setBallYDirection(-ball.getBallYDirection());
                            }
                            break loo;
                        }
                    }
                }
            }

            ball.move();
            // Check game over
            if (ball.getBallY() > 570) {
                gameStarted = false;
                gameOver = true;
                ball.setBallYDirection(0);
                ball.setBallXDirection(0);
            }
            // win
            if (totalBricks == 0) {
                win = true;
                gameStarted = false;
            }
        }

        // repaint
        repaint();
    }

    public Player getPlayer() {
        return player;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }
}
