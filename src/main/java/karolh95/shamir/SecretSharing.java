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
}
