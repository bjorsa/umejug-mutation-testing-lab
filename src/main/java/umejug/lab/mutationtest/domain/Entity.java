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

    @Override
    public final boolean equals(Object o) {
        return this == o || o != null && o instanceof Entity && identifier.equals(((Entity) o).identifier);
    }

    @Override
    public final int hashCode() {
        return identifier.hashCode();
    }
}
