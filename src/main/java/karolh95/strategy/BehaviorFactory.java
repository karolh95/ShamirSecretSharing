package karolh95.strategy;

import karolh95.adapters.CommandLineAdapter;
import karolh95.options.OptionsFactory;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class BehaviorFactory {

    public static Behavior getBehavior(String[] args) {

        Options options = OptionsFactory.getOptions();
        CommandLineParser parser = new BasicParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return new DefaultBehavior();
        }

        CommandLineAdapter adapter = new CommandLineAdapter(cmd);

        if (adapter.isHelpEnabled())
            return new DefaultBehavior();

        return adapter.isReconstruction()
                ? new SecretReconstructionBehavior(adapter)
                : new ShareSecretBehavior(adapter);
    }
}
