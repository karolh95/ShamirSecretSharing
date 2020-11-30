package karolh95.shamir;

import karolh95.RandomPolynomial;
import karolh95.Share;
import lombok.Builder;

import java.math.BigInteger;

@Builder
public class SecretSharing {

    private final BigInteger p;
    private final BigInteger secret;
    private final int threshold;
    private final int sharesNumber;

    public Share[] shareSecret() {

        validateParameters();

        RandomPolynomial polynomial = new RandomPolynomial(p, threshold);
        polynomial.setCoefficient(0, secret);

        Share[] shares = new Share[sharesNumber];

        for (int i = 0; i < sharesNumber; i++) {
            BigInteger key = BigInteger.valueOf(i + 1);
            BigInteger shadow = polynomial.apply(key);

            shares[i] = new Share(key, shadow);
        }

        return shares;
    }

    private void validateParameters() {

        if (threshold < 2)
            throw new IllegalArgumentException("Threshold should be >=2");

        if (sharesNumber < 2)
            throw new IllegalArgumentException("Shares number should be ");

        if (sharesNumber < threshold)
            throw new IllegalArgumentException("Shares number should be >= threshold");

        if (p.compareTo(BigInteger.ONE) <= 0)
            throw new IllegalArgumentException("Prime should be > 0");

        if (secret.compareTo(BigInteger.ONE) <= 0)
            throw new IllegalArgumentException("Secret should be > 0");

        if (p.compareTo(secret) <= 0)
            throw new IllegalArgumentException("Prime should be > than secret");
    }
}
