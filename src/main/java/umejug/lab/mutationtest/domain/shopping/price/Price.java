package umejug.lab.mutationtest.domain.shopping.price;

import umejug.lab.mutationtest.domain.ValueObject;
import umejug.lab.mutationtest.domain.util.Validate;

import java.math.BigDecimal;

public final class Price extends ValueObject {

    private final BigDecimal amount;

    public Price(BigDecimal amount) {
        Validate.notNegative(amount);
        this.amount = amount;
    }

    public final BigDecimal getAmount() {
        return amount;
    }
}
