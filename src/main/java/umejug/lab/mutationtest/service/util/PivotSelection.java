package umejug.lab.mutationtest.service.util;

import java.util.Comparator;

public final class PivotSelection {

    private static final int LONG_ARRAY = 40;

    static <T> T selectPivotAsPseudoMedianOf9(T[] a, Comparator<? super T> comparator, int offset, int length) {
        int low = offset;
        int mid = offset + length / 2;
        int high = offset + length - 1;

        if (length >= LONG_ARRAY) {
            int step = length / 8;
            low = Median.medianOf3(a, comparator, low, low + step, low + step * 2);
            mid = Median.medianOf3(a, comparator, mid - step, mid, mid + step);
            high = Median.medianOf3(a, comparator, high - step * 2, high - step, high);
        }

        mid = Median.medianOf3(a, comparator, low, mid, high);

        return a[mid];
    }
}
