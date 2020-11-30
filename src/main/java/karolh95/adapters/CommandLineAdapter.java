package karolh95.adapters;

import karolh95.BigIntegerFactory;
import karolh95.options.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.cli.CommandLine;

import java.math.BigInteger;

@RequiredArgsConstructor
public class CommandLineAdapter {

    private static final String ZERO = Integer.toString(0);
    private static final String DEFAULT_OUTPUT = "output";

    private final CommandLine cmd;

    public boolean isHelpEnabled() {
        return cmd.hasOption(HelpOption.longOpt);
    }

    public boolean isReconstruction() {
        return cmd.hasOption(ReconstructionOption.longOpt);
    }

    public String getInputFileName() {

        return cmd.getOptionValue(InputFileOption.longOpt, null);
    }

    public String getOutputFileName() {

        return cmd.getOptionValue(OutputFileOption.longOpt, DEFAULT_OUTPUT);
    }

    public BigInteger getPrime() {

        int bitLength = getPrimeBitLength();

        if (cmd.hasOption(PrimeOption.longOpt)) {
            String value = cmd.getOptionValue(PrimeOption.longOpt);
            return new BigInteger(value);

        } else if (bitLength > 0) {
            return BigIntegerFactory.getPrime(bitLength);

        } else {
            return BigInteger.ZERO;
        }
    }

    public BigInteger getSecret() {

        int bitLength = getSecretBitLength();

        if (cmd.hasOption(SecretOption.longOpt)) {
            String value = cmd.getOptionValue(SecretOption.longOpt);
            return new BigInteger(value);

        } else if (bitLength > 0) {
            return BigIntegerFactory.getPrime(bitLength);

        } else {
            return BigInteger.ZERO;
        }
    }

    public int getShares() {

        return getIntValue(SharesOption.longOpt);
    }

    public int getThreshold() {

        return getIntValue(ThresholdOption.longOpt);
    }

    private int getPrimeBitLength() {

        return getIntValue(PrimeBitLengthOption.longOpt);
    }

    private int getSecretBitLength() {

        return getIntValue(SecretBitLengthOption.longOpt);
    }

    private int getIntValue(String longOpt) {

        String value = cmd.getOptionValue(longOpt, ZERO);
        return Integer.parseInt(value);
    }
}
