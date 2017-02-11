package umejug.lab.mutationtest.repository;

import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.product.Product;
import umejug.lab.mutationtest.domain.shopping.product.ProductDescription;
import umejug.lab.mutationtest.domain.shopping.product.ProductName;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


public class Products {

    static final Product T_SHIRT = new Product(new Identifier(), new ProductName("Fancy Tee"), new ProductDescription("Appropriate attire for the most important party of the year!"), Collections.singleton(Categories.T_SHIRTS.getIdentifier()));
    static final Product SLIPPERS = new Product(new Identifier(), new ProductName("Softy Sneakies"), new ProductDescription("A sock/shoe hybrid for those weekday evenings."), new HashSet<>(Arrays.asList(Categories.SHOES.getIdentifier(), Categories.SOCKS.getIdentifier())));
    static final Product LEATHER_PANTS = new Product(new Identifier(), new ProductName("Snakeskin Leg Guards"), new ProductDescription("Protect your legs on those long desert hikes and do it in style!"), Collections.singleton(Categories.TROUSERS.getIdentifier()));
    static final Product LEG_WARMERS = new Product(new Identifier(), new ProductName("Leg Warmer"), new ProductDescription("Just your regular leg warmer."), new HashSet<>(Arrays.asList(Categories.TROUSERS.getIdentifier(), Categories.SOCKS.getIdentifier())));
    static final Product ROLLERBLADES = new Product(new Identifier(), new ProductName("Derby Dancers"), new ProductDescription("Professional Roller blades for when you really need the best you can get!"), Collections.singleton(Categories.SHOES.getIdentifier()));

    public static List<Product> products() {
        return Arrays.asList(T_SHIRT, SLIPPERS, LEATHER_PANTS, LEG_WARMERS, ROLLERBLADES);
    }
}
