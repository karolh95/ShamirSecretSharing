package karolh95.options;

import org.apache.commons.cli.Option;

public class PrimeOption extends Option {

    private static final String opt = "p";
    public static final String longOpt = "prime";
    private static final Boolean hasArg = true;
    private static final String description = "prime, prime number, greater than secret";

    public PrimeOption(){
        super(opt, longOpt, hasArg, description);
    }
}
