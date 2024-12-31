package a02b.e1;

import java.util.ArrayList;
import java.util.List;

public class CursorNaturals implements Cursor<Integer> {
    private final List<Integer> items;
    private int currentIndex;

    public CursorNaturals() {
        this.items = new ArrayList<>();
        this.currentIndex = 0;
        this.items.add(this.currentIndex);
    }

    @Override
    public Integer getElement() {
        return this.items.getLast();
    }

    @Override
    public boolean advance() {
        boolean nextItemsExists = true;
        final int nextItemIndex = this.currentIndex + 1;

        if (nextItemIndex == Integer.MAX_VALUE) {
            nextItemsExists = false;
        } else {
            this.currentIndex++;
            this.items.add(this.currentIndex);
        }

        return nextItemsExists;
    }

}
