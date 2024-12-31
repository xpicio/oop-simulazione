package a02b.e1;

import java.util.List;

public class CursorNonEmptyList<X> implements Cursor<X> {
    private final List<X> items;
    private int currentItemIndex;

    public CursorNonEmptyList(final List<X> items) {
        this.items = items;
        this.currentItemIndex = 0;
    }

    @Override
    public X getElement() {
        return this.items.get(this.currentItemIndex);
    }

    @Override
    public boolean advance() {
        boolean nextItemsExists = true;
        final int nextItemIndex = this.currentItemIndex + 1;

        if (nextItemIndex >= this.items.size()) {
            nextItemsExists = false;
        } else {
            this.currentItemIndex++;
        }

        return nextItemsExists;
    }

}
