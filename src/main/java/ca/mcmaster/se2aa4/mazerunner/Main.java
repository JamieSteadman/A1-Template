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

            // Handle missing arguments
            if (!cmd.hasOption("i")) {
                logger.error("No input file provided. Use -i <filename>");
                System.err.println("Error: No input file provided. Use -i <filename>");
                return;
            }
            fileInput = cmd.getOptionValue("i");

            if (cmd.hasOption("p")) {
                pathString = cmd.getOptionValue("p");
                if (pathString.isEmpty()) {
                    logger.error("Path string cannot be empty.");
                    System.err.println("Error: Path string cannot be empty.");
                    return;
                }
                logger.info("**** Path string provided: " + pathString);
            }

            // Validate file existence
            File file = new File(fileInput);
            if (!file.exists() || !file.isFile()) {
                logger.error("File not found or not a valid file: " + fileInput);
                System.err.println("Error: File not found or invalid file: " + fileInput);
                return;
            }

            // Read maze file
            char[][] maze;
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();
                if (line == null) {
                    logger.error("Empty maze file.");
                    System.err.println("Error: Maze file is empty.");
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
                MazeSolver ms = new RightHandAlgorithm(m);
                
                if (pathString == null) { //If user didn't use -p flag
                    // Solve maze and print solution
                    GeneratePathCommand gp = new GeneratePathCommand(ms);
                    gp.execute();
                    System.out.println(gp.getResult());
                    
                }
                else {
                    ValidatePathCommand vp = new ValidatePathCommand(ms, pathString);
                    vp.execute();
                    if (vp.getResult()) {
                        System.out.println("Path is valid.");
                    } else {
                        System.out.println("Path is invalid.");
                    }
                }

            } catch (IOException e) {
                logger.error("Error reading file: ", e);
                System.err.println("Error: Unable to read the file. Check file permissions.");
            }

        } catch (ParseException e) {
            logger.error("Command-line parsing error: ", e);
            System.err.println("Error: Invalid command-line arguments. Use -i <filename> [-p <path>]");
        } catch (Exception e) {
            logger.error("Unexpected error: ", e);
            System.err.println("An unexpected error occurred. Check logs for details.");
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
