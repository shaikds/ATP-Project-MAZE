package algorithms.maze3D;

import algorithms.search.AState;

public class Maze3DState extends AState {
    private Position3D position3D;

    /**
     * Constructor of 3D State. throws exception if position came null
     *
     * @param value
     * @param position3D
     */
    public Maze3DState(int value, Position3D position3D) {
        super(value);
        if (position3D != null)
            this.position3D = position3D;
        else throw new NullPointerException("position3D is null");
    }

    /**
     * Getter for position
     *
     * @return the current state position.
     */
    public Position3D getPosition3D() {
        return position3D;
    }

    @Override
    public String toString() {
        return "{" +position3D.getDepthIdx() +","+ position3D.getRowIdx()+"," + position3D.getColIdx()+"}";
    }

}
