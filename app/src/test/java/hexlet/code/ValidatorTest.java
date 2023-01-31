package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class ValidatorTest {

    private static final int AGE_OF_HUMAN1 = 100;
    private static final int AGE_OF_HUMAN4 = -5;

    @Test
    void validatorTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", AGE_OF_HUMAN1);

        assertThat(schema.isValid(human1)).isEqualTo(true);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);

        assertThat(schema.isValid(human2)).isEqualTo(true);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);

        assertThat(schema.isValid(human3)).isEqualTo(false);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", AGE_OF_HUMAN4);

        assertThat(schema.isValid(human4)).isEqualTo(false);
    }

}
