package bankapp.progetto20242025piragine.util;

import java.util.concurrent.ThreadLocalRandom;

public class last4DigitsPan {

    /**
     * Generates a random 4-digit string representing
     * the last 4 digits of a credit card PAN.
     * For testing/demo purposes only.
     */
    public static String generateLastFourDigits() {
        int number = ThreadLocalRandom.current().nextInt(0, 10000);
        return String.format("%04d", number);
    }


}
