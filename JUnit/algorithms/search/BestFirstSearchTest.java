package algorithms.search;

import algorithms.mazeGenerators.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BestFirstSearchTest {


    // Name Check //
    @Test
    void getName() {
        assertEquals("Best First Search", best.getName());
    }

    // ------------------//
    // Best First Search
    private BestFirstSearch best = new BestFirstSearch();

    // Maze Generators- Try them all.
    private IMazeGenerator sMaze = new SimpleMazeGenerator();
    private IMazeGenerator mMaze = new MyMazeGenerator();
    private IMazeGenerator eMaze = new EmptyMazeGenerator();
    //Mazes - MINUS, 1000 - MAXIMUM by requirement.
    private Maze simpleMaze2 = sMaze.generate(1000, 1000);
    private Maze simpleMaze3 = sMaze.generate(-5, -5);
    private Maze emptyMazeI = eMaze.generate(-20, -25);
    private Maze emptyMazeIII = eMaze.generate(1000, 1000);
    private Maze MyMaze2 = mMaze.generate(1000, 1000);
    private Maze MyMaze3 = mMaze.generate(-5, -5);
    // Searchable Mazes
    private SearchableMaze simpleSearchableMaze2 = new SearchableMaze(simpleMaze2);
    private SearchableMaze simpleSearchableMaze3 = new SearchableMaze(simpleMaze3);
    private SearchableMaze mySearchableMaze2 = new SearchableMaze(MyMaze2);
    private SearchableMaze mySearchableMaze3 = new SearchableMaze(MyMaze3);
    private SearchableMaze emptySearchableMaze1 = new SearchableMaze(emptyMazeI);
    private SearchableMaze emptySearchableMaze3 = new SearchableMaze(emptyMazeIII);

    @Test
    void solve() {
        Solution solution;
        solution = best.solve(null);
        assertNull(solution);
        solution = best.solve(emptySearchableMaze1);
        assert (checkMazeSolution(solution, emptyMazeI));
        solution = best.solve(emptySearchableMaze3);
        assert (checkMazeSolution(solution, emptyMazeIII));
        solution = best.solve(simpleSearchableMaze3);
        assert (checkMazeSolution(solution, simpleMaze3));
        solution = best.solve(mySearchableMaze3);
        assert (checkMazeSolution(solution, MyMaze3));
        solution = best.solve(mySearchableMaze2);
        assert (checkMazeSolution(solution, MyMaze2));
        solution = best.solve(simpleSearchableMaze2);
        assert (checkMazeSolution(solution, simpleMaze2));

    }


    private boolean checkMazeSolution(Solution solution, Maze maze) {
        if (maze != null && solution == null) return false;
        assert maze != null;
        if (maze.getStartPosition() == null || maze.getGoalPosition() == null) return false; // Goal||Initial == null ?
        if (maze.getStartPosition().equals(maze.getGoalPosition())) return false; // Goal == Initial ?

        List<AState> solutionPath = solution.getSolutionPath();

        for (AState aState : solutionPath) {
            if (aState.getCost() == 0) return false; // Cost 0 check ( 10,15 only)
            if (aState.getValue() == 1) return false; // Wall in solution checks
        }
        return true;

    }

}