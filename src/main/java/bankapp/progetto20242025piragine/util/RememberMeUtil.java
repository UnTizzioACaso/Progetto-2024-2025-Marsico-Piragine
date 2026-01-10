package bankapp.progetto20242025piragine.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RememberMeUtil {

    private static final Path FILE_PATH = Paths.get(
            System.getProperty("user.home"), ".bankapp", "remember_me.txt");

    public static void saveEmail(String email) throws IOException
    {
        Files.createDirectories(FILE_PATH.getParent()); // create .bankapp
        Files.writeString(FILE_PATH, email);
    }

    public static String loadSavedEmail()
    {
        try
        {
            if (Files.exists(FILE_PATH))
            {
                return Files.readString(FILE_PATH).trim();
            }
        }
        catch (IOException ignored)
        {

        }
        return null;
    }

    public static void deleteSavedEmail()
    {
        try
        {
            Files.deleteIfExists(FILE_PATH);
        }
        catch (IOException e)
        {
            System.err.println("Error deleting saved email: " + e.getMessage());
        }
    }
}
