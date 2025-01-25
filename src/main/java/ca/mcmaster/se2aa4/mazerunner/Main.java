package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(); 

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "Input file for maze path");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        
        logger.info("** Starting Maze Runner");

        char [][] maze;
        System.out.println("Ball");
        try {
            logger.info("**** Reading the maze from file " + args[0]);

            cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                Maze m = null;
                String fileInput = cmd.getOptionValue("i");
                try (BufferedReader reader = new BufferedReader(new FileReader(fileInput))) {
                    System.out.println("In -i flag");
                    String line = reader.readLine();
                    int counter = 0;
                    int length = line.length();
                    maze = new char[1][length]; //Initial size of maze array during reading

                    while (line != null) { //Reading loop
                        System.out.println(counter);
                        if (counter != 0) { 
                            maze = resizeArray(maze, counter, length);
                            System.out.println("Done Resizing");
                        }
                        for (int i = 0; i < line.length(); i++) {
                            maze[counter][i] = line.charAt(i);
                        }
                        for (int i = line.length(); i < length; i++) { //Accounting for non-whitespace characters in the grid
                            maze[counter][i] = ' ';
                        }
                        line = reader.readLine();
                        counter++;
                    }
                    
                    m = new Maze(maze, counter, length);
                    
                }
                m.printMaze();
                System.out.println(m.getPathResult());
            }
            else {
                BufferedReader reader = new BufferedReader(new FileReader(args[0]));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger.trace("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            logger.trace("PASS ");
                        }
                    }
                    logger.trace(System.lineSeparator());
                }
            }
        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
        }
        logger.info("**** Computing path");
        logger.info("PATH NOT COMPUTED");
        logger.info("** End of MazeRunner");
    }
    private static char [][] resizeArray(char [][] arr, int height, int length) {
        System.out.println("Resizing");
        char [][] newArr = new char[height + 1][length];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }
}