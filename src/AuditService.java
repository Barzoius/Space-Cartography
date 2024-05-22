import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuditService {
    private static AuditService instance = null;
    private Connection connection;

    private AuditService(Connection connection) {
        this.connection = connection;
    }

    public static AuditService getInstance(Connection connection) {
        if (instance == null) {
            instance = new AuditService(connection);
        }
        return instance;
    }

    public void exportActionsTableToCSV(String filePath) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT action_name, timestamp FROM actions")) {

            FileWriter csvWriter = new FileWriter(filePath);
            csvWriter.append("ACTION,TIMESTAMP\n");

            while (rs.next()) {
                String action = rs.getString("action_name");
                String time = rs.getString("timestamp");

                csvWriter.append(action);
                csvWriter.append(",");
                csvWriter.append(time);
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

            System.out.println("Data exported successfully to " + filePath);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
