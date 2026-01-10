package bankapp.progetto20242025piragine.db;

import java.sql.*;

public class BankAccountDAO {

    // 🔹 Recupera account tramite user_id
    public static BankAccount getAccountByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM Bank_Account WHERE user_id = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;

                BankAccount account = new BankAccount();
                account.setIdAccount(rs.getInt("id_account"));
                account.setUserId(rs.getInt("user_id"));
                account.setMoney(rs.getDouble("money"));
                account.setCurrency(rs.getString("currency"));
                account.setIban(rs.getString("iban"));
                account.setMaxTransfer(rs.getDouble("max_transfer"));
                account.setForcePin(rs.getBoolean("force_pin"));
                account.setCheckAccount(rs.getString("check_account"));

                return account;
            }
        }
    }
    public static boolean existsByIban(String iban) throws SQLException {
        String sql = "SELECT 1 FROM Bank_Account WHERE iban = ? LIMIT 1";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, iban);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // 🔹 Recupera account tramite id_account
    public static BankAccount getAccountById(int idAccount) throws SQLException {
        String sql = "SELECT * FROM Bank_Account WHERE id_account = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAccount);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;

                BankAccount account = new BankAccount();
                account.setIdAccount(rs.getInt("id_account"));
                account.setUserId(rs.getInt("user_id"));
                account.setMoney(rs.getDouble("money"));
                account.setCurrency(rs.getString("currency"));
                account.setIban(rs.getString("iban"));
                account.setMaxTransfer(rs.getDouble("max_transfer"));
                account.setForcePin(rs.getBoolean("force_pin"));
                account.setCheckAccount(rs.getString("check_account"));

                return account;
            }
        }
    }

    // 🔹 Inserisce un nuovo conto
    public static boolean insertAccount(BankAccount account) throws SQLException
    {
        String sql = "INSERT INTO Bank_Account ( user_id, money, currency, iban, max_transfer, force_pin, check_account) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, account.getUserId());
            stmt.setDouble(2, account.getMoney());
            stmt.setString(3, account.getCurrency());
            stmt.setString(4, account.getIban());
            stmt.setDouble(5, account.getMaxTransfer());
            stmt.setBoolean(6, account.isForcePin());
            stmt.setString(7, account.getCheckAccount());

            stmt.executeUpdate();
            return true;
        }
    }

    // 🔹 Aggiorna il saldo
    public static boolean updateBalance(int idAccount, double newBalance) throws SQLException {
        String sql = "UPDATE Bank_Account SET money = ? WHERE id_account = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newBalance);
            stmt.setInt(2, idAccount);

            return stmt.executeUpdate() > 0;
        }
    }

    // 🔹 Chiude il conto
    public static boolean closeAccount(int idAccount) throws SQLException {
        String sql = "UPDATE Bank_Account SET check_account = 'closed' WHERE id_account = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAccount);
            return stmt.executeUpdate() > 0;
        }
    }
}
