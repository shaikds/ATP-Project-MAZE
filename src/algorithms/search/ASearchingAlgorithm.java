package algorithms.search;

import java.util.ArrayList;
import java.util.List;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    private int numOfNodes;

    @Override
    public int getNumberOfNodesEvaluated() {
        return (numOfNodes);
    }

    public void setNumberOfNodes(int numOfNodes) {
        if (numOfNodes < 0) return;
        this.numOfNodes = numOfNodes;
    }

    public void incrementNumOfNodesVisited() {
        numOfNodes++;
    }

    // Creates new solution of the problem it solves, and return its list of state for end to start //
    // -------------------------------------------------------------------------------------------- //
    public Solution createGetSolution(ISearchable domain) {
        // Create new solution
        Solution result = new Solution();
        // set currState as goalState -> to go backwards and add it to the solution result.
        AState currState = domain.getGoalState();
        while (currState.getParent() != null) {
            result.addToPath(currState);
            currState = currState.getParent();
        }
        // add init state to result
        currState = domain.getInitialState();
        result.addToPath(currState);
        ArrayList<AState> reversed = new ArrayList<>();
        reversed = reverseList(result.getSolutionPath());

        result.setSolutionPath(reversed);
        // return the path
        return result;
    }

    // Reverse the list - JAVA 15 not allowing to use reversed function.
    private ArrayList<AState> reverseList(List<AState> list) {
        ArrayList<AState> reversed = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            AState temp = list.get(size - i - 1);
            reversed.add(temp);
        }
        return reversed;
    }
}
