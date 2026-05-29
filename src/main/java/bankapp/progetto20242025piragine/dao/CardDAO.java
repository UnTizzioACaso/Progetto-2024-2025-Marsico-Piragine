package bankapp.progetto20242025piragine.dao;


import bankapp.progetto20242025piragine.db.DataSourceProvider;
import bankapp.progetto20242025piragine.model.Card;

import java.sql.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CardDAO {
    public static List<Card> getCardsByUserId(int userId)  {
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
                    card.setColor(rs.getString("color"));
                    card.setFavourite(rs.getBoolean("favourite"));
                    card.setSpendingLimit(BigDecimal.valueOf(rs.getLong("spending_limit"), 2));
                    card.setStatus(rs.getBoolean("status"));

                    cards.add(card);
                }
            }
        } catch (Exception e) {
            System.err.println("Error during get card by user id: " + e.getMessage());
            e.printStackTrace();
        }
        return cards;
    }

    public static BigDecimal updateCardSpendingLimit(int cardId, BigDecimal spendingLimit)  {
        String sql = "UPDATE Card SET spending_limit = ? WHERE id_card = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, spendingLimit.movePointRight(2).longValueExact());
            stmt.setInt(2, cardId);
            stmt.executeUpdate();
            return spendingLimit;
        } catch (SQLException e) {
            System.err.println("Error during update card limit: " + e.getMessage());
            e.printStackTrace();
            return spendingLimit;
        }
    }

    public static Boolean updateCardFavourite(int cardId, boolean favourite) throws SQLException {
        String sql = "UPDATE Card SET favourite = ? WHERE id_card = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, favourite);
            stmt.setInt(2, cardId);
            stmt.executeUpdate();
            return favourite;
        } catch (SQLException e) {
            System.err.println("Error during update card favourite: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean updateCardStatus(int cardId, boolean status) throws SQLException {
        String sql = "UPDATE Card SET status = ? WHERE id_card = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, status);
            stmt.setInt(2, cardId);
            stmt.executeUpdate();
            return status;
        } catch (SQLException e) {
            System.err.println("Error during update card status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
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
                card.setColor(rs.getString("color"));
                card.setFavourite(rs.getBoolean("favourite"));
                card.setSpendingLimit(BigDecimal.valueOf(rs.getLong("spending_limit"), 2));
                card.setStatus(rs.getBoolean("status"));

                return card;
            }
        } catch (Exception e) {
            System.err.println("Error during get card by id: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteCard(int cardId) throws SQLException {
        String sql = "DELETE FROM Card WHERE id_card = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cardId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error during delete card: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static boolean insertCard(Card card) throws SQLException {
        String sql = "INSERT INTO Card (user_id, id_account, pan_last4, expired, nickname, color, favourite, spending_limit, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, card.getUserId());
            stmt.setInt(2, card.getIdAccount());
            stmt.setString(3, card.getPanLast4());
            stmt.setDate(4, card.getExpired());
            stmt.setString(5, card.getNickname());
            stmt.setString(6, card.getColor());
            stmt.setBoolean(7, card.isFavourite());
            stmt.setLong(8, card.getSpendingLimit().movePointRight(2).longValueExact());
            stmt.setBoolean(9, card.isStatus());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) return false;

            return true;
        } catch (Exception e) {
            System.err.println("Error during insert card by id: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
