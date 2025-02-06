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
        options.addOption("i", true, "Input file for maze path");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        logger.info("** Starting Maze Runner");
        System.out.println("Ball");

        // Debug arguments
        for (int i = 0; i < args.length; i++) {
            System.out.println("Arg " + i + ": " + args[i]);
        }

        try {
            cmd = parser.parse(options, args);

            if (!cmd.hasOption("i")) {
                logger.error("No input file provided. Use -i <filename>");
                return;
            }

            String fileInput = cmd.getOptionValue("i");
            logger.info("**** Reading the maze from file " + fileInput);

            File file = new File(fileInput);
            if (!file.exists()) {
                logger.error("File not found: " + fileInput);
                return;
            }

            char[][] maze;
            System.out.println("Reading from file");
            try (BufferedReader reader = new BufferedReader(new FileReader(fileInput))) {
                System.out.println("Reading from file");
                String line = reader.readLine();
                if (line == null) {
                    logger.error("Empty maze file.");
                    return;
                }

                int counter = 0;
                int length = line.length();
                System.out.println(line);
                System.out.println(length);
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

                System.out.println(length);
                Maze m = new Maze(maze, length, counter);
                MazeSolver ms = new MazeSolver(m);
                ms.solveMaze();
                System.out.println("Printing Maze:");
                System.out.println(ms.printPath());

            } catch (IOException e) {
                logger.error("Error reading file: ", e);
            }
        } catch (ParseException e) {
            logger.error("Command-line parsing error: ", e);
        }
    }

    private static char[][] resizeArray(char[][] arr, int height, int length) {
        System.out.println("Resizing");
        char[][] newArr = new char[height + 1][length];
        for (int i = 0; i < height; i++) {
            System.arraycopy(arr[i], 0, newArr[i], 0, length);
        }
        return newArr;
    }
}
