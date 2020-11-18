package karolh95.options;

import org.apache.commons.cli.Option;

public class SecretOption extends Option {

    private static final String opt = "k";
    public static final String longOpt = "secret";
    private static final Boolean hasArg = true;
    private static final String description = "secret, prime number";

    public SecretOption() {
        super(opt, longOpt, hasArg, description);
    }
}
