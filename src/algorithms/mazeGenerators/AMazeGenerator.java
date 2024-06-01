package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {
    @Override
    public long measureAlgorithmTimeMillis(int rows, int columns) {
        // get start computation time
        long startTime = System.currentTimeMillis();

        // generate Maze
        generate(rows, columns);

        // get end computation time
        long endTime = System.currentTimeMillis();

        //return total computation time
        return endTime - startTime;
    }

}
