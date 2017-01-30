package umejug.lab.mutationtest.domain.shopping;

import umejug.lab.mutationtest.domain.Entity;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.Collections;
import java.util.List;

public final class StockKeepingUnit extends Entity {

    private final Identifier product;
    private final List<Attribute> attributes;

    private int stockQuantity;

    public StockKeepingUnit(Identifier identifier, Identifier product, List<Attribute> attributes, int stockQuantity) {
        super(identifier);

        Validate.notNull(product, () -> new NullPointerException("product can't be null."));

        this.product = product;
        this.attributes = Collections.unmodifiableList(attributes == null ? Collections.emptyList() : attributes);
        setStockQuantity(stockQuantity);
    }

    public Identifier getProduct() {
        return product;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        Validate.notNegative(stockQuantity, () -> new IllegalArgumentException("stockQuantity can't be negative"));
        this.stockQuantity = stockQuantity;
    }
}
