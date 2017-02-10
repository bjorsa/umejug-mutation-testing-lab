package umejug.lab.mutationtest.domain.shopping.price;

import org.junit.Test;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PricesTest {

    @Test
    public void shouldRetainPricingInformation() {
        Map<Currency, Price> priceMap = Collections.singletonMap(Currency.getInstance(Locale.getDefault()), new Price(BigDecimal.valueOf(1000)));
        Prices prices = new Prices(priceMap);

        assertThat(prices.getCurrencies()).hasSameSizeAs(priceMap.keySet());
        assertThat(prices.getCurrencies()).allMatch(c -> prices.getPriceByCurrency(c).equals(prices.getPriceByCurrency(c)));
    }

    @Test
    public void shouldThrowWhenCreatedWithNullPriceMap() {
        assertThatThrownBy(() -> new Prices(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithEmptyPriceMap() {
        assertThatThrownBy(() -> new Prices(Collections.emptyMap())).isInstanceOf(IllegalArgumentException.class);
    }
}
