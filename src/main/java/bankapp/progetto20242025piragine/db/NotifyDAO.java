package bankapp.progetto20242025piragine.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotifyDAO {

    // 🔹 Inserisce una notifica
    public static boolean insertNotify(Notify n) throws SQLException {
        String sql = """
            INSERT INTO Notify
            (user_id, id_transaction, id_friend_request, message, read, data_creation)
            VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)
            """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (n.getUserId() != null)
                stmt.setInt(1, n.getUserId());
            else
                stmt.setNull(1, Types.INTEGER);

            if (n.getIdTransaction() != null)
                stmt.setInt(2, n.getIdTransaction());
            else
                stmt.setNull(2, Types.INTEGER);

            if (n.getIdFriendRequest() != null)
                stmt.setInt(3, n.getIdFriendRequest());
            else
                stmt.setNull(3, Types.INTEGER);


            stmt.setString(5, n.getMessage());
            stmt.setBoolean(6, n.isRead());

            int rows = stmt.executeUpdate();
            if (rows == 0) return false;

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    n.setIdNotify(keys.getInt(1));
                }
            }
            return true;
        }
    }

    // 🔹 Recupera notifiche di un utente
    public static List<Notify> getNotifyByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM Notify WHERE user_id = ? ORDER BY data_creation DESC";
        List<Notify> list = new ArrayList<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    // 🔹 Segna notifica come letta
    public static boolean markAsRead(int idNotify) throws SQLException {
        String sql = "UPDATE Notify SET read = 1 WHERE id_notify = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idNotify);
            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Cancella notifica
    public static boolean deleteNotify(int idNotify) throws SQLException {
        String sql = "DELETE FROM Notify WHERE id_notify = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idNotify);
            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Mapping ResultSet → Notify
    private static Notify mapRow(ResultSet rs) throws SQLException {
        Notify n = new Notify();
        n.setIdNotify(rs.getInt("id_notify"));
        n.setUserId((Integer) rs.getObject("user_id"));
        n.setIdTransaction((Integer) rs.getObject("id_transaction"));
        n.setIdFriendRequest((Integer) rs.getObject("id_friend_request"));
        n.setMessage(rs.getString("message"));
        n.setRead(rs.getBoolean("read"));
        n.setDataCreation(rs.getTimestamp("data_creation"));
        return n;
    }
}
