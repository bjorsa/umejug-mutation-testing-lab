package umejug.lab.mutationtest.domain.shopping;

import umejug.lab.mutationtest.domain.ValueObject;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.Collections;
import java.util.Set;

public final class ShoppingCart extends ValueObject {

    private final Set<ShoppingCartLineItem> lineItems;

    public ShoppingCart(Set<ShoppingCartLineItem> lineItems) {
        Validate.notNull(lineItems);

        this.lineItems = Collections.unmodifiableSet(lineItems);
    }

    public Set<ShoppingCartLineItem> getLineItems() {
        return lineItems;
    }
}
