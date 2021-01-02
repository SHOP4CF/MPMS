package tue.horse.process.common.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;
import java.sql.*;

import tue.horse.process.common.accessor.CommonDataAccessor;

@Named
public class DBConnectionDelegate {
    private final Logger LOGGER = Logger.getLogger(DBConnectionDelegate.class.getName());

    @Inject
    private CommonDataAccessor commonDataAccessor;


    public void execute(DelegateExecution execution) throws Exception {

        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName(commonDataAccessor.getJDBCDriver());
            connection = DriverManager.getConnection(commonDataAccessor.getDBUrl(), commonDataAccessor.getDBUsername(), commonDataAccessor.getDBPassword());

            System.out.println("Conenction to database opened successfully");

            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM taskdef");

            while (rs.next()) {
                System.out.println("Task_ID: " + rs.getString("task_id") + " | Task Name: "
                        + rs.getString("task_name"));
            }
            rs.close();
            statement.close();


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        if (connection != null) {
            connection.close();
        }

    }
}

