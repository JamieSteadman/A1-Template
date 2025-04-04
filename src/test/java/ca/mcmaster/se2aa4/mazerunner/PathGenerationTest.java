package ca.mcmaster.se2aa4.mazerunner;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PathGenerationTest {
    private MazeSolver mazeSolver;
    private Maze maze;

    @BeforeEach
    public void initializeMaze() {
        char[][] testMaze = {
            {'#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' '},
            {'#', ' ', '#', ' ', '#'},
            {' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#'}
        };
        maze = new Maze(testMaze, 5, 5);
        mazeSolver = new RightHandAlgorithm(maze);
    }

    @Test
    void testGeneratedPathNotEmpty() {
        assertNotEquals("", mazeSolver.getFactorizedPath());
    }

    @Test
    void testGeneratedPathContainsValidMoves() {
        assertTrue(mazeSolver.getFactorizedPath().matches("[FRL0-9 ]*"));
    }
}
