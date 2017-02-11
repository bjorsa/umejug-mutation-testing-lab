package umejug.lab.mutationtest.service.util;

import java.util.Comparator;

public class AttributeComparators {
    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    public static Comparator<ProductWithSKUs> getForAttributeName(String order) {
        switch (order) {
            case "size": return new SizeComparator();
            case "color": return new ColorComparator();
            default: return (o1, o2) -> 0;
        }
    }

    private static class SizeComparator implements Comparator<ProductWithSKUs> {

        @Override
        public int compare(ProductWithSKUs o1, ProductWithSKUs o2) {
            return getProductSizeOrder(o1).compareTo(getProductSizeOrder(o2));
        }

        private Integer getProductSizeOrder(ProductWithSKUs p) {
            return p.getSKUs().stream()
                    .flatMap(s -> s.getAttributes().stream())
                    .filter(a -> a.getName().getName().equals("size"))
                    .map(a -> a.getValue().getValue())
                    .map(this::getSizeOrder).min(Comparator.naturalOrder()).orElse(Integer.MAX_VALUE);
        }

        private Integer getSizeOrder(String size) {
            switch (size) {
                case "S": return 0;
                case "M": return 1;
                case "L": return 2;
                case "XL": return 3;
                case "11": return 10;
                case "11_1/2": return 11;
                case "12": return 12;
                case "12_1/2": return 13;
                case "13": return 14;
                case "13_1/2": return 15;
                default: return Integer.MAX_VALUE;
            }
        }
    }

    private static class ColorComparator implements Comparator<ProductWithSKUs> {

        @Override
        public int compare(ProductWithSKUs o1, ProductWithSKUs o2) {
            return getColor(o1).compareTo(getColor(o2));
        }

        private String getColor(ProductWithSKUs p) {
            return p.getSKUs().stream()
                    .flatMap(s -> s.getAttributes().stream())
                    .filter(a -> a.getName().getName().equals("color"))
                    .map(a -> a.getValue().getValue())
                    .min(Comparator.naturalOrder())
                    .orElse("zzzzzzzzz");
        }
    }
}
