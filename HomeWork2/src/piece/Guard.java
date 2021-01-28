package piece;

import java.awt.*;

public class Guard extends Piece {

    /**
     * Creates "Guard" piece on a given position from team one or team two.
     */
    public Guard(int rowIndex, int colIndex, int team) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.team = team;
        this.type = "Guard";
    }


    @Override
    public String getType() {
        return this.type;
    }


    @Override
    public boolean isMoveValid(Piece[][] pieceCollection, Piece givenPiece, int moveRow, int moveCol){

        if(moveRow == this.rowIndex - 1 && moveCol == this.colIndex){
            return true;
        }
        else if(moveRow == this.rowIndex + 1 && moveCol == this.colIndex){
            return true;
        }
        else if(moveRow == this.rowIndex && moveCol == this.colIndex - 1){
            return true;
        }
        else return moveRow == this.rowIndex && moveCol == this.colIndex + 1;
    }


    @Override
    public boolean isAttackValid(int attackRow, int attackCol) {
        return false;
    }

    /**
     * Visualize "Guard" piece with the color of his team.
     * 15 lines.
     */
    public void render(Graphics g){

        int TILE_SIZE = 100;
        if(getTeam() == 1){
            g.setColor(Color.YELLOW);
            g.fillOval(getColIndex() * TILE_SIZE + 25, getRowIndex() * TILE_SIZE + 25, TILE_SIZE - 50, TILE_SIZE - 50);
            g.setColor(new Color(0x21800A));
        }
        else if(getTeam() == 2){
            g.setColor(new Color(0x21800A));
            g.fillOval(getColIndex() * TILE_SIZE + 25, getRowIndex() * TILE_SIZE + 25, TILE_SIZE - 50, TILE_SIZE - 50);
            g.setColor(Color.YELLOW);
        }
        g.fillOval(getColIndex() * TILE_SIZE + 30, getRowIndex() * TILE_SIZE + 30, TILE_SIZE - 60, TILE_SIZE - 60);
    }
}
