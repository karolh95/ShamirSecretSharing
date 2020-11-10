package karolh95;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final int THRESHOLD = 8;
    private static final int SHADOWS = 16;

    public static void main(String[] args) {

        ShamirSecretSharing sss = new ShamirSecretSharing(THRESHOLD);

        Share[] shares = sss.shareSecret(SHADOWS);

        Share[] randomShares = getRandomShares(shares, THRESHOLD);

        BigInteger secret = sss.reconstruction(randomShares);
    }

    public static Share[] getRandomShares(Share[] shares, int threshold) {

        List<Share> shadow = Arrays.asList(shares);
        Collections.shuffle(shadow);

        return shadow.stream().limit(threshold).toArray(Share[]::new);
    }
}
