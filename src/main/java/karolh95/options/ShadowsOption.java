package karolh95.options;

import org.apache.commons.cli.Option;

public class ShadowsOption extends Option {

    private static final String opt = "n";
    public static final String longOpt = "shadow";
    private static final Boolean hasArg = true;
    private static final String description = "shadows number, greater or equal to threshold";

    public ShadowsOption() {
        super(opt, longOpt, hasArg, description);
    }
}
