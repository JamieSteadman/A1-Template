package ca.mcmaster.se2aa4.mazerunner;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidatePathTest {
    private LinkedList <Maze> mazeList = new LinkedList<Maze>();
    private MazeSolver mazeSolver;
    private String path;

    @BeforeEach
    public void initializeMaze() {
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
    void testGoingOutEntrance() {
        path = "LLF";
        for (Maze maze : mazeList) {
            mazeSolver = new RightHandAlgorithm(maze);
            assertFalse(mazeSolver.verifyPath(path));
        }
    }
    @Test
    void testDirectionChange() {
        path = "RLLL";
        for (Maze maze : mazeList) {
            mazeSolver = new RightHandAlgorithm(maze);
            mazeSolver.verifyPath(path);
            assertTrue(mazeSolver.getNavigatorCopy().getDirection() == 'W');
        }
    }
    @Test
    void testPositionChange() {
        path = "F";
        for (Maze maze : mazeList) {
            mazeSolver = new RightHandAlgorithm(maze);
            mazeSolver.verifyPath(path);
            assertTrue(mazeSolver.getNavigatorCopy().getCol() == 1 && mazeSolver.getNavigatorCopy().getRow() == maze.getLeftEntrance());
        }
    }
    @Test
    void overShootingExit() { //Test if the path goes beyond the maze exit
        for (Maze maze : mazeList) {
            mazeSolver = new RightHandAlgorithm(maze);
            mazeSolver.perform();
            path = mazeSolver.getPath() + "F"; //Add an extra F to the path
            assertFalse(mazeSolver.verifyPath(path));
        }
    }

    @Test
    void testPathLeadingToWall() {
        for (Maze maze : mazeList) {
            path = "FFFFFFFFFFFFFFFFFFFFFFFFFF";
            mazeSolver = new RightHandAlgorithm(maze);
            assertFalse(mazeSolver.verifyPath(path));
        }
    }
}