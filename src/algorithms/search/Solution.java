package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    ArrayList<AState> path;

    // Constructor //
    public Solution() {
        this.path = new ArrayList<>();
    }

    // Getter //
    public ArrayList<AState> getSolutionPath() {
        return path;
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

    //???????//=.=
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj.getClass() == Solution.class)) return false;
        Solution other = (Solution) obj;
        // not same length
        if (other.getSolutionPath().size() != path.size()) return false;

        for (int i = 0; i < path.size(); i++) {
            AState otherState = other.getSolutionPath().get(i);
            AState thisState = path.get(i);
            if (!otherState.equals(thisState)) return false;
        }

        return false;
    }
}
