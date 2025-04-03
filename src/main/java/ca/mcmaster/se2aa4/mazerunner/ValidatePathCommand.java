package ca.mcmaster.se2aa4.mazerunner;

public class ValidatePathCommand implements Command{
    private MazeSolver ms;
    private String path;
    private boolean isValid;
    public ValidatePathCommand(MazeSolver ms, String path) {
        this.ms = ms;
        this.path = path;
    }

    @Override 
    public void execute() {
        isValid = ms.verifyPath(path);
    }
    public boolean getResult() {
        return isValid;
    }
}
