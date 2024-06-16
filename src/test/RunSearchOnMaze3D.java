package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.SearchableMaze3D;
import algorithms.search.*;

import java.util.List;

public class RunSearchOnMaze3D {

    public static void main(String[] args) {
        // New generator
        IMaze3DGenerator mg = new MyMaze3DGenerator();
        // New 3D Maze
        Maze3D maze = mg.generate(5, 5, 5);
        // print the maze
        maze.print();
        SearchableMaze3D searchableMaze = new SearchableMaze3D(maze);
        // BFS
        solveProblem(searchableMaze, new BreadthFirstSearch());
        // DFS
        solveProblem(searchableMaze, new DepthFirstSearch());
    }

    // Same function as in RunSearchOnMaze ( NOT 3D )
    private static void solveProblem(ISearchable domain, ISearchingAlgorithm
            searcher) {
//Solve a searching problem with a searcher
        Solution solution = searcher.solve(domain);

        System.out.println(String.format("'%s' algorithm - nodes evaluated: %s", searcher.getName(), searcher.getNumberOfNodesEvaluated()));
//Printing Solution Path
        System.out.println("Solution path:");
        List<AState> solutionPath = solution.getSolutionPath();
        for (int i = 0; i < solutionPath.size(); i++) {
            System.out.println(String.format("%s. %s", i, solutionPath.get(i)));
        }
    }


}
