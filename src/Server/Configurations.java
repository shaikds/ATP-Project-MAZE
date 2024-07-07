package Server;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Singleton class, defining the config.properties file
 */
public class Configurations {

    private Properties properties ;
    private static Configurations instance = null;

    private final int NUM_OF_THREADS = 1;
    private final String GENERATOR = "SimpleMazeGenerator";
    private final String SEARCHER = "BreadthFirstSearch";

    // private constructor
    private Configurations() {
        InputStream input = null;
        try {

            //TODO : CHANGE PATH !!! //
            OutputStream out = new FileOutputStream("/Users/shaikds/Documents/GitHub/ATP-Project-MAZE/resources/config.properties");
            properties = new Properties();
            properties.setProperty("mazeGeneratingAlgorithm", GENERATOR);
            properties.setProperty("mazeSearchingAlgorithm", SEARCHER);
            properties.setProperty("threadPoolSize", String.valueOf(NUM_OF_THREADS));

            properties.store(out,null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Singleton get instance
    public static Configurations getInstance() {
        // First initialize ? Create new instance
        if (instance == null) {
            instance = new Configurations();
        }
        // Either way
        return instance;

    }

    // Get num of threads in thread pool defined in Properties file
    public  int getNumOfThreads() {
        return Integer.parseInt(properties.getProperty("threadPoolSize"));
    }

    // Get Searching algorithm
    public  String getSearchingAlgo() {
        return this.properties.getProperty("mazeSearchingAlgorithm");
    }

    // Get Maze Generator Algorithm
    public  String getMazeGenerator() {
        return this.properties.getProperty("mazeGeneratingAlgorithm");
    }


}
