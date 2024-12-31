package a06.e1;

import java.util.List;

public class FluentParserNaturalsLists implements FluentParser<List<Integer>> {
    private int listSize;

    public FluentParserNaturalsLists() {
        this.listSize = 0;
    }

    @Override
    public FluentParser<List<Integer>> accept(final List<Integer> value) {
        if (value.size() == listSize) {
            FluentParser<Integer> fluentParserNaturals = new FluentParserNaturals();
            for (Integer item : value) {
                fluentParserNaturals = fluentParserNaturals.accept(item);
            }
            this.listSize++;
            return this;
        }

        throw new IllegalStateException("value is not valid");
    }

}
