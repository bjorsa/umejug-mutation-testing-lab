package umejug.lab.mutationtest.representation.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.representation.EntityRepresentation;

import java.util.Collections;
import java.util.Set;

public class ProductRepresentation extends EntityRepresentation {

    private final String name;
    private final String description;
    private final Set<Identifier> categories;

    @JsonCreator
    public ProductRepresentation(Identifier id,
                                 @JsonProperty(required = true) String name,
                                 @JsonProperty(required = true) String description,
                                 @JsonProperty(required = true) Set<Identifier> categories) {
        super(id);
        this.name = name;
        this.description = description;
        this.categories = Collections.unmodifiableSet(categories);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Identifier> getCategories() {
        return categories;
    }
}
