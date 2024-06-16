


package algorithms.maze3D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator {
    private List<int[]> mWalls;
    Maze3D mMaze3D;
    Random rnd = new Random();


    @Override
    public Maze3D generate(int depth, int row, int column) {
        // Basic condition
        if (depth < 2 || row < 2 || column < 2)
            throw new IllegalArgumentException("Invalid Maze Dimensions Input");

        // Initalize list
        this.mWalls = new ArrayList<>();

        // Creates new maze with all walls
        mMaze3D = new Maze3D(depth, row, column);

        // return the new maze we've created.
        return DFS3DGenerator(mMaze3D);

    }


    /**
     * Creates a maze3D object and returns it
     * works by iterative DFS algorithm
     * @param maze to modify and keep creating
     * @return maze object
     */
    private Maze3D DFS3DGenerator(Maze3D maze) {
        Stack<Position3D> passNeighbors = new Stack<>();
        Position3D currentPosition = maze.getStartPosition();
        maze.setPositionValue(currentPosition.getDepthIdx(), currentPosition.getRowIdx(), currentPosition.getColIdx(), 0);
        passNeighbors.push(currentPosition);
        while (!passNeighbors.isEmpty()) {
            currentPosition = passNeighbors.pop();
            mWalls.clear();
            addWallNeighbors(maze, currentPosition.getDepthIdx(), currentPosition.getRowIdx(), currentPosition.getColIdx(), mWalls);
            if (!mWalls.isEmpty()) {
                passNeighbors.push(currentPosition);

                int[] randomNeighbor = mWalls.get(rnd.nextInt(mWalls.size()));
                Position3D currRandomNeighbor = new Position3D(randomNeighbor[0], randomNeighbor[1], randomNeighbor[2]);
                maze.setPositionValue(randomNeighbor[0], randomNeighbor[1], randomNeighbor[2], 0);
                maze.makeABridge(currentPosition, currRandomNeighbor);
                passNeighbors.push(currRandomNeighbor);

            }
        }
        maze.randomGoalPosition();
        return maze;
    }

    // Adds wall neighbors to the input list
    private void addWallNeighbors(Maze3D maze, int z, int x, int y, List<int[]> list) {

        if (x - 2 >= 0) { // Up
            if ((maze.isWall(new Position3D(z, x - 2, y)))) {
                list.add(new int[]{z, x - 2, y});
            }
        }
        if (y + 2 <= maze.getTotalNumOfColumns() - 1) { // Right
            if ((maze.isWall(new Position3D(z, x, y + 2)))) {
                list.add(new int[]{z, x, y + 2});
            }
        }
        if (x + 2 <= maze.getTotalNumOfRows() - 1) { // Down
            if ((maze.isWall(new Position3D(z, x + 2, y)))) {
                list.add(new int[]{z, x + 2, y});
            }
        }
        if (y - 2 >= 0) { // Left
            if ((maze.isWall(new Position3D(z, x, y - 2)))) {
                list.add(new int[]{z, x, y - 2});
            }
        }

        if (z - 2 >= 0) { // Outside
            if ((maze.isWall(new Position3D(z - 2, x, y)))) {
                list.add(new int[]{z - 2, x, y});
            }
        }
        if (z + 2 <= maze.getTotalNumOfDepth() - 1) { // Inside
            if ((maze.isWall(new Position3D(z + 2, x, y)))) {
                list.add(new int[]{z + 2, x, y});
            }
        }
    }

}

