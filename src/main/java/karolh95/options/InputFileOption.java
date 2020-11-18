package karolh95.options;

import org.apache.commons.cli.Option;

public class InputFileOption extends Option {

    private static final String opt = "i";
    public static final String longOpt = "input";
    private static final Boolean hasArg = true;
    private static final String description = "input file path";

    public InputFileOption() {
        super(opt, longOpt, hasArg, description);
    }
}
