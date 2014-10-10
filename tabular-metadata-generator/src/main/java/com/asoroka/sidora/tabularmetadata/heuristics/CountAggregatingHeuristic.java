/**
 * TEMPORARY LICENSE HEADER STANDIN
 * REPLACE WITH APPROPRIATE SIDORA LICENSE
 */

package com.asoroka.sidora.tabularmetadata.heuristics;

import static com.asoroka.sidora.tabularmetadata.datatype.DataType.parseableAs;
import static java.util.Objects.hash;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import org.slf4j.Logger;

import com.asoroka.sidora.tabularmetadata.datatype.DataType;

/**
 * A {@link DataTypeHeuristic} that aggregates candidate type appearance information for its field.
 * 
 * @author ajs6f
 * @param <T>
 */
public abstract class CountAggregatingHeuristic<T extends CountAggregatingHeuristic<T>> extends
        RunningMinMaxHeuristic<CountAggregatingHeuristic<T>> {

    /**
     * In this {@link Map}, we aggregate counts of parseable values for each datatype.
     */
    protected final EnumMap<DataType, Integer> typeCounts = new EnumMap<>(DataType.class);

    private static final Logger log = getLogger(CountAggregatingHeuristic.class);

    /**
     * Initialize counts for each datatype.
     */
    public CountAggregatingHeuristic() {
        for (final DataType type : DataType.values()) {
            typeCounts.put(type, 0);
        }
    }

    @Override
    public void addValue(final String value) {
        super.addValue(value);
        incrementCounts(parseableAs(value));
    }

    private void incrementCounts(final Collection<DataType> types) {
        for (final DataType type : types) {
            typeCounts.put(type, typeCounts.get(type) + 1);
        }
    }

    @Override
    abstract public T clone();

    @Override
    public int hashCode() {
        return hash(typeCounts);
    }
}
