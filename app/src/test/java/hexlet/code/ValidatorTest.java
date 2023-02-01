package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class ValidatorTest {

    private static final int MIN_LENGTH_OF_NAME = 6;
    private static final int AGE_OF_ARAGORN = 87;
    private static final int AGE_OF_SPIRIT = -5;
    private static final int AGE_OF_FRODO = 50;
    private static final int RINGS_FOR_ELVEN_KINGS = 3;
    private static final int LOW_RANGE = 5;
    private static final int HIGH_RANGE = 10;


    @Test
    void validatorTestMap() {
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

    @Test
    void validatorTestStringSchema() {

        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid(schema.isValid(""))).isEqualTo(true);
        assertThat(schema.isValid(schema.isValid(null))).isEqualTo(true);

        schema.required();

        assertThat(schema.isValid("One does not simply walk into Mordor"))
                .isEqualTo(true);
        assertThat(schema.isValid("My precious-s-s-s-s"))
                .isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(RINGS_FOR_ELVEN_KINGS)).isEqualTo(false);
        assertThat(schema.isValid("")).isEqualTo(false);

        assertThat(schema.contains("left").isValid("Mordor, Gandalf, is it left or right?"))
                .isEqualTo(true);
        assertThat(schema.contains("right").isValid("Mordor, Gandalf, is it left or right?"))
                .isEqualTo(true);
        assertThat(schema.contains("leftorright")
                .isValid("Mordor, Gandalf, is it left or right?"))
                .isEqualTo(false);
        assertThat(schema.isValid("Mordor, Gandalf, is it left or right?"))
                .isEqualTo(false);
    }

    @Test
    void validatorTestNumberSchema() {

        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.positive().isValid(null)).isEqualTo(true);

        schema.required();

        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(AGE_OF_FRODO)).isEqualTo(true);
        assertThat(schema.isValid("5")).isEqualTo(false);
        assertThat(schema.isValid(AGE_OF_SPIRIT)).isEqualTo(false);
        assertThat(schema.isValid(0)).isEqualTo(false);

        schema.range(LOW_RANGE, HIGH_RANGE);

        assertThat(schema.isValid(LOW_RANGE)).isEqualTo(true);
        assertThat(schema.isValid(HIGH_RANGE)).isEqualTo(true);
        assertThat(schema.isValid(LOW_RANGE - 1)).isEqualTo(false);
        assertThat(schema.isValid(HIGH_RANGE + 1)).isEqualTo(false);
    }
}
