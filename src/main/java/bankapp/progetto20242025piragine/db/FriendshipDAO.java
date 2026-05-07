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
                    int user1 = rs.getInt("requester");
                    int user2 = rs.getInt("requested");

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

    public static Friendship getFriendshipById(int idFriendship)
    {
        String sql = """
        SELECT *
        FROM Friendship
        WHERE id_friendship = ?
        """;

        Friendship f = new Friendship();
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idFriendship);

            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    f.setRequester(rs.getInt("requester"));
                    f.setRequested(rs.getInt("requested"));
                    return f;
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error during adding a new friendship in the db: " + e.getMessage());
            e.printStackTrace();
        }
        return f;
    }


    public static Friendship getFriendship(int user1Id, int user2Id) {

        String sql = """
        SELECT id_friendship, requester, requested, date_friendship 
        FROM Friendship 
        WHERE (requester = ? AND requested = ?) 
           OR (requester = ? AND requested = ?)
        LIMIT 1
    """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, user1Id);
            stmt.setInt(2, user2Id);
            stmt.setInt(3, user2Id);
            stmt.setInt(4, user1Id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {

                    Friendship friendship = new Friendship();
                    friendship.setIdFriendship(rs.getInt("id_friendship"));
                    friendship.setRequester(rs.getInt("requester"));
                    friendship.setRequested(rs.getInt("requested"));


                    Timestamp ts = rs.getTimestamp("date_friendship");
                    if (ts != null) {
                        friendship.setDateFriendship(ts.toLocalDateTime());
                    }

                    return friendship;
                }
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error during getting friendship between " + user1Id + " and " + user2Id);
            e.printStackTrace();
        }

        return null;
    }

}
