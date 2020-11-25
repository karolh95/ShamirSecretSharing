package karolh95.shamir;

import karolh95.LagrangeInterpolation;
import lombok.Builder;

import java.math.BigInteger;

@Builder
public class SecretReconstruction {

    private final BigInteger p;
    private final int threshold;
    private final BigInteger[] inputs;
    private final BigInteger[] outputs;

    public BigInteger getSecret() {

        LagrangeInterpolation interpolation = LagrangeInterpolation.builder()
                .p(p)
                .threshold(threshold)
                .inputs(inputs)
                .outputs(outputs)
                .build();
        return interpolation.solve(BigInteger.ZERO);
    }
}
