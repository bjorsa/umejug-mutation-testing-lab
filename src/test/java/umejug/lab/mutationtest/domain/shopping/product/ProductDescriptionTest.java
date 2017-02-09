package umejug.lab.mutationtest.domain.shopping.product;

import org.junit.Test;
import umejug.lab.mutationtest.TestHelper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ProductDescriptionTest {

    @Test
    public void shouldProperlyCreateADescription() {
        String description = "Shiny thing";
        ProductDescription productDescription = new ProductDescription(description);
        assertThat(productDescription.getDescription()).isSameAs(description);
    }

    @Test
    public void shouldThrowWhenCreatedWithNullDescription() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> new ProductDescription(null));
    }

    @Test
    public void shouldThrowWhenCreatedWithTooLongDescription() {
        String tooLong = TestHelper.randomAlphabeticalString(ProductDescription.MAX_DESCRIPTION_LENGTH + 1);
        assertThatThrownBy(() -> new ProductDescription(tooLong)).isInstanceOf(IllegalArgumentException.class);
    }
}
