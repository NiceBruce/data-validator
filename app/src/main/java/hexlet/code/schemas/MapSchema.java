package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public final void sizeof(int size) {
        Predicate<Map> isActualSize = m -> m.size() == size;
        addToSummaryOfCheck(isActualSize);
    }

    public final void shape(Map<String, BaseSchema> schemas) {
        Predicate<Map> checkValue = m -> schemas.entrySet().stream()
                .map(o -> o.getValue().isValid(m.get(o.getKey())))
                .reduce(Boolean.TRUE, Boolean::logicalAnd);

        addToSummaryOfCheck(checkValue);
    }

    @Override
    public final boolean isCorrectType(Object obj) {
        return Map.class.isInstance(obj);
    }

    @Override
    public final MapSchema required() {
        setIsRequiredEnabled(true);
        return this;
    }
}
