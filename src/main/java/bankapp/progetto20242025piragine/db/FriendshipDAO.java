package bankapp.progetto20242025piragine.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendshipDAO
{

    public static List<Integer> getFriendshipsByUserId(int userId) throws SQLException {

        List<Integer> friends = new ArrayList<>();

        String sql = """
        SELECT user1, user2
        FROM Friendship
        WHERE user1 = ? OR user2 = ?
        """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int user1 = rs.getInt("user1");
                    int user2 = rs.getInt("user2");

                    // restituisce SEMPRE l'altro utente
                    friends.add(user1 == userId ? user2 : user1);
                }
            }
        }

        return friends;
    }


    public static void addFriendship(int user1, int user2) throws SQLException {
        String sql = "INSERT INTO Friendship(user1, user2) VALUES (?, ?)";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user1);
            stmt.setInt(2, user2);
            stmt.executeUpdate();
        }
    }


}
