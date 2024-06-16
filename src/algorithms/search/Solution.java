package algorithms.search;

import java.util.ArrayList;

public class Solution {
    ArrayList<AState> path;

    // Constructor //
    public Solution() {
        this.path = new ArrayList<>();
    }

    // Getter //
    public ArrayList<AState> getSolutionPath() {
        return  path;
    }


    // Add a State to the current path. //
    boolean addToPath(AState state) {
        try {
            path.add(state);
            return true; // Adding Completed.
        } catch (Exception e) {
            return false; // Adding failed.
        }
    }


    public void setSolutionPath(ArrayList<AState> reversed) {
        this.path = reversed;
    }
}
