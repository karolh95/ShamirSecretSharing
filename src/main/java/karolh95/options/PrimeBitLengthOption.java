package karolh95.options;

import org.apache.commons.cli.Option;

public class PrimeBitLengthOption extends Option {

    public static final String longOpt = "primeBitLength";
    private static final String opt = "z";
    private static final Boolean hasArg = true;
    private static final String description = "bit length of the prime, greater than secret";

    public PrimeBitLengthOption() {
        super(opt, longOpt, hasArg, description);
    }
}
