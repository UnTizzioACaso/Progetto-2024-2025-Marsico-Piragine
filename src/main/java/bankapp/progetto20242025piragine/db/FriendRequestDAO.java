package bankapp.progetto20242025piragine.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDAO {

    public static void sendRequest(FriendRequest request)  {
        String sql = "INSERT INTO Friend_Request (requester, requested, date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection()) {
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

            conn.commit();
        }
        catch (SQLException e)
        {
            System.err.println("Error during sending friendship request: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Boolean findRequestByUsersIds(int id1, int id2)
    {
        String sql = """
        SELECT 1 FROM Friend_Request 
        WHERE ((requester = ? AND requested = ?) OR (requester = ? AND requested = ?)) 
        AND status = 'pending'
        """;
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id1);
            stmt.setInt(2, id2);
            stmt.setInt(3, id2);
            stmt.setInt(4, id1);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e)
        {
            System.out.println("error searching an existing non-pending request: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static FriendRequest getFriendRequestById(int requestId ) {
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

            return f;
        }

        catch (SQLException e)
        {
            System.err.println("Error during getting friendship request by id: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



    public static List<FriendRequest> getPendingRequests(int beneficiaryId) {
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
            while (rs.next())
            {
                requests.add(new FriendRequest(
                        rs.getInt("id_request"),
                        rs.getInt("requester"),
                        rs.getInt("requested"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getString("status")
                ));
            }
            return requests;
        }
        catch (SQLException e)
        {
            System.err.println("Error during getting pending friendship requests: " + e.getMessage());
            e.printStackTrace();
            return requests;
        }
    }


    public static void acceptRequest(int requestId)  {
        String sql = "UPDATE Friend_Request SET status = 'completed' WHERE id_request = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        }

        catch (SQLException e)
        {
            System.err.println("Error during accepting request: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void declineRequest(int requestId) {
        String sql = "UPDATE Friend_Request SET status = 'declined' WHERE id_request = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        }

        catch (SQLException e)
        {
            System.err.println("Error during declining friendship request: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
