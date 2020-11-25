package karolh95.strategy;

import karolh95.RandomPolynomial;
import karolh95.Share;
import karolh95.adapters.CommandLineAdapter;
import karolh95.parameters.ShareSecretParameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;

public class ShareSecretBehavior implements Behavior {

    private final ShareSecretParameters parameters;

    public ShareSecretBehavior(CommandLineAdapter adapter) {
        parameters = new ShareSecretParameters(adapter);
    }

    @Override
    public void run() {

        RandomPolynomial polynomial = new RandomPolynomial(parameters.getP(), parameters.getThreshold());
        polynomial.setCoefficient(0, parameters.getSecret());

        Share[] shares = new Share[parameters.getShares()];

        for (int i = 0; i < parameters.getShares(); i++) {

            BigInteger key = BigInteger.valueOf(i + 1);
            BigInteger shadow = polynomial.apply(key);

            shares[i] = new Share(key, shadow);
        }

        write(shares);
    }

    private void write(Share[] shares) {

        File file = new File(parameters.getOutputFileName());

        try {
            PrintStream printer = new PrintStream(file);

            printer.println(parameters.getP());

            for (Share share : shares) {
                printer.println(share);
            }

            printer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
