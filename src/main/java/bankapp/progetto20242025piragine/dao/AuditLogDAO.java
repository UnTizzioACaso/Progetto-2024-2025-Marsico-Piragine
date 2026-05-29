package bankapp.progetto20242025piragine.dao;
import bankapp.progetto20242025piragine.db.DataSourceProvider;
import bankapp.progetto20242025piragine.model.AuditLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditLogDAO {


    public static boolean insertLog(AuditLog log){
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
        } catch (Exception e) {
            System.err.println("Error during insert log to the db: " + e.getMessage());
            e.printStackTrace();
            return false;        }
    }

    public static List<AuditLog> getLogsByUserId(int userId)  {
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
        } catch (Exception e) {
            System.err.println("Error during getLogsByUserId to the db: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }


    public static List<AuditLog> getAllLogs() {
        String sql = "SELECT * FROM Audit_Log ORDER BY timestamp DESC";
        List<AuditLog> list = new ArrayList<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error during getAllLogs to the db: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public static boolean deleteOlderThan(Timestamp limit)  {
        String sql = "DELETE FROM Audit_Log WHERE timestamp < ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, limit);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during deleteOlderThan to the db: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

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
