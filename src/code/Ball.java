package code;

public class Ball {
    private int ballX; // x coordinate of ball
    private int ballY; // y coordinate of ball
    private int ballXDirection; // x direction of ball
    private int ballYDirection; // y direction of ball

    public Ball(int x, int y, int xDirection, int yDirection) {
        ballX = x;
        ballY = y;
        ballXDirection = xDirection;
        ballYDirection = yDirection;
    }

    // getters and setters
    public int getBallX() {
        return ballX;
    }

    public void setBallX(int x) {
        ballX = x;
    }

    public int getBallY() {
        return ballY;
    }

    public void setBallY(int y) {
        ballY = y;
    }

    public int getBallXDirection() {
        return ballXDirection;
    }

    public void setBallXDirection(int xDirection) {
        ballXDirection = xDirection;
    }

    public int getBallYDirection() {
        return ballYDirection;
    }

    public void setBallYDirection(int yDirection) {
        ballYDirection = yDirection;
    }

    // move the ball
    public void move() {
        ballX += ballXDirection;
        ballY += ballYDirection;

        if (ballX < 0) { // top border
            ballXDirection = -ballXDirection;
        }
        if (ballY < 0) { // left border
            ballYDirection = -ballYDirection;
        }
        if (ballX > 670) { // right border
            ballXDirection = -ballXDirection;
        }
    }
}
