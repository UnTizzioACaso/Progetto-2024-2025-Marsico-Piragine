package bankapp.progetto20242025piragine.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlockDAO {

    // 🔹 Inserisce un blocco
    public static boolean blockUser(int blockerId, int blockedId) {
        String sql = "INSERT INTO Block (blocker, block) VALUES (?, ?)";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, blockerId);
            stmt.setInt(2, blockedId);

            return stmt.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.err.println("Error during adding the block to the db: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Rimuove un blocco
    public static boolean unblockUser(int blockerId, int blockedId) {
        String sql = "DELETE FROM Block WHERE blocker = ? AND block = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, blockerId);
            stmt.setInt(2, blockedId);

            return stmt.executeUpdate() > 0;
        }
        catch (SQLException e)
        {
            System.err.println("Error during removing the block from the db: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Controlla se un utente ha bloccato un altro
    public static boolean isBlocked(int blockerId, int blockedId)  {
        String sql = "SELECT 1 FROM Block WHERE blocker = ? AND block = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, blockerId);
            stmt.setInt(2, blockedId);

            try (ResultSet rs = stmt.executeQuery())
            {
                return rs.next();
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error checing if user" + blockerId + " blocked user" + blockedId + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    //List of blocked users by blocker id
    public static List<Block> getBlockedUsers(int blockerId) {
        String sql = "SELECT * FROM Block WHERE blocker = ?";
        List<Block> blocks = new ArrayList<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, blockerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Block block = new Block();
                    block.setIdBlock(rs.getInt("id_block"));
                    block.setBlocker(rs.getInt("blocker"));
                    block.setBlock(rs.getInt("block"));
                    block.setDataBlocco(rs.getTimestamp("data_blocco"));
                    blocks.add(block);
                }

            }
            return blocks;
        }

        catch (SQLException e)
        {
            System.err.println("Error getting blocked users: " + e.getMessage());
            e.printStackTrace();
            return null;
        }


    }
}
