package bankapp.progetto20242025piragine.db;

import java.math.BigDecimal;
import java.sql.*;

public class BankAccountDAO {


    public static BankAccount getAccountByUserId(int userId)  {
        String sql = "SELECT * FROM Bank_Account WHERE user_id = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;

                BankAccount account = new BankAccount();
                account.setIdAccount(rs.getInt("id_account"));
                account.setUserId(rs.getInt("user_id"));
                account.setMoney(BigDecimal.valueOf(rs.getInt("money"), 2));
                account.setCurrency(rs.getString("currency"));
                account.setIban(rs.getString("iban"));
                account.setMaxTransfer(BigDecimal.valueOf( rs.getInt("max_transfer"), 2));
                account.setForcePin(rs.getBoolean("force_pin"));
                account.setCheckAccount(rs.getString("check_account"));

                return account;
            }
        } catch (Exception e) {
            System.err.println("Error during get account by user id to the db: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public static boolean existsByIban(String iban)  {
        String sql = "SELECT 1 FROM Bank_Account WHERE iban = ? LIMIT 1";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, iban);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error during check to exist by iban to the db: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static int getUserIdByAccountId(int accountId)  {
        String sql = "SELECT user_id FROM Bank_Account WHERE account_id = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next())
                {
                    return rs.getInt("user_id");
                }
            }
        } catch (Exception e) {
            System.err.println("Error during get userID by account to the db: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public static BankAccount getAccountById(int idAccount) {
        String sql = "SELECT * FROM Bank_Account WHERE id_account = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAccount);

            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;

                BankAccount account = new BankAccount();
                account.setIdAccount(rs.getInt("id_account"));
                account.setUserId(rs.getInt("user_id"));
                account.setMoney(BigDecimal.valueOf(rs.getInt("money"), 2));
                account.setCurrency(rs.getString("currency"));
                account.setIban(rs.getString("iban"));
                account.setMaxTransfer(BigDecimal.valueOf(rs.getInt("max_transfer"), 2));
                account.setForcePin(rs.getBoolean("force_pin"));
                account.setCheckAccount(rs.getString("check_account"));

                return account;
            }
        } catch (Exception e) {
            System.err.println("Error during get recovery account by ID to the db: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static int getIdAccountByUserId(int userId)  {
        String sql = "SELECT id_account FROM Bank_Account WHERE user_id = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next()) {return rs.getInt("id_account");}
            }
        } catch (SQLException e) {
            System.err.println("Error during get recovery account by user ID to the db: " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }


    public static boolean insertAccount(BankAccount account)
    {
        String sql = "INSERT INTO Bank_Account ( user_id, money, currency, iban, max_transfer, force_pin, check_account) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setInt(1, account.getUserId());
            stmt.setInt(2, account.getMoney().movePointRight(2).intValueExact());
            stmt.setString(3, account.getCurrency());
            stmt.setString(4, account.getIban());
            stmt.setInt(5, account.getMaxTransfer().movePointRight(2).intValueExact());
            stmt.setBoolean(6, account.isForcePin());
            stmt.setString(7, account.getCheckAccount());


            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error during add account to the db: " + e.getMessage());
            e.printStackTrace();
            return false;}
    }
    public static boolean updateBalance(int idAccount, BigDecimal newBalance) throws SQLException {
        String sql = "UPDATE Bank_Account SET money = ? WHERE id_account = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newBalance.movePointRight(2).intValueExact());
            stmt.setInt(2, idAccount);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during update balance : " + e.getMessage());
            e.printStackTrace();
            return false;

        }
    }
    public static boolean closeAccount(int idAccount) {
        String sql = "UPDATE Bank_Account SET check_account = 'closed' WHERE id_account = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAccount);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error during close account: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
