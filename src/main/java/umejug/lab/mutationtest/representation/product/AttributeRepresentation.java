package umejug.lab.mutationtest.representation.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributeRepresentation {

    private final String name;
    private final String value;

    @JsonCreator
    public AttributeRepresentation(
            @JsonProperty(value = "name", required = true) String name,
            @JsonProperty(value = "value", required = true) String value) {
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
