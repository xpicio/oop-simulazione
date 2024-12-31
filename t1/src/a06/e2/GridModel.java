package a06.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class GridModel {
    private final int size;
    private final Map<Pair<Integer, Integer>, Optional<Integer>> grid;

    public GridModel(final int size) {
        this.size = size;
        this.grid = new HashMap<>();

        IntStream.range(0, this.size).forEach(x -> {
            IntStream.range(0, this.size).forEach(y -> {
                Random random = new Random();
                this.grid.put(new Pair<Integer, Integer>(x, y), Optional.of(random.nextInt(2) + 1));
            });
        });
    }

    private boolean collapseColumns(boolean checkOnly) {
        for (int x = 0; x < this.size; x++) {
            for (int y = this.size - 1; y >= 0; y--) {
                final Optional<Integer> itemValue = this.grid.get(new Pair<>(x, y));
                final Optional<Integer> nextItemValue = this.grid.get(new Pair<>(x, y - 1));

                if (nextItemValue == null || itemValue.isEmpty() || nextItemValue.isEmpty()) {
                    break;
                }

                if (itemValue.get() == nextItemValue.get()) {
                    if (checkOnly) {
                        return true;
                    } else {
                        this.grid.put(new Pair<>(x, y), Optional.of(itemValue.get() + nextItemValue.get()));
                        Optional<Integer> previousItemValue = Optional.empty();
                        for (int j = 0; j < y; j++) {
                            final Optional<Integer> itemValueToUpdate = this.grid.get(new Pair<>(x, j));

                            if (itemValueToUpdate.isPresent() && previousItemValue.isEmpty()) {
                                previousItemValue = this.grid.put(new Pair<>(x, j), Optional.empty());
                            } else {
                                previousItemValue = this.grid.put(new Pair<>(x, j), previousItemValue);
                            }
                        }
                        break;
                    }
                }
            }
        }

        return false;
    }

    public Map<Pair<Integer, Integer>, Optional<Integer>> getGrid() {
        return new HashMap<>(this.grid);
    }

    public int getSize() {
        return this.size;
    }

    public void collapseColumns() {
        this.collapseColumns(false);
    }

    public boolean gridColumnsCanCollapse() {
        return this.collapseColumns(true);
    }
}
