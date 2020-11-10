package karolh95;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;

@Builder
@RequiredArgsConstructor
public class LagrangeInterpolation {

    private final BigInteger p;
    private final int threshold;
    private final BigInteger[] inputs;
    private final BigInteger[] outputs;

    public BigInteger solve(BigInteger x) {

        BigInteger result = BigInteger.ZERO;

        for (int s = 0; s < threshold; s++) {

            BigInteger xs = inputs[s];
            BigInteger tmp = outputs[s];

            for (int j = 0; j < threshold; j++) {

                if (s == j) continue;

                BigInteger xj = inputs[j];

                BigInteger q1 = x.subtract(xj).mod(p);
                BigInteger q2 = xs.subtract(xj).modInverse(p);

                tmp = tmp.multiply(q1.multiply(q2));
            }
            result = result.add(tmp);
        }
        return result.mod(p);
    }
}
