package bankapp.progetto20242025piragine.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {


    public static String hashPassword(String password) //generates hash password from string password
    {
        return BCrypt.hashpw(password, BCrypt.gensalt()); //returning hash password
    }

    //compares password hash with string password
    public static boolean checkPassword(String password, String hashedPassword)
    {
        return BCrypt.checkpw(password, hashedPassword);//returning true if is correct, false if not
    }
}
