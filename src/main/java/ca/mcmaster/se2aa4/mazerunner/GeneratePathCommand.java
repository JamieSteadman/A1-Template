package ca.mcmaster.se2aa4.mazerunner;

public class GeneratePathCommand implements Command {
    private MazeSolver mazeSolver;
    private String generatedPath;

    public GeneratePathCommand(MazeSolver mazeSolver) {
        this.mazeSolver = mazeSolver;
        this.generatedPath = "";  // Initialize to avoid null issues
    }

    @Override
    public void execute() {
        mazeSolver.perform();
        generatedPath = mazeSolver.getFactorizedPath();
    }

    public String getResult() {
        return generatedPath;
    }
}
