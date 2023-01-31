package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import java.util.function.Supplier;

public class Validator {

    public final StringSchema string() {
        Supplier<StringSchema> string = StringSchema::new;
        return string.get();
    }

    public final NumberSchema number() {
        Supplier<NumberSchema> num = NumberSchema::new;
        return num.get();
    }

    public final MapSchema map() {
        Supplier<MapSchema> map = MapSchema::new;
        return map.get();
    }
}
