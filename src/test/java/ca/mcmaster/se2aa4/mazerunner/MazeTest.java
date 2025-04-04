package ca.mcmaster.se2aa4.mazerunner;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class MazeTest {
    private Maze maze;
    
    @BeforeEach
    void setUp() throws IOException {
        char[][] testMaze = {
            {'#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' '},
            {'#', ' ', '#', ' ', '#'},
            {' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#'}
        };
        maze = new Maze(testMaze, 5, 5);
    }
    
    @Test
    void testMazeInitialization() {
        assertNotNull(maze, "Maze object should not be null");
        assertEquals(5, maze.getLength());
        assertEquals(5, maze.getHeight());
    }
    
    @Test
    void testMazeBoundaries() {
        assertEquals('#', maze.getTile(0, 0));
        assertEquals('#', maze.getTile(4, 4));
    }
    
    @Test
    void testValidPathDetection() {
        String validPath = "FFFLFFRF";
        MazeSolver solver = new RightHandAlgorithm(maze);
        assertTrue(solver.verifyPath(validPath));
    }
    
    @Test
    void testInvalidPathDetection() {
        String invalidPath = "FFFFF";
        MazeSolver solver = new RightHandAlgorithm(maze);
        assertFalse(solver.verifyPath(invalidPath));
    }
}
