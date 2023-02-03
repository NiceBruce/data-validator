package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public final void range(int lowRange, int highRange) {
        Predicate<Integer> isNumberFromRange = i -> (i >= lowRange && i <= highRange);
        addToSummaryOfCheck(isNumberFromRange);
    }

    public final NumberSchema positive() {
        Predicate<Integer> isPositive = i -> i > 0;
        addToSummaryOfCheck(isPositive);
        return this;
    }

    @Override
    public final boolean isCorrectType(Object obj) {
        return Integer.class.isInstance(obj);
    }

    @Override
    public final NumberSchema required() {
        setIsRequired(true);
        return this;
    }

}
