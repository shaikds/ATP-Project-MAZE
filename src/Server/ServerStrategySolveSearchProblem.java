package Server;

import algorithms.mazeGenerators.Maze;
import algorithms.search.*;

import java.io.*;

public class ServerStrategySolveSearchProblem implements IServerStrategy {


    // empty ctr
    public ServerStrategySolveSearchProblem() {
    }

    @Override
    public void applyStrategy(InputStream inputStream, OutputStream outputStream) {
        ASearchingAlgorithm searcher;

        try {
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);

            // get maze from client
            Maze maze = (Maze) fromClient.readObject();

            // get searching algorithm from configuration file
            String algorithm = Configurations.getInstance().getSearchingAlgo();

            // get type of searcher
            searcher = getSearchingAlgorithm(algorithm);


            // create new searchable maze from built maze
            SearchableMaze searchableMaze = new SearchableMaze(maze);


            // get identifier for maze to solution
            int hashCode = maze.hashCode();

            // load from solution file or create a solution file if not existed
            Solution solution = saveOrLoadTmpFile(hashCode, searchableMaze, searcher);

            // send to client
            toClient.writeObject(solution);
            toClient.flush();

            // close connections
            toClient.close();
            fromClient.close();

        } catch (Exception e) {

        }


    }

    private ASearchingAlgorithm getSearchingAlgorithm(String algorithm) {
        if (algorithm.equals("BreadthFirstSearch")) return new BreadthFirstSearch(); // BFS
        else if (algorithm.equals("DepthFirstSearch")) return new DepthFirstSearch(); // DFS
        else if (algorithm.equals("BestFirstSearch")) return new BestFirstSearch(); // BestFS

        // None of the cases ?
        return null;
    }

    private Solution saveOrLoadTmpFile(int hashCode, SearchableMaze searchableMaze, ASearchingAlgorithm searcher) {
        String tempDirectoryPath = System.getProperty("java.io.tmpdir");
        Solution res = new Solution();
        String path = tempDirectoryPath + hashCode;
        // create new file
        File file = new File(path); // sol0,sol1,....,soln
        if (file.exists()) { // file exists -> read the solution
            try {
                FileInputStream in = new FileInputStream(path);
                ObjectInputStream inObj = new ObjectInputStream(in);
                // read from existing file
                res = (Solution) inObj.readObject();
                // close the streams
                inObj.close();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else { // file not exists -> save the solution in new file
            try {
                // solve the searchable maze by the searcher type
                res = searcher.solve(searchableMaze);

                // write solution into new file
                FileOutputStream out = new FileOutputStream(path);
                ObjectOutputStream objOut = new ObjectOutputStream(out);
                objOut.writeObject(res);
                objOut.flush();

                // close the stream
                out.close();
                objOut.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return res;
    }
}
