
package com.asoroka.sidora.tabularmetadata.heuristics;

import static com.google.common.base.Functions.constant;
import static com.google.common.collect.Collections2.transform;
import static com.google.common.collect.Maps.toMap;
import static com.google.common.collect.Sets.powerSet;
import static java.util.Objects.hash;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import com.asoroka.sidora.tabularmetadata.datatype.DataType;
import com.google.common.base.Function;

/**
 * A {@link DataTypeHeuristic} that records the number of each possible choice of parseables made for values
 * submitted.
 * 
 * @author ajs6f
 */
public abstract class ChoiceCountAggregatingHeuristic extends PerTypeHeuristic<ChoiceCountAggregatingHeuristic> {

    /**
     * A {@link Map} from choices of {@link DataType}s to the number of times that choice occurred.
     */
    private Map<EnumSet<DataType>, Integer> choiceOccurrences;

    protected ChoiceCountAggregatingHeuristic() {
        // This long construction simply fills the choice map with all possible choices, each mapped to zero, to
        // record that we haven't yet seen any choices made. Java is verbose.
        choiceOccurrences.putAll(toMap(transform(powerSet(DataType.valuesSet()), set2enumset), constant(0)));
    }

    @Override
    public void addValue(final String value) {
        super.addValue(value);
        final EnumSet<DataType> choices = DataType.parseableAs(value);
        choiceOccurrences.put(choices, choiceOccurrences.get(choices) + 1);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 2 * hash(choiceOccurrences);
    }

    private static final Function<Set<DataType>, EnumSet<DataType>> set2enumset =
            new Function<Set<DataType>, EnumSet<DataType>>() {

                @Override
                public EnumSet<DataType> apply(final Set<DataType> s) {
                    return EnumSet.copyOf(s);
                }
            };
}
