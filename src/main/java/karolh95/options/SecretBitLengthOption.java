package karolh95.options;

import org.apache.commons.cli.Option;

public class SecretBitLengthOption extends Option {

    public static final String longOpt = "secretBitLength";
    private static final String opt = "k";
    private static final Boolean hasArg = true;
    private static final String description = "bit length of the secret";

    public SecretBitLengthOption() {
        super(opt, longOpt, hasArg, description);
    }

}
