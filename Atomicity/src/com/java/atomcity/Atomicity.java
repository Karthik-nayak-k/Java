package com.java.atomcity;

import java.util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Atomicity implements Lab4Interface {

	public void viewDetails(){
	Connection conn = null;
	 try 
	 {
		 String dbURL = "jdbc:sqlserver://DESKTOP-8EH6SIV\\SQLEXPRESS;databaseName=DemoDB;user=Karthik;password=karthik@1997";
		 //String user = "charan";
		 //String pass = "1234";
		 conn = DriverManager.getConnection(dbURL);
		 if (conn != null) {
	 
			 Statement sta = conn.createStatement();
	 
			 ResultSet rs = sta.executeQuery("select * from AMT");
	 
			 System.out.println("ID" + "\t" + "Name" + "\t" + "Amount");
	 
			 while(rs.next()) {
				 int id = rs.getInt("id");
				 String name = rs.getString("names");
				 int amount = rs.getInt("amount");
	 
	 
				 System.out.println(id + "\t" + name + "\t" + amount);
			 }
	 
		 }
	 } 
	 
	 catch (SQLException ex) {
		 ex.printStackTrace();
	 } 
	 
	 finally {
		 try {
			 if (conn != null && !conn.isClosed()) {
				 conn.close();
			 }
	 } 
		 catch (SQLException ex) {
			 ex.printStackTrace();
		 }
	 	}
	}
	public void getinfo(){
		int Receiverid, Senderid, Amount;
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Receiverid");
		Receiverid = scan.nextInt();
		System.out.println("Enter the Senderid");
		Senderid = scan.nextInt();
		System.out.println("Enter the Amount");
		Amount = scan.nextInt();
		updateAmount(Receiverid,Senderid,Amount);
	 
	 
	}
	
	
	public void updateAmount(int Receiverid,int Senderid,int Amount) {
		Connection conn = null;
		int SenderAvailBal = 0;
		int ReciverAvailBal = 0;
		int Senid = 0;
		int Recid = 0;
		try {
			//String dbURL = "jdbc:sqlserver://DESKTOP-HT8RAPF:1433;databaseName=atom;encrypt=true;trustServerCertificate=true;";
			String dbURL = "jdbc:sqlserver://DESKTOP-8EH6SIV\\SQLEXPRESS;databaseName=DemoDB;user=Karthik;password=karthik@1997";
			//String user = "nikhil";
			//String pass = "1234";
			conn = DriverManager.getConnection(dbURL);
			if (conn != null) 
			{
	 
				Statement sta = conn.createStatement();
	 
				conn.setAutoCommit(false); 
	 
	
	 
				ResultSet rs1 = sta.executeQuery("select id,amount from AMT where id="+Receiverid);
	 
				while(rs1.next()) {
					Recid = rs1.getInt("id");
					ReciverAvailBal = rs1.getInt("amount");
				}
	 
				ResultSet rs2 = sta.executeQuery("select id,amount from AMT where id="+Senderid);
	 
				while(rs2.next()) {
					Senid = rs2.getInt("id");
					SenderAvailBal = rs2.getInt("amount");
				}
	 
				if(Amount <= SenderAvailBal) {
	 
					int diffamount = SenderAvailBal - Amount;
					int addedamount = ReciverAvailBal + Amount;
	 
					sta.executeUpdate("update AMT set amount=" + diffamount + "where iD=" + Senid);
	 
					sta.executeUpdate("update AMT set amount=" + addedamount + "where iD=" + Recid);
	 
					conn.commit();
	 
					viewDetails();
	 
				}
	 else {
	 
		 System.out.println("Insufficient Balance of Senderid " + Senid + " Amount = " + SenderAvailBal);
		 conn.rollback();
		 // viewDetails();
	 	}
	  
			}
		} 
		catch (SQLException ex) {
			ex.printStackTrace();
					} 
		finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
	 } 
			catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	 public static void main(String[] args) {
		 Atomicity at = new Atomicity();
		 at.viewDetails();
	 
		 at.getinfo();
	 
	 }
}
