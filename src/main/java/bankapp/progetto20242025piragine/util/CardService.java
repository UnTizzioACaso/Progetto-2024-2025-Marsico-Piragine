package bankapp.progetto20242025piragine.util;
import bankapp.progetto20242025piragine.db.Card;
import bankapp.progetto20242025piragine.db.CardDAO;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class CardService
{

    public static Card createCard(int userId, int idAccount, String nickname, String colour, BigDecimal spendingLimit) throws Exception {


        String panLast4 = pan.substring(pan.length() - 4);

        LocalDate expiry = LocalDate.now().plusYears(3);


        Card card = new Card(0, userId, idAccount, panLast4, Date.valueOf(expiry), new Timestamp(System.currentTimeMillis()), nickname, colour,false, spendingLimit,true );

        CardDAO.insertCard(card);

        return card;
    }


    public static String getFullPan(Card card) throws Exception
    {
        return CryptoUtil.decrypt(card.getPanEncrypted());
    }

    public static String getCvv(Card card) throws Exception
    {
        return CryptoUtil.decrypt(card.getCvvEncrypted());
    }


    private static String generatePan()
    {
        SecureRandom random = new SecureRandom();
        StringBuilder pan = new StringBuilder("4"); // Visa-like

        for (int i = 0; i < 15; i++)
        {
            pan.append(random.nextInt(10));
        }
        return pan.toString();
    }

    private static String generateCvv()
    {
        SecureRandom random = new SecureRandom();
        int cvv = random.nextInt(900) + 100;
        return String.valueOf(cvv);
    }
}