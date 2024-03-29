package hexlet.code.schemas;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema {

    private List<Predicate> summaryOfChecks = new LinkedList<>();
    private boolean isRequired = false;

    public final void setIsRequired(boolean required) {
        isRequired = required;
    }

    public final void addToSummaryOfCheck(Predicate predicate) {
        this.summaryOfChecks.add(predicate);
    }

    public abstract boolean isCorrectType(Object obj);

    public abstract BaseSchema required();

    public final boolean isValid(Object obj) {
        if (!isCorrectType(obj)) {
            return !isRequired;
        } else {
            return summaryOfChecks.stream()
                    .allMatch(i -> i.test(obj));
        }
    }
}
