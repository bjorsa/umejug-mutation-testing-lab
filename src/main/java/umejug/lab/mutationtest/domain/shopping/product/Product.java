package umejug.lab.mutationtest.domain.shopping.product;

import umejug.lab.mutationtest.domain.Entity;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.category.Category;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.Collections;
import java.util.Set;

public final class Product extends Entity {

    private final ProductName name;
    private final ProductDescription description;
    private final Set<Category> categories;

    public Product(Identifier identifier, ProductName name, ProductDescription description, Set<Category> categories) {
        super(identifier);

        Validate.notNull(name);
        Validate.notNull(description);
        Validate.notNull(categories);
        Validate.notEmpty(categories);

        this.name = name;
        this.description = description;
        this.categories = Collections.unmodifiableSet(categories);
    }

    public ProductName getName() {
        return name;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public ProductDescription getDescription() {
        return description;
    }
}
