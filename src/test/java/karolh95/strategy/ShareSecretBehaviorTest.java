package karolh95.strategy;

import karolh95.adapters.CommandLineAdapter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShareSecretBehaviorTest {

    private CommandLineAdapter adapter;

    private ShareSecretBehavior behavior;

    private static List<Arguments> shareSecretBehaviorTest() {

        return List.of(
                Arguments.of(BigInteger.valueOf(13), BigInteger.valueOf(11), 3, 6),
                Arguments.of(BigInteger.valueOf(19), BigInteger.valueOf(11), 3, 6),
                Arguments.of(BigInteger.valueOf(19), BigInteger.valueOf(11), 2, 6),
                Arguments.of(BigInteger.valueOf(19), BigInteger.valueOf(11), 2, 12),
                Arguments.of(BigInteger.valueOf(23), BigInteger.valueOf(11), 4, 6),
                Arguments.of(BigInteger.valueOf(23), BigInteger.valueOf(11), 4, 6)
        );
    }

    private static List<Arguments> shareSecretBehaviorTest_illegalArguments() {

        // Illegal Arguments
        return List.of(
                // Secret > Prime
                Arguments.of(BigInteger.valueOf(11), BigInteger.valueOf(13), 3, 6),
                // Secret == Prime
                Arguments.of(BigInteger.valueOf(11), BigInteger.valueOf(11), 3, 6),
                // Threshold > Shares number
                Arguments.of(BigInteger.valueOf(19), BigInteger.valueOf(11), 4, 3),
                // Threshold == 1
                Arguments.of(BigInteger.valueOf(23), BigInteger.valueOf(11), 1, 5)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void shareSecretBehaviorTest(BigInteger prime, BigInteger secret, int threshold, int sharesNumber) {

        adapter = mockAdapter(prime, secret, threshold, sharesNumber);
        behavior = new ShareSecretBehavior(adapter);

        assertDoesNotThrow(behavior::run, "Behavior should not throw any exceptions");
    }

    @ParameterizedTest
    @MethodSource
    public void shareSecretBehaviorTest_illegalArguments(BigInteger prime, BigInteger secret, int threshold, int sharesNumber) {

        adapter = mockAdapter(prime, secret, threshold, sharesNumber);
        behavior = new ShareSecretBehavior(adapter);

        assertThrows(IllegalArgumentException.class, behavior::run, "Behavior should throw Illegal Argument Exception");

    }

    private CommandLineAdapter mockAdapter(BigInteger prime, BigInteger secret, int threshold, int sharesNumber) {

        CommandLineAdapter adapter = mock(CommandLineAdapter.class);
        when(adapter.getPrime()).thenReturn(prime);
        when(adapter.getSecret()).thenReturn(secret);
        when(adapter.getThreshold()).thenReturn(threshold);
        when(adapter.getShares()).thenReturn(sharesNumber);
        when(adapter.getOutputFileName()).thenReturn("output");

        return adapter;
    }
}