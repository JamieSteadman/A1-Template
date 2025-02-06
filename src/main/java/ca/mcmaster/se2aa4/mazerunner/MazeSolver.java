package ca.mcmaster.se2aa4.mazerunner;

public class MazeSolver {
    private Maze maze;
    private Navigator navigator;
    private String path;

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.navigator = new Navigator('E', maze.getLeftEntrance(), 0);
        this.path = "";
    }
    public void solveMaze() {
        while (true) { 
            if (navigator.getCol() == maze.getLength() - 1 && navigator.getDirection() == 'E') {
                System.out.println(maze.getLength());
                System.out.println(navigator.getCol());
                path += "F";
                break;
            }
            int[] rightCoordinates = navigator.getRightCoordinates();
            int[] forwardCoordinates = navigator.getForwardCoordinates();
            char rightTile = maze.getTile(rightCoordinates[0], rightCoordinates[1]);
            char forwardTile = maze.getTile(forwardCoordinates[0], forwardCoordinates[1]);
            
            if (rightTile == '#' && forwardTile == ' ') {
                navigator.moveForward();
                path += "F";
            }
            else if (rightTile == '#' && forwardTile == '#') {
                navigator.turnLeft();
                path += "L";
            }
            else if (rightTile == ' ') {
                navigator.turnRight();
                navigator.moveForward();
                path += "RF";
            }
        }
    }
    public String printPath() {
        return path;
    }
}
