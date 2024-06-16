package algorithms.search;

import java.util.List;
import java.util.Stack;

/**
 * DFS search algorithm implementation
 */
public class DepthFirstSearch extends ASearchingAlgorithm {


    /**
     * DFS algorithm
     */
    public void DFS(ISearchable domain, AState start) {
        // At first, reset all the old uses of each state of the current Maze.
        List<AState> allPossibleStates =  domain.allPossibleStates();

        Stack<AState> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            AState currState = stack.pop();
            if (currState.getColor().equals(AState.mColor.WHITE)) {
                currState.setColor(AState.mColor.GRAY);
                List<AState> adjVertices = currState.getNeighbors();
                for (AState v : adjVertices) {
                    if (v.getColor().equals(AState.mColor.WHITE)) {
                        stack.push(v);
                        v.setParent(currState);

                        // for every adj we visited mark 1+ node we evaluated
                        incrementNumOfNodesVisited();
                    }
                }
            }
            currState.setColor(AState.mColor.BLACK);
            // dont forget to increment numOfNodes evaluated
            incrementNumOfNodesVisited();
        }
    }

    @Override
    public String getName() {
        return "Depth First Search";
    }

    /**
     * Use DFS algorithm and return a solution
     *
     * @param domain the problem for solving
     * @return a new solution for this problem
     */
    @Override
    public Solution solve(ISearchable domain) {
        DFS(domain, domain.getInitialState());

        // get&return result path
        Solution result = this.createGetSolution(domain);
//        setNumberOfNodes(result.getSolutionPath().size());
        return result;

    }


}