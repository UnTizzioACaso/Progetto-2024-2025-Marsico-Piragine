package bankapp.progetto20242025piragine.db;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO
{

    public static boolean loginCheck(String email, String password) throws SQLException
    {
        String sql = "SELECT password_hash FROM User WHERE email = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery())
            {
                if (!rs.next()) {return false;}
                String passwordHash = rs.getString("password_hash");
                return BCrypt.checkpw(password, passwordHash);
            }
        }
    }

    public static boolean deleteUserById(int userId) throws SQLException {

        String sql = "DELETE FROM User WHERE user_id = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public static boolean updateUserTheme(int userId, String theme) throws SQLException
    {
        if (!"light".equalsIgnoreCase(theme) && !"dark".equalsIgnoreCase(theme))
        {
            throw new IllegalArgumentException("Theme must be 'light' or 'dark'");
        }

        String sql = "UPDATE User SET theme = ? WHERE user_id = ?";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1, theme.toLowerCase());
            stmt.setInt(2, userId);

            return stmt.executeUpdate() > 0;
        }
    }


    public static boolean registerUser(User user) throws SQLException
    {

        String sql = "INSERT INTO User (" + "first_name, last_name, username, birth_day, birth_place, " + "gender, email, password_hash, phone_number, state, province, city, " + "address, street_number, cap, pin_hash, remember_credentials, last_access_date, theme" + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getBirthDate());
            stmt.setString(5, user.getBirthPlace());
            stmt.setString(6, user.getGender());
            stmt.setString(7, user.getEmail());
            stmt.setString(8, user.getPasswordHash());
            stmt.setString(9, user.getPhoneNumber());
            stmt.setString(10, user.getState());
            stmt.setString(11, user.getProvince());
            stmt.setString(12, user.getCity());
            stmt.setString(13, user.getAddress());
            stmt.setString(14, user.getStreetNumber());
            stmt.setString(15, user.getCap());
            stmt.setString(16, user.getPinHash());
            stmt.setBoolean(17, user.isRememberCredentials());
            stmt.setString(18, user.getLastAccessDate());
            String theme = user.getTheme();
            if (theme == null) theme = "light";
            theme = theme.toLowerCase();
            if (!theme.equals("light") && !theme.equals("dark")) theme = "light";

            stmt.setString(19, theme);


            stmt.executeUpdate();
            return true;
        }

    }

    public static User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM User WHERE email = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;

                User user = new User();
                user.setUserID(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setPinHash(rs.getString("pin_hash"));
                user.setTheme(rs.getString("theme"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setGender(rs.getString("gender"));
                user.setBirthDate(rs.getString("birth_day"));
                user.setBirthPlace(rs.getString("birth_place"));
                user.setState(rs.getString("state"));
                user.setProvince(rs.getString("province"));
                user.setCity(rs.getString("city"));
                user.setAddress(rs.getString("address"));
                user.setStreetNumber(rs.getString("street_number"));
                user.setCap(rs.getString("cap"));
                user.setRememberCredentials(rs.getBoolean("remember_credentials"));
                user.setLastAccessDate(rs.getString("last_access_date"));

                return user;
            }
        }
    }

    public static User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM User WHERE username = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;

                User user = new User();
                user.setUserID(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setPinHash(rs.getString("pin_hash"));
                user.setTheme(rs.getString("theme"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setGender(rs.getString("gender"));
                user.setBirthDate(rs.getString("birth_day"));
                user.setBirthPlace(rs.getString("birth_place"));
                user.setState(rs.getString("state"));
                user.setProvince(rs.getString("province"));
                user.setCity(rs.getString("city"));
                user.setAddress(rs.getString("address"));
                user.setStreetNumber(rs.getString("street_number"));
                user.setCap(rs.getString("cap"));
                user.setRememberCredentials(rs.getBoolean("remember_credentials"));
                user.setLastAccessDate(rs.getString("last_access_date"));

                return user;
            }
        }
    }

    public static User getUserByUserID(int userid) throws SQLException {
        String sql = "SELECT * FROM User WHERE user_id = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;

                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setTheme(rs.getString("theme"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setGender(rs.getString("gender"));
                user.setBirthDate(rs.getString("birth_day"));
                user.setBirthPlace(rs.getString("birth_place"));
                user.setCap(rs.getString("cap"));
                return user;
            }
        }
    }

    public static boolean existUserByUsername(String username) throws SQLException
    {
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM User WHERE username = ? LIMIT 1"))
        {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery())
            {
                return rs.next();
            }
        }
    }


    public static boolean existUserByPhone(int phone) throws SQLException
    {
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM User WHERE phone_number = ? LIMIT 1"))
        {
            stmt.setInt(1, phone);
            try (ResultSet rs = stmt.executeQuery())
            {
                return rs.next();
            }
        }
    }

    public static boolean existUserByPhone(String phone) throws SQLException
    {
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM User WHERE phone_number = ? LIMIT 1"))
        {
            stmt.setString(1, phone);
            try (ResultSet rs = stmt.executeQuery())
            {
                return rs.next();
            }
        }
    }

    public static String getUsernameByUserId(int userId) throws  SQLException
    {
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM User WHERE phone_number = ? LIMIT 1"))
        {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery())
            {
                if (rs.next()) {return rs.getString("username");}
            }
        }
        return null;
    }

    public static boolean existUserByEmail(String email) throws SQLException
    {
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM User WHERE email = ? LIMIT 1"))
        {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery())
            {
                return rs.next();
            }
        }
    }

    public static User getUserByCellphone(String phoneNumber) throws SQLException {

        String sql = "SELECT * FROM User WHERE phone_number = ?";
        try (Connection conn = DataSourceProvider.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, phoneNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) return null;

                User user = new User();
                user.setUserID(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setPinHash(rs.getString("pin_hash"));
                user.setTheme(rs.getString("theme"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setGender(rs.getString("gender"));
                user.setBirthDate(rs.getString("birth_day"));
                user.setBirthPlace(rs.getString("birth_place"));
                user.setState(rs.getString("state"));
                user.setProvince(rs.getString("province"));
                user.setCity(rs.getString("city"));
                user.setAddress(rs.getString("address"));
                user.setStreetNumber(rs.getString("street_number"));
                user.setCap(rs.getString("cap"));
                user.setRememberCredentials(rs.getBoolean("remember_credentials"));
                user.setLastAccessDate(rs.getString("last_access_date"));

                return user;
            }
        }

    }
}
