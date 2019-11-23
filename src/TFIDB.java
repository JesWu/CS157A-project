import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TFIDB {
    public static String USERNAME = new String("Jeff");
    public static String PASSWORD = new String("1234");
    public static String DB_URL = new String("jdbc:mysql://localhost:3306/test");
    public static String JDBC_DRIVER = new String("com.mysql.cj.jdbc.Driver");
    
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con = DriverManager.getConnection(
                DB_URL,
    USERNAME, PASSWORD);
        SQLBuilder.build(con);
        new TFI(con);
    }
}
