package umejug.lab.mutationtest.representation.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.Currency;
import java.util.Map;

public class PricesRepresentation {

    private final Map<Currency, PriceRepresentation> prices;

    @JsonCreator
    public PricesRepresentation(@JsonProperty Map<Currency, PriceRepresentation> prices) {
        this.prices = Collections.unmodifiableMap(prices);
    }

    public Map<Currency, PriceRepresentation> getPrices() {
        return prices;
    }
}
