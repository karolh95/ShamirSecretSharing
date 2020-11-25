package karolh95;

import karolh95.shamir.SecretReconstruction;
import karolh95.shamir.SecretSharing;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class ShamirSecretSharingTest {

    private static List<Arguments> source() {

        BigInteger secret = BigIntegerFactory.getPrime(2048);
        BigInteger p = BigIntegerFactory.getPrime(2560);

        List<Arguments> arguments = new ArrayList<>();

        for (int threshold = 2; threshold <= 10; threshold++) {
            for (int shadows = threshold; shadows <= 10; shadows++) {
                arguments.add(Arguments.of(secret, p, threshold, shadows));
            }
        }

        return arguments;
    }

    @ParameterizedTest(name = "#{index} - threshold: {2}, shadows: {3}")
    @MethodSource("source")
    public void testShamirSecretSharing(BigInteger secret, BigInteger p, int threshold, int shadows) {

        SecretSharing secretSharing = SecretSharing.builder()
                .p(p)
                .secret(secret)
                .sharesNumber(shadows)
                .threshold(threshold)
                .build();

        Share[] shares = secretSharing.shareSecret();

        Iterator<int[]> combinationsIterator = CombinatoricsUtils.combinationsIterator(shadows, threshold);

        while (combinationsIterator.hasNext()) {

            Share[] sharesCombination = getCombination(shares, combinationsIterator.next());

            BigInteger[] inputs = mapToArray(sharesCombination, Share::getKey);
            BigInteger[] outputs = mapToArray(sharesCombination, Share::getShadow);

            SecretReconstruction reconstruction = SecretReconstruction.builder()
                    .p(p)
                    .threshold(sharesCombination.length)
                    .inputs(inputs)
                    .outputs(outputs)
                    .build();

            BigInteger result = reconstruction.getSecret();

            Assertions.assertEquals(secret, result, "Every combinations of shares should be correct");
        }
    }

    private Share[] getCombination(Share[] shares, int[] numbers) {

        Share[] combination = new Share[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            combination[i] = shares[numbers[i]];
        }

        return combination;
    }

    private BigInteger[] mapToArray(Share[] shares, Function<Share, BigInteger> function) {

        return Arrays.stream(shares).map(function).toArray(BigInteger[]::new);
    }
}