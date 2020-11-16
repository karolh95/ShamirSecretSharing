package karolh95;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class LagrangeInterpolationTest {

    @Test
    public void testSolve() {

        BigInteger secret = BigInteger.valueOf(11);

        BigInteger p = BigInteger.valueOf(13);

        int threshold = 3;

        BigInteger[] inputs = {
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3),
                BigInteger.valueOf(4),
                BigInteger.valueOf(5)
        };
        BigInteger[] outputs = {
                BigInteger.valueOf(0),
                BigInteger.valueOf(3),
                BigInteger.valueOf(7),
                BigInteger.valueOf(12),
                BigInteger.valueOf(5)
        };

        LagrangeInterpolation interpolation = LagrangeInterpolation.builder()
                .p(p)
                .threshold(threshold)
                .inputs(inputs)
                .outputs(outputs)
                .build();

        BigInteger a0 = interpolation.solve(BigInteger.ZERO);

        Assertions.assertEquals(secret, a0, "a0 should be equal to secret");
    }
}