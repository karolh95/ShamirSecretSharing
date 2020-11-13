package karolh95;

import java.math.BigInteger;
import java.util.function.Function;

import static karolh95.BigIntegerFactory.getRandom;

public class RandomPolynomial implements Function<BigInteger, BigInteger> {

    private final BigInteger p;
    private final BigInteger[] coefficients;

    public RandomPolynomial(BigInteger p, int threshold) {

        this.p = p;
        this.coefficients = new BigInteger[threshold];

        int numBits = p.bitCount();
        for (int i = 0; i < threshold; i++) {
            coefficients[i] = getRandom(numBits);
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
