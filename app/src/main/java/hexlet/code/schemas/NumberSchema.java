package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema {
    private int[] rangeArr = new int[2];

    public final void range(int lowRange, int highRange) {
        this.rangeArr[0] = lowRange;
        this.rangeArr[1] = highRange;
        Predicate<Integer> isNumberFromRange = i -> (i >= rangeArr[0] && i <= rangeArr[1]);
        addToSummaryOfCheck(isNumberFromRange);
    }

    public final NumberSchema positive() {
        Predicate<Integer> isPositive = i -> getIsRequiredEnabled() ? i > 0 : (i instanceof Integer) ? i > 0 : true;
        addToSummaryOfCheck(isPositive);
        return this;
    }

    @Override
    public final BaseSchema required() {
        Predicate<Object> isNumber = i -> i instanceof Integer;
        addToSummaryOfCheck(0, isNumber);
        return super.required();
    }

}
