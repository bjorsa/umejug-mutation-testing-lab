package umejug.lab.mutationtest.domain.shopping.product;

import umejug.lab.mutationtest.domain.Entity;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.Collections;
import java.util.List;

public final class StockKeepingUnit extends Entity {

    private final Identifier product;
    private final Attributes attributes;

    private final int stockQuantity;

    public StockKeepingUnit(Identifier identifier, Identifier product, Attributes attributes, int stockQuantity) {
        super(identifier);

        Validate.notNull(product);
        Validate.notNull(attributes);

        this.product = product;
        this.attributes = attributes;
        this.stockQuantity = stockQuantity;
    }

    public Identifier getProduct() {
        return product;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}
