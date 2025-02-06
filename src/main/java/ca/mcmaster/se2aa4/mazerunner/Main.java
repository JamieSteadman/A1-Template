package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "Input file for maze");
        options.addOption("p", true, "Path string");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        logger.info("** Starting Maze Runner");
        
        String fileInput = null;
        String pathString = null;
        
        try {
            cmd = parser.parse(options, args);
            
            if (cmd.hasOption("i")) {
                fileInput = cmd.getOptionValue("i");
                logger.info("**** Reading the maze from file: " + fileInput);
            }
            
            if (cmd.hasOption("p")) {
                pathString = cmd.getOptionValue("p");
                logger.info("**** Path string provided: " + pathString);
            }
            
            if (fileInput == null) {
                logger.error("No input file provided. Use -i <filename>");
                return;
            }
            
            File file = new File(fileInput);
            if (!file.exists()) {
                logger.error("File not found: " + fileInput);
                return;
            }
            
            char[][] maze;
            try (BufferedReader reader = new BufferedReader(new FileReader(fileInput))) {
                String line = reader.readLine();
                if (line == null) {
                    logger.error("Empty maze file.");
                    return;
                }
                
                int counter = 0;
                int length = line.length();
                maze = new char[1][length];
                
                while (line != null) {
                    if (counter != 0) {
                        maze = resizeArray(maze, counter, length);
                    }
                    for (int i = 0; i < line.length(); i++) {
                        maze[counter][i] = line.charAt(i);
                    }
                    for (int i = line.length(); i < length; i++) {
                        maze[counter][i] = ' ';
                    }
                    line = reader.readLine();
                    counter++;
                }
                
                Maze m = new Maze(maze, length, counter);
                MazeSolver ms = new MazeSolver(m);
                
                
                if (pathString != null) {
                    boolean validPath = ms.verifyPath(pathString);
                    if (validPath == true) {
                        System.out.println("Path Works");
                        return;
                    }
                    else {
                        System.out.println("Path Does Not Work");
                        return;
                    }
                }
                ms.solveMaze();
                System.out.println("Printing Maze:");
                System.out.println(ms.getFactorizedPath());
                
            } catch (IOException e) {
                logger.error("Error reading file: ", e);
            }
        } catch (ParseException e) {
            logger.error("Command-line parsing error: ", e);
        }
    }

    private static char[][] resizeArray(char[][] arr, int height, int length) {
        char[][] newArr = new char[height + 1][length];
        for (int i = 0; i < height; i++) {
            System.arraycopy(arr[i], 0, newArr[i], 0, length);
        }
        return newArr;
    }
}
