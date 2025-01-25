package ca.mcmaster.se2aa4.mazerunner;

public class Navigator {
    private char direction = ' ';
    private int row;
    private int col;

    public Navigator (char direction, int row, int col) {
        this.direction = direction;
        this.row = row;
        this.col = col;
    }
    public void turnRight() {

    }
    public void turnAround() {

    }
    public void turnLeft() {

    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public char getDirection() {
        return direction;
    }
}
