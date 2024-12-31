package a06.e1;

public class FluentParserIncrementNaturals implements FluentParser<Integer> {
    private FluentParser<Integer> fluentParserNaturals;

    public FluentParserIncrementNaturals() {
        this.fluentParserNaturals = new FluentParserNaturals();
    }

    @Override
    public FluentParser<Integer> accept(final Integer value) {
        if (value == 0) {
            this.fluentParserNaturals = new FluentParserNaturals();
        }
        this.fluentParserNaturals = this.fluentParserNaturals.accept(value);
        return this;
    }

}
