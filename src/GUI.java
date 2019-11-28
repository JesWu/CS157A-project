import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * GUI class which will run the application and take the user directly to the main panel, which
 * is the Interview panel
 * @author Team 7
 *
 */
public class GUI {
    public static String USERNAME = new String("root");
    public static String PASSWORD = new String("sh100");
    public static String DB_URL = new String("jdbc:mysql://localhost:3306/test");
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = DriverManager.getConnection(
                DB_URL,
    USERNAME, PASSWORD);
        SQLBuilder.build(con);
        con = DriverManager.getConnection(
                DB_URL,
    USERNAME, PASSWORD);
        new Visit(con);
    }
}
