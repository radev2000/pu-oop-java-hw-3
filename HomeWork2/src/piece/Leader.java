package piece;

import java.awt.*;

public class Leader extends Piece {

    private int validatedRow;
    private int validatedCol;


    /**
     * Creates "Leader" piece on a given position from team one or team two.
     */
    public Leader(int rowIndex, int colIndex, int team ){

        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.team     = team;
        this.type = "Leader";
    }


    /**
     * This method will check if the given coordinates are valid
     * and will return int with a value between 1 and 4.
     * @return :
     * 1 - if the piece have to move up.
     * 2 - if the piece have to move down.
     * 3 - if the piece have to move left.
     * 4 - if the piece have to move right.
     * 0 - invalid direction.
     *
     * @param givenRow - row index of the given coordinates.
     * @param givenCol - col index of the given coordinates.
     */
    public int moveDirection(int givenRow, int givenCol){

        if(givenRow < this.rowIndex && givenCol == this.colIndex){
            return 1;
        }
        else if(givenRow > this.rowIndex  && givenCol == this.colIndex){
            return 2;
        }
        else if(givenRow == this.rowIndex && givenCol < this.colIndex){
            return 3;
        }
        else if(givenRow == this.rowIndex && givenCol > this.colIndex){
            return 4;
        }
        else{
            return 0;
        }
    }


    /**
     * Methods that will give us valid coordinates to move in a given direction.
     * @param pieceCollection - contains all the pieces on the board.
     * @param givenPiece      - our selected piece.

     */
    // {
    private void moveUp(Piece[][] pieceCollection, Piece givenPiece, int givenRow, int givenCol){

        int moveDirection = moveDirection(givenRow, givenCol);
        int currentRow = givenPiece.getRowIndex();
        boolean keepUpdating = true;
        if(moveDirection == 1) {
            while (keepUpdating) {
                if(currentRow == 1){
                    if(pieceCollection[currentRow - 1][givenCol] == null){
                        this.validatedRow = 0;
                    } else{
                        this.validatedRow = 1;
                    }
                    keepUpdating        = false;
                } else {
                    if(pieceCollection[currentRow - 1][givenCol] == null){
                        currentRow     -= 1;
                    } else {
                        this.validatedRow = currentRow;
                        keepUpdating    = false;
                    }
                }
            }
        }
    }

    private void moveDown(Piece[][] pieceCollection, Piece givenPiece, int givenRow, int givenCol){

        int moveDirection = moveDirection(givenRow, givenCol);
        int currentRow = givenPiece.getRowIndex();
        boolean keepUpdating = true;
        if(moveDirection == 2) {
            while (keepUpdating) {
                if(currentRow == 3){
                    if(pieceCollection[currentRow + 1][givenCol] == null){
                        this.validatedRow = 4;
                    } else{
                        this.validatedRow = 3;
                    }
                    keepUpdating        = false;
                } else {
                    if(pieceCollection[currentRow + 1][givenCol] == null){
                        currentRow     += 1;
                    } else {
                        this.validatedRow = currentRow;
                        keepUpdating    = false;
                    }
                }
            }
        }
    }

    private void moveLeft(Piece[][] pieceCollection, Piece givenPiece, int givenRow, int givenCol){

        int moveDirection = moveDirection(givenRow, givenCol);
        int currentCol = givenPiece.getColIndex();
        boolean keepUpdating = true;
        if(moveDirection == 3) {
            while (keepUpdating) {
                if(currentCol == 1){
                    if(pieceCollection[givenRow][currentCol - 1] == null){
                        this.validatedCol = 0;
                    } else{
                        this.validatedCol = 1;
                    }
                    keepUpdating        = false;
                } else {
                    if(pieceCollection[givenRow][currentCol - 1] == null){
                        currentCol     -= 1;
                    } else {
                        this.validatedCol = currentCol;
                        keepUpdating    = false;
                    }
                }
            }
        }
    }

    private void moveRight(Piece[][] pieceCollection, Piece piece, int givenRow, int givenCol){

        int moveDirection = moveDirection(givenRow, givenCol);
        int currentCol = piece.getColIndex();
        boolean keepUpdating = true;
        if(moveDirection == 4) {
            while (keepUpdating) {
                if(currentCol == 3){
                    if(pieceCollection[givenRow][currentCol + 1] == null){
                        this.validatedCol = 4;
                    } else{
                        this.validatedCol = 3;
                    }
                    keepUpdating        = false;
                } else {
                    if(pieceCollection[givenRow][currentCol + 1] == null){
                        currentCol     += 1;
                    } else {
                        this.validatedCol = currentCol;
                        keepUpdating    = false;
                    }
                }
            }
        }
    }
    // }


    @Override
    //TODO: See if you can make this method smaller!
    public boolean isMoveValid(Piece[][] pieceCollection, Piece givenPiece, int givenRow, int givenCol) {

        int moveDirection = moveDirection(givenRow, givenCol);

        if (moveDirection == 1) {
            moveUp(pieceCollection, givenPiece, givenRow, givenCol);
            if(pieceCollection[givenRow][givenCol] != null && pieceCollection[givenRow][givenCol].getType().equals("Turtle"))
                return this.validatedRow - 1 == givenRow;
            return this.validatedRow == givenRow;
        }
        else if (moveDirection == 2) {
            moveDown(pieceCollection, givenPiece, givenRow, givenCol);
            if(pieceCollection[givenRow][givenCol] != null && pieceCollection[givenRow][givenCol].getType().equals("Turtle"))
                return this.validatedRow + 1 == givenRow;
            return this.validatedRow == givenRow;
        }
        else if (moveDirection == 3) {
            moveLeft(pieceCollection, givenPiece, givenRow, givenCol);
            if(pieceCollection[givenRow][givenCol] != null && pieceCollection[givenRow][givenCol].getType().equals("Turtle"))
                return this.validatedCol - 1 == givenCol;
            return this.validatedCol == givenCol;
        }
        else if (moveDirection == 4) {
            moveRight(pieceCollection, givenPiece, givenRow, givenCol);
            if(pieceCollection[givenRow][givenCol] != null && pieceCollection[givenRow][givenCol].getType().equals("Turtle"))
                return this.validatedCol + 1 == givenCol;
            return this.validatedCol == givenCol;
        }
        return false;
    }


    @Override
    public boolean isAttackValid(int attackRow, int attackCol) {
        return false;
    }

    @Override
    public String getType() {
        return this.type;
    }

    /**
     * Visualize "Leader" piece with the color of his team.
     */
    public void render(Graphics g){

        int TILE_SIZE = 100;
        if(getTeam() == 1){
            g.setColor(Color.YELLOW);
            g.fillRect(getColIndex() * TILE_SIZE + 25, getRowIndex() * TILE_SIZE + 25, TILE_SIZE - 50, TILE_SIZE - 50);
            g.setColor(new Color(0x21800A));
        }
        else if(getTeam() == 2){
            g.setColor(new Color(0x21800A));
            g.fillRect(getColIndex() * TILE_SIZE + 25, getRowIndex() * TILE_SIZE + 25, TILE_SIZE - 50, TILE_SIZE - 50);
            g.setColor(Color.YELLOW);
        }
        g.fillRect(getColIndex() * TILE_SIZE + 30, getRowIndex() * TILE_SIZE + 30, TILE_SIZE - 60, TILE_SIZE - 60);
    }

}