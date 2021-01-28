package piece;

import java.awt.*;

public abstract class Piece{

    protected int    rowIndex;
    protected int    colIndex;
    protected int    team;
    protected String type;

    /**
     * Will be used to get current Row.
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Will be used to get current Col.
     */
    public int getColIndex() {
        return colIndex;
    }

    /**
     * Will be used to get the team of this piece.
     */
    public int getTeam() {
        return team;
    }


    /**
     * Will be used to get the type of this piece.
     */
    public abstract String getType();


    /**
     * Will move the piece into a given row and col coordinates.
     */
    public void move(int givenRow, int givenCol) {
        this.rowIndex = givenRow;
        this.colIndex = givenCol;
    }


    /**
     * This method will visualize the piece.
     */
    public abstract void render(Graphics g);


    /**
     * Method that will check if this piece can move to a given coordinates.
     * @param pieceCollection - the collection of all pieces.
     * @param givenPiece      - the given one.
     * @param moveRow         - given row index.
     * @param moveCol         - given col index.
     * @return                - true/false if the piece can be moved or not.
     */
    public abstract boolean isMoveValid(Piece[][] pieceCollection, Piece givenPiece, int moveRow, int moveCol);

    /**
     * This method will be used to check if this piece can attack another one.
     */
    public abstract boolean isAttackValid(int attackRow, int attackCol);
}