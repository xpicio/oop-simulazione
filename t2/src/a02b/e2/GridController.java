package a02b.e2;

import java.util.Map;

import a02b.e2.GridModel.CellStatus;

public class GridController {
    private final int size;
    private final int itemSelectedPerDiagonal;
    private GridModel gridModel;

    public GridController(final int size, final int itemSelectedPerDiagonal) {
        this.size = size;
        this.itemSelectedPerDiagonal = itemSelectedPerDiagonal;
        this.gridModel = new GridModel(size, itemSelectedPerDiagonal);
    }

    public Map<Pair<Integer, Integer>, CellStatus> getValues() {
        return this.gridModel.getValues();
    }

    public CellStatus selectCell(final int x, final int y) {
        return this.gridModel.selectCell(x, y);
    }

    public boolean updateDiagonal() {
        return this.gridModel.updateDiagonal();
    }

    public void restart() {
        this.gridModel = new GridModel(this.size, this.itemSelectedPerDiagonal);
    }
}
