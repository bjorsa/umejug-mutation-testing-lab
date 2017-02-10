package umejug.lab.mutationtest.domain.shopping.category;

import umejug.lab.mutationtest.domain.ValueObject;
import umejug.lab.mutationtest.domain.util.Validate;

public final class CategoryName extends ValueObject {

    public static final int MAX_NAME_LENGTH = 100;

    private final String name;

    public CategoryName(String name) {
        Validate.notNull(name);
        Validate.notEmpty(name);
        Validate.lessThanOrEqual(name.length(), MAX_NAME_LENGTH, () -> new IllegalArgumentException("Category name can be no longer than " + MAX_NAME_LENGTH + " characters"));
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
