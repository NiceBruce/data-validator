package hexlet.code.schemas;

import java.util.List;
import java.util.function.Predicate;


public class StringSchema extends BaseSchema {

    public final StringSchema minLength(int length) {
        Predicate<String> minLenght = s -> s.length() >= length;
        addToSummaryOfCheck(minLenght);
        return this;
    }

    public final StringSchema contains(String subString) {
        Predicate<String> contains = s -> s.contains(subString);
        addToSummaryOfCheck(contains);
        return this;
    }
    @Override
    public final BaseSchema required() {
        Predicate<String> isNotNull = s -> s != null;
        Predicate<Object> isString = s -> s instanceof String;
        Predicate<String> isNotEmpty = s -> s.length() > 0;
        addToSummaryOfCheck(List.of(isString, isNotNull, isNotEmpty));
        return super.required();
    }
}
