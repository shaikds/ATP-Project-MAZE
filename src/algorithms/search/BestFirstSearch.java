package algorithms.search;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

public class BestFirstSearch extends BreadthFirstSearch {
    //constructor
    public BestFirstSearch() {
        statesQueue = new PriorityQueue<>(Comparator.comparingInt(AState::getCost).reversed());
    }

    @Override
    public String getName() {
        return "Best First Search";
    }

}
