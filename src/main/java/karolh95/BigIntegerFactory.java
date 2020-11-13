package karolh95;

import java.math.BigInteger;
import java.util.Random;

public class BigIntegerFactory {

    private static final Random random = new Random();

    public static BigInteger getRandom(int bound) {
        return new BigInteger(bound, random);
    }

    public static BigInteger getPrime(int bitLength) {
        return BigInteger.probablePrime(bitLength, random);
    }
}
