package bankapp.progetto20242025piragine.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendshipDAO {
    private Connection conn;
    public FriendshipDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Integer> getFriends(int userId) throws SQLException {
        String sql = "SELECT user1, user2 FROM Friendship WHERE user1 = ? OR user2 = ?";
        List<Integer> friends = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int user1 = rs.getInt("user1");
                int user2 = rs.getInt("user2");
                friends.add(user1 == userId ? user2 : user1);
            }
        }
        return friends;
    }
}
