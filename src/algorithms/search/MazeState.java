package algorithms.search;

import algorithms.mazeGenerators.Position;


public class MazeState extends AState {
    private Position position;

    public MazeState(int value, Position position) {
        super(value);
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void addNeighbor(MazeState neighbor) {
        neighbors.add(neighbor);
    }



    @Override
    public int hashCode() {
        return position.hashCode();
    }

@Override
    public String toString() {
        return "{" + position.getRowIdx()+"," + position.getColIdx()+"}";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==this) return true;
        if (!(obj instanceof MazeState)) return false;
        MazeState other = (MazeState) obj;
        return position.equals(other.position);
    }
}
