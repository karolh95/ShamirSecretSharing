package karolh95.strategy;

import karolh95.parameters.SecretReconstructionParameters;
import karolh95.shamir.SecretReconstruction;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;

@RequiredArgsConstructor
public class SecretReconstructionBehavior implements Behavior {

    private final SecretReconstructionParameters parameters;

    @Override
    public void run() {

        SecretReconstruction reconstruction = SecretReconstruction.builder()
                .p(parameters.getP())
                .threshold(parameters.getThreshold())
                .inputs(parameters.getInputs())
                .outputs(parameters.getOutputs())
                .build();

        write(reconstruction.getSecret());
    }

    private void write(BigInteger secret) {

        File file = new File(parameters.getOutputFileName());
        try {
            PrintStream printer = new PrintStream(file);
            printer.println(secret);
            printer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
