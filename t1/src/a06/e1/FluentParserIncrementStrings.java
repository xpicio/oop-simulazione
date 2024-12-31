package a06.e1;

import java.util.stream.Stream;

public class FluentParserIncrementStrings implements FluentParser<String> {
    private final String initValue;
    private int expectedMaxSize;
    private int expectedSize;

    public FluentParserIncrementStrings(final String initValue) {
        this.initValue = initValue;
        this.expectedMaxSize = 1;
        this.expectedSize = 1;
    }

    @Override
    public FluentParser<String> accept(final String value) {
        final String expectedValue = Stream.generate(() -> initValue).limit(expectedSize).reduce((x, y) -> x + y).get();
        if (expectedValue.equals(value) && this.expectedSize == value.length()) {
            if (this.expectedSize >= this.expectedMaxSize) {
                this.expectedSize = 1;
                this.expectedMaxSize++;
            } else {
                this.expectedSize++;
            }
            return this;
        }

        throw new IllegalStateException("value is not valid");
    }

}
