package hexlet.code.schemas;

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
    public final boolean isCorrectType(Object obj) {
        return String.class.isInstance(obj);
    }

    @Override
    public final StringSchema required() {
        setIsRequiredEnabled(true);
        Predicate<String> isNotEmpty = s -> s.length() > 0;
        addToSummaryOfCheck(isNotEmpty);
        return this;
    }
}
