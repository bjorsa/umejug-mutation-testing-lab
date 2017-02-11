package umejug.lab.mutationtest.service.util;

import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class PartialQuickSortTest {

    @Test
    public void shouldPreserveTheOrderOfASortedArray() {
        Integer[] a = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        PartialQuickSort.sort(a, Comparator.naturalOrder(), 0, 10, 0, 10);
        assertThat(a).isEqualTo(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
    }

    @Test
    public void shouldCorrectlySortTheRelevantNumbers() {
        Integer[] a = new Integer[] {2, 7, 1, 8, 6, 5, 9, 10, 3, 4};
        PartialQuickSort.sort(a, Comparator.naturalOrder(), 0, 10, 1, 2);
        assertThat(a[1]).isEqualTo(2);
        assertThat(a[2]).isEqualTo(3);
    }
}
