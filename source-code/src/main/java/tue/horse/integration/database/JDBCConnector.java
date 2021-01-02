package tue.horse.integration.database;

import tue.horse.utils.ConfigPropertiesMain;

import javax.inject.Named;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Named
public class JDBCConnector {

//    public static JDBCConnector instance;

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     * @throws SQLException
     */
    public Connection connect() throws SQLException {
        Connection conn = null;

        try {
            ConfigPropertiesMain configmain = new ConfigPropertiesMain();
            Properties props = new Properties();
            props = configmain.main("config.properties");
            configmain = null;
            conn = DriverManager.getConnection(props.getProperty("db_url"), props.getProperty("db_username"), props.getProperty("db_password"));
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
