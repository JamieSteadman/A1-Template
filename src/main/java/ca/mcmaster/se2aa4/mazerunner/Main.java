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
        try {
            logger.info("**** Reading the maze from file " + args[0]);

            cmd = parser.parse(options, args);

            if (cmd.hasOption("i")) {
                String fileInput = cmd.getOptionValue("i");
                try (BufferedReader reader = new BufferedReader(new FileReader(fileInput))) {
                    String line = reader.readLine();
                    int counter = 0;
                    int length = line.length();
                    maze = new char[1][length]; //Initial size of maze array during reading

                    while (line != null) { //Reading loop
                        if (counter != 0) { 
                            maze = resizeArray(maze, counter, length);
                        }
                        for (int i = 0; i < length; i++) {
                            maze[counter][i] = line.charAt(i);
                        }
                        line = reader.readLine();
                        counter++;
                    }
                }
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
        char [][] newArr = new char[height + 1][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < height + 1; j++) {
                newArr[i][j] = arr[i][j];
            }
        }
        return newArr;
    }
}