package umejug.lab.mutationtest.domain.shopping.product;

import umejug.lab.mutationtest.domain.Entity;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.price.Prices;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.Set;

public final class StockKeepingUnit extends Entity {

    private final Identifier product;
    private final Prices prices;
    private final Set<Attribute> attributes;

    private final int stockQuantity;

    public StockKeepingUnit(Identifier identifier, Identifier product, Prices prices, Set<Attribute> attributes, int stockQuantity) {
        super(identifier);

        Validate.notNull(product);
        Validate.notNull(attributes);
        Validate.notNull(prices);

        this.product = product;
        this.attributes = attributes;
        this.prices = prices;
        this.stockQuantity = stockQuantity;
    }

    public Identifier getProduct() {
        return product;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public Prices getPrices() {
        return prices;
    }
}
