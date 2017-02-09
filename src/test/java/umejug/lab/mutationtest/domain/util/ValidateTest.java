package umejug.lab.mutationtest.domain.util;

import org.junit.Test;

import javax.validation.constraints.Null;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ValidateTest {

    @Test
    public void shouldNotThrowWhenNonNullValueIsNotNull() {
        Validate.notNull(new Object());
    }

    @Test
    public void shouldThrowWhenNullValueIsNotNotNull() {
        assertThatThrownBy(() -> Validate.notNull(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldNotThrowWhenPositiveValueIsPositive() {
        Validate.positive(13);
    }

    @Test
    public void shouldThrowWhenZeroValueIsNotPositive() {
        assertThatThrownBy(() -> Validate.positive(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldThrowWhenNegativeValueIsNotPositive() {
        assertThatThrownBy(() -> Validate.positive(-10)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotThrowWhenPositiveValueIsNotNegative() {
        Validate.notNegative(13);
    }

    @Test
    public void shouldNotThrowWhenZeroValueIsNotNegative() {
        Validate.notNegative(0);
    }

    @Test
    public void shouldThrowWhenNegativeValueIsNotNotNegative() {
        assertThatThrownBy(() -> Validate.notNegative(-10)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotThrowWhenLesserValueIsLessThan() {
        Validate.lessThan(12, 25);
    }

    @Test
    public void shouldThrowWhenEqualValueIsNotLessThan() {
        assertThatThrownBy(() -> Validate.lessThan(12, 12)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldThrowWhenGreaterValueIsNotLessThan() {
        assertThatThrownBy(() -> Validate.lessThan(25, 12)).isInstanceOf(IllegalArgumentException.class);
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
        assertThatThrownBy(() -> Validate.lessThanOrEqual(25, 12)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldThrowWhenLesserValueIsNotGreaterThanOrEqual() {
        assertThatThrownBy(() -> Validate.greaterThanOrEqual(12, 25)).isInstanceOf(IllegalArgumentException.class);
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
        assertThatThrownBy(() -> Validate.greaterThan(12, 25)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldThrowWhenEqualValueIsGreaterThan() {
        assertThatThrownBy(() -> Validate.greaterThan(12, 12)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotThrowWhenGreaterValueIsGreaterThan() {
        Validate.greaterThan(25, 12, IllegalArgumentException::new);
    }

    @Test
    public void shouldNotThrowWhenNonEmptyStringIsNotEmpty() {
        Validate.notEmpty("a");
    }

    @Test
    public void shouldThrowWhenEmptyStringIsNotNotEmpty() {
        assertThatThrownBy(() -> Validate.notEmpty("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotThrowWhenNonEmptyCollectionIsNotEmpty() {
        Validate.notEmpty(Collections.singleton(1));
    }

    @Test
    public void shouldThrowWhenEmptyCollectionIsNotNotEmpty() {
        assertThatThrownBy(() -> Validate.notEmpty(Collections.emptySet())).isInstanceOf(IllegalArgumentException.class);
    }
}
