package umejug.lab.mutationtest.representation.product.builder;

import umejug.lab.mutationtest.domain.shopping.product.Product;
import umejug.lab.mutationtest.representation.product.ProductRepresentation;

import java.util.List;
import java.util.stream.Collectors;

public final class ProductRepresentationBuilder {

    public static ProductRepresentation fromProduct(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductRepresentation(product.getIdentifier(), product.getName().getName(), product.getDescription().getDescription(), product.getCategories());
    }

    public static List<ProductRepresentation> fromProducts(List<Product> products) {
        return products.stream().map(ProductRepresentationBuilder::fromProduct).collect(Collectors.toList());
    }
}
