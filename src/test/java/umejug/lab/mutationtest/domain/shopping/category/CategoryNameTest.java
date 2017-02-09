package umejug.lab.mutationtest.domain.shopping.category;

import org.junit.Test;
import umejug.lab.mutationtest.domain.TestHelper;

import javax.validation.constraints.Null;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.assertThatThrownBy;

public class CategoryNameTest {

    @Test
    public void shouldRetainTheGivenName() {
        String name = "toys";
        assertThat(new CategoryName(name).getName()).isEqualTo(name);
    }

    @Test
    public void shouldNotAcceptANullName() {
        assertThatThrownBy(() -> new CategoryName(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldNotAcceptAnEmptyName() {
        assertThatThrownBy(() -> new CategoryName("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void shouldNotAcceptAVeryLongName() {
        String name = TestHelper.randomAlphabeticalString(CategoryName.MAX_NAME_LENGTH * 2);
        assertThatThrownBy(() -> {
            new CategoryName(name);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
