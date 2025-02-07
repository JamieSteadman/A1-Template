package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    protected char[][] maze;
    protected int length; 
    protected int height;
    protected String path;

    public Maze(char[][] maze, int length, int height) { //Constructor method
        this.maze = maze;
        this.length = length;
        this.height = height;
    }

    public int getLeftEntrance() {
        for (int i = 0; i < height; i++) {
            if (maze[i][0] == ' ') {
                return i;
            }
        }
        return -1;
    }
    public int getRightEntrance() {
        for (int i = 0; i < height; i++) {
            if (maze[i][length - 1] == ' ') {
                return i;
            }
        }
        return -1;
    }
    public void printMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.print("\n");
        }
    }
    public char getTile(int row, int col) {
        return maze[row][col];
    }
    public int getLength() {
        return length;
    }
    public int getHeight() {
        return height;
    }
}
