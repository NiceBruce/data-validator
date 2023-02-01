### Hexlet tests and linter status:
[![Actions Status](https://github.com/NiceBruce/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/NiceBruce/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/e24dfc6a9af99d81e682/maintainability)](https://codeclimate.com/github/NiceBruce/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/e24dfc6a9af99d81e682/test_coverage)](https://codeclimate.com/github/NiceBruce/java-project-78/test_coverage)

# A data validator is a library with which you can check the correctness of any data.

## usage example:

````
Validator v = new Validator();

// For Strings
StringSchema schema = v.string().required();

schema.isValid("You have no power here, Gandalf the Grey"); // true
schema.isValid(""); // false

// For Numbers
NumberSchema schema = v.number().required().positive();

schema.isValid(-1); // false
schema.isValid(1); // true

// Object Map with structure verification support

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().minLength(6).required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> character1 = new HashMap<>();
character1.put("name", "Aragorn");
character1.put("age", 87);
schema.isValid(character1); // true

Map<String, Object> character2 = new HashMap<>();
character2.put("name", "Frodo");
character2.put("age", 50);
schema.isValid(character2); // false

Map<String, Object> noNameChar = new HashMap<>();
noNameChar.put("name", "");
noNameChar.put("age", null);
schema.isValid(noNameChar); // false