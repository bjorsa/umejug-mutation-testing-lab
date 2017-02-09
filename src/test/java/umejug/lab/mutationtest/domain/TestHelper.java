package umejug.lab.mutationtest.domain;

import umejug.lab.mutationtest.domain.util.Validate;

import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

public final class TestHelper {

    private TestHelper() {
        // prevent instantiation and sub-classing
    }

    public static String randomAlphabeticalString(int n) {
        Validate.notNegative(n);
        Random rnd = new Random(UUID.randomUUID().getLeastSignificantBits());
        return IntStream.range(0, n)
                .map(i -> 'a' + rnd.nextInt('z' - 'a' + 1))
                .sequential()
                .collect(StringBuilder::new, (sb, v) -> sb.append((char) v), StringBuilder::append)
                .toString();
    }
}
