package bankapp.progetto20242025piragine.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDAO {

    public static void sendRequest(FriendRequest request) throws SQLException {
        String sql = "INSERT INTO Friend_Request (requester, requested, date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection()) {
            // Disabilitiamo l'auto-commit per sicurezza (opzionale)
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, request.getRequester());
                stmt.setInt(2, request.getRequested());
                stmt.setTimestamp(3, Timestamp.valueOf(request.getDateRequest()));
                stmt.setString(4, request.getStatus());
                stmt.executeUpdate();
            }

            // Recupero ID manuale via query (Metodo più sicuro per SQLite)
            String idSql = "SELECT last_insert_rowid()";
            try (Statement idStmt = conn.createStatement();
                 ResultSet rs = idStmt.executeQuery(idSql)) {
                if (rs.next()) {
                    request.setIdRequest(rs.getInt(1));
                }
            }

            conn.commit(); // Confermiamo l'operazione
        } catch (SQLException e) {
            // Se qualcosa va storto, l'eccezione viene lanciata
            throw e;
        }
    }

    public static FriendRequest getFriendRequestById(int requestId ) throws SQLException {
        String sql = "SELECT * FROM Friend_Request WHERE id_request = ?";
        FriendRequest f;
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, requestId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            f = new FriendRequest(
                    rs.getInt("id_request"),
                    rs.getInt("requester"),
                    rs.getInt("requested"),
                    rs.getTimestamp("date").toLocalDateTime(),
                    rs.getString("status"));

        }
        return f;
    }



    public static List<FriendRequest> getPendingRequests(int beneficiaryId) throws SQLException {
        String sql = """
            SELECT id_request, requester, requested, date, status
            FROM Friend_Request
            WHERE requested = ?
            AND status = 'pending'
            ORDER BY date DESC
            """;


        List<FriendRequest> requests = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, beneficiaryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                requests.add(new FriendRequest(
                        rs.getInt("id_request"),
                        rs.getInt("requester"),
                        rs.getInt("requested"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getString("status")
                ));
            }
        }
        return requests;
    }


    public static void acceptRequest(int requestId) throws SQLException {

        String sql = "SELECT requester, requested FROM Friend_Request WHERE id_request = ?";
        int requester = 0;
        int requested = 0;
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, requestId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                requester = rs.getInt("requester");
                requested = rs.getInt("requested");
            }
        }

        if (requester == 0 || requested == 0) return;


        sql = "UPDATE Friend_Request SET status = 'completed' WHERE id_request = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        }
    }

    public static void declineRequest(int requestId) throws SQLException {
        String sql = "UPDATE Friend_Request SET status = 'declined' WHERE id_request = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        }
    }
}
