package umejug.lab.mutationtest.domain.shopping.product;

import umejug.lab.mutationtest.domain.ValueObject;
import umejug.lab.mutationtest.domain.util.Validate;

public final class Attribute extends ValueObject {

    private final AttributeName name;
    private final AttributeValue value;

    public Attribute(AttributeName name, AttributeValue value) {
        Validate.notNull(name);
        Validate.notNull(value);

        this.name = name;
        this.value = value;
    }

    public AttributeName getName() {
        return name;
    }

    public AttributeValue getValue() {
        return value;
    }
}
