package umejug.lab.mutationtest.domain.shopping;

import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.ValueObject;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ShoppingCartLineItems extends ValueObject {

    private final List<ShoppingCartLineItem> lineItems;


    public ShoppingCartLineItems(List<ShoppingCartLineItem> lineItems) {
        Validate.notNull(lineItems);

        this.lineItems = new ArrayList<>(lineItems);
    }

    private static class ItemAccumulator {
        private final List<ShoppingCartLineItem> result = new ArrayList<>();
        private Identifier SKUToAdd;


        ItemAccumulator(Identifier SKUToAdd) {
            this.SKUToAdd = SKUToAdd;
        }

        void addLineItem(ShoppingCartLineItem lineItem) {
            ShoppingCartLineItem toAdd = lineItem;
            if (lineItem.getSKU().equals(SKUToAdd)) {
                toAdd = new ShoppingCartLineItem(SKUToAdd, lineItem.getQuantity() + 1);
                SKUToAdd = null;
            }
            result.add(toAdd);
        }

        void addAll(ItemAccumulator other) {
            result.addAll(other.result);
            if (SKUToAdd != null) {
                SKUToAdd = other.SKUToAdd;
            }
        }

        List<ShoppingCartLineItem> getResult() {
            if (SKUToAdd != null) {
                result.add(new ShoppingCartLineItem(SKUToAdd, 1));
            }
            return result;
        }
    }

    public final int getNumberOfSKUs() {
        return lineItems.size();
    }

    public final ShoppingCartLineItems addSKU(Identifier SKU) {
        Validate.notNull(SKU);
        List<ShoppingCartLineItem> newItems = lineItems.stream().sequential().collect(Collector.of(
                () -> new ItemAccumulator(SKU),
                ItemAccumulator::addLineItem,
                (left, right) -> {
                    left.addAll(right);
                    return left;
                },
                ItemAccumulator::getResult));
        return new ShoppingCartLineItems(newItems);
    }

    public final ShoppingCartLineItems removeSKU(Identifier SKU) {
        Validate.notNull(SKU);
        return new ShoppingCartLineItems(lineItems.stream().filter(s -> !s.getSKU().equals(SKU)).collect(Collectors.toList()));
    }

    public List<ShoppingCartLineItem> getLineItems() {
        return lineItems;
    }
}
