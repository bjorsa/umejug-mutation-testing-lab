package umejug.lab.mutationtest.domain.shopping.product;

import org.junit.Test;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.category.Category;
import umejug.lab.mutationtest.domain.shopping.category.CategoryName;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductTest {

    @Test
    public void shouldConstructAProductAndRetainGivenValues() {
        ProductName productName = new ProductName("a");
        ProductDescription productDescription = new ProductDescription("b");
        Set<Identifier> categories = Collections.singleton(new Identifier());
        Product product = new Product(new Identifier(), productName, productDescription, categories);
        assertThat(product.getName()).isEqualTo(productName);
        assertThat(product.getDescription()).isEqualTo(productDescription);
        assertThat(product.getCategories()).containsExactlyElementsOf(categories);
    }

    @Test
    public void shouldThrowWhenCreatedWithANullName() {
        ProductDescription productDescription = new ProductDescription("b");
        Set<Identifier> categories = Collections.singleton(new Identifier());
        assertThatThrownBy(() -> new Product(new Identifier(), null, productDescription, categories)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithANullDescription() {
        ProductName productName = new ProductName("a");
        Set<Identifier> categories = Collections.singleton(new Identifier());
        assertThatThrownBy(() -> new Product(new Identifier(), productName, null, categories)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithANullCategoriesSet() {
        ProductName productName = new ProductName("a");
        ProductDescription productDescription = new ProductDescription("b");
        assertThatThrownBy(() -> new Product(new Identifier(), productName, productDescription, null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithAnEmptyCategoriesSet() {
        ProductName productName = new ProductName("a");
        ProductDescription productDescription = new ProductDescription("b");
        Set<Identifier> categories = Collections.emptySet();
        assertThatThrownBy(() -> new Product(new Identifier(), productName, productDescription, categories)).isInstanceOf(IllegalArgumentException.class);
    }
}
