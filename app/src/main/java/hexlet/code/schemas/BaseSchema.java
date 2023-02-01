package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema {

    private List<Predicate> summaryOfCheck = new LinkedList<>();
    private boolean isRequiredEnabled = false;

    public final void addToSummaryOfCheck(List<Predicate> listOfPredicate) {
        this.summaryOfCheck.addAll(listOfPredicate);
    }
    public final void addToSummaryOfCheck(Predicate predicate) {
        this.summaryOfCheck.add(predicate);
    }
    public final void addToSummaryOfCheck(int index, Predicate predicate) {
        this.summaryOfCheck.add(index, predicate);
    }

    public final void setIsRequiredEnabled(boolean requiredEnabled) {
        isRequiredEnabled = requiredEnabled;
    }

    public final boolean getIsRequiredEnabled() {
        return isRequiredEnabled;
    }

    abstract BaseSchema required();

    public final boolean isValid(Object obj) {
        return summaryOfCheck.stream()
                 .allMatch(i -> i.test(obj));
    }
}
