package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class ValidatorTest {

    private static final int MIN_LENGTH_OF_NAME = 6;
    private static final int AGE_OF_ARAGORN = 87;
    private static final int AGE_OF_SPIRIT = -5;
    private static final int AGE_OF_FRODO = 50;

    @Test
    void validatorTest() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().minLength(MIN_LENGTH_OF_NAME).required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> character1 = new HashMap<>();
        character1.put("name", "Aragorn");
        character1.put("age", AGE_OF_ARAGORN);

        assertThat(schema.isValid(character1)).isEqualTo(true);

        Map<String, Object> character2 = new HashMap<>();
        character2.put("name", "Gendalf");
        character2.put("age", null);

        assertThat(schema.isValid(character2)).isEqualTo(true);

        Map<String, Object> noNameChar = new HashMap<>();
        noNameChar.put("name", "");
        noNameChar.put("age", null);

        assertThat(schema.isValid(noNameChar)).isEqualTo(false);

        Map<String, Object> character3 = new HashMap<>();
        character3.put("name", "Spirit");
        character3.put("age", AGE_OF_SPIRIT);

        assertThat(schema.isValid(character3)).isEqualTo(false);

        Map<String, Object> character4 = new HashMap<>();
        character4.put("name", "Frodo");
        character4.put("age", AGE_OF_FRODO);

        assertThat(schema.isValid(character4)).isEqualTo(false);
    }

}
