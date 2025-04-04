package ca.mcmaster.se2aa4.mazerunner;

public abstract class MazeSolver implements Solver{
    protected Maze maze;
    protected Navigator navigator;
    protected String path;

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.path = "";
    }
    @Override
    public final void perform() {
        initializeNavigator();
        executeAlgorithm();
        log();
    }
    protected abstract void executeAlgorithm();

    protected void initializeNavigator() {
        this.navigator = new Navigator('E', maze.getLeftEntrance(), 0);
    }
    private void log() {
        System.out.println("Maze Solved using " + this.getClass().getSimpleName() + ":");
    }
    public boolean verifyPath(String input) { //Checks if a given path gets the navigator through the maze
        int length = input.length();
        int counter = 0;
        initializeNavigator();

        for (int i = 0; i < length; i++) {
            //Navigation
            if (input.charAt(i) == 'F') {
                navigator.moveForward();
            }
            else if (input.charAt(i) == 'R') {
                navigator.turnRight();
            }
            else if (input.charAt(i) == 'L') {
                navigator.turnLeft();
            }

            //Boundary cases
            if (navigator.getRow() <= 0 || navigator.getRow() >= maze.getHeight() - 1) {
                return false;
            }
            if (navigator.getCol() < 0 || navigator.getCol() > maze.getLength() - 1) {
                return false;
            }

            
            //Regular case
            if (maze.getTile(navigator.getRow(), navigator.getCol()) == '#') {
                return false;
            }
        }
        //End case
        return navigator.getCol() == maze.getLength() - 1  && navigator.getDirection() == 'E' && input.charAt(length - 1) == 'F';
    }
    @Override
    public void undo() {
        initializeNavigator(); 
    }
    public String getPath() {
        return path;
    }
    public String getFactorizedPath() {
        String factorizedPath = "";
        for (int i = 0; i < path.length(); i++) {
            for (int j = i + 1; j < path.length(); j++) {
                if (path.charAt(j) != path.charAt(i)) {
                    if (j - i > 1) {
                        factorizedPath += Integer.toString(j - i);
                    }
                    i = j - 1;
                    break;
                }
                else if (j == path.length() - 1) { //Edge case for factorizing at the end of the array
                    factorizedPath += Integer.toString(j - i + 1);
                    i = j;
                }
            }
            factorizedPath += path.charAt(i) + " ";
        }
        return factorizedPath;
    }
    //Testing purposes
    public Navigator getNavigatorCopy() { 
        return new Navigator(navigator.getDirection(), navigator.getRow(), navigator.getCol());
    }
}
