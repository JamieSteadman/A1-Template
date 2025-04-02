package ca.mcmaster.se2aa4.mazerunner;
import java.io.IOException;
import java.lang.System.Logger;
import java.util.logging.LogManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PathGenerationTest {
    private Maze maze;

    @BeforeEach
    public void initializeMaze() {
        char[][] testMaze = {
            {'#', '#', '#', '#', '#'},
            {'#', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#'}
        };
    }

    @Test
    void testGeneratedPathNotEmpty() {
        maze.printAlgorithm();
        assertNotEquals("", maze.getGeneratedPath());
    }

    @Test
    void testGeneratedPathContainsValidMoves() {
        maze.printAlgorithm();
        assertTrue(maze.getGeneratedPath().matches("[FRL0-9 ]*"));
    }
}
