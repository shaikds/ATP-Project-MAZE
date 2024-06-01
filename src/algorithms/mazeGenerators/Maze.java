package algorithms.mazeGenerators;

import java.util.HashMap;

public class Maze {
    private Position startPosition, goalPosition;
    private HashMap<Position, Integer> positionMap;
    private Position[][] maze;
    protected static final int[][] directions ={{0,-1},{1,0},{0,1},{-1,0}};

    // Constructor - Empty maze
    public Maze(int rows, int columns) {
        // Safety check ( TODO : Maybe add 0 check for tests)
        if (rows < 0 || columns < 0) return;

        // Create the maze
        maze = new Position[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maze[i][j] = new Position(i, j);
            }
        }
    }


    // toString
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        // Iterate through all maze positions
        for (Position position : positionMap.keySet()) {

            // Start Position = S (tart)
            if (position.equals(startPosition)) result.append('S');

                // Goal position = F (inal)
            else if (position.equals(goalPosition)) result.append('F');

                // All other position values.
            else result.append(positionMap.get(position));
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

    public HashMap<Position, Integer> getPositionMap() {
        return positionMap;
    }

    public Position[][] getMaze() {
        return maze;
    }


    // End Getters

    // Inbound checks
    protected boolean inBounds(Position position) {
        return positionMap.containsKey(position);
    }

}
