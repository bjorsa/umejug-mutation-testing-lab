package umejug.lab.mutationtest.domain.shopping.product;

import org.junit.Test;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.price.Price;
import umejug.lab.mutationtest.domain.shopping.price.Prices;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StockKeepingUnitTest {

    @Test
    public void shouldRetainGivenValues() {
        Identifier productIdentifier = new Identifier();
        Set<Attribute> attributes = Collections.singleton(new Attribute(new AttributeName("a"), new AttributeValue("b")));
        int stockQuantity = 2;
        Prices prices = new Prices(Collections.singletonMap(Currency.getInstance(Locale.getDefault()), new Price(BigDecimal.valueOf(100))));
        StockKeepingUnit stockKeepingUnit = new StockKeepingUnit(new Identifier(), productIdentifier, prices, attributes, stockQuantity);
        assertThat(stockKeepingUnit.getStockQuantity()).isEqualTo(stockQuantity);
        assertThat(stockKeepingUnit.getProduct()).isEqualTo(productIdentifier);
        assertThat(stockKeepingUnit.getAttributes()).containsExactlyElementsOf(attributes);
        assertThat(stockKeepingUnit.getPrices()).isEqualTo(prices);
    }

    @Test
    public void shouldThrowWhenCreatedWithANullProductIdentifier() {
        Set<Attribute> attributes = Collections.singleton(new Attribute(new AttributeName("a"), new AttributeValue("b")));
        int stockQuantity = 2;
        Prices prices = new Prices(Collections.singletonMap(Currency.getInstance(Locale.getDefault()), new Price(BigDecimal.valueOf(100))));
        assertThatThrownBy(() -> new StockKeepingUnit(new Identifier(), null, prices, attributes, stockQuantity)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithNullPrices() {
        Identifier productIdentifier = new Identifier();
        Set<Attribute> attributes = Collections.singleton(new Attribute(new AttributeName("a"), new AttributeValue("b")));
        int stockQuantity = 2;
        assertThatThrownBy(() -> new StockKeepingUnit(new Identifier(), productIdentifier, null, attributes, stockQuantity)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithNullAttributes() {
        Identifier productIdentifier = new Identifier();
        int stockQuantity = 2;
        Prices prices = new Prices(Collections.singletonMap(Currency.getInstance(Locale.getDefault()), new Price(BigDecimal.valueOf(100))));
        assertThatThrownBy(() -> new StockKeepingUnit(new Identifier(), productIdentifier, prices,null, stockQuantity)).isInstanceOf(NullPointerException.class);
    }
}
