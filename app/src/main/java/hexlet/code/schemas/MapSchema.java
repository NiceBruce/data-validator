package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema {

    public final void sizeof(int size) {
        Predicate<Map> isActualSize = m -> m.size() == size;
        addToSummaryOfCheck(isActualSize);
    }
    public final void shape(Map<String, BaseSchema> schemas) {
        Predicate<Map> checkValue = m -> schemas.get("name").isValid(m.get("name"))
                && schemas.get("age").isValid(m.get("age"));
        addToSummaryOfCheck(checkValue);
    }
    @Override
    public final BaseSchema required() {
        Predicate<Object> isMap = m -> m instanceof Map<?, ?>;
        addToSummaryOfCheck(isMap);
        return super.required();
    }
}
