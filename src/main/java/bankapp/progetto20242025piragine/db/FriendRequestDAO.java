package bankapp.progetto20242025piragine.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDAO {



    public static void sendRequest(FriendRequest request) throws SQLException {
        String sql = "INSERT INTO Friend_Request (Requester, Beneficiary, date, transaction_status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, request.getRequester());
            stmt.setInt(2, request.getBeneficiary());
            stmt.setTimestamp(3, Timestamp.valueOf(request.getDateRequest()));
            stmt.setString(4, request.getStatus());
            stmt.executeUpdate();
        }
    }

    public static List<FriendRequest> getPendingRequests(int beneficiaryId) throws SQLException {
        String sql = "SELECT id_richiesta, Requester, Beneficiary, date, transaction_status FROM Friend_Request WHERE Beneficiary = ? AND transaction_status = 'pending'";


        {

        }
        List<FriendRequest> requests = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, beneficiaryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                requests.add(new FriendRequest(
                        rs.getInt("id_richiesta"),
                        rs.getInt("Requester"),
                        rs.getInt("Beneficiary"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getString("transaction_status")
                ));
            }
        }
        return requests;
    }


    public static void acceptRequest(int requestId) throws SQLException {

        String sql = "SELECT Requester, Beneficiary FROM Friend_Request WHERE id_richiesta = ?";
        int requester = 0;
        int beneficiary = 0;
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, requestId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                requester = rs.getInt("Requester");
                beneficiary = rs.getInt("Beneficiary");
            }
        }

        if (requester == 0 || beneficiary == 0) return;


        sql = "UPDATE Friend_Request SET transaction_status = 'completed' WHERE id_richiesta = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        }
    }

    public static void declineRequest(int requestId) throws SQLException {
        String sql = "UPDATE Friend_Request SET transaction_status = 'declined' WHERE id_richiesta = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        }
    }
}
