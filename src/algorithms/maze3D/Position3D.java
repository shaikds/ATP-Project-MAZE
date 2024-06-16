
package algorithms.maze3D;

/**
 * This class is a representation of a Position in a 3D maze ( depth added )
 */
public class Position3D {

    private int depth;
    private int row;
    private int colum;

    /**
     * Constructor
     *
     * @param depth
     * @param row
     * @param column
     */
    public Position3D(int depth, int row, int column) {
        this.depth = depth;
        this.row = row;
        this.colum = column;
    }

    /**
     * @return depth index
     */
    public int getDepthIdx() {

        return this.depth;
    }

    /**
     * @return row index
     */
    public int getRowIdx() {

        return this.row;
    }

    /**
     * @return column index
     */
    public int getColIdx() {

        return this.colum;
    }

    /**
     * toString
     *
     * @return representation of a position 3D when printing.
     */
    @Override
    public String toString() {
        return "{ d:" + this.getDepthIdx() + "r:" + this.getRowIdx() + " c:" + this.getColIdx() + "}";
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj != null && obj.getClass() == this.getClass()) {

            Position3D position3D = (Position3D) obj;
            return this.row == position3D.row && this.colum == position3D.colum && this.depth == position3D.depth;
        }

        return false;
    }
}
