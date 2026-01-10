package bankapp.progetto20242025piragine.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // 🔹 Inserisce una nuova transazione
    public static boolean insertTransaction(Transaction t) throws SQLException {
        String sql = """
            INSERT INTO Transaction1
            (sender, beneficiary, ammount, note, status, type, used_card)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            if (t.getSender() != null)
                stmt.setInt(1, t.getSender());
            else
                stmt.setNull(1, Types.INTEGER);

            if (t.getBeneficiary() != null)
                stmt.setInt(2, t.getBeneficiary());
            else
                stmt.setNull(2, Types.INTEGER);

            stmt.setBigDecimal(3, t.getAmmount());
            stmt.setString(4, t.getNote());
            stmt.setString(5, t.getStatus());
            stmt.setString(6, t.getType());

            if (t.getUsedCard() != null)
                stmt.setInt(7, t.getUsedCard());
            else
                stmt.setNull(7, Types.INTEGER);

            int rows = stmt.executeUpdate();
            if (rows == 0) return false;

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    t.setIdTransaction(keys.getInt(1));
                }
            }
            return true;
        }
    }

    // 🔹 Transazioni inviate da un conto
    public static List<Transaction> getTransactionsBySender(int senderId) throws SQLException {
        String sql = "SELECT * FROM Transaction1 WHERE sender = ? ORDER BY transaction_date DESC";
        return getTransactions(sql, senderId);
    }

    // 🔹 Transazioni ricevute da un conto
    public static List<Transaction> getTransactionsByBeneficiary(int beneficiaryId) throws SQLException {
        String sql = "SELECT * FROM Transaction1 WHERE beneficiary = ? ORDER BY transaction_date DESC";
        return getTransactions(sql, beneficiaryId);
    }

    // 🔹 Recupera una transazione per ID
    public static Transaction getTransactionById(int idTransaction) throws SQLException {
        String sql = "SELECT * FROM Transaction1 WHERE id_transaction = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTransaction);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;
                return mapRow(rs);
            }
        }
    }

    // 🔹 Metodo comune per liste
    private static List<Transaction> getTransactions(String sql, int accountId) throws SQLException {
        List<Transaction> list = new ArrayList<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        return list;
    }

    // 🔹 Mapping ResultSet → Transaction
    private static Transaction mapRow(ResultSet rs) throws SQLException {
        Transaction t = new Transaction();
        t.setIdTransaction(rs.getInt("id_transaction"));
        t.setSender((Integer) rs.getObject("sender"));
        t.setBeneficiary((Integer) rs.getObject("beneficiary"));
        t.setAmmount(rs.getBigDecimal("ammount"));
        t.setNote(rs.getString("note"));
        t.setTransactionDate(rs.getTimestamp("transaction_date"));
        t.setStatus(rs.getString("status"));
        t.setType(rs.getString("type"));
        t.setUsedCard((Integer) rs.getObject("used_card"));
        return t;
    }
}
