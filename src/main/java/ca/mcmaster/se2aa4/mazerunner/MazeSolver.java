package ca.mcmaster.se2aa4.mazerunner;

import java.lang.classfile.Signature;

public class MazeSolver {
    private Maze maze;
    private Navigator navigator;

    public MazeSolver(Maze maze) {
        this.maze = maze;
        this.navigator = new Navigator('E', maze.getLeftEntrance(), 0);
    }
}
