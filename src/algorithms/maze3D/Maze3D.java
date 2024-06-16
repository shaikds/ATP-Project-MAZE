
package algorithms.maze3D;

import java.util.Random;

/**
 * 3D Maze
 * like in 2D, the 3D maze had frame, start and end point.
 */
public class Maze3D {

    private int[][][] maze3D;
    private Position3D initialPosition;
    private Position3D finalPosition;

    /**
     * Constructor
     *
     * @param depth  of new maze
     * @param row    of new maze
     * @param column of new maze
     *               Initialize the start (0,0,0) and end point (0,row-1,column-1)
     */
    public Maze3D(int depth, int row, int column) {
        this.maze3D = new int[depth][row][column];

        initialPosition = new Position3D(0, 0, 0);
        finalPosition = new Position3D(0, row - 1, column - 1);
        setAllMazeToWalls(this);
    }

    /**
     * function that makes all maze cells -> to walls ( ==1 )
     *
     * @param maze to transform all the cells to walls (1)
     */
    public void setAllMazeToWalls(Maze3D maze) {
        for (int k = 0; k < maze.getTotalNumOfDepth(); k++) {
            for (int i = 0; i < maze.getTotalNumOfRows(); i++) {
                for (int j = 0; j < maze.getTotalNumOfColumns(); j++) {
                    maze.maze3D[k][i][j] = 1;
                }
            }
        }

    }

    /**
     * Returns true/false if a position is valid in the maze
     *
     * @param position - position to be checked
     * @return boolean.
     */
    public boolean isValidPosition(Position3D position) {
        return (position != null &&
                0 <= position.getDepthIdx() && position.getDepthIdx() < this.getTotalNumOfColumns() &&
                0 <= position.getRowIdx() && position.getRowIdx() < this.getTotalNumOfRows() &&
                0 <= position.getColIdx() && position.getColIdx() < this.getTotalNumOfDepth()
        );
    }

    /**
     * function that set a cell in maze to 0 or 1 (val)
     *
     * @param depth of the maze
     * @param row   of the maze
     * @param colum of the maze
     * @param val   to set
     */
    public void setPositionValue(int depth, int row, int colum, int val) {
        if (0 <= depth && depth <= getTotalNumOfDepth() - 1 && 0 <= row && row <= getTotalNumOfRows() - 1 && 0 <= colum && colum <= getTotalNumOfColumns() - 1)
            this.maze3D[depth][row][colum] = val;
    }

    /**
     * @return the start position
     */
    public Position3D getStartPosition() {
        if (initialPosition.getColIdx() == 0 && initialPosition.getRowIdx() == 0 && initialPosition.getDepthIdx() == 0) {
            Random random = new Random();
            int startZ = random.nextInt(getTotalNumOfDepth()) < getTotalNumOfDepth() / 2 ? 0 : getTotalNumOfDepth() - 1;
            int startX = random.nextInt(getTotalNumOfRows() - 1);
            int startY = random.nextInt(2) == 1 ? 0 : getTotalNumOfColumns() - 1;
            // choose randomly new position
            this.initialPosition = new Position3D(startZ, startX, startY);
        }

        return this.initialPosition;
    }

    /**
     * @return the goal position
     */
    public Position3D getGoalPosition() {

        return this.finalPosition;
    }


    /**
     * function that print the maze by the required format
     */
    public void print() {
        System.out.println("{");
        for (int depth = 0; depth < this.maze3D.length; depth++) {
            for (int row = 0; row < this.maze3D[0].length; row++) {
                System.out.print("{");
                for (int col = 0; col < this.maze3D[0][0].length; col++) {
                    if (depth == initialPosition.getDepthIdx() && row == initialPosition.getRowIdx() && col == initialPosition.getColIdx()) // if the position is the start - mark with S
                        System.out.print(" S");
                    else {
                        if (depth == finalPosition.getDepthIdx() && row == finalPosition.getRowIdx() && col == finalPosition.getColIdx()) // if the position is the goal - mark with E
                            System.out.print(" E");
                        else
                            System.out.print(" " + this.maze3D[depth][row][col]);
                    }
                }
                System.out.println("}");
            }
            if (depth < this.maze3D.length - 1) {
                System.out.print("---");
                for (int i = 0; i < this.maze3D[0][0].length; i++)
                    System.out.print("--");
                System.out.println();
            }
        }
        System.out.println("}");
    }


    // Getters & Setters //
    // ----------------- //
    public int[][][] getMap() {
        return this.maze3D;
    }


    public void setGoalPosition(Position3D finalPosition) {
        this.finalPosition = finalPosition;
    }

    public int getTotalNumOfDepth() {
        return this.maze3D.length;
    }

    public int getTotalNumOfRows() {
        return this.maze3D[0].length;
    }

    public int getTotalNumOfColumns() {
        return this.maze3D[0][0].length;
    }

    /**
     * Checks if an existing position is a wall (==1)
     *
     * @param position3D - position to be checked
     * @return true if a position is a wall
     */
    public boolean isWall(Position3D position3D) {
        return isValidPosition(position3D) && maze3D[position3D.getDepthIdx()][position3D.getRowIdx()][position3D.getColIdx()] == 1;
    }


    /**
     * Make a passage between two other positions, that are also passages.
     */
    public void makeABridge(Position3D currentPosition, Position3D neighbour) {
        int currDepth = currentPosition.getDepthIdx();
        int currRow = currentPosition.getRowIdx();
        int currColumn = currentPosition.getColIdx();
        int neighDepth = neighbour.getDepthIdx();
        int neighRow = neighbour.getRowIdx();
        int neighColumn = neighbour.getColIdx();

        if (currDepth == neighDepth) {
            handleSameDepth(currDepth, currRow, currColumn, neighRow, neighColumn);
        } else if (currRow == neighRow) {
            handleSameRow(currDepth, currRow, currColumn, neighDepth, neighColumn);
        } else if (currColumn == neighColumn) {
            handleSameColumn(currDepth, currRow, currColumn, neighDepth, neighRow, neighColumn);
        }
    }

    // Helper method of makeABridge
    private void handleSameDepth(int depth, int currRow, int currColumn, int neighRow, int neighColumn) {
        if (currRow == neighRow) {
            setBridgeValue(depth, currRow, Math.min(neighColumn, currColumn) + 1);
        } else if (currColumn == neighColumn) {
            setBridgeValue(depth, Math.min(neighRow, currRow) + 1, currColumn);
        }
    }

    // Helper method of makeABridge
    private void handleSameRow(int currDepth, int currRow, int currColumn, int neighDepth, int neighColumn) {
        if (currColumn == neighColumn) {
            setBridgeValue(Math.min(neighDepth, currDepth) + 1, currRow, currColumn);
        } else if (currDepth == neighDepth) {
            setBridgeValue(currDepth, currRow, Math.min(neighColumn, currColumn) + 1);
        }
    }

    // Helper method of makeABridge
    private void handleSameColumn(int currDepth, int currRow, int currColumn, int neighDepth, int neighRow, int neighColumn) {
        if (currDepth == neighDepth) {
            setBridgeValue(currDepth, Math.min(neighRow, currRow) + 1, Math.min(neighColumn, currColumn) + 1);
        } else if (currRow == neighRow) {
            setBridgeValue(Math.min(neighDepth, currDepth) + 1, currRow, currColumn);
        }
    }

    // Helper method of makeABridge
    private void setBridgeValue(int depth, int row, int column) {
        this.setPositionValue(depth, row, column, 0);
    }


    /**
     * Determines maze goal position, when finish generating the maze.
     */
    public void randomGoalPosition() {
        Random random = new Random();
        int endX, endY, endZ;

        do {
            endZ = random.nextInt(getTotalNumOfDepth()) == 1 ? 0 : getTotalNumOfDepth() - 1;
            endX = random.nextInt(getTotalNumOfRows());
            endY = random.nextInt(2) == 1 ? 0 : getTotalNumOfColumns() - 1;
        } while (getMap()[endZ][endX][endY] == 1 || getStartPosition().equals(new Position3D(endZ, endX, endY)));

        setPositionValue(endZ, endX, endY, 0);
        setGoalPosition(new Position3D(endZ, endX, endY));
    }
}
