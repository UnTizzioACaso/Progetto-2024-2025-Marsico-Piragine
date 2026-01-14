package bankapp.progetto20242025piragine.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FriendRequestDAO {
    private Connection conn;

    public FriendRequestDAO(Connection conn) {
        this.conn = conn;
    }


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

    public List<FriendRequest> getPendingRequests(int beneficiaryId) throws SQLException {
        String sql = "SELECT id_richiesta, Requester, Beneficiary, date, transaction_status FROM Friend_Request WHERE Beneficiary = ? AND transaction_status = 'pending'";


        {

        }
        List<FriendRequest> requests = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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


    public void acceptRequest(int requestId) throws SQLException {

        String selectSql = "SELECT Requester, Beneficiary FROM Friend_Request WHERE id_richiesta = ?";
        int requester = 0;
        int beneficiary = 0;
        try (PreparedStatement stmt = conn.prepareStatement(selectSql)) {
            stmt.setInt(1, requestId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                requester = rs.getInt("Requester");
                beneficiary = rs.getInt("Beneficiary");
            }
        }

        if (requester == 0 || beneficiary == 0) return;


        String updateSql = "UPDATE Friend_Request SET transaction_status = 'completed' WHERE id_richiesta = ?";
        try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        }

        String insertSql = "INSERT INTO Friendship(user1, user2) VALUES (?, ?)";
        int user1 = Math.min(requester, beneficiary);
        int user2 = Math.max(requester, beneficiary);
        try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
            stmt.setInt(1, user1);
            stmt.setInt(2, user2);
            stmt.executeUpdate();
        }
    }

    public void declineRequest(int requestId) throws SQLException {
        String sql = "UPDATE Friend_Request SET transaction_status = 'declined' WHERE id_richiesta = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, requestId);
            stmt.executeUpdate();
        }
    }
}
