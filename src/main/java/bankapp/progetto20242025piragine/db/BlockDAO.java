package bankapp.progetto20242025piragine.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlockDAO {

    // 🔹 Inserisce un blocco
    public static boolean blockUser(int blockerId, int blockedId) throws SQLException {
        String sql = "INSERT INTO Block (blocker, block) VALUES (?, ?)";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, blockerId);
            stmt.setInt(2, blockedId);

            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Rimuove un blocco
    public static boolean unblockUser(int blockerId, int blockedId) throws SQLException {
        String sql = "DELETE FROM Block WHERE blocker = ? AND block = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, blockerId);
            stmt.setInt(2, blockedId);

            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Controlla se un utente ha bloccato un altro
    public static boolean isBlocked(int blockerId, int blockedId) throws SQLException {
        String sql = "SELECT 1 FROM Block WHERE blocker = ? AND block = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, blockerId);
            stmt.setInt(2, blockedId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // 🔹 Lista utenti bloccati da un utente
    public static List<Block> getBlockedUsers(int blockerId) throws SQLException {
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
        }
        return blocks;
    }
}
