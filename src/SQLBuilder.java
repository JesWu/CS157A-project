import java.sql.*;

public class SQLBuilder
{
	public static String USERNAME = new String("Jeff");
    public static String PASSWORD = new String("1234");
	public static String DB_URL = new String("jdbc:mysql://localhost:3306/test");
	public static String JDBC_DRIVER = new String("com.mysql.cj.jdbc.Driver");
	static String[]	Tables = {
					"create table TFI (" +
					        "ID int not null, " +
					        "DATE date not null, " +
					        "FIRSTNAME varchar(16) not null, " +
					        "LASTNAME varchar(16) not null, " + 
					        "AwarenessLevel int not null, " +
					        "LoudnessLevel int not null, " +
					        "AnnoyanceLevel int not null, " +
					        "ControllingImpairmentLevel int not null, " +
					        "CopingImpairmentLevel int not null, " +
					        "IgnoringImpairmentLevel int not null, " +
					        "ConcentratingImpairmentLevel int not null, " +
					        "ThinkingClearImpairmentLevel int not null, " +
					        "FocusImpairmentLevel int not null, " +
					        "EaseOfSleepImpairmentLevel int not null, " +
					        "SleepingTimeImpairmentLevel int not null, " +
					        "PeacefulSleepImpairmentLevel int not null, " +
					        "HearingClearImpairmentLevel int not null, " +
					        "UnderstandPeopleImpairmentLevel int not null, " +
					        "ConversationFollowingImpairmentLevel int not null, " +
					        "QuietImpairmentLevel int not null, " +
					        "RelaxingImpairmentLevel int not null, " +
					        "PeaceandQuietImpairmentLevel int not null, " +
					        "SocialActivityImpairmentLevel int not null, " +
					        "EnjoymentImpairmentLevel int not null, " +
					        "RelationshipInterferenceLevel int not null, " +
					        "WorkInterferenceLevel int not null, " +
					        "AnxietyLevel int not null, " +
					        "UpsetLevel int not null, " +
					        "DepressionLevel int not null)"
						};

	public static void
	build(Connection con) throws ClassNotFoundException
	{
		
		// Load the Driver
		//Class.forName("oracle.jdbc.driver.OracleDriver");
		Class.forName(JDBC_DRIVER);

		try
		{
	
			// Show some database/driver metadata
			SQLUtil.printDriverInfo(con);

			// Create a Statement object so we can submit SQL statements to the driver
			Statement stmt = con.createStatement();

			// Submit the statement
			for (int i=0; i<Tables.length; ++i)
			{
				System.out.print(Tables[i] + "...");
				int rowsAffected = stmt.executeUpdate(Tables[i]);
				if (rowsAffected == 0)	// DDL statements return rowcount of 0
					System.out.println("OK");
			}

			// Close the statement
			stmt.close();

			// Close the connection
			con.close();
		}
		catch (SQLException e)
		{
          	 	SQLUtil.printSQLExceptions(e);		
          	 }
	}	
}
