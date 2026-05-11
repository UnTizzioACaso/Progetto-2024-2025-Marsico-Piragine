package bankapp.progetto20242025piragine.db;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {


    public static boolean declineTransacion(int idTransaction)
    {
        String sql = """
            UPDATE Bank_Transaction 
            SET status = 'declined' WHERE id_transaction = ?
            """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idTransaction);
            int rows = stmt.executeUpdate();
            if (rows == 0) return false;
            return true;
        }
        catch (SQLException e) {
            System.err.println("Error during declining a transaction: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public static boolean acceptTransacion(int idTransaction)
    {
        String sql = """
            UPDATE Bank_Transaction 
            SET status = 'accepted' WHERE id_transaction = ?
            """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idTransaction);
            int rows = stmt.executeUpdate();
            if (rows == 0) return false;
            return true;
        }
        catch (SQLException e) {
            System.err.println("Error during declining a transaction: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    // 🔹 Inserisce una nuova transazione (Fix per SQLite "not implemented")
    public static boolean insertTransaction(Transaction t) {
        String sql = """
            INSERT INTO Bank_Transaction
            (sender, beneficiary, amount, note, type, status, used_card)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Gestione Sender (Null se è un deposito/ricarica)
            if (t.getSender() != null && t.getSender() != 0) stmt.setInt(1, t.getSender());
            else stmt.setNull(1, Types.INTEGER);

            // Gestione Beneficiary (Null se è un prelievo)
            if (t.getBeneficiary() != null && t.getBeneficiary() != 0) stmt.setInt(2, t.getBeneficiary());
            else stmt.setNull(2, Types.INTEGER);


            stmt.setInt(3, t.getAmount().multiply(BigDecimal.valueOf(100)).intValue());
            stmt.setString(4, t.getNote());
            stmt.setString(5, t.getType());
            stmt.setString(6, t.getStatus());
            stmt.setInt(7, t.getUsedCard());


            int rows = stmt.executeUpdate();
            if (rows == 0) return false;

            // Recupero manuale dell'ID generato (Standard SQLite)
            try (Statement idStmt = conn.createStatement();
                 ResultSet rs = idStmt.executeQuery("SELECT last_insert_rowid()")) {
                if (rs.next()) {
                    t.setIdTransaction(rs.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.err.println("Error during inserting a transaction in the db: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 🔹 Tutte le transazioni di un conto (Sia inviate che ricevute)
    public static List<Transaction> getAllTransactionsByAccount(int accountId) {
        String sql = """
            SELECT * FROM Bank_Transaction
            WHERE sender = ? OR beneficiary = ?
            ORDER BY transaction_date ASC
            """;
        List<Transaction> transactions = new ArrayList<>();

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            stmt.setInt(2, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all account's transactions the db: " + e.getMessage());
            e.printStackTrace();
        }
        return transactions;
    }

    // 🔹 Transazioni tra due utenti specifici (Chat o Cronologia Amici)
    public static List<Transaction> getTransactionsBetweenAccounts(int user1, int user2) {
        String sql = """
            SELECT * FROM Bank_Transaction
            WHERE (sender = ? AND beneficiary = ?)
               OR (sender = ? AND beneficiary = ?)
            ORDER BY transaction_date ASC
            """;
        List<Transaction> transactions = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user1);
            stmt.setInt(2, user2);
            stmt.setInt(3, user2);
            stmt.setInt(4, user1);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all transaction between accounts: " + e.getMessage());
            e.printStackTrace();
        }
        return transactions;
    }

    // 🔹 Transazioni del mese corrente (Entrate)
    public static List<Transaction> getCurrentMonthIncome(int accountId) {
        String sql = """
            SELECT * FROM Bank_Transaction
            WHERE beneficiary = ?
              AND strftime('%Y-%m', transaction_date) = strftime('%Y-%m', 'now')
            ORDER BY transaction_date DESC
            """;
        try {
            return fetchList(sql, accountId);
        }
        catch (SQLException e) {
            System.err.println("Error getting current month income from the db" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Transazioni del mese corrente (Uscite)
    public static List<Transaction> getCurrentMonthOutcome(int accountId) {
        String sql = """
            SELECT * FROM Bank_Transaction
            WHERE sender = ?
              AND strftime('%Y-%m', transaction_date) = strftime('%Y-%m', 'now')
            ORDER BY transaction_date DESC
            """;
        try {
            return fetchList(sql, accountId);
        }
        catch (SQLException e) {
            System.err.println("Error getting current month outcome from the db" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // 🔹 Helper
    private static List<Transaction> fetchList(String sql, int id) throws SQLException{
        List<Transaction> list = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting a transaction from the db");
        }
        return list;
    }

    // 🔹 Mapping ResultSet → Transaction
    private static Transaction mapRow(ResultSet rs) throws SQLException {
        Transaction t = new Transaction();
        t.setIdTransaction(rs.getInt("id_transaction"));
        t.setSender(rs.getInt("sender"));
        t.setBeneficiary(rs.getInt("beneficiary"));
        t.setAmount(BigDecimal.valueOf(rs.getInt("amount"), 2));
        t.setNote(rs.getString("note"));
        t.setTransactionDate(rs.getTimestamp("transaction_date"));
        t.setType(rs.getString("type"));
        t.setStatus(rs.getString("status"));
        t.setUsedCard(rs.getInt("used_card"));
        return t;
    }

    public static List<Transaction> getTransactionsByBeneficiary(int idAccount) {
        String sql = """
            SELECT * FROM Bank_Transaction
            WHERE  beneficiary = ?
            ORDER BY transaction_date ASC
            """;

        List<Transaction> transactions = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAccount);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all account's transactions the db: " + e.getMessage());
            e.printStackTrace();
        }
        return transactions;
    }

    public static List<Transaction> getTransactionsBySender(int idAccount) {
        String sql = """
            SELECT * FROM Bank_Transaction
            WHERE  sender = ?
            ORDER BY transaction_date DESC
            """;

        List<Transaction> transactions = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAccount);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting all account's transactions the db: " + e.getMessage());
            e.printStackTrace();
        }
        return transactions;
    }

    public static Transaction getTransactionById(int idTransaction)
    {
        String sql = """
            SELECT * FROM Bank_Transaction
            WHERE  id_transaction = ?
            ORDER BY transaction_date DESC
            """;
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, idTransaction);
            try (ResultSet rs = stmt.executeQuery())
            {
                if (!rs.next()) return null;

                return mapRow(rs);

            }
        }
        catch (SQLException e)
        {
            System.err.println("Error getting transaction by id: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<Transaction> getCurrentMonthTransactions(int idAccountByUserId)
    {
        String sql = """
            SELECT * FROM Bank_Transaction
            WHERE sender = ? OR beneficiary = ?
              AND strftime('%Y-%m', transaction_date) = strftime('%Y-%m', 'now')
            ORDER BY transaction_date DESC
            """;
        List<Transaction> list = new ArrayList<>();
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idAccountByUserId);
            stmt.setInt(2, idAccountByUserId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapRow(rs));
                }
            }
        }
        catch (SQLException e) {
            System.err.println("Error getting current month transaction from the db: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
}