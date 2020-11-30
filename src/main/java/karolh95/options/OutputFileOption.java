package karolh95.options;

import org.apache.commons.cli.Option;

public class OutputFileOption extends Option {

    public static final String longOpt = "output";
    private static final String opt = "o";
    private static final Boolean hasArg = true;
    private static final String description = "output file path";

    public OutputFileOption() {
        super(opt, longOpt, hasArg, description);
    }
}
