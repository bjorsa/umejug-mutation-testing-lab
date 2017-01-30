package umejug.lab.mutationtest.domain;

import umejug.lab.mutationtest.domain.util.Validate;

public abstract class Entity {

    private final Identifier identifier;

    protected Entity(Identifier identifier) {
        Validate.notNull(identifier, () -> new NullPointerException(getClass().getSimpleName() + " identifier can't be null"));
        this.identifier = identifier;
    }

    public final Identifier getIdentifier() {
        return identifier;
    }
}
