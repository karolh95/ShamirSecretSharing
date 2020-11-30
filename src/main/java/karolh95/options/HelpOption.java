package karolh95.options;

import org.apache.commons.cli.Option;

public class HelpOption extends Option {

    public static final String longOpt = "help";
    private static final String opt = "h";
    private static final Boolean hasArg = false;
    private static final String description = "prints this message";

    public HelpOption(){
        super(opt, longOpt, hasArg, description);
    }
}
