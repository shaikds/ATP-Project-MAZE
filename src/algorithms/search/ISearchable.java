package algorithms.search;

import java.util.List;

public interface ISearchable {

    List<AState> getAllPossibleStates(List<AState> neighbors);

    // The Searchable Properties That Depends On The Problem ( The Maze )

    AState getInitialState();

    AState getGoalState();

    List<AState> allPossibleStates();

}
