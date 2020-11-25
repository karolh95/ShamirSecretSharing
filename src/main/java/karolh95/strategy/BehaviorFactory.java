package karolh95.strategy;

import karolh95.adapters.CommandLineAdapter;
import org.apache.commons.cli.CommandLine;

public class BehaviorFactory {

    public static Behavior getBehavior(CommandLine cmd) {

        if (cmd == null) return new DefaultBehavior();

        CommandLineAdapter adapter = new CommandLineAdapter(cmd);

        if (adapter.isReconstruction()) {
            return new SecretReconstructionBehavior(adapter);

        } else {
            return new ShareSecretBehavior(adapter);
        }
    }
}
