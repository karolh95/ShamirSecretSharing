package karolh95.parameters;

import karolh95.adapters.CommandLineAdapter;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

        if (filename == null) {
            System.out.println("Input file name is null");
            return;
        }

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
            System.out.println("File: '" + filename + "' does not exist.");

        } catch (NoSuchElementException e) {
            System.out.println("Incorrect file format");
            System.out.println("Expected:");
            System.out.println("<prime>");
            System.out.println("<key1> <shadow1>");
            System.out.println("<key2> <shadow2>");
            System.out.println("...");
            System.out.println("<keyN> <shadowN>");
        }
    }
}
