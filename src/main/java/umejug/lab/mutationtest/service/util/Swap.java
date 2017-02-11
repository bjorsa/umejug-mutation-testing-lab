package umejug.lab.mutationtest.service.util;

public final class Swap {

    public static void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void rangeSwap(Object[] a, int i, int j, int length) {
        for (int k = 0; k < length; ++k) {
            swap(a, i + k, j + k);
        }
    }
}
