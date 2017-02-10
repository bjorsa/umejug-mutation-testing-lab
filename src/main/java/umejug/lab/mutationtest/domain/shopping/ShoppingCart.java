package umejug.lab.mutationtest.domain.shopping;

import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.ValueObject;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.*;
import java.util.stream.Collectors;

public final class ShoppingCart extends ValueObject {

    private final ShoppingCartLineItems items;
    private final Set<Identifier> abandonedSKUs;

    public ShoppingCart(ShoppingCartLineItems items, Set<Identifier> abandonedSKUs) {
        Validate.notNull(items);
        Validate.notNull(abandonedSKUs);

        this.items = items;
        this.abandonedSKUs = Collections.unmodifiableSet(abandonedSKUs);
    }

    public List<ShoppingCartLineItem> getItems() {
        return items.getLineItems();
    }

    public Set<Identifier> getAbandonedSKUs() {
        return abandonedSKUs;
    }

    public final ShoppingCart addSKU(Identifier SKU) {
        ShoppingCartLineItems newItems = items.addSKU(SKU);
        Set<Identifier> newAbandonedSKUs = new HashSet<>(abandonedSKUs);
        newAbandonedSKUs.remove(SKU);
        return new ShoppingCart(newItems, newAbandonedSKUs);
    }

    public final ShoppingCart removeSKU(Identifier SKU) {
        int nbrOfSKUs = items.getNumberOfSKUs();
        ShoppingCartLineItems newItems = items.removeSKU(SKU);
        Set<Identifier> newAbandonedSKUs = new HashSet<>(abandonedSKUs);
        if (nbrOfSKUs > newItems.getNumberOfSKUs()) {
            newAbandonedSKUs.add(SKU);
        }
        return new ShoppingCart(newItems, newAbandonedSKUs);
    }

    public final ShoppingCart updateLineItems(ShoppingCartLineItems newItems) {
        Validate.notNull(newItems);

        Set<Identifier> newAbandonedSKUs = new HashSet<>(abandonedSKUs);
        Set<Identifier> newSKUs = newItems.getLineItems().stream().map(ShoppingCartLineItem::getSKU).collect(Collectors.toSet());
        Set<Identifier> currentSKUs = items.getLineItems().stream().map(ShoppingCartLineItem::getSKU).collect(Collectors.toSet());

        Set<Identifier> removedSKUs = currentSKUs.stream().filter(id -> !newSKUs.contains(id)).collect(Collectors.toSet());
        newAbandonedSKUs.addAll(removedSKUs);
        Set<Identifier> addedSKUs = newSKUs.stream().filter(id -> !currentSKUs.contains(id)).collect(Collectors.toSet());
        newAbandonedSKUs.removeAll(addedSKUs);

        return new ShoppingCart(newItems, newAbandonedSKUs);
    }
}
