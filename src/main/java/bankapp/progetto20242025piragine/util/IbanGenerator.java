package bankapp.progetto20242025piragine.util;

import java.math.BigInteger;
import java.util.Random;

public class IbanGenerator {

    private static final Random random = new Random();

    public static String generateItalianIban() {
        String countryCode = "IT";
        String cin = randomLetter();
        String abi = randomDigits(5);
        String cab = randomDigits(5);
        String accountNumber = randomDigits(12);

        String ibanWithoutCheck = countryCode + "00" + cin + abi + cab + accountNumber;

        int checkDigits = calculateCheckDigits(ibanWithoutCheck);

        return countryCode + String.format("%02d", checkDigits)
                + cin + abi + cab + accountNumber;
    }

    private static int calculateCheckDigits(String iban) {
        String rearranged = iban.substring(4) + iban.substring(0, 4);
        String numeric = rearranged
                .replace("A", "10").replace("B", "11").replace("C", "12")
                .replace("D", "13").replace("E", "14").replace("F", "15")
                .replace("G", "16").replace("H", "17").replace("I", "18")
                .replace("J", "19").replace("K", "20").replace("L", "21")
                .replace("M", "22").replace("N", "23").replace("O", "24")
                .replace("P", "25").replace("Q", "26").replace("R", "27")
                .replace("S", "28").replace("T", "29").replace("U", "30")
                .replace("V", "31").replace("W", "32").replace("X", "33")
                .replace("Y", "34").replace("Z", "35");

        BigInteger number = new BigInteger(numeric);
        int mod = number.mod(BigInteger.valueOf(97)).intValue();

        return 98 - mod;
    }

    private static String randomDigits(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private static String randomLetter() {
        return String.valueOf((char) ('A' + random.nextInt(26)));
    }
}
