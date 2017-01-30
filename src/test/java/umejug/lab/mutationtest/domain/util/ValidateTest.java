package umejug.lab.mutationtest.domain.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidateTest {

    @Test
    public void shouldNotThrowWhenNonNullValueIsNotNull() {
        Validate.notNull(new Object(), NullPointerException::new);
    }

    @Test
    public void shouldThrowWhenNullValueIsNotNotNull() {
        String message = "null is not not null";
        assertThatThrownBy(() -> Validate.notNull(null, () -> new NullPointerException(message)))
                .isInstanceOf(NullPointerException.class)
                .hasMessage(message);
    }

    @Test
    public void shouldNotThrowWhenPositiveValueIsPositive() {
        Validate.positive(13, IllegalArgumentException::new);
    }

    @Test
    public void shouldThrowWhenZeroValueIsNotPositive() {
        String message = "value is not positive";
        assertThatThrownBy(() -> Validate.positive(0, () -> new IllegalArgumentException(message)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void shouldThrowWhenNegativeValueIsNotPositive() {
        String message = "value is not positive";
        assertThatThrownBy(() -> Validate.positive(-10, () -> new IllegalArgumentException(message)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void shouldNotThrowWhenPositiveValueIsNotNegative() {
        Validate.notNegative(13, IllegalArgumentException::new);
    }

    @Test
    public void shouldNotThrowWhenZeroValueIsNotNegative() {
        Validate.notNegative(0, IllegalArgumentException::new);
    }

    @Test
    public void shouldThrowWhenNegativeValueIsNotNotNegative() {
        String message = "value is not not negative";
        assertThatThrownBy(() -> Validate.notNegative(-10, () -> new IllegalArgumentException(message)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void shouldNotThrowWhenLesserValueIsLessThan() {
        Validate.lessThan(12, 25, IllegalArgumentException::new);
    }

    @Test
    public void shouldThrowWhenEqualValueIsNotLessThan() {
        String message = "value is not less than";
        assertThatThrownBy(() -> Validate.lessThan(12, 12, () -> new IllegalArgumentException(message)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void shouldThrowWhenGreaterValueIsNotLessThan() {
        String message = "value is not less than";
        assertThatThrownBy(() -> Validate.lessThan(25, 12, () -> new IllegalArgumentException(message)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void shouldNotThrowWhenLesserValueIsLessThanOrEqual() {
        Validate.lessThanOrEqual(12, 25, IllegalArgumentException::new);
    }

    @Test
    public void shouldNotThrowWhenEqualValueIsLessThanOrEqual() {
        Validate.lessThanOrEqual(12, 12, IllegalArgumentException::new);
    }

    @Test
    public void shouldThrowWhenGreaterValueIsNotLessThanOrEqual() {
        String message = "value is not less than or equal";
        assertThatThrownBy(() -> Validate.lessThanOrEqual(25, 12, () -> new IllegalArgumentException(message)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void shouldThrowWhenLesserValueIsNotGreaterThanOrEqual() {
        String message = "value is not greater than or equal";
        assertThatThrownBy(() -> Validate.greaterThanOrEqual(12, 25, () -> new IllegalArgumentException(message)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void shouldNotThrowWhenEqualValueIsGreaterThanOrEqual() {
        Validate.greaterThanOrEqual(12, 12, IllegalArgumentException::new);
    }

    @Test
    public void shouldNotThrowWhenGreaterValueIsGreaterThanOrEqual() {
        Validate.greaterThanOrEqual(25, 12, IllegalArgumentException::new);
    }

    @Test
    public void shouldThrowWhenLesserValueIsNotGreaterThan() {
        String message = "value is not greater than";
        assertThatThrownBy(() -> Validate.greaterThan(12, 25, () -> new IllegalArgumentException(message)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void shouldThrowWhenEqualValueIsGreaterThan() {
        String message = "value is not greater than";
        assertThatThrownBy(() -> Validate.greaterThan(12, 12, () -> new IllegalArgumentException(message)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }

    @Test
    public void shouldNotThrowWhenGreaterValueIsGreaterThan() {
        Validate.greaterThan(25, 12, IllegalArgumentException::new);
    }

    @Test
    public void shouldNotThrowWhenNonEmptyStringIsNotEmpty() {
        Validate.notEmpty("a", IllegalArgumentException::new);
    }

    @Test
    public void shouldThrowWhenEmptyStringIsNotNotEmpty() {
        String message = "value is not not empty";
        assertThatThrownBy(() -> Validate.notEmpty("", () -> new IllegalArgumentException(message)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
}
