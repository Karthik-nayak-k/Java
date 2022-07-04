package Lab06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.sql.Statement;
public class data {

	
	public static void getConnection(int userId, String pswd) {
	//Connection initialization
	Connection conn = null;
	try {
	//Connection parameters
	 String dbURL1 = "jdbc:sqlserver://DESKTOP-HT8RAPF:1433;databaseName=lab6;encrypt=true;trustServerCertificate=true;";
	 String user = "charan";
	 String pass = "1234";
	 
	 //Getting the connection
	 conn = DriverManager.getConnection(dbURL1, user, pass);
	 
	 
	 if (conn != null) {
	 
	 //Statement Declaration
	 /*Statement sta = conn.createStatement();
	 //ResultSet for Statement
	 ResultSet rs = sta.executeQuery("select * from sqlinjection where user_ID="+userId+"and passwd='"+pswd+"'" );
	 */
	 
	 //Prepared Statement Declaration
	 PreparedStatement sta = conn.prepareStatement("select * from sqlinjection where user_ID = ? and passwd = ?");
	 //Passing the parameters to the prepared statement
	 sta.setInt(1, userId);
	 sta.setString(2, pswd);
	 
	 //ResultSet for Prepared Statement
	 ResultSet rs = sta.executeQuery(); 
	 
	 
	 if(rs.next()) {
	 
	 //Storing the userName locally
	 //String USerName = rs.getString("userName");
	 System.out.println("True\nWelcome user");
	 
	 //break;
	 } //endif
	 else {
	 System.out.println("Invalid credentials\n");
	 } //end else
	 }
	 } catch (SQLException ex) {
	 ex.printStackTrace();
	 } finally {
	 try {
	 if (conn != null && !conn.isClosed()) {
	 conn.close();
	 }
	 } catch (SQLException ex) {
	 ex.printStackTrace();
	 }
	 }
	}
	}
