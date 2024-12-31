package a06.e1;

import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class FluentParserIncrementalPairs implements FluentParser<Pair<Integer, List<String>>> {
    private final int initValue;
    private final String initString;
    private final UnaryOperator<Integer> op;
    private int expectedKey;
    private int expectedSize;

    public FluentParserIncrementalPairs(final int initValue, final UnaryOperator<Integer> op, final String initString) {
        this.initValue = initValue;
        this.op = op;
        this.initString = initString;
        this.expectedKey = this.initValue;
        this.expectedSize = this.expectedKey;
    }

    @Override
    public FluentParser<Pair<Integer, List<String>>> accept(final Pair<Integer, List<String>> value) {
        final String expectedValue = Stream.generate(() -> initString).limit(expectedSize).reduce("", (x, y) -> x + y);
        final String stringValue = value.getY().stream().reduce("", (x, y) -> x + y);
        if (this.expectedKey == value.getX()
                && expectedValue.equals(stringValue)) {
            this.expectedKey = this.op.apply(this.expectedKey);
            this.expectedSize = this.expectedKey;
            return this;
        }

        throw new IllegalStateException("value is not valid");
    }

}
