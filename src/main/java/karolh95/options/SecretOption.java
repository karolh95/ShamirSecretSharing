package karolh95.options;

import org.apache.commons.cli.Option;

public class SecretOption extends Option {

    public static final String longOpt = "secret";
    private static final String opt = "s";
    private static final Boolean hasArg = true;
    private static final String description = "secret number in hexadecimal format";

    public SecretOption() {
        super(opt, longOpt, hasArg, description);
    }
}
