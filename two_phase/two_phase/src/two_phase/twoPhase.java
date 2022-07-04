package two_phase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

	public class twoPhase implements Lab5interface{

		public void viewData(){
		Connection conn = null;
		try {
		 String dbURL1 = "jdbc:sqlserver://DESKTOP-HT8RAPF:1433;databaseName=db1;encrypt=true;trustServerCertificate=true;";
		 
		 String user = "lakshmi";
		 String pass = "1234";
		 conn = DriverManager.getConnection(dbURL1, user, pass);
		 if (conn != null) {
		 
		 Statement sta = conn.createStatement();
		 
		 ResultSet rs = sta.executeQuery("select * from emp");
		 
		 System.out.println("ID" + "\t" + "Names" + "\t" + "Amount");
		 
		 while(rs.next()) {
		 int id = rs.getInt("id");
		 String name = rs.getString("Name");
		 int amount = rs.getInt("Amount");
		 
		 
		 System.out.println(id + "\t" + name + "\t" + amount);
		 }
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

		public void getinfo() {
		int Receiverid, Senderid, Amount;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Receiver");
		Receiverid = scan.nextInt();
		System.out.println("Enter the Sender");
		Senderid = scan.nextInt();
		System.out.println("Enter the Amount");
		Amount = scan.nextInt();
		 updateData(Receiverid,Senderid,Amount);
		}
		
		public void updateData(int Receiverid, int Senderid, int Amount) {
		Connection conn = null;
		Connection conn1 = null;
		int SenderAvailBal = 0;
		int ReciverAvailBal = 0;
		int Senid = 0;
		int Recid = 0;
		 try {
		 String dbURL1 = "jdbc:sqlserver://DESKTOP-HT8RAPF:1433;databaseName=db1;encrypt=true;trustServerCertificate=true;";
		 String dbURL2 = "jdbc:sqlserver://DESKTOP-HT8RAPF:1433;databaseName=db2.2;encrypt=true;trustServerCertificate=true;";
		 String user = "lakshmi";
		 String pass = "1234";
		 conn = DriverManager.getConnection(dbURL1, user, pass);
		 conn1 = DriverManager.getConnection(dbURL2, user, pass);
		 if (conn != null) {
		 
		 Statement sta = conn.createStatement();
		 Statement sta1 = conn1.createStatement();
		 
		 conn.setAutoCommit(false); 
		 
		 
		 ResultSet rs1 = sta.executeQuery("select id,Amount from emp where ID="+Receiverid);
		 
		 while(rs1.next()) {
		 Recid = rs1.getInt("ID");
		 ReciverAvailBal = rs1.getInt("Amount");
		 
		 
		 
		 }
		 
		 ResultSet rs2 = sta.executeQuery("select id,Amount from emp where id="+Senderid);
		 
		 while(rs2.next()) {
		 Senid = rs2.getInt("ID");
		 SenderAvailBal = rs2.getInt("Amount");
		 
		 
		 
		 }
		 
		 if(Amount <= SenderAvailBal) {
		 
		 int diffamount = SenderAvailBal - Amount;
		 int addedamount = ReciverAvailBal + Amount;
		 
		 
		 sta.executeUpdate("update emp set Amount=" + diffamount + "where Id=" + Senid);
		 
		 sta.executeUpdate("update emp set Amount=" + addedamount + "where Id=" + Recid);
		 
		 sta1.executeUpdate("update emp set Amount=" + diffamount + "where Id=" + Senid);
		 
		 sta1.executeUpdate("update emp set Amount=" + addedamount + "where Id=" + Recid);
		 
		 conn.commit();
		 
		 viewData();
		 
		 }else {
		 
		 System.out.println("Insufficient Balance of Senderid " + Senid + " Amount = " + SenderAvailBal);
		 conn.rollback();
		 // viewDetails();
		 }
		 
		 
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
		public static void main(String[] args) {
			twoPhase at = new twoPhase();
		 at.viewData();
		 
		 at.getinfo();
		 
		 }

		@Override
		public void viewDetails() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateAmount(int Receiverid, int Senderid, int Amount) {
			// TODO Auto-generated method stub
			
		}
		}




