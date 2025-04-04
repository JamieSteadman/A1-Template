package ca.mcmaster.se2aa4.mazerunner;

public class RightHandAlgorithm extends MazeSolver {
    public RightHandAlgorithm(Maze maze) {
        super(maze);
    }
    @Override
    protected void executeAlgorithm() {
        while (true) { 
            if (navigator.getCol() == maze.getLength() - 1 && navigator.getDirection() == 'E') {
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

}
