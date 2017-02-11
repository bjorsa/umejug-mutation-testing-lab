package umejug.lab.mutationtest.representation.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class PriceRepresentation {

    private final BigDecimal amount;

    @JsonCreator
    public PriceRepresentation(@JsonProperty(required = true) BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
