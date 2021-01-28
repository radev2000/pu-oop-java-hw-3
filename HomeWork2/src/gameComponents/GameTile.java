package gameComponents;

import java.awt.*;

public class GameTile {

    public static final int TILE_SIZE = 100;

    private final int row;
    private final int col;
    private final Color color;

    /**
     * Creates tile in the given row and col with a specific color.
     */
    public GameTile(int row, int col, Color color) {

        this.row        = row;
        this.col        = col;
        this.color      = color;
    }

    /**
     * Visualizes already created tile.
     */
    public void render(Graphics g) {

        int tileX = this.col * TILE_SIZE;
        int tileY = this.row * TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);

        g.setColor(Color.GRAY);
        g.fillOval(225,225, 50, 50);
        g.setColor(Color.BLACK);
        g.drawOval(225,225, 50, 50);
    }


    /**
     * Visualizes improvised tiled grid.
     * This method draws lines, it doesn't make real grid that can be used for movements.
     */
    public void renderTiledGrid(Graphics g){

        g.setColor(Color.BLACK);

        for (int index = 0; index < 5; index++) {
            g.drawLine(0,                index * TILE_SIZE, 500,               index * TILE_SIZE);
            g.drawLine(index * TILE_SIZE,0,                 index * TILE_SIZE, 500);
        }
    }
}