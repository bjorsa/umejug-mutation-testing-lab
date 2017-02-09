package umejug.lab.mutationtest.domain.shopping.product;

import umejug.lab.mutationtest.domain.util.Validate;

public final class ProductName {

    private static final int MAX_NAME_LENGTH = 250;

    private final String name;

    public ProductName(String name) {
        Validate.notNull(name);
        Validate.notEmpty(name);
        Validate.lessThanOrEqual(name.length(), MAX_NAME_LENGTH, () -> new IllegalArgumentException("Product name can be no longer than " + MAX_NAME_LENGTH + " characters"));

        this.name = name;
    }

    public final String getName() {
        return name;
    }
}
