package gameComponents;

import piece.Leader;
import piece.Guard;
import piece.Piece;
import piece.Turtle;
import ui.Modal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class GameBoard extends JFrame implements MouseListener {

    private final   Piece[][] pieceCollection = new Piece[5][5];
    private         Piece     selectedPiece;

    private int     initialRow;
    private int     initialCol;
    private boolean isGameOver = false;


    /**
     * Method that creates the board with all pieces.
     */
    public GameBoard(){
        //team 1 - bottom side.
        this.pieceCollection[4][0] = new Leader(4,0,1);
        this.pieceCollection[4][1] = new Guard (4,1,1);
        this.pieceCollection[4][2] = new Guard (4,2,1);
        this.pieceCollection[4][3] = new Guard (4,3,1);
        this.pieceCollection[4][4] = new Guard (4,4,1);

        //Team 2 - Top side.
        this.pieceCollection[0][4] = new Leader(0,4,2);
        this.pieceCollection[0][3] = new Guard (0,3,2);
        this.pieceCollection[0][2] = new Guard (0,2,2);
        this.pieceCollection[0][1] = new Guard (0,1,2);
        this.pieceCollection[0][0] = new Guard (0,0,2);

        placeTurtles();

        this.setTitle("GrogNock vs KittenPusan");
        this.setSize(500,500);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);
    }


    /**
     * Mouse Events:
     * I hope you don't count { and } as lines :D
     * but if so /TODO: Check how to make this method smaller!
     */
    // {
    @Override
    public void mouseClicked(MouseEvent e) {

        if (!isGameOver) {
            int selectedRow = this.getBoardDimensionBasedOnCoordinates(e.getY());
            int selectedCol = this.getBoardDimensionBasedOnCoordinates(e.getX());

            if (this.selectedPiece != null) {

                Piece newPiece = this.getBoardPiece(initialRow, initialCol);
                if (newPiece.isMoveValid(pieceCollection, newPiece, selectedRow, selectedCol)){

                    if (!this.hasBoardPiece(selectedRow, selectedCol)){
                        movePiece(selectedRow, selectedCol, newPiece);
                        this.repaint();
                        checkForWinner();
                        return;
                    }
                    else{
                        onTriggerEffect(pieceCollection, selectedRow, selectedCol);
                        this.selectedPiece = null;
                    }
                }
            }
            if (this.selectedPiece == null && this.hasBoardPiece(selectedRow, selectedCol)) {
                this.selectedPiece = this.getBoardPiece(selectedRow, selectedCol);
                this.initialRow = selectedRow;
                this.initialCol = selectedCol;
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }


    @Override
    public void mouseExited(MouseEvent e) {

    }
    // }


    /**
     * Method that is used to paint all the GameBoard components
     * including tiles and pieces.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 5; col++) {

                this.renderGameTile(g, row, col);
                this.renderGamePiece(g, row, col);
            }
        }
    }


    /**
     * This method contains the logic that is going to be used for
     * giving different colors to different tiles.
     * @param row - Given row index.
     * @param col - Given col index.
     * @return    - Gives a color that is going to be used when the tile is rendered.
     */
    private Color getTileColor(int row, int col){

        if     (row == 0 && col == 0){return Color.RED;}
        else if(row == 0 && col == 4){return Color.RED;}
        else if(row == 4 && col == 1){return Color.RED;}
        else if(row == 4 && col == 3){return Color.RED;}
        else if(row == 0 && col == 1){return Color.BLACK;}
        else if(row == 0 && col == 3){return Color.BLACK;}
        else if(row == 4 && col == 0){return Color.BLACK;}
        else if(row == 4 && col == 4){return Color.BLACK;}
        else if(row == 1 && col == 0){return Color.GRAY;}
        else if(row == 1 && col == 1){return Color.GRAY;}
        else if(row == 1 && col == 3){return Color.GRAY;}
        else if(row == 1 && col == 4){return Color.GRAY;}
        else if(row == 3 && col == 0){return Color.GRAY;}
        else if(row == 3 && col == 1){return Color.GRAY;}
        else if(row == 3 && col == 3){return Color.GRAY;}
        else if(row == 3 && col == 4){return Color.GRAY;}
        else return Color.WHITE;
    }


    /**
     * Visualises a given Tile with his color (declared in "getTileColor()").
     * @param row - Given row index.
     * @param col - Given col index.
     */
    private void renderGameTile(Graphics g, int row, int col) {

        Color tileColor = this.getTileColor(row, col);
        GameTile tile = new GameTile(row, col, tileColor);
        tile.render(g);
        tile.renderTiledGrid(g);
    }


    /**
     * Method that will give us piece that is placed on the board.
     * @param row - Given row index.
     * @param col - Given col index.
     * @return    - Piece with the given row and col index.
     */
    private Piece getBoardPiece(int row, int col) {
        return this.pieceCollection[row][col];
    }


    /**
     * This method is made to check if a piece is placed on a given position.
     * @param row - Given row index.
     * @param col - Given col index.
     * @return    - True if there is a piece and that piece is not equal to null.
     */
    private boolean hasBoardPiece(int row, int col) {
        return this.getBoardPiece(row, col) != null;
    }


    /**
     * Visualizes piece on a given coordinates (Only if they exist).
     * @param row - Given row index.
     * @param col - Given col index.
     */
    private void renderGamePiece(Graphics g, int row, int col) {

        if(this.hasBoardPiece(row, col)) {

            Piece piece = this.getBoardPiece(row, col);
            piece.render(g);
        }
    }


    /**
     * This method will move the piece into a given coordinates.
     * @param selectedRow - selected row index.
     * @param selectedCol - selected col index.
     * @param piece       - initial piece.
     */
    private void movePiece(int selectedRow, int selectedCol, Piece piece){

        int oldRow = piece.getRowIndex();
        int oldCol = piece.getColIndex();

        piece.move(selectedRow, selectedCol);

        this.pieceCollection[piece.getRowIndex()][piece.getColIndex()] = this.selectedPiece;
        this.pieceCollection[oldRow][oldCol] = null;

        this.selectedPiece = null;
    }


    /**
     * Places two turtles in a random position (only in the given coordinates).
     */
    private void placeTurtles(){
        Random random = new Random();
        int turtles = 0;
        while(turtles < 2){
        int index = random.nextInt(4);
            switch (index) {
                case 0 -> {
                    if (this.pieceCollection[2][0] == null)
                        turtles++;
                    this.pieceCollection[2][0] = new Turtle(2, 0);
                }
                case 1 -> {
                    if (this.pieceCollection[2][1] == null)
                        turtles++;
                    this.pieceCollection[2][1] = new Turtle(2, 1);
                }
                case 2 -> {
                    if (this.pieceCollection[2][3] == null)
                        turtles++;
                    this.pieceCollection[2][3] = new Turtle(2, 3);
                }
                case 3 -> {
                    if (this.pieceCollection[2][4] == null)
                        turtles++;
                    this.pieceCollection[2][4] = new Turtle(2, 4);
                }
            }
        }
    }


    /**
     * Method that will be used when we want to destroy a given turtle
     * aka the one that interacted with a given piece.
     */
    private void destroyTurtle(int row, int col){
        if(pieceCollection[row][col] != null && pieceCollection[row][col].getType().equals("Turtle")){
            this.pieceCollection[row][col] = null;
        }
    }


    /**
     * Activates when a given piece interact with a given turtle.
     */
    private void onTriggerEffect(Piece[][] pieceCollection, int givenRow, int givenCol) {
        if (pieceCollection[givenRow][givenCol] != null) {
            if (pieceCollection[givenRow][givenCol].getType().equals("Turtle")) {
                pieceCollection[initialRow][initialCol] = null;
                destroyTurtle(givenRow, givenCol);
                this.repaint();
            }
        }
    }


    /**
     * This method will transfer the given coordinates into index.
     */
    private int getBoardDimensionBasedOnCoordinates (int coordinates){
        return coordinates / GameTile.TILE_SIZE;
    }


    /**
     *  Will check if a Leader is placed in Tile[2][2](center of the board)
     *  and if so it will stop the game by freezing the MouseClicked() event
     *  and make him winner.
     */
    private void checkForWinner(){

        if(pieceCollection[2][2] != null &&
                pieceCollection[2][2].getType().equals("Leader")){

            Modal.render(this, "We got a winner!", "Player " +
                    pieceCollection[2][2].getTeam() + " won the game!");

            this.isGameOver = true;
        }
        //TODO: Check if a Leader died and make his opponent winner!
    }
}

