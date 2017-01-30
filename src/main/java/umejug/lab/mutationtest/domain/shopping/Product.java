package umejug.lab.mutationtest.domain.shopping;

import umejug.lab.mutationtest.domain.Entity;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.Collections;
import java.util.Set;

public final class Product extends Entity {

    private final String name;
    private final Set<Category> categories;

    public Product(Identifier identifier, String name, Set<Category> categories) {
        super(identifier);

        Validate.notNull(name, () -> new NullPointerException("name can't be null"));
        Validate.notEmpty(name, () -> new IllegalArgumentException("name can't be empty"));
        Validate.notNull(categories, () -> new NullPointerException("categories can't be null"));
        Validate.notEmpty(categories, () -> new IllegalArgumentException("categories can't be empty"));

        this.name = name;
        this.categories = Collections.unmodifiableSet(categories);
    }

    public String getName() {
        return name;
    }

    public Set<Category> getCategories() {
        return categories;
    }
}
