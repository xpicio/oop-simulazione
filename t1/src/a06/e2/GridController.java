package a06.e2;

import java.util.Map;
import java.util.Optional;

public class GridController {
    private final GridModel model;

    public GridController(final int size) {
        this.model = new GridModel(size);
    }

    public Map<Pair<Integer, Integer>, Optional<Integer>> getValues() {
        return this.model.getGrid();
    }

    public void collapseColumns() {
        this.model.collapseColumns();
    }

    public boolean gridColumnsCanCollapse() {
        return this.model.gridColumnsCanCollapse();
    }
}
