package bankapp.progetto20242025piragine.db;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {

    private static final String DB_URL = "jdbc:sqlite:bancaApp.db";

    private static final SQLiteDataSource dataSource = new SQLiteDataSource();

    static {
        dataSource.setUrl(DB_URL);
    }


    private DataSourceProvider() {}

    public static DataSource getDataSource() {
        return dataSource;
    }
}
