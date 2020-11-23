package karolh95.options;

import org.apache.commons.cli.Options;

public class CustomOptions extends Options {

    public CustomOptions() {

        addOption(new ReconstructionOption());
        addOption(new InputFileOption());
        addOption(new OutputFileOption());
        addOption(new PrimeOption());
        addOption(new PrimeBitLengthOption());
        addOption(new SecretOption());
        addOption(new SecretBitLengthOption());
        addOption(new ShadowsOption());
        addOption(new ThresholdOption());
    }
}
