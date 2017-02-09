package umejug.lab.mutationtest.domain.shopping.product;

import org.junit.Test;
import umejug.lab.mutationtest.domain.Identifier;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StockKeepingUnitTest {

    @Test
    public void shouldRetainGivenValues() {
        Identifier productIdentifier = new Identifier();
        Set<Attribute> attributes = Collections.singleton(new Attribute(new AttributeName("a"), new AttributeValue("b")));
        int stockQuantity = 2;
        StockKeepingUnit stockKeepingUnit = new StockKeepingUnit(new Identifier(), productIdentifier, attributes, stockQuantity);
        assertThat(stockKeepingUnit.getStockQuantity()).isEqualTo(stockQuantity);
        assertThat(stockKeepingUnit.getProduct()).isEqualTo(productIdentifier);
        assertThat(stockKeepingUnit.getAttributes()).containsExactlyElementsOf(attributes);
    }

    @Test
    public void shouldThrowWhenCreatedWithANullProductIdentifier() {
        Set<Attribute> attributes = Collections.singleton(new Attribute(new AttributeName("a"), new AttributeValue("b")));
        int stockQuantity = 2;
        assertThatThrownBy(() -> new StockKeepingUnit(new Identifier(), null, attributes, stockQuantity)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithNullAttributes() {
        Identifier productIdentifier = new Identifier();
        int stockQuantity = 2;
        assertThatThrownBy(() -> new StockKeepingUnit(new Identifier(), productIdentifier, null, stockQuantity)).isInstanceOf(NullPointerException.class);
    }
}
