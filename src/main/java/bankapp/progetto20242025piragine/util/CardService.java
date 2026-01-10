package bankapp.progetto20242025piragine.util;

import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.db.CardDAO;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class CardService {

    /**
     * Generates a random 4-digit string representing
     * the last 4 digits of a credit card PAN.
     * For testing/demo purposes only.
     */
    private static String generateLastFourDigits() {
        int number = ThreadLocalRandom.current().nextInt(0, 10000);
        return String.format("%04d", number);
    }

    public static Card createCard(
            int userId,
            int idAccount,
            String nickname,
            String colour,
            BigDecimal spendingLimit
    ) throws Exception {

        // Generate the last 4 digits of the PAN
        String panLast4 = generateLastFourDigits();

        // Set the card expiry date to 3 years from now
        LocalDate expiry = LocalDate.now().plusYears(3);

        // Create the card object
        Card card = new Card(
                0,                     // id (auto-generated in DB)
                userId,
                idAccount,
                panLast4,
                Date.valueOf(expiry),
                new Timestamp(System.currentTimeMillis()),
                nickname,
                colour,
                false,                 // blocked
                spendingLimit,
                true                   // active
        );

        // Insert the card into the database
        CardDAO.insertCard(card);

        return card;
    }
}
