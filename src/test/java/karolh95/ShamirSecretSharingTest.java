package karolh95;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

        ShamirSecretSharing sss = new ShamirSecretSharing(threshold, secret, p);

        Share[] shares = sss.shareSecret(shadows);

        Iterator<int[]> combinationsIterator = CombinatoricsUtils.combinationsIterator(shadows, threshold);

        while (combinationsIterator.hasNext()) {

            Share[] sharesCombination = getCombination(shares, combinationsIterator.next());

            BigInteger result = sss.reconstruction(sharesCombination);

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

//        String secret = "85583293427518763932407382077233708608907744591022138463981786212146730513297";
}