package algorithms.mazeGenerators;

public class Position {
    int rowIndex;
    int columnIndex;

    public Position(int startRow, int startColumn) {
        this.rowIndex = startRow;
        this.columnIndex = startColumn;
    }


    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    @Override
    public String toString() {
        return "{" +
                rowIndex + ',' + columnIndex +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        // Null and Position class check
        if (obj == null || !(obj instanceof Position other)) {
            return false;
        }

        // Indexes not equal check
        if (other.getRowIndex() != rowIndex || other.getColumnIndex() != columnIndex) {
            return false;
        }

        return true;
    }
}
