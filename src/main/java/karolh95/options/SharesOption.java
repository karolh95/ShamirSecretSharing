package karolh95.options;

import org.apache.commons.cli.Option;

public class SharesOption extends Option {

    public static final String longOpt = "shares";
    private static final String opt = "n";
    private static final Boolean hasArg = true;
    private static final String description = "shares number, greater or equal to threshold";

    public SharesOption() {
        super(opt, longOpt, hasArg, description);
    }
}
