package umejug.lab.mutationtest.domain.shopping.product;

import org.junit.Test;
import umejug.lab.mutationtest.TestHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AttributeValueTest {

    @Test
    public void shouldRetainTheGivenValue() {
        String value = TestHelper.randomAlphabeticalString(10);
        assertThat(new AttributeValue(value).getValue()).isEqualTo(value);
    }

    @Test
    public void shouldThrowWhenGivenATooLongValue() {
        String value = TestHelper.randomAlphabeticalString(AttributeValue.MAX_VALUE_LENGTH + 1);
        assertThatThrownBy(() -> new AttributeValue(value)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldThrowWhenGivenValueIsNull() {
        assertThatThrownBy(() -> new AttributeValue(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenGivenValueIsEmpty() {
        assertThatThrownBy(() -> new AttributeValue("")).isInstanceOf(IllegalArgumentException.class);
    }
}
