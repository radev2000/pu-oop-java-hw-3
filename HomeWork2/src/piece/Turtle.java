package piece;

import java.awt.*;

/**
 * Child class that will create Turtles on a specific coordinates.
 */
public class Turtle extends Piece{

    public Turtle(int rowIndex, int colIndex){

        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.type = "Turtle";
    }


    @Override
    public String getType() {
        return this.type;
    }


    @Override
    public int getRowIndex() {
        return rowIndex;
    }


    @Override
    public int getColIndex() {
        return colIndex;
    }


    @Override
    public void render(Graphics g) {
        int TILE_SIZE = 100;

        g.setColor(Color.RED);
        g.fillOval(getColIndex() * TILE_SIZE + 25, getRowIndex() * TILE_SIZE + 25, TILE_SIZE - 50, TILE_SIZE - 50);
        g.setColor(Color.WHITE);
        g.fillOval(getColIndex() * TILE_SIZE + 33, getRowIndex() * TILE_SIZE + 33, TILE_SIZE - 65, TILE_SIZE - 65);
    }


    @Override
    public boolean isMoveValid(Piece[][] pieceCollection, Piece givenPiece, int moveRow, int moveCol) {
        return false;
    }


    @Override
    public boolean isAttackValid(int attackRow, int attackCol) {

        return false;
    }
}
