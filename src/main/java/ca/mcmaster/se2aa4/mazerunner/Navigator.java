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
        if (direction == 'N') {
            direction = 'E';
        }
        else if (direction == 'E') {
            direction = 'S';
        }
        else if (direction == 'S') {
            direction = 'W';
        }
        else if (direction == 'W') {
            direction = 'N';
        }
    }
    public void turnLeft() {
        if (direction == 'N') {
            direction = 'W';
        }
        else if (direction == 'E') {
            direction = 'N';
        }
        else if (direction == 'S') {
            direction = 'E';
        }
        else if (direction == 'W') {
            direction = 'S';
        }
    }
    public void moveForward() {
        if (direction == 'N') {
            row--;
        }
        else if (direction == 'E') {
            col++;
        }
        else if (direction == 'S') {
            row++;
        }
        else if (direction == 'W') {
            col--;
        }
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int[] getRightCoordinates() {
        int coordinates[] = new int[2];
        if (direction == 'N') {
            coordinates[0] = row;
            coordinates[1] = col + 1;
        }
        else if (direction == 'E') {
            coordinates[0] = row + 1;
            coordinates[1] = col;
        }
        else if (direction == 'S') {
            coordinates[0] = row;
            coordinates[1] = col - 1;
        }
        else if (direction == 'W') {
            coordinates[0] = row - 1;
            coordinates[1] = col;
        }
        return coordinates;
    }
    public int[] getForwardCoordinates() {
        int coordinates[] = new int[2];
        if (direction == 'N') {
            coordinates[0] = row - 1;
            coordinates[1] = col;
        }
        else if (direction == 'E') {
            coordinates[0] = row;
            coordinates[1] = col + 1;
        }
        else if (direction == 'S') {
            coordinates[0] = row + 1;
            coordinates[1] = col;
        }
        else if (direction == 'W') {
            coordinates[0] = row;
            coordinates[1] = col - 1;
        }
        return coordinates;
    }
    public char getDirection() {
        return direction;
    }
}
