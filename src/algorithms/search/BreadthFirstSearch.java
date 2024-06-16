package algorithms.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    Queue<AState> statesQueue;


    public BreadthFirstSearch() {
        statesQueue = new LinkedList<>();
    }


    @Override
    public Solution solve(ISearchable domain) {
        // Null check
        if (domain == null) return null;
        // At first, reset all the old uses of each state of the current Maze.
        List<AState> allPossibleStates =domain.allPossibleStates();

        // Add initial state
        domain.getInitialState().setColor(AState.mColor.GRAY);
        statesQueue.add(domain.getInitialState());
        while (!statesQueue.isEmpty()) {
            // Remove 1st state from the Queue
            AState currState =  statesQueue.remove();
            // Init costs for each cell
            allPossibleStates = domain.getAllPossibleStates(currState.getNeighbors());
            // neighbors traversal
            for (int i = 0; i < allPossibleStates.size(); i++) {
                AState neighbor =  allPossibleStates.get(i);
                if (neighbor.getColor().equals(AState.mColor.WHITE)) {
                    // change color - from unvisited -> to visited
                    neighbor.setColor(AState.mColor.GRAY);
                    // set parent
                    neighbor.setParent(currState);
                    // add to the queue.
                    statesQueue.add(neighbor);
                }
                // increment by 1, because we have visited a node ( For each neighbor of current state )
                incrementNumOfNodesVisited();
            }
            // increment by 1, because we have visited a node ( For curr state )
            incrementNumOfNodesVisited();
            // We have finished with the current State. Let's move on.
            currState.setColor(AState.mColor.BLACK);
        }
        // At last,
        // create new solution and return it
        return createGetSolution(domain);
    }



    @Override
    public String getName() {
        return "Breadth First Search";
    }


}
