package karolh95.options;

import org.apache.commons.cli.Option;

public class ThresholdOption extends Option {

    public static final String longOpt = "threshold";
    private static final String opt = "t";
    private static final Boolean hasArg = true;
    private static final String description = "threshold, minimum number of shadows required to reconstruct the secret";

    public ThresholdOption() {
        super(opt, longOpt, hasArg, description);
    }
}
