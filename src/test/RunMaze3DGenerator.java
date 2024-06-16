package test;

import algorithms.maze3D.IMaze3DGenerator;
import algorithms.maze3D.Maze3D;
import algorithms.maze3D.MyMaze3DGenerator;
import algorithms.maze3D.Position3D;

public class RunMaze3DGenerator {

    public static void main(String[] args) {
        testMazeGenerator(new MyMaze3DGenerator());
    }

    private static void testMazeGenerator(IMaze3DGenerator mazeGenerator) {
//         prints the time it takes the algorithm to run
        System.out.println(String.format("Maze generation " +
                "time(ms): %s", mazeGenerator.measureAlgorithmTimeMillis(30/*rows*/, 30/*columns*/, 30)));
//         generate another maze
        Maze3D maze = mazeGenerator.generate(30/*rows*/, 30/*columns*/, 30);
        // prints the maze
        maze.print();
        // get the maze entrance
        Position3D startPosition = maze.getStartPosition();
        // print the start position
        System.out.println(String.format("Start Position: %s", startPosition)); // format "{row,column}"
        // prints the maze exit position
        System.out.println(String.format("Goal Position: %s", maze.getGoalPosition()));
    }
}

