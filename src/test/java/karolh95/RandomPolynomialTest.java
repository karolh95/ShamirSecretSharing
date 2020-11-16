package karolh95;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class RandomPolynomialTest {

    @Test
    public void applyTest() {

        BigInteger p = BigInteger.valueOf(13);
        int threshold = 3;

        RandomPolynomial polynomial = new RandomPolynomial(p, threshold);

        polynomial.setCoefficient(0, BigInteger.valueOf(11));
        polynomial.setCoefficient(1, BigInteger.valueOf(8));
        polynomial.setCoefficient(2, BigInteger.valueOf(7));

        BigInteger[] inputs = {
                BigInteger.valueOf(1),
                BigInteger.valueOf(2),
                BigInteger.valueOf(3),
                BigInteger.valueOf(4),
                BigInteger.valueOf(5)
        };

        BigInteger[] expectedOutputs = {
                BigInteger.valueOf(0),
                BigInteger.valueOf(3),
                BigInteger.valueOf(7),
                BigInteger.valueOf(12),
                BigInteger.valueOf(5)
        };

        BigInteger[] outputs = {
                polynomial.apply(inputs[0]),
                polynomial.apply(inputs[1]),
                polynomial.apply(inputs[2]),
                polynomial.apply(inputs[3]),
                polynomial.apply(inputs[4])
        };

        Assertions.assertArrayEquals(expectedOutputs, outputs, "Output should equals to expected one");
    }
}