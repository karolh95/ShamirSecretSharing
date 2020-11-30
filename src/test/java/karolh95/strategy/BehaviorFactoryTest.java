package karolh95.strategy;

import karolh95.options.HelpOption;
import karolh95.options.ReconstructionOption;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BehaviorFactoryTest {

    private static final String MESSAGE = "Factory should return instance of ";

    @Test
    public void getDefaultBehaviorTest() {

        String[] args = {"--" + HelpOption.longOpt};

        Behavior behavior = BehaviorFactory.getBehavior(args);

        assertTrue(behavior instanceof DefaultBehavior, MESSAGE + "DefaultBehavior");
    }

    @Test
    public void getShareSecretBehaviorTest() {

        String[] args = {};

        Behavior behavior = BehaviorFactory.getBehavior(args);

        assertTrue(behavior instanceof ShareSecretBehavior, MESSAGE + "ShareSecretBehavior");
    }

    @Test
    public void getSecretReconstructionBehaviorTest() {

        String[] args = {"--" + ReconstructionOption.longOpt};

        Behavior behavior = BehaviorFactory.getBehavior(args);

        assertTrue(behavior instanceof SecretReconstructionBehavior, MESSAGE + "SecretReconstructionBehavior");
    }
}