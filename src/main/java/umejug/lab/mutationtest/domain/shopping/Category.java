package umejug.lab.mutationtest.domain.shopping;

import umejug.lab.mutationtest.domain.Entity;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.util.Validate;

import java.util.Collections;
import java.util.List;

public class Category extends Entity {

    private final String label;
    private final List<Category> subCategories;

    protected Category(Identifier identifier, String label, List<Category> subCategories) {
        super(identifier);

        Validate.notNull(label, () -> new NullPointerException("label can't be null."));
        Validate.notEmpty(label, () -> new IllegalArgumentException("label can't be empty."));

        this.label = label;
        this.subCategories = Collections.unmodifiableList(subCategories == null ? Collections.emptyList() : subCategories);
    }
}
