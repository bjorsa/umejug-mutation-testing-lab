package umejug.lab.mutationtest.domain.shopping;

import org.junit.Test;
import umejug.lab.mutationtest.domain.Identifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class ShoppingCartLineItemsTest {

    @Test
    public void shouldRetainTheGivenValues() {
        ShoppingCartLineItem item1 = new ShoppingCartLineItem(new Identifier(), 1);
        ShoppingCartLineItem item2 = new ShoppingCartLineItem(new Identifier(), 2);
        ShoppingCartLineItem item3 = new ShoppingCartLineItem(new Identifier(), 3);
        ShoppingCartLineItem item4 = new ShoppingCartLineItem(new Identifier(), 4);

        List<ShoppingCartLineItem> items = Arrays.asList(item1, item2, item3, item4);
        ShoppingCartLineItems lineItems = new ShoppingCartLineItems(items);

        assertThat(lineItems.getNumberOfSKUs()).isEqualTo(items.size());
        assertThat(lineItems.getLineItems()).containsExactlyElementsOf(items);
    }

    @Test
    public void shouldThrowWhenGivenNullLineItems() {
        assertThatThrownBy(() -> new ShoppingCart(null, Collections.emptySet())).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldThrowWhenGivenNullAbandondedSKUs() {
        assertThatThrownBy(() -> new ShoppingCart(new ShoppingCartLineItems(Collections.emptyList()), null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void shouldAddASKUProperly() {
        ShoppingCartLineItem item1 = new ShoppingCartLineItem(new Identifier(), 1);
        ShoppingCartLineItem item2 = new ShoppingCartLineItem(new Identifier(), 2);
        ShoppingCartLineItem item3 = new ShoppingCartLineItem(new Identifier(), 3);
        ShoppingCartLineItem item4 = new ShoppingCartLineItem(new Identifier(), 4);

        List<ShoppingCartLineItem> items = Arrays.asList(item1, item2, item3, item4);
        ShoppingCartLineItems lineItems = new ShoppingCartLineItems(items);

        ShoppingCartLineItem item5 = new ShoppingCartLineItem(new Identifier(), 1);
        ShoppingCartLineItems modifiedLineItems = lineItems.addSKU(item5.getSKU());
        List<ShoppingCartLineItem> expectedItemList = Arrays.asList(item1, item2, item3, item4, item5);
        assertThat(modifiedLineItems.getLineItems()).containsExactlyElementsOf(expectedItemList);
    }

    @Test
    public void shouldRemoveASKUProperly() {
        ShoppingCartLineItem item1 = new ShoppingCartLineItem(new Identifier(), 1);
        ShoppingCartLineItem item2 = new ShoppingCartLineItem(new Identifier(), 2);
        ShoppingCartLineItem item3 = new ShoppingCartLineItem(new Identifier(), 3);
        ShoppingCartLineItem item4 = new ShoppingCartLineItem(new Identifier(), 4);

        List<ShoppingCartLineItem> items = Arrays.asList(item1, item2, item3, item4);
        ShoppingCartLineItems lineItems = new ShoppingCartLineItems(items);

        ShoppingCartLineItems modifiedLineItems = lineItems.removeSKU(item2.getSKU());
        List<ShoppingCartLineItem> expectedItemList = Arrays.asList(item1, item3, item4);
        assertThat(modifiedLineItems.getLineItems()).containsExactlyElementsOf(expectedItemList);
    }
}
