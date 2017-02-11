package umejug.lab.mutationtest.service.util;

import java.util.Comparator;

public final class Median {

    private Median() {
        // prevent instantiation and sub-classing
    }

    public static <T> int medianOf3(T[] a, Comparator<? super T> comparator, int i, int j, int k) {
        return comparator.compare(a[i], a[j]) < 0
                ? comparator.compare(a[j], a[k]) < 0 ? j : comparator.compare(a[i], a[k]) < 0 ? k : i
                : comparator.compare(a[i], a[k]) < 0 ? i : comparator.compare(a[j], a[k]) < 0 ? k : j;
    }
}
