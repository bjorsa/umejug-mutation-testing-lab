package umejug.lab.mutationtest.service.util;

import java.util.Comparator;

public final class InsertionSort {

    private InsertionSort() {
        // prevent instantiation and sub-classing
    }

    public static <T> void sort(T[] a, Comparator<? super T> comparator, int offset, int length) {
        for (int i = offset + 1, e = offset + length; i < e; ++i) {
            T x = a[i];
            int j = i;
            while (j > 0 && comparator.compare(a[j - 1], x) > 0) {
                a[j] = a[j - 1];
                --j;
            }
            a[j] = x;
        }
    }
}
