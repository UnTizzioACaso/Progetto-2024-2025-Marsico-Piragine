package bankapp.progetto20242025piragine.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomeWidgetCustomDAO {

    // 🔹 Inserisce un widget
    public static boolean insertWidget(HomeWidgetCustom w) throws SQLException {
        String sql = """
            INSERT INTO Home_Widget_Custom
            (user_id, type_widget, size, position, remove)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, w.getUserId());
            stmt.setString(2, w.getTypeWidget());
            stmt.setString(3, w.getSize());
            stmt.setInt(4, w.getPosition());
            stmt.setBoolean(5, w.isRemove());

            int rows = stmt.executeUpdate();
            if (rows == 0) return false;

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    w.setIdWidget(keys.getInt(1));
                }
            }
            return true;
        }
    }

    // 🔹 Widget di un utente
    public static List<HomeWidgetCustom> getWidgetsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM Home_Widget_Custom WHERE user_id = ? ORDER BY position";
        List<HomeWidgetCustom> list = new ArrayList<>();

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

    // 🔹 Aggiorna posizione widget
    public static boolean updatePosition(int idWidget, int newPosition) throws SQLException {
        String sql = "UPDATE Home_Widget_Custom SET position = ? WHERE id_widget = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newPosition);
            stmt.setInt(2, idWidget);
            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Rimuove (soft delete)
    public static boolean markAsRemoved(int idWidget) throws SQLException {
        String sql = "UPDATE Home_Widget_Custom SET remove = 1 WHERE id_widget = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idWidget);
            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Cancella definitivamente
    public static boolean deleteWidget(int idWidget) throws SQLException {
        String sql = "DELETE FROM Home_Widget_Custom WHERE id_widget = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idWidget);
            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Mapping ResultSet → HomeWidgetCustom
    private static HomeWidgetCustom mapRow(ResultSet rs) throws SQLException {
        HomeWidgetCustom w = new HomeWidgetCustom();
        w.setIdWidget(rs.getInt("id_widget"));
        w.setUserId(rs.getInt("user_id"));
        w.setTypeWidget(rs.getString("type_widget"));
        w.setSize(rs.getString("size"));
        w.setPosition(rs.getInt("position"));
        w.setRemove(rs.getBoolean("remove"));
        return w;
    }
}
