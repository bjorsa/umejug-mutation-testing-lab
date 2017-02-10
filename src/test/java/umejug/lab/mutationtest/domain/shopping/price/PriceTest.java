package umejug.lab.mutationtest.domain.shopping.price;

import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PriceTest {

    @Test
    public void shouldRetainAmount() {
        BigDecimal amount = BigDecimal.valueOf(10);
        Price price = new Price(amount);

        assertThat(price.getAmount()).isEqualTo(amount);
    }

    @Test
    public void shouldThrowWhenCreatedWithNullAmount() {
        assertThatThrownBy(() -> new Price(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithNegativeAmount()  {
        assertThatThrownBy(() -> new Price(BigDecimal.valueOf(-10)));
    }
}
