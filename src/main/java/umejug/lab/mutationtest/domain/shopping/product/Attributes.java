package umejug.lab.mutationtest.domain.shopping.product;

import umejug.lab.mutationtest.domain.ValueObject;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

public class Attributes extends ValueObject implements Iterable<Attribute> {

    private final Set<Attribute> attributes;

    public Attributes(Set<Attribute> attributes) {
        Validate.notNull(attributes);

        this.attributes = Collections.unmodifiableSet(attributes);
    }

    public final int getNumberOfAttributes() {
        return this.attributes.size();
    }

    @Override
    public Iterator<Attribute> iterator() {
        return this.attributes.iterator();
    }
}
