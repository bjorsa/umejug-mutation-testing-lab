package umejug.lab.mutationtest.repository;

import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.product.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class ProductRepository {

    private ProductRepository() {
        // prevent instantiation and sub classing
    }

    public static Product find(Identifier productIdentifier) {
        return Products.products()
                .stream()
                .filter(p -> p.getIdentifier().equals(productIdentifier)).findFirst().orElse(null);
    }

    public static List<Product> findByCategory(Identifier categoryIdentifier) {
        Set<Identifier> expanded = Categories.expand(categoryIdentifier);
        return Products.products()
                .stream()
                .filter(p -> p.getCategories().stream().anyMatch(expanded::contains))
                .collect(Collectors.toList());
    }

    public static List<Product> products() {
        return Products.products();
    }
}
