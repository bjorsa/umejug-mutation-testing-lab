package umejug.lab.mutationtest.service.util;

import org.junit.Test;
import umejug.lab.mutationtest.domain.Entity;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.product.Product;
import umejug.lab.mutationtest.repository.ProductRepository;
import umejug.lab.mutationtest.representation.EntityRepresentation;
import umejug.lab.mutationtest.representation.product.AttributeRepresentation;
import umejug.lab.mutationtest.representation.product.ProductRepresentation;
import umejug.lab.mutationtest.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static umejug.lab.mutationtest.repository.Products.LEATHER_PANTS;
import static umejug.lab.mutationtest.repository.Products.ROLLERBLADES;
import static umejug.lab.mutationtest.repository.Products.T_SHIRT;

public class ProductServiceTest {

    @Test
    public void shouldListAllTheProductsWhenCalledWithoutArguments() {
        List<ProductRepresentation> products = new ProductService().listProducts(null, null, null, null);
        List<TestEntity> result = products.stream().map(EntityRepresentation::getId).map(TestEntity::new).collect(Collectors.toList());
        List<TestEntity> expected = ProductRepository.products().stream().map(Product::getIdentifier).map(TestEntity::new).collect(Collectors.toList());
        assertThat(result).containsExactlyInAnyOrder(expected.toArray(new TestEntity[]{}));
    }

    @Test
    public void shouldListOnlyTheProductsWithTheSpecifiedAttribute() {
        List<ProductRepresentation> products = new ProductService().listProducts(new AttributeRepresentation("color", "Black"), null, null, null);
        List<TestEntity> result = products.stream().map(EntityRepresentation::getId).map(TestEntity::new).collect(Collectors.toList());
        List<TestEntity> expected = Stream.of(T_SHIRT, ROLLERBLADES).map(Product::getIdentifier).map(TestEntity::new).collect(Collectors.toList());
        assertThat(result).containsExactlyInAnyOrder(expected.toArray(new TestEntity[]{}));
    }

    @Test
    public void shouldOrderProductsAccordingToTheSpecifiedAttribute() {
        List<ProductRepresentation> products = new ProductService().listProducts(new AttributeRepresentation("size", "S"), "color", null, null);
        List<TestEntity> result = products.stream().map(EntityRepresentation::getId).map(TestEntity::new).collect(Collectors.toList());
        List<TestEntity> expected = Stream.of(T_SHIRT, LEATHER_PANTS).map(Product::getIdentifier).map(TestEntity::new).collect(Collectors.toList());
        assertThat(result).containsExactly(expected.toArray(new TestEntity[]{}));
    }

    private static class TestEntity extends Entity {

        protected TestEntity(Identifier identifier) {
            super(identifier);
        }
    }
}
