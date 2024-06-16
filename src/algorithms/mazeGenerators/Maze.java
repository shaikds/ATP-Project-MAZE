package algorithms.mazeGenerators;

import java.util.Random;

public class Maze {
    private Position startPosition, goalPosition;
    private int[][] maze;
    private final int rows;
    private final int columns;

    // Constructor - Empty maze
    public Maze(int rows, int columns) {
        // Safety check
        if (rows <= 0 || columns <= 0) {
            // minimum is rows,columns = 2
            rows = 2;
            columns =2;
        }

        this.startPosition = new Position(0, 0);
        this.goalPosition = new Position(rows - 1, columns - 1);
        // by default, each cell = 0
        maze = new int[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    // Setters for start and goal positions

    public void setStartPosition(Position startPosition) {
        this.startPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        this.goalPosition = goalPosition;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    /**
     * Sets cell value in maze, in a specific row & column.
     *
     * @param value
     * @param row
     * @param column
     */
    public void setCell(int value, int row, int column) {
        if (0 <= column && column < this.columns && 0 <= row && row < this.rows)
            maze[row][column] = value;
    }

    /**
     * Returns cell value in maze, in a specific row & column.
     *
     * @param row    - wanted row
     * @param column - wanted column
     * @return
     */
    public int getCell(int row, int column) {
        if (0 <= column && column < this.columns && 0 <= row && row < this.rows) return this.maze[row][column];
        else return -1;
    }

    public void print() {
        // prints the maze in format of 0 - path , 1 - wall , S - Start point , F - final point
        System.out.println(this);
    }


    /**
     * Returns a representation of maze object.
     *
     * @return result string represents the maze it self.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        // Iterate through all maze positions
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                // Start Position = S (tart)
                if (i == startPosition.getRowIdx() && j == startPosition.getColIdx()) result.append('S');
                    // Goal position = F (inal)
                else if (i == goalPosition.getRowIdx() && j == goalPosition.getColIdx()) result.append('F');
                else result.append(maze[i][j]);
            }
            result.append(System.lineSeparator()); // Newline after each row
        }
        return result.toString();
    }

    // Getters
    public Position getStartPosition() {
        return startPosition;
    }

    public Position getGoalPosition() {
        return goalPosition;
    }

    public int[][] getMaze() {
        return maze;
    }


    /**
     *  makes all maze cells to walls
     *
     * @param maze to transform all the cells to walls (1)
     */
    public static void setAllWalls(Maze maze) {
        for (int i = 0; i < maze.rows; i++) {
            for (int j = 0; j < maze.columns; j++) {
                maze.setCell(1, i, j);
            }
        }
    }

    public boolean isValidStep(int row, int column) {
        if (row < 0 || row >= rows || column < 0 || column >= columns) // Not in bounds
            return false;
        // Wall ? its not valid path
        if ( maze[row][column] == 1) return false;
        // Valid path
        return true;
    }



    // Randomly select a side for the entry and exit points
    protected void randomSelectStartGoal(Maze maze, int width, int height) {
        Random random = new Random();
        int side = random.nextInt(4); // 0: top, 1: right, 2: bottom, 3: left
        Position goalPosition = null;
        Position startPosition = null;
        // Looping, to make sure startPosition and goalPosition are not equal. if it does, keep randomizing.
        while((goalPosition == null && startPosition == null) || startPosition == goalPosition) {
            // Place entry point on the selected side
            switch (side) {
                case 0: // Top side
                    startPosition = (new Position(0, random.nextInt(width)));
                    break;
                case 1: // Right side
                    startPosition = (new Position(random.nextInt(height), width - 1));
                    break;
                case 2: // Bottom side
                    startPosition = (new Position(height - 1, random.nextInt(width)));
                    break;
                case 3: // Left side
                    startPosition = (new Position(random.nextInt(height), 0));
                    break;
            }

            // Place exit point on the next side (cyclically)
            int nextSide = random.nextInt(4);
            switch (nextSide) {
                case 0: // Top side
                    goalPosition = new Position(0, random.nextInt(width));
                    break;
                case 1: // Right side
                    goalPosition = new Position(random.nextInt(height), width - 1);
                    break;
                case 2: // Bottom side
                    goalPosition = new Position(height - 1, random.nextInt(width));
                    break;
                case 3: // Left side
                    goalPosition = new Position(random.nextInt(height), 0);
                    break;
            }
        }
        // Set entry and exit points as open spaces
        maze.getMaze()[startPosition.getRowIdx()][startPosition.getColIdx()] = 0;
        maze.getMaze()[goalPosition.getRowIdx()][goalPosition.getColIdx()] = 0;
        maze.setStartPosition(startPosition);
        maze.setGoalPosition(goalPosition);
    }

}
