package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int rows, int columns) {
        // Map for marking for each Position:Visited/Not Visited
        Maze maze = new Maze(rows, columns);
        int totalCells = rows * columns;
        int visitedCells = 1;

        Random random = new Random();
        // get start location randomly
        // TODO : rows, columns maybe need to add 1 for each of them.
        int startRow = random.nextInt(rows);
        int startColumn = random.nextInt(columns);
        // Create start position
        Position startPosition = new Position(startRow, startColumn);
        // Mark startPosition as visited
        maze.getPositionMap().put(startPosition, 1);

        // Loop through the map
        while (visitedCells < totalCells) {
            int[] direction = Maze.directions[random.nextInt(4)];
            int nx = startRow + direction[0];
            int ny = startColumn + direction[1];

            Position newPosition = new Position(nx, ny);
            // new Position in bounds of maze?
            if (maze.inBounds(newPosition)) {
                // new Position is visited?
                if (maze.getPositionMap().get(newPosition) == 0) {
                    // Remove walls between current cell and the next cell
                    removeWalls(x, y, nx, ny);
                    maze.getPositionMap().put(newPosition, 1);
                    visitedCells++;
                }
                // Move to the next cell
                startRow = nx;
                startColumn = ny;
            }


            private void removeWalls(int x1, int y1, int x2, int y2) {
                if (x1 == x2) {
                    if (y1 > y2) {
                        maze.get[x1][y1].walls[0] = false; // Remove Up wall of (x1, y1)
                        maze.get[x2][y2].walls[2] = false; // Remove Down wall of (x2, y2)
                    } else {
                        maze.get[x1][y1].walls[2] = false; // Remove Down wall of (x1, y1)
                        maze.get[x2][y2].walls[0] = false; // Remove Up wall of (x2, y2)
                    }
                } else if (y1 == y2) {
                    if (x1 > x2) {
                        maze.get[x1][y1].walls[3] = false; // Remove Left wall of (x1, y1)
                        maze.get[x2][y2].walls[1] = false; // Remove Right wall of (x2, y2)
                    } else {
                        maze.get[x1][y1].walls[1] = false; // Remove Right wall of (x1, y1)
                        maze.get[x2][y2].walls[3] = false; // Remove Left wall of (x2, y2)
                    }
                }
            }
        }
    }

}
}
