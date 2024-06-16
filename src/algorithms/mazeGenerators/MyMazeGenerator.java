package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {

    ArrayList<Position> wallNeighbors;

    // Constructor
    public MyMazeGenerator() {
        wallNeighbors = new ArrayList<>();
    }


    /**
     * Find the nearest neighbors of currPosition
     *
     * @param currPosition current position
     */
    public void setNeighbors(Maze maze, Position currPosition) {
        int countVisited = 0;
        countVisited = primHelper(currPosition, maze, countVisited, true);
        // By prim: If only one of the cells that the wall divides is visited, then:
        if (countVisited < 2) {
            maze.setCell(0, currPosition.getRowIdx(), currPosition.getColIdx());
            primHelper(currPosition, maze, countVisited, false);
        }
    }

    // Loop through all non-diagonal neighbors of a position
    private int primHelper(Position point, Maze maze, int countVisited, boolean isFirstStage) {
        for (int rowIdx = point.getRowIdx() - 1; rowIdx <= point.getRowIdx() + 1; rowIdx++)
            for (int columnIdx = point.getColIdx() - 1; columnIdx <= point.getColIdx() + 1; columnIdx++)
                if (rowIdx >= 0 && rowIdx < maze.getRows() && columnIdx >= 0 && columnIdx < maze.getColumns()) //in bounds
                    if (!(rowIdx != point.getRowIdx() && columnIdx != point.getColIdx())) // not diagonal
                        if (isFirstStage && maze.getCell(rowIdx, columnIdx) == 0)
                            countVisited++;
                        else if (!isFirstStage && maze.getCell(rowIdx, columnIdx) == 1)
                            wallNeighbors.add(new Position(rowIdx, columnIdx));

    return countVisited;}

    /**
     * Iterative randomized Prim's algorithm for maze creation
     *
     * @param rows
     * @param columns
     * @return
     */

    @Override
    public Maze generate(int rows, int columns) {
        Maze maze = new Maze(rows, columns);
        // Prevent bugs of negative/0
        rows = maze.getRows();
        columns = maze.getColumns();

        // fill all maze with walls ( each cell == 1 )
        Maze.setAllWalls(maze);

        // Select start&goal positions randomly
        maze.randomSelectStartGoal(maze, columns, rows);

        // add the start position to the list
        wallNeighbors.add(maze.getStartPosition());

        // randomize decisions in prims algorithm
        Random random = new Random();

        // Prim's algorithm //
        while (!wallNeighbors.isEmpty()) {
            // randomly remove a wall neighbor of each position
            Position wallPosition = wallNeighbors.remove(random.nextInt(wallNeighbors.size()));
            setNeighbors(maze, wallPosition);

        }

        //At last, Create a path
        createPath(maze);

        return maze;

    }
}