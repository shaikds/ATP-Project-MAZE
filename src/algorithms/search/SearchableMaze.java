package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze implements ISearchable {

    private Maze maze;
    private MazeState[][] searchableMaze;
    private MazeState goalState, initState;
    final int[] rowDirections = {-1, -1, 0, 1, 1, 1, 0, -1};
    final int[] colDirections = {0, 1, 1, 1, 0, -1, -1, -1};
    private List<AState> possibleStates;

    public SearchableMaze(Maze maze) {
        if (maze != null) {
            this.maze = maze;
            this.initState = new MazeState(0, maze.getStartPosition());
            this.goalState = new MazeState(0, maze.getGoalPosition());
            this.searchableMaze = new MazeState[maze.getRows()][maze.getColumns()];
            this.possibleStates = new ArrayList<>();

            initSearchableMazeCells();
            buildSearchableMaze(maze);

            this.initState = searchableMaze[maze.getStartPosition().getRowIdx()][maze.getStartPosition().getColIdx()];
            this.goalState = searchableMaze[maze.getGoalPosition().getRowIdx()][maze.getGoalPosition().getColIdx()];
        }
    }

    private void initSearchableMazeCells() {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                this.searchableMaze[i][j] = new MazeState(maze.getCell(i, j), new Position(i, j));
            }
        }
    }

    protected void buildSearchableMaze(Maze maze) {
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getColumns(); col++) {
                for (int i = 0; i < rowDirections.length; i++) {
                    int rowDirection = rowDirections[i];
                    int colDirection = colDirections[i];
                    if (maze.isValidStep(row + rowDirection, col + colDirection)) {
                        this.searchableMaze[row][col].addNeighbor(searchableMaze[row + rowDirection][col + colDirection]);
                    }
                }
            }
        }
    }


    @Override
    public ArrayList<AState> getAllPossibleStates(List<AState> neighbors) {

        ArrayList<AState> possibleStates = new ArrayList<>();
        for (AState currState : neighbors) {
            if (currState.getValue() == 0) {
                determineCosts((MazeState) currState);
                possibleStates.add(currState);
            }
        }
        return possibleStates;
    }

    private void determineCosts(MazeState state) {
        MazeState parent = (MazeState) (state.getParent());
        if (parent != null && Math.abs(state.getPosition().getRowIdx() - parent.getPosition().getRowIdx()) == 1 && Math.abs(state.getPosition().getColIdx() - parent.getPosition().getColIdx()) == 1)
            state.setCost(15);
        else state.setCost(10);
    }

    @Override
    public AState getInitialState() {
        return initState;
    }

    @Override
    public AState getGoalState() {
        return goalState;
    }



    @Override
    public List<AState> allPossibleStates() {
        this.possibleStates = new ArrayList<>();
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getColumns(); j++) {
                if (searchableMaze[i][j].getValue() == 0)
                    possibleStates.add(searchableMaze[i][j]);
            }
        }
        for (AState possibleState : possibleStates) {
            possibleState.setParent(null);
            possibleState.setColor(AState.mColor.WHITE);
        }
        return this.possibleStates;
    }

}
