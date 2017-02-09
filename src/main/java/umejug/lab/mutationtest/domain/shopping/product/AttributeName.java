package umejug.lab.mutationtest.domain.shopping.product;

import umejug.lab.mutationtest.domain.ValueObject;
import umejug.lab.mutationtest.domain.util.Validate;

public final class AttributeName extends ValueObject {

    public static final int MAX_NAME_LENGTH = 30;

    private final String name;

    public AttributeName(String name) {
        Validate.notNull(name);
        Validate.lessThan(name.length(), MAX_NAME_LENGTH, () -> new IllegalArgumentException("name can be no longer than " + MAX_NAME_LENGTH + " characters"));

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
