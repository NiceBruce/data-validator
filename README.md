### Hexlet tests and linter status:
[![Actions Status](https://github.com/NiceBruce/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/NiceBruce/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/25e8fbdcd434d17f2621/maintainability)](https://codeclimate.com/github/NiceBruce/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/25e8fbdcd434d17f2621/test_coverage)](https://codeclimate.com/github/NiceBruce/java-project-78/test_coverage)

# A data validator is a library with which you can check the correctness of any data.

## usage example:

````
Validator v = new Validator();

// For Strings
StringSchema schema = v.string().required();

schema.isValid("what does the fox say"); // true
schema.isValid(""); // false

// For Numbers
NumberSchema schema = v.number().required().positive();

schema.isValid(-10); // false
schema.isValid(10); // true

// Object Map with structure verification support

Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", v.string().required());
schemas.put("age", v.number().positive());

MapSchema schema = v.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human1); // false````