package umejug.lab.mutationtest.repository;

import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.price.Price;
import umejug.lab.mutationtest.domain.shopping.price.Prices;
import umejug.lab.mutationtest.domain.shopping.product.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public final class StockKeepingUnitRepository {

    private static final Currency SEK = Currency.getInstance("SEK");

    private static final List<StockKeepingUnit> SKUs = new ArrayList<>();
    private static final Attribute SIZE_SMALL = new Attribute(new AttributeName("size"), new AttributeValue("S"));
    private static final Attribute SIZE_MEDIUM = new Attribute(new AttributeName("size"), new AttributeValue("M"));
    private static final Attribute SIZE_LARGE = new Attribute(new AttributeName("size"), new AttributeValue("L"));
    private static final Attribute SIZE_EXTRA_LARGE = new Attribute(new AttributeName("size"), new AttributeValue("XL"));
    private static final Attribute SIZE_11 = new Attribute(new AttributeName("size"), new AttributeValue("11"));
    private static final Attribute SIZE_11_HALF = new Attribute(new AttributeName("size"), new AttributeValue("11_1/2"));
    private static final Attribute SIZE_12 = new Attribute(new AttributeName("size"), new AttributeValue("12"));
    private static final Attribute SIZE_12_HALF = new Attribute(new AttributeName("size"), new AttributeValue("12_1/2"));
    private static final Attribute SIZE_13 = new Attribute(new AttributeName("size"), new AttributeValue("13"));
    private static final Attribute SIZE_13_HALF = new Attribute(new AttributeName("size"), new AttributeValue("13_1/2"));
    private static final Attribute COLOR_BLACK = new Attribute(new AttributeName("color"), new AttributeValue("Black"));
    private static final Attribute COLOR_BROWN = new Attribute(new AttributeName("color"), new AttributeValue("Brown"));
    private static final Attribute COLOR_RED = new Attribute(new AttributeName("color"), new AttributeValue("Red"));
    private static final Attribute COLOR_BLUE = new Attribute(new AttributeName("color"), new AttributeValue("Blue"));
    private static final Attribute COLOR_MAGENTA = new Attribute(new AttributeName("color"), new AttributeValue("Magenta"));
    private static final Attribute COLOR_PINK = new Attribute(new AttributeName("color"), new AttributeValue("Pink"));
    private static final Attribute COLOR_ORANGE = new Attribute(new AttributeName("color"), new AttributeValue("Orange"));
    private static final Attribute COLOR_WHITE = new Attribute(new AttributeName("color"), new AttributeValue("White"));

    static {
        //noinspection unchecked
        SKUs.addAll(generateSKUs(
                Products.T_SHIRT,
                generatePrices(150),
                Arrays.asList(SIZE_SMALL, COLOR_BLACK),
                Arrays.asList(SIZE_MEDIUM, COLOR_BLACK),
                Arrays.asList(SIZE_EXTRA_LARGE, COLOR_BLACK),
                Arrays.asList(SIZE_SMALL, COLOR_RED),
                Arrays.asList(SIZE_MEDIUM, COLOR_RED),
                Arrays.asList(SIZE_LARGE, COLOR_RED),
                Arrays.asList(SIZE_EXTRA_LARGE, COLOR_RED),
                Arrays.asList(SIZE_MEDIUM, COLOR_BROWN),
                Arrays.asList(SIZE_LARGE, COLOR_BROWN)
        ));

        //noinspection unchecked
        SKUs.addAll(generateSKUs(
                Products.SLIPPERS,
                generatePrices(200),
                Arrays.asList(SIZE_11, COLOR_MAGENTA),
                Arrays.asList(SIZE_11_HALF, COLOR_MAGENTA),
                Arrays.asList(SIZE_12, COLOR_MAGENTA),
                Arrays.asList(SIZE_13, COLOR_MAGENTA),
                Arrays.asList(SIZE_13_HALF, COLOR_MAGENTA),
                Arrays.asList(SIZE_11, COLOR_ORANGE),
                Arrays.asList(SIZE_12, COLOR_ORANGE),
                Arrays.asList(SIZE_13, COLOR_ORANGE),
                Arrays.asList(SIZE_12_HALF, COLOR_WHITE),
                Arrays.asList(SIZE_13, COLOR_WHITE),
                Arrays.asList(SIZE_13_HALF, COLOR_WHITE)
        ));

        //noinspection unchecked
        SKUs.addAll(generateSKUs(
                Products.LEATHER_PANTS,
                generatePrices(499),
                Collections.singletonList(SIZE_SMALL),
                Collections.singletonList(SIZE_MEDIUM)
        ));

        //noinspection unchecked
        SKUs.addAll(generateSKUs(
                Products.LEG_WARMERS,
                generatePrices(149),
                Collections.singletonList(COLOR_PINK),
                Collections.singletonList(COLOR_BLUE)
        ));

        //noinspection unchecked
        SKUs.addAll(generateSKUs(
                Products.ROLLERBLADES,
                generatePrices(799),
                Arrays.asList(SIZE_11, COLOR_WHITE),
                Arrays.asList(SIZE_11, COLOR_ORANGE),
                Arrays.asList(SIZE_11_HALF, COLOR_WHITE),
                Arrays.asList(SIZE_11_HALF, COLOR_ORANGE),
                Arrays.asList(SIZE_11_HALF, COLOR_BLACK),
                Arrays.asList(SIZE_12, COLOR_BLACK),
                Arrays.asList(SIZE_12_HALF, COLOR_WHITE),
                Arrays.asList(SIZE_12_HALF, COLOR_BLACK),
                Arrays.asList(SIZE_13, COLOR_ORANGE),
                Arrays.asList(SIZE_13, COLOR_BLACK),
                Arrays.asList(SIZE_13_HALF, COLOR_WHITE),
                Arrays.asList(SIZE_13_HALF, COLOR_ORANGE),
                Arrays.asList(SIZE_13_HALF, COLOR_BLACK)
        ));
    }

    @SafeVarargs
    private static Collection<? extends StockKeepingUnit> generateSKUs(Product product, Prices prices, List<Attribute>... attributes) {
        return Arrays.stream(attributes).map(a -> new StockKeepingUnit(new Identifier(), product.getIdentifier(), prices, new HashSet<>(a), 10)).collect(Collectors.toSet());
    }

    private static Prices generatePrices(int amount) {
        return new Prices(Collections.singletonMap(SEK, new Price(BigDecimal.valueOf(amount))));
    }

    private StockKeepingUnitRepository() {
        // prevent instantiation and sub classing
    }

    public static StockKeepingUnit find(Identifier SKUIdentifier) {
        return SKUs.stream().filter(s -> s.getIdentifier().equals(SKUIdentifier)).findFirst().orElse(null);
    }

    public static List<StockKeepingUnit> findByAttributes(List<Attribute> attributes) {
        return SKUs.stream()
                .filter(s -> s.getAttributes().containsAll(attributes))
                .collect(Collectors.toList());
    }

    public static List<StockKeepingUnit> findByProduct(Identifier productIdentifier) {
        return SKUs.stream()
                .filter(s -> s.getProduct().equals(productIdentifier))
                .collect(Collectors.toList());
    }

    public static List<StockKeepingUnit> findByProductAndAttributes(Identifier productIdentifier, List<Attribute> attributes) {
        return SKUs.stream()
                .filter(s -> s.getProduct().equals(productIdentifier))
                .filter(s -> s.getAttributes().containsAll(attributes))
                .collect(Collectors.toList());
    }
}
