package umejug.lab.mutationtest.domain.shopping.category;

import org.junit.Test;
import umejug.lab.mutationtest.domain.Identifier;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class CategoryTest {

    @Test
    public void shouldCreateACategoryWithoutSubCategories() {
        CategoryName name = new CategoryName("test-category");
        Category category = new Category(new Identifier(), name, Collections.emptySet());
        assertThat(category.getName()).isEqualTo(name);
        assertThat(category.getSubCategories()).isEmpty();
    }

    @Test
    public void shouldCreateACategory() {
        CategoryName subCategoryName = new CategoryName("test-sub-category");
        Category subCategory = new Category(new Identifier(), subCategoryName, Collections.emptySet());
        Set<Category> subCategories = Collections.singleton(subCategory);
        CategoryName categoryName = new CategoryName("test-category");
        Category category = new Category(new Identifier(), categoryName, subCategories);
        assertThat(category.getName()).isEqualTo(categoryName);
        assertThat(category.getSubCategories()).isEqualTo(subCategories);
    }

    @Test
    public void shouldThrowWhenCreatedWithANullName() {
        assertThatThrownBy(() -> new Category(new Identifier(), null, Collections.emptySet())).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithNullCategories() {
        assertThatThrownBy(() -> new Category(new Identifier(), new CategoryName("test-category"), null)).isInstanceOf(NullPointerException.class);
    }
}
