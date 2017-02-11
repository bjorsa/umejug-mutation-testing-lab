package umejug.lab.mutationtest.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import umejug.lab.mutationtest.domain.Identifier;

public abstract class EntityRepresentation {

    private final Identifier id;

    public EntityRepresentation(@JsonProperty(value = "id", required = true) Identifier id) {
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }
}
