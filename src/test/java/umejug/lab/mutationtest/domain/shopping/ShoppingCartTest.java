package umejug.lab.mutationtest.domain.shopping;

import org.junit.Test;
import umejug.lab.mutationtest.domain.Identifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ShoppingCartTest {

    @Test
    public void shouldRetainTheGivenValues() {
        List<ShoppingCartLineItem> items = Collections.singletonList(new ShoppingCartLineItem(new Identifier(), 1));
        ShoppingCartLineItems lineItems = new ShoppingCartLineItems(items);
        Set<Identifier> abandonedSKUs = Collections.singleton(new Identifier());
        ShoppingCart cart = new ShoppingCart(lineItems, abandonedSKUs);

        assertThat(cart.getItems()).containsExactlyElementsOf(items);
        assertThat(cart.getAbandonedSKUs()).containsExactlyElementsOf(abandonedSKUs);
    }

    @Test
    public void shouldThrowWhenCreatedWithNullLineItems() {
        assertThatThrownBy(() -> new ShoppingCart(null, Collections.emptySet())).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenCreatedWithNullAbandonedSKUs() {
        assertThatThrownBy(() -> new ShoppingCart(new ShoppingCartLineItems(Collections.emptyList()), null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldAddSKUCorrectly() {
        Identifier SKUToAdd = new Identifier();
        Set<Identifier> abandonedSKUs = Collections.singleton(SKUToAdd);
        ShoppingCart cart = new ShoppingCart(new ShoppingCartLineItems(Collections.emptyList()), abandonedSKUs);
        ShoppingCart modifiedCart = cart.addSKU(SKUToAdd);
        assertThat(modifiedCart.getAbandonedSKUs()).doesNotContain(SKUToAdd);
        assertThat(modifiedCart.getItems()).containsExactlyElementsOf(Collections.singletonList(new ShoppingCartLineItem(SKUToAdd, 1)));
    }

    @Test
    public void shouldRemoveSKUCorrectly() {
        Identifier SKUToRemove = new Identifier();
        List<ShoppingCartLineItem> items = Collections.singletonList(new ShoppingCartLineItem(SKUToRemove, 1));
        ShoppingCartLineItems lineItems = new ShoppingCartLineItems(items);
        Set<Identifier> abandonedSKUs = Collections.emptySet();
        ShoppingCart cart = new ShoppingCart(lineItems, abandonedSKUs);

        ShoppingCart modifiedCart = cart.removeSKU(SKUToRemove);
        assertThat(modifiedCart.getItems()).extracting("SKU").doesNotContain(SKUToRemove);
        assertThat(modifiedCart.getAbandonedSKUs()).contains(SKUToRemove);
    }

    @Test
    public void shouldUpdateLineItemsCorrectly() {
        ShoppingCartLineItem item1 = new ShoppingCartLineItem(new Identifier(), 1);
        ShoppingCartLineItem item2 = new ShoppingCartLineItem(new Identifier(), 2);
        ShoppingCartLineItem item3 = new ShoppingCartLineItem(new Identifier(), 3);

        ShoppingCartLineItems items = new ShoppingCartLineItems(Arrays.asList(item1, item2));
        ShoppingCart cart = new ShoppingCart(items, Collections.singleton(item3.getSKU()));

        ShoppingCartLineItems newItems = new ShoppingCartLineItems(Arrays.asList(item2, item3));
        ShoppingCart modifiedCart = cart.updateLineItems(newItems);
        assertThat(modifiedCart.getAbandonedSKUs()).containsExactly(item1.getSKU());
        assertThat(modifiedCart.getItems()).containsExactlyElementsOf(newItems.getLineItems());
    }
}
