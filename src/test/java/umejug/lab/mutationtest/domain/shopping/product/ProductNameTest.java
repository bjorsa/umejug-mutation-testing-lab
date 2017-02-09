package umejug.lab.mutationtest.domain.shopping.product;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductNameTest {

    @Test
    public void shouldProperlyConstructAProductName() {
        String name = "name";
        ProductName productName = new ProductName(name);
        assertThat(productName.getName()).isEqualTo(name);
    }

    @Test
    public void shouldThrowWhenCreatedWithANullName() {
        assertThatThrownBy(() -> new ProductName(null)).isInstanceOf(NullPointerException.class);
    }
}
