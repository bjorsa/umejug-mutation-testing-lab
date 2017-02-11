package umejug.lab.mutationtest.service.util;

import java.util.Comparator;

public final class PartialQuickSort {

    private PartialQuickSort() {
        // prevent instantiation and sub-classing
    }

    private static final int SHORT_ARRAY = 7;

    public static <T> void sort(T[] a, Comparator<? super T> comparator, int offset, int length, int relevantOffset, int relevantLength) {
        if (offset + length <= relevantOffset || relevantOffset + relevantLength <= offset) {
            return;
        }

        if (length <= SHORT_ARRAY) {
            InsertionSort.sort(a, comparator, offset, length);
            return;
        }

        T pivot = PivotSelection.selectPivotAsPseudoMedianOf9(a, comparator, offset, length);

        int end = offset + length;

        int i = offset;
        int pi = i;
        int j = end;
        int pj = j;

        while (i < j) {
            int comparisonResult = comparator.compare(a[i], pivot);
            if (comparisonResult <= 0) {
                if (comparisonResult == 0) {
                    Swap.swap(a, i, pi++);
                }
                ++i;
                continue;
            }

            while (--j > i) {
                comparisonResult = comparator.compare(a[j], pivot);
                if (comparisonResult < 0) {
                    break;
                }
                if (comparisonResult == 0) {
                    Swap.swap(a, j, --pj);
                }
            }

            if (i == j) {
                break;
            }

            Swap.swap(a, i++, j);
        }

        // | k in [offset .. pi): a[k] == pivot | k in [pi .. i): a[k] < pivot | k in [i .. pj): a[k] > pivot | k in [pj .. end): a[k] == pivot |
        int swapLow = Math.min(pi - offset, i - pi);
        Swap.rangeSwap(a, offset, i - swapLow, swapLow);
        int swapHigh = Math.min(pj - i, end - pj);
        Swap.rangeSwap(a, i, end - swapHigh, swapHigh);

        sort(a, comparator, offset, i - pi, relevantOffset, relevantLength);
        sort(a, comparator, i + (end - pj), pj - i, relevantOffset, relevantLength);
        // completely sorted
    }
}
