package karolh95.strategy;

import karolh95.options.OptionsFactory;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class DefaultBehavior implements Behavior {

    private static final String CMD_LINE_SYNTAX = "shamirSecretSharing";

    @Override
    public void run() {

        Options options = OptionsFactory.getOptions();
        HelpFormatter formatter = new HelpFormatter();

        formatter.printHelp(CMD_LINE_SYNTAX, options, true);
    }
}
