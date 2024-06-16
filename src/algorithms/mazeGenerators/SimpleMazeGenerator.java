package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {

    // Constructor
    public SimpleMazeGenerator() {
        super();
    }


    /**
     * crates a simple maze that has one or more paths.
     *
     * @param columns: number of columns in maze
     * @param rows:    number of rows in maze
     * @return : Maze type
     */
    @Override
    public Maze generate(int rows, int columns) {

        // init new maze with the input rows&columns number.
        Maze maze = new Maze(rows, columns);

        // Prevent bugs of negative/0
        rows = maze.getRows();
        columns = maze.getColumns();

        //  all maze cells = 1
        Maze.setAllWalls(maze);

        // set start&goal positions in maze - Randomly
        maze.randomSelectStartGoal(maze, columns, rows);

        // create a solution path
        createPath(maze);

        // determine cells to 0/1 randomly
        randomizeCells(maze);

        return maze;
    }


    /**
     * All the cells that are not equal to 1(NOT PART OF THE PATH), will randomly change to 1,0.
     *
     * @param maze:int 2D maze representation to be updated.
     */
    public void randomizeCells(Maze maze) {
        Random random = new Random();
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getColumns(); col++) {
                int cellValue = random.nextInt(2);
                if (maze.getCell(row, col) == 1) // cell == 1 ====> part of the path.
                    maze.setCell(cellValue, row, col);
            }
        }
    }
}