package karolh95;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.function.Function;

import static karolh95.BigIntegerFactory.getPrime;

public final class ShamirSecretSharing {

    private static final int DEFAULT_SECRET_BIT_LENGTH = 2048;
    private static final int P_ADDITIONAL_LENGTH = 512;

    private final BigInteger p;
    private final RandomPolynomial polynomial;
    private final int threshold;

    public ShamirSecretSharing(int threshold) {
        this(threshold, DEFAULT_SECRET_BIT_LENGTH);
    }

    public ShamirSecretSharing(int threshold, int secretBitLength) {

        this(threshold, getPrime(secretBitLength));
    }

    public ShamirSecretSharing(int threshold, BigInteger secret) {

        this(threshold, secret, getPrime(secret.bitLength() + P_ADDITIONAL_LENGTH));
    }

    public ShamirSecretSharing(int threshold, BigInteger secret, BigInteger p) {

        this.threshold = threshold;
        this.p = p;

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

        if (keys.length < threshold)
            throw new IllegalArgumentException("Keys number should be greater or equal to threshold");

        Share[] shares = new Share[keys.length];

        for (int i = 0; i < keys.length; i++) {
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
                .threshold(shares.length)
                .build();

        return interpolation.solve(BigInteger.ZERO);
    }

    private BigInteger[] map(Share[] shares, Function<Share, BigInteger> supplier) {
        return Arrays.stream(shares).map(supplier).toArray(BigInteger[]::new);
    }
}
