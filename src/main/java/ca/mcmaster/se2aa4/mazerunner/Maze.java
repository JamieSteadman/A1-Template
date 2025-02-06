package ca.mcmaster.se2aa4.mazerunner;

public class Maze {
    private char[][] maze;
    private int length;
    private int height;
    private String path;
    private Navigator navigator;

    public Maze(char[][] maze, int length, int height) {
        this.maze = maze;
        this.length = length;
        this.height = height;
        //path = getPath();
    }
    private String getPath() {
        navigator = new Navigator('E', getLeftEntrance(), 0);
        
        while (true) { 
            if (navigator.getDirection() == 'E') {
                if (maze[navigator.getRow() - 1][navigator.getCol()] == '#' && maze[navigator.getRow()][navigator.getCol() + 1] == ' ') {
                    
                }
                else if (maze[navigator.getRow() - 1][navigator.getCol()] == '#' && maze[navigator.getRow()][navigator.getCol()+1] == '#') {
    
                }
                else if (maze[navigator.getRow() - 1][navigator.getCol()] == ' ') {
                    
                }
            }
        }
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
}
