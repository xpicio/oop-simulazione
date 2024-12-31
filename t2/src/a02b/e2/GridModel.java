package a02b.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class GridModel {
    private enum Diagonals {
        UPPER_DIAGONALS,
        LOWER_DIAGONALS
    }

    public class CellStatus {
        private boolean isSelected;
        private boolean isEnabled;

        public CellStatus() {
            this.isSelected = false;
            this.isEnabled = true;
        }

        public CellStatus(final boolean isSelected, final boolean isEnabled) {
            this.isSelected = isSelected;
            this.isEnabled = isEnabled;
        }

        public boolean isSelected() {
            return this.isSelected;
        }

        public boolean isEnabled() {
            return this.isEnabled;
        }
    }

    private final Map<Pair<Integer, Integer>, CellStatus> grid;
    private final int size;
    private final int itemSelectedPerDiagonal;

    public GridModel(final int size, final int itemSelectedPerDiagonal) {
        this.size = size;
        this.itemSelectedPerDiagonal = itemSelectedPerDiagonal;
        this.grid = new HashMap<>();

        IntStream.range(0, this.size).forEach(y -> {
            IntStream.range(0, this.size).forEach(x -> {
                this.grid.put(new Pair<Integer, Integer>(x, y), new CellStatus());
            });
        });
    }

    private boolean updateDiagonal(Diagonals diagonals) {
        final Map<Pair<Integer, Integer>, CellStatus> currentDiagonal = new HashMap<>();
        for (int i = 0; i < this.size; i++) {
            int x = diagonals.equals(Diagonals.UPPER_DIAGONALS) ? i : 0;
            int y = diagonals.equals(Diagonals.LOWER_DIAGONALS) ? i : 0;
            while (x < this.size && y < this.size) {
                var cell = new Pair<>(x, y);
                currentDiagonal.put(cell, this.grid.get(cell));
                x++;
                y++;
            }

            if (currentDiagonal.entrySet().stream().filter(k -> k.getValue().isSelected())
                    .count() == this.itemSelectedPerDiagonal) {
                currentDiagonal.forEach(
                        (cell, cellStatus) -> this.grid.put(cell, new CellStatus(cellStatus.isSelected(), false)));

                return true;
            }

            currentDiagonal.clear();
        }

        return false;
    }

    public Map<Pair<Integer, Integer>, CellStatus> getValues() {
        return new HashMap<>(this.grid);
    }

    public CellStatus selectCell(final int x, final int y) {
        final CellStatus previousCellStatus = this.grid.get(new Pair<Integer, Integer>(x, y));
        final CellStatus cellStatus = new CellStatus(!previousCellStatus.isSelected(), true);
        this.grid.put(new Pair<Integer, Integer>(x, y), cellStatus);
        return cellStatus;
    }

    public boolean updateDiagonal() {
        if (!this.updateDiagonal(Diagonals.UPPER_DIAGONALS)) {
            return this.updateDiagonal(Diagonals.LOWER_DIAGONALS);
        }

        return true;
    }
}
