package bankapp.progetto20242025piragine.db;


import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CardDAO {
    public static List<Card> getCardsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM Card WHERE user_id = ?";
        List<Card> cards = new ArrayList<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Card card = new Card();
                    card.setIdCard(rs.getInt("id_card"));
                    card.setUserId(rs.getInt("user_id"));
                    card.setIdAccount(rs.getInt("id_account"));
                    card.setPanLast4(rs.getString("pan_last4"));
                    card.setExpired(rs.getDate("expired"));
                    card.setCreatedAt(rs.getTimestamp("created_at"));
                    card.setNickname(rs.getString("nickname"));
                    card.setColour(rs.getString("colour"));
                    card.setFavourite(rs.getBoolean("favourite"));
                    card.setSpendingLimit(rs.getBigDecimal("spending_limit"));
                    card.setStatus(rs.getBoolean("status"));

                    cards.add(card);
                }
            }
        }
        return cards;
    }

    public static Card getCardById(int idCard) throws SQLException {
        String sql = "SELECT * FROM Card WHERE id_card = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCard);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;

                Card card = new Card();
                card.setIdCard(rs.getInt("id_card"));
                card.setUserId(rs.getInt("user_id"));
                card.setIdAccount(rs.getInt("id_account"));
                card.setPanLast4(rs.getString("pan_last4"));
                card.setExpired(rs.getDate("expired"));
                card.setCreatedAt(rs.getTimestamp("created_at"));
                card.setNickname(rs.getString("nickname"));
                card.setColour(rs.getString("colour"));
                card.setFavourite(rs.getBoolean("favourite"));
                card.setSpendingLimit(rs.getBigDecimal("spending_limit"));
                card.setStatus(rs.getBoolean("status"));

                return card;
            }
        }
    }

    // 🔹 Inserire una nuova carta
    public static boolean insertCard(Card card) throws SQLException {
        String sql = "INSERT INTO Card (user_id, id_account, pan_last4, expired, nickname, colour, favourite, spending_limit, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, card.getUserId());
            stmt.setInt(2, card.getIdAccount());
            stmt.setString(3, card.getPanLast4());
            stmt.setDate(4, card.getExpired());
            stmt.setString(5, card.getNickname());
            stmt.setString(6, card.getColour());
            stmt.setBoolean(7, card.isFavourite());
            stmt.setBigDecimal(8, card.getSpendingLimit());
            stmt.setBoolean(9, card.isStatus());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) return false;

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    card.setIdCard(generatedKeys.getInt(1)); // assegna id generato
                }
            }
            return true;
        }
    }
}
