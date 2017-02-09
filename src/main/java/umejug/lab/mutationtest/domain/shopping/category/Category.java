package umejug.lab.mutationtest.domain.shopping.category;

import umejug.lab.mutationtest.domain.Entity;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.Collections;
import java.util.Set;

public class Category extends Entity {

    private final CategoryName name;
    private final Set<Category> subCategories;

    public Category(Identifier identifier, CategoryName name, Set<Category> subCategories) {
        super(identifier);

        Validate.notNull(name);
        Validate.notNull(subCategories);

        this.name = name;
        this.subCategories = Collections.unmodifiableSet(subCategories);
    }

    public CategoryName getName() {
        return name;
    }

    public Set<Category> getSubCategories() {
        return subCategories;
    }
}
