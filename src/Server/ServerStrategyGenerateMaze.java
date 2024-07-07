package Server;

import IO.MyCompressorOutputStream;
import algorithms.mazeGenerators.*;

import java.io.*;

public class ServerStrategyGenerateMaze implements IServerStrategy {

    // Empty constructor
    public ServerStrategyGenerateMaze() {
    }

    @Override
    public void applyStrategy(InputStream inputStream, OutputStream outputStream) {
        try (ObjectInputStream fromClient = new ObjectInputStream(inputStream);
             ObjectOutputStream toClient = new ObjectOutputStream(outputStream)) {

            // Get the input sizes of the maze request from client.
            int[] sizes = (int[]) fromClient.readObject();

            byte[] customMaze = createMaze(sizes[0], sizes[1]);

            // Respond to the client with the object
            toClient.writeObject(customMaze);
            toClient.flush();

            // close the streams
            toClient.close();
            fromClient.close();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Creating a maze
    private byte[] createMaze(int rows, int cols) {
        AMazeGenerator generator;

        // Get the algorithm wanted to create a maze with from the config file
        String algorithm = Configurations.getInstance().getMazeGenerator();

        // Create maze generator
        generator = createGenerator(algorithm);
        if (generator == null) {
            throw new RuntimeException("Maze generator algorithm not found: " + algorithm);
        }

        // Create maze
        Maze maze = generator.generate(rows, cols);

        // Compress the maze
        return compressMaze(maze.toByteArray());
    }

    // Compress maze represented by byte array
    private byte[] compressMaze(byte[] maze) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (MyCompressorOutputStream out = new MyCompressorOutputStream(outputStream)) {
            out.write(maze);
            out.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

    // Generator creator by string input
    private AMazeGenerator createGenerator(String algorithm) {
        if (algorithm == null || algorithm.isEmpty()) {
            throw new IllegalArgumentException("Algorithm cannot be null or empty");
        }

        switch (algorithm) {
            case "SimpleMazeGenerator":
                return new SimpleMazeGenerator();
            case "MyMazeGenerator":
                return new MyMazeGenerator();
            case "EmptyMazeGenerator":
                return new EmptyMazeGenerator();
            default:
                throw new IllegalArgumentException("Generator type not found: " + algorithm);
        }
    }
}
