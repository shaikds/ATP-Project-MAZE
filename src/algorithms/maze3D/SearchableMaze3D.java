package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze3D implements ISearchable {
    // Allowed Directions Are : Up,Right,Down,Left,In,Out
    final int[] depthDirections = {0, 0, 0, 0, 1, -1};
    final int[] rowDirections = {-1, 0, 1, 0, 0, 0};
    final int[] colDirections = {0, 1, 0, -1, 0, 0};

    // Values just like in the 2D SearchableMaze.
    private Maze3D maze3D;
    private Maze3DState[][][] searchable3DMaze;
    private List<AState> possibleStates;
    private AState initialState, goalState;

    // Constructor //
    public SearchableMaze3D(Maze3D maze3D) {
        if (maze3D == null) // Safety check
            throw new IllegalArgumentException("Maze Is Null");

        // Initialize variables
        this.maze3D = maze3D;
        this.searchable3DMaze = new Maze3DState[maze3D.getTotalNumOfDepth()][maze3D.getTotalNumOfRows()][maze3D.getTotalNumOfColumns()];
        this.possibleStates = new ArrayList<>();

        // Copy maze values to Searchable Maze
        initSearchableMaze3D();
        // build it and each maze state neighbors
        buildSearchableMaze3D();

        // Set the initial and goal states
        this.initialState = searchable3DMaze[maze3D.getStartPosition().getDepthIdx()][maze3D.getStartPosition().getRowIdx()][maze3D.getStartPosition().getColIdx()];
        this.goalState = searchable3DMaze[maze3D.getGoalPosition().getDepthIdx()][maze3D.getGoalPosition().getRowIdx()][maze3D.getGoalPosition().getColIdx()];
    }

    @Override
    public List<AState> getAllPossibleStates(List<AState> neighbors) {
        if (neighbors == null || neighbors.isEmpty()) return new ArrayList<>();
        ArrayList<AState> possibleStates = new ArrayList<>();
        for (AState neighbor : neighbors) {
            Maze3DState maze3DState = (Maze3DState) neighbor;
            // only a passage has a cost
            if (maze3DState.getValue() == 0) {
                maze3DState.setCost(10); // as required
                possibleStates.add(maze3DState);
            }
        }
        return possibleStates;
    }

    @Override
    public AState getInitialState() {
        return initialState;
    }

    @Override
    public AState getGoalState() {
        return goalState;
    }

    /**
     * Initialize the searchableMaze3D states with original maze3D values
     */
    private void initSearchableMaze3D() {
        for (int depth = 0; depth < maze3D.getTotalNumOfDepth(); depth++) {
            for (int rows = 0; rows < maze3D.getTotalNumOfRows(); rows++) {
                for (int cols = 0; cols < maze3D.getTotalNumOfColumns(); cols++) {
                    // Initialize new maze state
                    searchable3DMaze[depth][rows][cols] = new Maze3DState(maze3D.getMap()[depth][rows][cols], new Position3D(depth, rows, cols));

                }
            }
        }
    }

    /**
     * Fill the searchable maze states neighbors
     */
    private void buildSearchableMaze3D() {
        for (int depth = 0; depth < maze3D.getTotalNumOfDepth(); depth++) {
            for (int row = 0; row < maze3D.getTotalNumOfRows(); row++) {
                for (int col = 0; col < maze3D.getTotalNumOfColumns(); col++) {
                    for (int i = 0; i < rowDirections.length; i++) {

                        // Current Maze3D State
                        Maze3DState curr3DState = this.searchable3DMaze[depth][row][col];

                        // get updated directions for new position
                        int depthDirection = depthDirections[i];
                        int rowDirection = rowDirections[i];
                        int colDirection = colDirections[i];
                        // initialize new position
                        Position3D neighborPosition = new Position3D(depth + depthDirection, row + rowDirection, col + colDirection);

                        // If current cell is not a wall, It means its a passage
                        // Add passages to the neighbors list
                        if (maze3D.isValidPosition(neighborPosition) && !maze3D.isWall(neighborPosition)) {
                            // Add the neighbor to Current Cell neighbors List
                            curr3DState.addNeighbor(searchable3DMaze[depth + depthDirection][row + rowDirection][col + colDirection]);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<AState> allPossibleStates() {
        this.possibleStates = new ArrayList<>();
        for (int z = 0; z < maze3D.getTotalNumOfDepth(); z++) {
            for (int i = 0; i < maze3D.getTotalNumOfRows(); i++) {
                for (int j = 0; j < maze3D.getTotalNumOfColumns(); j++) {
                    if (searchable3DMaze[z][i][j].getValue() == 0)
                        possibleStates.add(searchable3DMaze[z][i][j]);
                }
            }
        }
        for (AState possibleState : possibleStates) {
            possibleState.setParent(null);
            possibleState.setColor(AState.mColor.WHITE);
        }
        return this.possibleStates;

    }
}
