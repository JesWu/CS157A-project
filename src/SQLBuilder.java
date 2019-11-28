import java.sql.*;

/**
 * SQLBuilder class provides methods to create tables for the database
 * @author Team 7
 *
 */
public class SQLBuilder
{
	public static String JDBC_DRIVER = new String("com.mysql.cj.jdbc.Driver");
	static String[]	Tables = {
					"create table TFI (" +
					        "ID int not null, " +
					        "DATE date not null, " +
					        "FIRSTNAME varchar(16) not null, " +
					        "LASTNAME varchar(16) not null, " + 
					        "AwarenessLevel int, " +
					        "LoudnessLevel int, " +
					        "AnnoyanceLevel int, " +
					        "ControllingImpairmentLevel int, " +
					        "CopingImpairmentLevel int, " +
					        "IgnoringImpairmentLevel int, " +
					        "ConcentratingImpairmentLevel int, " +
					        "ThinkingClearImpairmentLevel int, " +
					        "FocusImpairmentLevel int, " +
					        "EaseOfSleepImpairmentLevel int, " +
					        "SleepingTimeImpairmentLevel int, " +
					        "PeacefulSleepImpairmentLevel int, " +
					        "HearingClearImpairmentLevel int, " +
					        "UnderstandPeopleImpairmentLevel int, " +
					        "ConversationFollowingImpairmentLevel int, " +
					        "QuietImpairmentLevel int, " +
					        "RelaxingImpairmentLevel int, " +
					        "PeaceandQuietImpairmentLevel int, " +
					        "SocialActivityImpairmentLevel int, " +
					        "EnjoymentImpairmentLevel int, " +
					        "RelationshipInterferenceLevel int, " +
					        "WorkInterferenceLevel int, " +
					        "AnxietyLevel int, " +
					        "UpsetLevel int, " +
					        "DepressionLevel int," +
					        "I int, " +
					        "SC int, " +
					        "C int, " +
					        "SL int, " +
					        "A int, " +
					        "R int, " +
					        "Q int, " +
					        "E int, " +
					        "Total int)",
					"create table Visit (" +
					        "ID int not null, " +
					        "THC int not null, " +
					        "VisitNum int not null, " +
					        "FirstName varchar(16) not null, " +
					        "LastName varchar(16) not null, " +
					        "DATE date not null)",
					"create table THI (" +
					        "VISIT_ID int not null," +
							"Sc_T int," +
					        "Sc_F int," +
							"Sc_E int," +
					        "Sc_C int," +
							"F1 int," +
							"F2 int," +
							"E3 int," +
							"F4 int," +
							"C5 int," +
							"E6 int," +
							"F7 int," +
							"C8 int," +
							"F9 int," +
							"E10 int," +
							"C11 int," +
							"F12 int," +
							"F13 int," +
							"E14 int," +
							"F15 int," +
							"E16 int," +
							"E17 int," +
							"F18 int," +
							"C19 int," +
							"F20 int," +
							"E21 int," +
							"E22 int," +
							"C23 int," +
							"F24 int," +
							"E25 int)"
						};
	/**
	 * Executes create tables specified in Tables variable
	 * @param con connection for connecting to the database
	 * @throws ClassNotFoundException
	 */
	public static void build(Connection con) throws ClassNotFoundException
	{
		// Load the Driver
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
