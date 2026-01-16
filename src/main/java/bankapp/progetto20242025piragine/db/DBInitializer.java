package bankapp.progetto20242025piragine.db;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import javax.sql.DataSource;


public class DBInitializer {

    public static void initialize() {
        DataSource dataSource = DataSourceProvider.getDataSource();

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

        try {
            // Using PathMatchingResourcePatternResolver for reading all SQL files under "sql/"
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

            Resource[] resources = resolver.getResources("classpath:bankapp/progetto20242025piragine/sql/*.sql");
            System.out.println("Trovati file SQL: " + resources.length);

            // adding al files found in a populator
            for (Resource resource : resources) {
                populator.addScript(resource);
            }

            // execution and internal connection to the db (Spring does all the work)
            populator.execute(dataSource);

            System.out.println("database initialized correctly.");

        } catch (Exception e) {
            System.err.println("error during database initialization" + e.getMessage());
            e.printStackTrace();
        }
    }
}