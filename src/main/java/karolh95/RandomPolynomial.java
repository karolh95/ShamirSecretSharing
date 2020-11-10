package karolh95;

import java.math.BigInteger;
import java.util.Random;
import java.util.function.Function;

public class RandomPolynomial implements Function<BigInteger, BigInteger> {

    private final BigInteger p;
    private final BigInteger[] coefficients;

    public RandomPolynomial(BigInteger p, int threshold) {

        Random random = new Random();

        this.p = p;
        this.coefficients = new BigInteger[threshold];

        int numBits = p.bitCount();
        for (int i = 0; i < threshold; i++) {
            coefficients[i] = new BigInteger(numBits, random);
        }
    }

    public void setCoefficient(int i, BigInteger value) {
        this.coefficients[i] = value;
    }

    @Override
    public BigInteger apply(BigInteger x) {
        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < coefficients.length; i++) {
            result = result.add(coefficients[i].multiply(x.pow(i)));
        }
        return result.mod(p);
    }
}
