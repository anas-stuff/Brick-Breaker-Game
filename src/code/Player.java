package code;

public class Player {
    private String name; // player name
    private int playerX; // x coordinate of player
    private int speed; // speed of player

    public Player(String name, int playerX) {
        this.name = name;
        this.playerX = playerX;
        this.speed = 30;
    }

    public String getName() {
        return name;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    // move player to the right
    public void moveRight() {
        playerX += speed;
    }

    // move player to the left
    public void moveLeft() {
        playerX -= speed;
    }
}
