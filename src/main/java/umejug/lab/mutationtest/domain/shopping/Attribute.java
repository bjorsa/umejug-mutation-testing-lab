package umejug.lab.mutationtest.domain.shopping;

import umejug.lab.mutationtest.domain.ValueObject;
import umejug.lab.mutationtest.domain.util.Validate;

public final class Attribute extends ValueObject {

    private final String name;
    private final String value;

    public Attribute(String name, String value) {
        Validate.notNull(name, () -> new NullPointerException("name can't be null"));
        Validate.notEmpty(name, () -> new IllegalArgumentException("name can't be empty"));
        Validate.notNull(name, () -> new NullPointerException("value can't be null"));
        Validate.notEmpty(name, () -> new IllegalArgumentException("value can't be empty"));

        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
