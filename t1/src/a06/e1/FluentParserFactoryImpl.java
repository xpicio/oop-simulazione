package a06.e1;

import java.util.List;
import java.util.function.UnaryOperator;

public class FluentParserFactoryImpl implements FluentParserFactory {

    @Override
    public FluentParser<Integer> naturals() {
        return new FluentParserNaturals();
    }

    @Override
    public FluentParser<List<Integer>> incrementalNaturalLists() {
        return new FluentParserNaturalsLists();
    }

    @Override
    public FluentParser<Integer> repetitiveIncrementalNaturals() {
        return new FluentParserIncrementNaturals();
    }

    @Override
    public FluentParser<String> repetitiveIncrementalStrings(String s) {
        return new FluentParserIncrementStrings(s);
    }

    @Override
    public FluentParser<Pair<Integer, List<String>>> incrementalPairs(int i0, UnaryOperator<Integer> op, String s) {
        return new FluentParserIncrementalPairs(i0, op, s);
    }

}
