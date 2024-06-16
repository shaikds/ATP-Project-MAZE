package algorithms.mazeGenerators;

import java.util.Random;

public abstract class AMazeGenerator implements IMazeGenerator {
    private Maze maze;


    public Maze getMaze() {
        return maze;
    }

    public void setMaze(Maze maze) {
        this.maze = maze;
    }

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

    /**
     * creates one path from start position to end position in maze
     *
     * @param maze: maze to edit
     */
    public void createPath(Maze maze) {
        Random random = new Random();

        // pathStart,pathGoal
        int pathStartRow = maze.getStartPosition().getRowIdx();
        int pathStartCol = maze.getStartPosition().getColIdx();
        int pathGoalRow = maze.getGoalPosition().getRowIdx();
        int pathGoalCol = maze.getGoalPosition().getColIdx();



        // Create a single path from start to end
        while (pathStartRow != pathGoalRow || pathStartCol != pathGoalCol) {
            // Determine the direction to move: 0 for horizontal, 1 for vertical
            int direction = random.nextInt(2);

            if (direction == 0) {
                // Horizontal movement
                if (pathStartCol < pathGoalCol ) {
                    pathStartCol++;
                } else if (pathStartCol > pathGoalCol) {
                    pathStartCol--;
                }
            } else {
                // Vertical movement
                if (pathStartRow < pathGoalRow) {
                    pathStartRow++;
                } else if (pathStartRow > pathGoalRow) {
                    pathStartRow--;
                }
            }

            // Carve out a path in the maze
            maze.setCell(0,pathStartRow, pathStartCol);
        }
    }





}
