package a02b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CursorHelpersImpl implements CursorHelpers {

    @Override
    public <X> Cursor<X> fromNonEmptyList(List<X> list) {
        return new CursorNonEmptyList<>(list);
    }

    @Override
    public Cursor<Integer> naturals() {
        return new CursorNaturals();
    }

    @Override
    public <X> Cursor<X> take(Cursor<X> input, int max) {
        return new CursorTakeList<>(input, max);
    }

    @Override
    public <X> void forEach(Cursor<X> input, Consumer<X> consumer) {
        consumer.accept(input.getElement());
        while (input.advance()) {
            consumer.accept(input.getElement());
        }
    }

    @Override
    public <X> List<X> toList(Cursor<X> input, int max) {
        final List<X> items = new ArrayList<>();
        final Cursor<X> cursorTakeList = this.take(input, max);

        this.forEach(cursorTakeList, x -> items.add(x));

        return items;
    }

}
