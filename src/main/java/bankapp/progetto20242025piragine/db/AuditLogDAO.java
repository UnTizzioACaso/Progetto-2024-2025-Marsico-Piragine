package bankapp.progetto20242025piragine.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditLogDAO {

    // 🔹 Inserisce log
    public static boolean insertLog(AuditLog log) throws SQLException {
        String sql = """
            INSERT INTO Audit_Log
            (user_id, action, Details, Result)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (log.getUserId() != null)
                stmt.setInt(1, log.getUserId());
            else
                stmt.setNull(1, Types.INTEGER);

            stmt.setString(2, log.getAction());
            stmt.setString(3, log.getDetails());
            stmt.setString(4, log.getResult());

            int rows = stmt.executeUpdate();
            if (rows == 0) return false;

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    log.setIdLog(keys.getInt(1));
                }
            }
            return true;
        }
    }

    // 🔹 Log di un utente
    public static List<AuditLog> getLogsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM Audit_Log WHERE user_id = ? ORDER BY timestamp DESC";
        List<AuditLog> list = new ArrayList<>();

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

    // 🔹 Tutti i log (admin)
    public static List<AuditLog> getAllLogs() throws SQLException {
        String sql = "SELECT * FROM Audit_Log ORDER BY timestamp DESC";
        List<AuditLog> list = new ArrayList<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        }
        return list;
    }

    // 🔹 Cancella log vecchi (manutenzione)
    public static boolean deleteOlderThan(Timestamp limit) throws SQLException {
        String sql = "DELETE FROM Audit_Log WHERE timestamp < ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, limit);
            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Mapping ResultSet → AuditLog
    private static AuditLog mapRow(ResultSet rs) throws SQLException {
        AuditLog log = new AuditLog();
        log.setIdLog(rs.getInt("id_log"));
        log.setUserId((Integer) rs.getObject("user_id"));
        log.setAction(rs.getString("action"));
        log.setDetails(rs.getString("Details"));
        log.setResult(rs.getString("Result"));
        log.setTimestamp(rs.getTimestamp("timestamp"));
        return log;
    }
}
