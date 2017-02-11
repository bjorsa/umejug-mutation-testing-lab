package umejug.lab.mutationtest.repository;

import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.category.Category;
import umejug.lab.mutationtest.domain.shopping.category.CategoryName;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Categories {

    private Categories() {
        // prevent instantiation and sub classing
    }

    static final Category SHOES = new Category(new Identifier(), new CategoryName("Shoes"), Collections.emptySet());
    static final Category SOCKS = new Category(new Identifier(), new CategoryName("Socks"), Collections.emptySet());
    static final Category T_SHIRTS = new Category(new Identifier(), new CategoryName("T-Shirts"), Collections.emptySet());
    static final Category TROUSERS = new Category(new Identifier(), new CategoryName("Trousers"), Collections.emptySet());
    static final Category CLOTHING = new Category(new Identifier(), new CategoryName("Clothing"), new HashSet<>(Arrays.asList(SHOES, SOCKS, T_SHIRTS, TROUSERS)));

    static Set<Category> categories() {
        return Stream.of(SHOES, SOCKS, T_SHIRTS, TROUSERS, CLOTHING).collect(Collectors.toSet());
    }

    static Set<Identifier> expand(Identifier categoryIdentifier) {
        Category category = categories().stream().filter(c -> c.getIdentifier().equals(categoryIdentifier)).findFirst().orElse(null);
        if (category == null) {
            return Collections.emptySet();
        }

        return expand(category);
    }

    private static Set<Identifier> expand(Category category) {
        Set<Identifier> expanded = Collections.singleton(category.getIdentifier());
        expanded.addAll(
                category.getSubCategories().stream()
                        .map(Categories::expand)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toSet()));
        return expanded;
    }
}
