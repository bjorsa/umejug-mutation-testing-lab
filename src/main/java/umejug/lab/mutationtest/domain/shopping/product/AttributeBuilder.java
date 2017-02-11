package umejug.lab.mutationtest.domain.shopping.product;

import umejug.lab.mutationtest.representation.product.AttributeRepresentation;

public final class AttributeBuilder {

    private AttributeBuilder() {
        // prevent instantiation and sub classing
    }

    public static Attribute fromAttributeRepresentation(AttributeRepresentation representation) {
        return new Attribute(new AttributeName(representation.getName()), new AttributeValue(representation.getValue()));
    }
}
