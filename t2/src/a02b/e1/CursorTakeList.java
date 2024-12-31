package a02b.e1;

import java.util.List;

public class CursorTakeList<X> implements Cursor<X> {
    private final Cursor<X> input;
    private final int max;
    private int computedItems;

    public CursorTakeList(final Cursor<X> input, final int max) {
        this.input = input;
        this.max = max;
        this.computedItems = 1;
    }

    @Override
    public X getElement() {
        return this.input.getElement();
    }

    @Override
    public boolean advance() {
        if (this.computedItems >= this.max) {
            return false;
        }

        this.computedItems++;

        return this.input.advance();
    }

}
