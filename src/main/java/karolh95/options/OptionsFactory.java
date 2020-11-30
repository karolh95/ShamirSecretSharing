package karolh95.options;

import org.apache.commons.cli.Options;

public class OptionsFactory {

    public static Options getOptions() {

        Options options = new Options();
        options.addOption(new HelpOption());
        options.addOption(new ReconstructionOption());
        options.addOption(new InputFileOption());
        options.addOption(new OutputFileOption());
        options.addOption(new PrimeOption());
        options.addOption(new SecretOption());
        options.addOption(new SharesOption());
        options.addOption(new ThresholdOption());
        options.addOption(new PrimeBitLengthOption());
        options.addOption(new SecretBitLengthOption());
        
        return options;
    }
}
