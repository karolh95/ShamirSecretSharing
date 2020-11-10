package karolh95;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

public final class ShamirSecretSharing {

    private static final int DEFAULT_SECRET_BIT_LENGTH = 2048;
    private static final int P_ADDITIONAL_LENGTH = 512;

    private final BigInteger secret;
    private final BigInteger p;
    private final RandomPolynomial polynomial;

    private final int threshold;

    public ShamirSecretSharing(int threshold) {
        this(threshold, DEFAULT_SECRET_BIT_LENGTH);
    }

    public ShamirSecretSharing(int threshold, int secretBitLength) {

        Random random = new Random();

        this.threshold = threshold;

        secret = BigInteger.probablePrime(secretBitLength, random);
        p = BigInteger.probablePrime(secretBitLength + P_ADDITIONAL_LENGTH, random);

        polynomial = new RandomPolynomial(p, threshold);
        polynomial.setCoefficient(0, secret);
    }

    public Share[] shareSecret(int shadows) {

        if (shadows < threshold)
            throw new IllegalArgumentException("Shadows number should be greater or equal to threshold");

        BigInteger[] x = new BigInteger[shadows];

        for (int i = 0; i < shadows; i++) {
            x[i] = BigInteger.valueOf(i + 1);
        }

        return shareSecret(x);
    }

    public Share[] shareSecret(BigInteger[] keys) {

        Share[] shares = new Share[threshold];

        for (int i = 0; i < threshold; i++) {
            BigInteger shadow = polynomial.apply(keys[i]);
            shares[i] = new Share(keys[i], shadow);
        }

        return shares;
    }

    public BigInteger reconstruction(Share[] shares) {

        BigInteger[] keys = map(shares, Share::getKey);
        BigInteger[] shadows = map(shares, Share::getShadow);

        LagrangeInterpolation interpolation = LagrangeInterpolation.builder()
                .p(p)
                .inputs(keys)
                .outputs(shadows)
                .threshold(threshold)
                .build();

        BigInteger result = interpolation.solve(BigInteger.ZERO);

        if (result.equals(secret)) {
            System.out.println("Secret reconstructed:");
            System.out.println(secret);
            return result;
        } else {
            System.err.println("Error during reconstruction");
            return BigInteger.ZERO;
        }
    }

    private BigInteger[] map(Share[] shares, Function<Share, BigInteger> supplier) {
        return Arrays.stream(shares).map(supplier).toArray(BigInteger[]::new);
    }
}
