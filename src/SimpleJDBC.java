/* This sample shows how to connect and compile a JDBC program
 You should have Driver for your database installed.
*/

// You need to import the java.sql package to use JDBC
import java.sql.*;
// you need this import if you are connecting to Oracle database
//import oracle.jdbc.*;

class SimpleJDBC
{
  public static void main (String args [])
       throws SQLException
  {
    // Load the JDBC driver
	//Uncomment below if you are using Oracle
    //DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	//Uncomment below if you are using Oracle
    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

    // Connect to the database
    	    Connection conn =
    	      DriverManager.getConnection 
					//Oracle: You must put a database name after the @ sign in the connection URL.
					// You can use either the fully specified SQL*net syntax or a short cut
					// syntax as <host>:<port>:<sid>.  The example uses the short cut syntax.
    	    		//Uncomment below if you are using Oracle, provide your username and password
					//("jdbc:oracle:thin:@10.250.9.170:1521:XE","username", "password");
					//Uncomment below if you are using MySQL, provide name of your database, username and password
					("jdbc:mysql://localhost:3306/test","Jeff", "1234"); 


    // Create a Statement
    Statement stmt = conn.createStatement ();

    // Select the table names from the user_tables
  //Uncomment below if you are using Oracle
    //ResultSet rset = stmt.executeQuery ("select TABLE_NAME from USER_TABLES");
  //Uncomment below if you are using MySQL
    ResultSet rset = stmt.executeQuery ("show tables");

    // Iterate through the result and print out the table names
    while (rset.next ())
      System.out.println (rset.getString (1));
  }
}