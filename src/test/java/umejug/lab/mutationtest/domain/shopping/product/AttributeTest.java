package umejug.lab.mutationtest.domain.shopping.product;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AttributeTest {

    @Test
    public void shouldRetainAttributeName() {
        AttributeName name = new AttributeName("a");
        assertThat(new Attribute(name, new AttributeValue("b")).getName()).isEqualTo(name);
    }

    @Test
    public void shouldRetainAttributeValue() {
        AttributeValue value = new AttributeValue("b");
        assertThat(new Attribute(new AttributeName("a"), value).getValue()).isEqualTo(value);
    }

    @Test
    public void shouldThrowWhenSuppliedWithNullName() {
        assertThatThrownBy(() -> new Attribute(null, new AttributeValue("b"))).isInstanceOf(NullPointerException.class);
    }
}
