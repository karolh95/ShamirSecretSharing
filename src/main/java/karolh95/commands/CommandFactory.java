package karolh95.commands;

import karolh95.options.ReconstructionOption;
import org.apache.commons.cli.CommandLine;

public class CommandFactory {

    public static Command getCommand(CommandLine cmd) {

        if (cmd == null) {
            return new NullCommand();
        }

        if (cmd.hasOption(ReconstructionOption.longOpt)) {
            return new SecretReconstructionCommand(cmd);
        } else {
            return new ShareSecretCommand(cmd);
        }
    }
}
