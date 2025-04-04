package ca.mcmaster.se2aa4.mazerunner;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class MazeTest {
    private Maze maze;
    private LinkedList <Maze> mazeList = new LinkedList<Maze>();
    
    @BeforeEach
    void setUp() throws IOException {
        char[][] testMaze1 = {
            {'#', '#', '#', '#', '#'},
            {' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' '},
            {'#', '#', '#', '#', '#'}
        };
    
        char[][] testMaze2 = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' '},
            {'#', ' ', '#', ' ', '#', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', '#'},
            {' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#'}
        };
    
        char[][] testMaze3 = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#', ' ', '#', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
            {' ', ' ', '#', '#', '#', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' '},
            {'#', '#', '#', ' ', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
    
        char[][] testMaze4 = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', '#', ' ', ' '},
            {'#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', '#'},
            {'#', '#', '#', ' ', '#', ' ', '#', '#', '#', ' ', '#'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
    
        char[][] testMaze5 = {
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', '#', ' ', '#', ' ', '#', ' ', '#'},
            {' ', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', '#', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#', ' ', ' ', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', ' ', '#', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#', ' ', '#', '#', '#', '#', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
        mazeList.add(new Maze(testMaze1, 5, 5));
        mazeList.add(new Maze(testMaze2, 7, 7));
        mazeList.add(new Maze(testMaze3, 9, 9));
        mazeList.add(new Maze(testMaze4, 11, 11));
        mazeList.add(new Maze(testMaze5, 13, 13));
    }
    
    @Test
    void testMazeInitialization() {
        for (Maze maze : mazeList) {
            assertNotNull(maze, "Maze object should not be null");
        }
    }
    
    @Test
    void testValidPathDetection() {
        for (Maze maze : mazeList) {
            MazeSolver solver = new RightHandAlgorithm(maze);
            solver.perform();
            String validPath = solver.getPath();
            assertTrue(solver.verifyPath(validPath), "Valid path should be detected as valid");
        }
    }
    
    @Test
    void testInvalidPathDetection() {
        for (Maze maze : mazeList) {
            String invalidPath = "FFF";
            MazeSolver solver = new RightHandAlgorithm(maze);
            assertFalse(solver.verifyPath(invalidPath));
        }
    }
}
