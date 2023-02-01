package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {

    public final void range(int lowRange, int highRange) {
        Predicate<Integer> isNumberFromRange = i -> (i >= lowRange && i <= highRange);
        addToSummaryOfCheck(isNumberFromRange);
    }

    public final NumberSchema positive() {
        Predicate<Integer> isPositive = i -> getIsRequiredEnabled() ? i > 0 : (i instanceof Integer) ? i > 0 : true;
        addToSummaryOfCheck(isPositive);
        return this;
    }

    @Override
    public final NumberSchema required() {
        setIsRequiredEnabled(true);
        Predicate<Object> isNumber = i -> i instanceof Integer;
        addToSummaryOfCheck(0, isNumber);
        return this;
    }

}
