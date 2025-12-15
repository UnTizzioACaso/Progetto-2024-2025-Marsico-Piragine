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
        String sql = "SELECT password_hash FROM User WHERE email = ?"; //query string for searching the password hash of the respective email

        try (Connection conn = DataSourceProvider.getDataSource().getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) //opening (and closing at the end of the block) Connection and PreparedStatement automatically
        {
            stmt.setString(1, email); //changing the first "?" with the email string
            try (ResultSet rs = stmt.executeQuery())
            {
                if (!rs.next()) {return false;} //return false if the email does not exist
                String passwordHash = rs.getString("password_hash"); //saving the password hash found
                return BCrypt.checkpw(password, passwordHash); //checking if the password is the same as the hash password found in the db
            }
        }
    }


    public static boolean registerUser(User user) throws SQLException
    {

        String sql = "INSERT INTO User (" + "first_name, last_name, username, birth_day, birth_place, " + "gender, email, password_hash, phone_number, state, province, city, " + "address, street_number,cap, pin_hash, remember_credentials, last_access_date, theme" + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; //insert string command for the db

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
            stmt.setString(19, user.getTheme());

            stmt.executeUpdate();
            return true;
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
                user.setUsername(rs.getString("username"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setTheme(rs.getString("theme"));
                user.setPhoneNumber(rs.getString("phone_number"));
                return user;
            }
        }
    }

}
