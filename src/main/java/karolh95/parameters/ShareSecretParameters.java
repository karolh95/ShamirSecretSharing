package karolh95.parameters;

import karolh95.adapters.CommandLineAdapter;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

@Getter
public class ShareSecretParameters {

    private final String outputFileName;
    private int threshold;
    private int shares;
    private BigInteger p;
    private BigInteger secret;

    public ShareSecretParameters(CommandLineAdapter adapter) {

        String input = adapter.getInputFileName();
        if (input != null) {
            readDataFromFile(input);
        } else {
            setParametersFromCommandLineAdapter(adapter);
        }
        outputFileName = adapter.getOutputFileName();
    }

    private void readDataFromFile(String input) {

        File file = new File(input);

        try {
            Scanner scanner = new Scanner(file);

            threshold = scanner.nextInt();
            shares = scanner.nextInt();

            String value = scanner.next();
            p = new BigInteger(value);

            value = scanner.next();
            secret = new BigInteger(value);

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setParametersFromCommandLineAdapter(CommandLineAdapter adapter) {

        threshold = adapter.getThreshold();
        shares = adapter.getShares();
        p = adapter.getPrime();
        secret = adapter.getSecret();
    }
}
