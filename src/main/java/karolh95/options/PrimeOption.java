package karolh95.options;

import org.apache.commons.cli.Option;

public class PrimeOption extends Option {

    public static final String longOpt = "prime";
    private static final String opt = "p";
    private static final Boolean hasArg = true;
    private static final String description = "prime number in hexadecimal format, greater than secret";

    public PrimeOption() {
        super(opt, longOpt, hasArg, description);
    }
}
