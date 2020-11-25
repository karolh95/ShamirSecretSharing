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
        validateParameters();
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

    private void validateParameters() {
        if (threshold < 2)
            throw new IllegalArgumentException("Threshold should be >=2");

        if (shares < 2)
            throw new IllegalArgumentException("Shares number should be ");

        if (shares < threshold)
            throw new IllegalArgumentException("Shares number should be >= threshold");

        if (p.compareTo(BigInteger.ONE) <= 0)
            throw new IllegalArgumentException("Prime should be > 0");

        if (secret.compareTo(BigInteger.ONE) <= 0)
            throw new IllegalArgumentException("Secret should be > 0");

        if (p.compareTo(secret) <= 0)
            throw new IllegalArgumentException("Prime should be > than secret");
    }
}
