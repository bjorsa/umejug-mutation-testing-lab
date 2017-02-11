package umejug.lab.mutationtest.representation.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.product.StockKeepingUnit;
import umejug.lab.mutationtest.representation.EntityRepresentation;

import java.util.Set;

public class StockKeepingUnitRepresentation extends EntityRepresentation {

    private final Identifier productId;
    private final PricesRepresentation prices;
    private final Set<AttributeRepresentation> attributeRepresentations;
    private final int stockQuantity;

    @JsonCreator
    public StockKeepingUnitRepresentation(Identifier id,
                                          @JsonProperty(required = true) Identifier productId,
                                          @JsonProperty PricesRepresentation prices,
                                          @JsonProperty Set<AttributeRepresentation> attributeRepresentations,
                                          @JsonProperty int stockQuantity) {
        super(id);
        this.productId = productId;
        this.prices = prices;
        this.attributeRepresentations = attributeRepresentations;
        this.stockQuantity = stockQuantity;
    }

    public Identifier getProductId() {
        return productId;
    }

    public PricesRepresentation getPrices() {
        return prices;
    }

    public Set<AttributeRepresentation> getAttributeRepresentations() {
        return attributeRepresentations;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}
