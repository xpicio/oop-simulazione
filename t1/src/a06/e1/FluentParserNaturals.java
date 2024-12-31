package a06.e1;

public class FluentParserNaturals implements FluentParser<Integer> {
    private int expectedValue;

    public FluentParserNaturals() {
        this.expectedValue = 0;
    }

    @Override
    public FluentParser<Integer> accept(final Integer value) {
        if (value >= 0 && value == expectedValue) {
            this.expectedValue++;
            return this;
        }

        throw new IllegalStateException("value is not valid");
    }

}
