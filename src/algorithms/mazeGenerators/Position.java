package algorithms.mazeGenerators;

public class Position {
    int rowIndex;
    int columnIndex;

    public Position(int startRow, int startColumn) {
        this.rowIndex = startRow;
        this.columnIndex = startColumn;
    }


    public int getColIdx() {
        return columnIndex;
    }

    public int getRowIdx() {
        return rowIndex;
    }

    @Override
    public String toString() {
        return "{" +
                rowIndex + ',' + columnIndex +
                '}';
    }

    /**
     * Checking if a position is equal to another position.
     *
     * @param obj
     * @return
     */

    @Override
    public boolean equals(Object obj) {
        // identical objects
        if (obj == this) return true;
        // Null and Position class check
        if (obj == null || !(obj.getClass() == this.getClass())) return false;

        Position other = (Position) obj;
        // Indexes not equal check
        if (other.getRowIdx() != rowIndex || other.getColIdx() != columnIndex) {
            return false;
        }

        return true;
    }
}
