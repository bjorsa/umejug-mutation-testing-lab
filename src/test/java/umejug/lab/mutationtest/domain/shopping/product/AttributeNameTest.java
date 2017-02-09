package umejug.lab.mutationtest.domain.shopping.product;

import org.junit.Test;
import umejug.lab.mutationtest.TestHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AttributeNameTest {

    @Test
    public void shouldRetainTheGivenName() {
        String name = TestHelper.randomAlphabeticalString(10);
        assertThat(new AttributeName(name).getName()).isEqualTo(name);
    }

    @Test
    public void shouldThrowIfCreatedWithANullName() {
        assertThatThrownBy(() -> new AttributeName(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowIfCreatedWithATooLongName() {
        String name = TestHelper.randomAlphabeticalString(AttributeName.MAX_NAME_LENGTH + 1);
        assertThatThrownBy(() -> new AttributeName(name)).isInstanceOf(IllegalArgumentException.class);
    }
}
