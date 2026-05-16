package bankapp.progetto20242025piragine.dao;

import bankapp.progetto20242025piragine.db.DataSourceProvider;
import bankapp.progetto20242025piragine.model.Notify;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotifyDAO {

    // 🔹 inserts notify in the db
    public static boolean insertNotify(Notify n)  {
        String sql = """
        INSERT INTO Notify
        (user_id, id_transaction, id_friend_request, message, read, data_creation)
        VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP)
        """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            if (n.getUserId() != null) stmt.setInt(1, n.getUserId());
            else stmt.setNull(1, Types.INTEGER);

            if (n.getIdTransaction() != null) stmt.setInt(2, n.getIdTransaction());
            else stmt.setNull(2, Types.INTEGER);

            if (n.getIdFriendRequest() != null) stmt.setInt(3, n.getIdFriendRequest());
            else stmt.setNull(3, Types.INTEGER);

            stmt.setString(4, n.getMessage());
            stmt.setBoolean(5, n.isRead());

            int rows = stmt.executeUpdate();
            return rows > 0;
        }

        catch (SQLException e)
        {
            System.err.println("Error during inserting notify in the db: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public static List<Notify> getTransactionNotifiesBetweenUserAndUser2(int userId, int userId2) {

        String sql = """
        
                SELECT *
        FROM Notify
        WHERE (sender = ? AND beneficiary = ?)
           OR (sender = ? AND beneficiary = ?)
        ORDER BY data_creation ASC 
        """;
        List<Notify> list = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {



            stmt.setInt(1, userId);
            stmt.setInt(2, userId2);
            stmt.setInt(3, userId2);
            stmt.setInt(4, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error during getting all notifies by user id: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }


    // 🔹 Recupera notifiche di un utente
    public static List<Notify> getNotifyByUserId(int userId)  {
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
        catch (SQLException e)
        {
            System.err.println("Error during getting all notifies by user id: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    // 🔹 Segna notifica come letta
    public static boolean markAsRead(int idNotify)  {
        String sql = "UPDATE Notify SET read = 1 WHERE id_notify = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idNotify);
            return stmt.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.err.println("Error during marking notify as read: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Cancella notifica


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
