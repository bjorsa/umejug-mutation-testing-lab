package umejug.lab.mutationtest.representation.product.builder;

import umejug.lab.mutationtest.domain.shopping.product.StockKeepingUnit;
import umejug.lab.mutationtest.representation.product.StockKeepingUnitRepresentation;

import java.util.List;
import java.util.stream.Collectors;

public class StockKeepingUnitRepresentationBuilder {


    public static StockKeepingUnitRepresentation fromSKU(StockKeepingUnit SKU) {
        if (SKU == null) {
            return null;
        }

        return new StockKeepingUnitRepresentation(SKU.getIdentifier(), SKU.getProduct(), PricesRepresentationBuilder.fromPrices(SKU.getPrices()), AttributeRepresentationBuilder.fromAttributes(SKU.getAttributes()), SKU.getStockQuantity());
    }

    public static List<StockKeepingUnitRepresentation> fromSKUs(List<StockKeepingUnit> SKUs) {
        return SKUs.stream().map(StockKeepingUnitRepresentationBuilder::fromSKU).collect(Collectors.toList());
    }
}
