package karolh95.options;

import org.apache.commons.cli.Option;

public class ReconstructionOption extends Option {

    public static final String longOpt = "reconstruction";
    private static final String opt = "r";
    private static final Boolean hasArg = false;
    private static final String description = "reconstruction mode";

    public ReconstructionOption() {
        super(opt, longOpt, hasArg, description);
    }
}
