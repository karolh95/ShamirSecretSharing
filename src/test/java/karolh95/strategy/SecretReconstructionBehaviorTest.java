package karolh95.strategy;

import karolh95.parameters.SecretReconstructionParameters;
import karolh95.utility.StringToBigIntegerArrayConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SecretReconstructionBehaviorTest {

    @ParameterizedTest(name = "{index} Secret Reconstruction Behavior test")
    @CsvFileSource(resources = "/validReconstructionParameters.csv")
    public void secretReconstructionBehaviorTest(BigInteger p, BigInteger secret, @ConvertWith(StringToBigIntegerArrayConverter.class) BigInteger[] outputs) {

        int threshold = outputs.length;
        BigInteger[] inputs =
                IntStream.rangeClosed(1, outputs.length)
                        .mapToObj(BigInteger::valueOf)
                        .toArray(BigInteger[]::new);
        SecretReconstructionParameters parameters = mockParameters(p, inputs, outputs, threshold);
        SecretReconstructionBehavior behavior = new SecretReconstructionBehavior(parameters);

        assertDoesNotThrow(behavior::run, "Behavior should not throw any exceptions");

        File file = new File(parameters.getOutputFileName());

        assertDoesNotThrow(() -> {

            Scanner scanner = new Scanner(file);
            String number = scanner.next();
            scanner.close();
            BigInteger result = new BigInteger(number);

            assertEquals(secret, result, "The result of a reconstruction should match the Secret");
        }, "The output file should exists.");

    }

    private SecretReconstructionParameters mockParameters(BigInteger p, BigInteger[] inputs, BigInteger[] outputs, int threshold) {

        SecretReconstructionParameters parameters = mock(SecretReconstructionParameters.class);
        when(parameters.getP()).thenReturn(p);
        when(parameters.getInputs()).thenReturn(inputs);
        when(parameters.getOutputs()).thenReturn(outputs);
        when(parameters.getThreshold()).thenReturn(threshold);
        when(parameters.getOutputFileName()).thenReturn("output");

        return parameters;
    }
}