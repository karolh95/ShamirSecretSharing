package karolh95.parameters;

import karolh95.adapters.CommandLineAdapter;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
public class SecretReconstructionParameters {

    private final String outputFileName;
    private int threshold;
    private BigInteger p;
    private BigInteger[] inputs;
    private BigInteger[] outputs;

    public SecretReconstructionParameters(CommandLineAdapter adapter) {

        readFromFile(adapter.getInputFileName());
        outputFileName = adapter.getOutputFileName();
    }

    private void readFromFile(String filename) {

        File file = new File(filename);

        try {
            Scanner scanner = new Scanner(file);

            String prime = scanner.next();
            p = new BigInteger(prime);

            List<BigInteger> inputs = new ArrayList<>();
            List<BigInteger> outputs = new ArrayList<>();

            while (scanner.hasNextLine()) {

                String value = scanner.next();
                inputs.add(new BigInteger(value));

                value = scanner.next();
                outputs.add(new BigInteger(value));
            }

            this.threshold = inputs.size();
            this.inputs = inputs.toArray(BigInteger[]::new);
            this.outputs = outputs.toArray(BigInteger[]::new);

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
