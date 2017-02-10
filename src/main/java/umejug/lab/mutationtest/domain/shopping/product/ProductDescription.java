package umejug.lab.mutationtest.domain.shopping.product;

import umejug.lab.mutationtest.domain.ValueObject;
import umejug.lab.mutationtest.domain.util.Validate;

public final class ProductDescription extends ValueObject {
    public static final int MAX_DESCRIPTION_LENGTH = 1000;

    private final String description;

    public ProductDescription(String description) {
        Validate.notNull(description);
        Validate.lessThanOrEqual(description.length(), MAX_DESCRIPTION_LENGTH, () -> new IllegalArgumentException("Product description can be no longer than " + MAX_DESCRIPTION_LENGTH + " characters"));

        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
