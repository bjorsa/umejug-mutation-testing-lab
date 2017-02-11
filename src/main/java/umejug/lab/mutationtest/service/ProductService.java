package umejug.lab.mutationtest.service;

import org.springframework.stereotype.Service;
import umejug.lab.mutationtest.domain.Identifier;
import umejug.lab.mutationtest.domain.shopping.product.AttributeBuilder;
import umejug.lab.mutationtest.domain.shopping.product.StockKeepingUnit;
import umejug.lab.mutationtest.repository.ProductRepository;
import umejug.lab.mutationtest.repository.StockKeepingUnitRepository;
import umejug.lab.mutationtest.representation.product.AttributeRepresentation;
import umejug.lab.mutationtest.representation.product.ProductRepresentation;
import umejug.lab.mutationtest.representation.product.builder.ProductRepresentationBuilder;
import umejug.lab.mutationtest.service.util.AttributeComparators;
import umejug.lab.mutationtest.service.util.PartialQuickSort;
import umejug.lab.mutationtest.service.util.ProductWithSKUs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ProductService {

    public ProductRepresentation findProduct(UUID productUUID) {
        return ProductRepresentationBuilder.fromProduct(ProductRepository.find(new Identifier(productUUID)));
    }

    public List<ProductRepresentation> findProductsByCategory(UUID categoryUUID) {
        return ProductRepresentationBuilder.fromProducts(ProductRepository.findByCategory(new Identifier(categoryUUID)));
    }

    public List<ProductRepresentation> listProducts(AttributeRepresentation attribute, String order, Integer page, Integer pageSize) {
        List<AttributeRepresentation> attributes = attribute == null ? Collections.emptyList() : Collections.singletonList(attribute);
        List<ProductWithSKUs> products = findProductsByAttributes(attributes);
        List<ProductWithSKUs> orderedProducts = orderProductsByAttribute(products, order, page, pageSize);

        return ProductRepresentationBuilder.fromProducts(orderedProducts.stream().map(ProductWithSKUs::getProduct).map(ProductRepository::find).collect(Collectors.toList()));
    }

    private List<ProductWithSKUs> findProductsByAttributes(List<AttributeRepresentation> attributes) {
        if (attributes == null || attributes.isEmpty()) {
            return ProductRepository.products().stream()
                    .map(p -> new ProductWithSKUs(p.getIdentifier(), StockKeepingUnitRepository.findByProduct(p.getIdentifier())))
                    .collect(Collectors.toList());
        }

        List<StockKeepingUnit> SKUs = StockKeepingUnitRepository
                .findByAttributes(
                        attributes.stream()
                                .map(AttributeBuilder::fromAttributeRepresentation)
                                .collect(Collectors.toList())
                );

        return SKUs.stream()
                .collect(Collectors.groupingBy(StockKeepingUnit::getProduct))
                .entrySet().stream()
                .map(e -> new ProductWithSKUs(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    private List<ProductWithSKUs> orderProductsByAttribute(List<ProductWithSKUs> products, String order, Integer page, Integer pageSize) {
        if (page == null || pageSize == null) {
            page = 1;
            pageSize = products.size();
        }

        if (page <= 0) {
            page = 1;
        }

        if (pageSize <= 0) {
            pageSize = 2;
        }

        if ((page - 1) * pageSize >= products.size()) {
            return Collections.emptyList();
        }

        ProductWithSKUs[] productsArray = products.toArray(new ProductWithSKUs[products.size()]);

        if (order != null && !order.isEmpty()) {
            Comparator<ProductWithSKUs> attributeComparator = AttributeComparators.getForAttributeName(order);
            PartialQuickSort.sort(productsArray, attributeComparator, 0, products.size(), (page - 1) * pageSize, pageSize);
        }

        return IntStream.range((page - 1) * pageSize, Math.min(page * pageSize, products.size()))
                .mapToObj(i -> productsArray[i])
                .collect(Collectors.toList());
    }
}
