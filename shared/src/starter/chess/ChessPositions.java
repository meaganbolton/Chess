package chess;

import java.util.Objects;

public class ChessPositions implements ChessPosition{
    private int row;
    private int col;
    public ChessPositions(Integer row, Integer col){
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPositions that = (ChessPositions) o;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
