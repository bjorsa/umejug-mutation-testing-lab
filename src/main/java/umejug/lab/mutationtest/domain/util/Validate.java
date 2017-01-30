package umejug.lab.mutationtest.domain.util;

import java.util.Collection;
import java.util.function.Supplier;

public final class Validate {

    private Validate()  {
        // prevent instantiation and sub classing
    }

    public static void notNull(Object o, Supplier<NullPointerException> exceptionSupplier) {
        if (o == null) {
            throw exceptionSupplier.get();
        }
    }

    public static void positive(int n, Supplier<IllegalArgumentException> exceptionSupplier) {
        greaterThan(n, 0, exceptionSupplier);
    }

    public static void notNegative(int n, Supplier<IllegalArgumentException> exceptionSupplier) {
        greaterThanOrEqual(n, 0, exceptionSupplier);
    }

    public static void lessThan(int n, int lessThan, Supplier<IllegalArgumentException> exceptionSupplier) {
        if (n >= lessThan) {
            throw exceptionSupplier.get();
        }
    }

    public static void lessThanOrEqual(int n, int lessThanOrEqual, Supplier<IllegalArgumentException> exceptionSupplier) {
        if (n > lessThanOrEqual) {
            throw exceptionSupplier.get();
        }
    }

    public static void greaterThanOrEqual(int n, int greaterThanOrEqual, Supplier<IllegalArgumentException> exceptionSupplier) {
        lessThanOrEqual(greaterThanOrEqual, n, exceptionSupplier);
    }

    public static void greaterThan(int n, int greaterThan, Supplier<IllegalArgumentException> exceptionSupplier) {
        lessThan(greaterThan, n, exceptionSupplier);
    }

    public static void notEmpty(String s, Supplier<IllegalArgumentException> exceptionSupplier) {
        if (s.isEmpty()) {
            throw exceptionSupplier.get();
        }
    }

    public static void notEmpty(Collection<?> c, Supplier<IllegalArgumentException> exceptionSupplier) {
        if (c.isEmpty()) {
            throw exceptionSupplier.get();
        }
    }
}
