package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator {

    // Constructor
    public EmptyMazeGenerator() {}


    @Override
    public Maze generate(int rows, int columns) {

        // Creates an empty maze
        return new Maze(rows, columns);
    }
}
