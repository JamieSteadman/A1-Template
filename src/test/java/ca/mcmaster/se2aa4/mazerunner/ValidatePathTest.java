package ca.mcmaster.se2aa4.mazerunner;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidatePathTest {
    private Maze maze;
    private MazeSolver mazeSolver;
    private String path;

    @BeforeEach
    public void initializeMaze() {
        char[][] testMaze = {
            {'#', '#', '#', '#', '#'},
            {' ', ' ', '#', '#', '#'},
            {'#', ' ', '#', ' ', ' '},
            {'#', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#'}
        };
        maze = new Maze(testMaze, 5, 5);
    }
    @Test
    void testGoingOutEntrance() {
        path = "LLF";
        MazeSolver mazeSolver = new MazeSolver(maze);
        assertFalse(mazeSolver.verifyPath(path));
    }
    @Test
    void overShootingExit() { //Test if the path goes beyond the maze exit
        path = "FRFFLFFLFRFF";
        MazeSolver mazeSolver = new MazeSolver(maze);
        assertFalse(mazeSolver.verifyPath(path));
    }

    @Test
    void testPathLeadingToWall() {
        path = "FFFFFFFF";
        MazeSolver mazeSolver = new MazeSolver(maze);
        assertFalse(mazeSolver.verifyPath(path));
    }

    @Test
    void testPathReachingExit() {
        path = "FRFFLFFLFRF";
        MazeSolver mazeSolver = new MazeSolver(maze);
        assertTrue(mazeSolver.verifyPath(path));
    }
}