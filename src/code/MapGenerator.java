package code;

import java.awt.*;
import java.util.Arrays;

public class MapGenerator {
    private int[][] map;
    private int blockWidth;
    private int blockHeight;
    private byte border;

    public MapGenerator(int rows, int columns) {
        map = new int [rows][columns];
        blockWidth = 540 / columns;
        blockHeight = 150 / rows;
        border = 3;
        // Fill map with 1s
        fillMap();
    }

    public void fillMap() {
        for (int[] ints : map) {
            Arrays.fill(ints, 1);
        }
    }

    public void draw(Graphics2D g2d) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] > 0) {
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(j * blockWidth + 80, i * blockHeight + 50, blockWidth, blockHeight);

                    // Draw border
                    g2d.setStroke(new BasicStroke(border));
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(j * blockWidth + 80, i * blockHeight + 50, blockWidth, blockHeight);
                }
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    public int getBlockHeight() {
        return blockHeight;
    }
    public int getMapHeight() {
        return blockHeight * map.length;
    }
}
