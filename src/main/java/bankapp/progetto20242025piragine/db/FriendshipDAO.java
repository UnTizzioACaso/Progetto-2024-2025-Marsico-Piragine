package bankapp.progetto20242025piragine.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendshipDAO
{

    public static List<Integer> getFriendshipsByUserId(int userId) throws SQLException {

        List<Integer> friends = new ArrayList<>();

        String sql = """
        SELECT requester, requested
        FROM Friendship
        WHERE requester = ? OR requested = ?
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


    public static void addFriendship(int requester, int requested)  {
        String sql = "INSERT INTO Friendship(requester, requested) VALUES (?, ?)";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, requester);
            stmt.setInt(2, requested);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println("Error during adding a new friendship in the db: " + e.getMessage());
            e.printStackTrace();
        }

    }


}
