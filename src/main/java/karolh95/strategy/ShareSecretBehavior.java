package karolh95.strategy;

import karolh95.Share;
import karolh95.adapters.CommandLineAdapter;
import karolh95.parameters.ShareSecretParameters;
import karolh95.shamir.SecretSharing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ShareSecretBehavior implements Behavior {

    private final ShareSecretParameters parameters;

    public ShareSecretBehavior(CommandLineAdapter adapter) {
        parameters = new ShareSecretParameters(adapter);
    }

    @Override
    public void run() {

        SecretSharing sharing = SecretSharing.builder()
                .p(parameters.getP())
                .secret(parameters.getSecret())
                .sharesNumber(parameters.getShares())
                .threshold(parameters.getThreshold())
                .build();

        Share[] shares = sharing.shareSecret();

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
