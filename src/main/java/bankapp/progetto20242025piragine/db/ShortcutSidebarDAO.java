package bankapp.progetto20242025piragine.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShortcutSidebarDAO {

    // 🔹 Inserisce shortcut
    public static boolean insertShortcut(ShortcutSidebar s) throws SQLException {
        String sql = """
            INSERT INTO Shortcut_sidebar
            (user_id, type_shortcut, Order1)
            VALUES (?, ?, ?)
            """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, s.getUserId());
            stmt.setString(2, s.getTypeShortcut());
            stmt.setInt(3, s.getOrder());

            int rows = stmt.executeUpdate();
            if (rows == 0) return false;

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    s.setIdCustom(keys.getInt(1));
                }
            }
            return true;
        }
    }

    // 🔹 Shortcut di un utente
    public static List<ShortcutSidebar> getShortcutsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM Shortcut_sidebar WHERE user_id = ? ORDER BY Order1";
        List<ShortcutSidebar> list = new ArrayList<>();

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

    // 🔹 Aggiorna ordine
    public static boolean updateOrder(int idCustom, int newOrder) throws SQLException {
        String sql = "UPDATE Shortcut_sidebar SET Order1 = ? WHERE id_custom = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newOrder);
            stmt.setInt(2, idCustom);
            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Cancella shortcut
    public static boolean deleteShortcut(int idCustom) throws SQLException {
        String sql = "DELETE FROM Shortcut_sidebar WHERE id_custom = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCustom);
            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Mapping ResultSet → ShortcutSidebar
    private static ShortcutSidebar mapRow(ResultSet rs) throws SQLException {
        ShortcutSidebar s = new ShortcutSidebar();
        s.setIdCustom(rs.getInt("id_custom"));
        s.setUserId(rs.getInt("user_id"));
        s.setTypeShortcut(rs.getString("type_shortcut"));
        s.setOrder(rs.getInt("Order1"));
        return s;
    }
}
