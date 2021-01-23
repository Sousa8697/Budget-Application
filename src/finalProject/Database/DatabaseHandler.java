package finalProject.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import finalProject.Obj.BudgetData;
import finalProject.Obj.PaymentsData;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DatabaseHandler {
	private Connection conn;
	private String sql;
	private String DB_PATH = DatabaseHandler.class.getResource("budgetApp.sqlite").getFile();
	
	public DatabaseHandler() throws ClassNotFoundException, SQLException{
		
		Class.forName("org.sqlite.JDBC");
		
		conn = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
	}
	
	public ArrayList<PaymentsData> getPaymentTitle(String input, int bID) throws SQLException{
		Connection connection  = reconnect();
		if (input.equalsIgnoreCase("")) {
			sql = "SELECT Payments.paymentID AS pID, Payments.budgetID AS bID, Payments.title AS title, "+
				  "Payments.status AS status, Payments.quantity AS quantity FROM Payments";
		}else {
			sql = "SELECT Payments.paymentID AS pID, Payments.budgetID AS bID, Payments.title AS title, "+
				  "Payments.status AS status, Payments.quantity AS quantity FROM Payments WHERE Payments.title = ? AND Payments.budgetID = ?";
		}
		PreparedStatement stmt = connection.prepareStatement(sql);
		if (input.equalsIgnoreCase("")) {
			stmt.setString(1, input);
			stmt.setInt(2, bID);
		}
		ResultSet res = stmt.executeQuery();
		ArrayList<PaymentsData> data = new ArrayList<PaymentsData>();
		while(!res.next()) {
			data.add(new PaymentsData(res.getInt("pID"),res.getInt("bID"),res.getString("title"),res.getString("status"),res.getInt("quantity")));
		}
		stmt.close();
		connection.close();
		return data;
	}
	
	public ArrayList<PaymentsData> getPayPeriodPayments(int bID, String input) throws SQLException{
		Connection connection  = reconnect();
		if (input.equalsIgnoreCase("")) {
			sql = "SELECT budgetID as bID, paymentID as pID, title, status, quantity FROM Payments WHERE bID = ?";
		}else {
			sql = "SELECT budgetID as bID, paymentID as pID, title, status, quantity FROM Payments WHERE bID = ? AND title LIKE ?";
		}
		PreparedStatement stmt = connection.prepareStatement(sql);
		if (!input.equalsIgnoreCase("")) {
			stmt.setInt(1, bID);
			stmt.setString(2, "%"+input+"%");
		}else {
			stmt.setInt(1, bID);
		}
		ResultSet res = stmt.executeQuery();
		ArrayList<PaymentsData> data = new ArrayList<PaymentsData>();
		while(res.next()) {
			data.add(new PaymentsData(res.getInt("pID"),res.getInt("bID"),res.getString("title"),res.getString("status"),res.getInt("quantity")));
		}
		stmt.close();
		connection.close();
		return data;
	}
	
	public ArrayList<BudgetData> getPayPeriodTitle(String input) throws SQLException{
		Connection connection  = reconnect();
		if (input.equalsIgnoreCase("")) {
			sql = "SELECT Budget.budgetID AS bID, Budget.title AS title FROM Budget";
		}else {
			sql = "SELECT Budget.budgetID AS bID, Budget.title AS title FROM Budget WHERE Budget.title LIKE ?";
		}
		PreparedStatement stmt = connection.prepareStatement(sql);
		if (!input.equalsIgnoreCase("")) {
			stmt.setString(1, "%"+input+"%");
		}
		ResultSet res = stmt.executeQuery();
		ArrayList<BudgetData> data = new ArrayList<BudgetData>();
		while(res.next()) {
			data.add(new BudgetData(res.getInt("bID"),res.getString("title")));
		}
		stmt.close();
		connection.close();
		return data;
	}
	
	
	public void createPayPeriod(int bID, String title) throws SQLException {
		Connection connection  = reconnect();
		sql = "INSERT INTO Budget (budgetID, title) VALUES (?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1,bID);
		stmt.setString(2, title);
		stmt.executeUpdate();
		stmt.close();
		connection.close();
		
	}
	
	public void createPayment(int pID, int bID, String title, String inOut, int quantity) throws SQLException {
		Connection connection = reconnect();
		sql = "INSERT INTO Payments (paymentID, budgetID, title, status, quantity) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1,pID);
		stmt.setInt(2, bID);
		stmt.setString(3, title);
		stmt.setString(4, inOut);
		stmt.setInt(5, quantity);
		stmt.executeUpdate();
		stmt.close();
		connection.close();
	}
	
	public void deletePayment(int pID) throws SQLException {
		Connection connection = reconnect();
		sql = "DELETE FROM Payments WHERE paymentID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, pID);
		stmt.executeUpdate();
		stmt.close();
		connection.close();
	}
	
	public void deletePayPeriods(int bID) throws SQLException {
		Connection connection = reconnect();
		sql = "DELETE FROM Budget WHERE budgetID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, bID);
		stmt.executeUpdate();
		stmt.close();
		connection.close();
	}
	
	public ArrayList<BudgetData> getCurrentBID() throws SQLException {
		Connection connection  = reconnect();
		sql = "SELECT MAX(budgetID) AS bID, title FROM Budget";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		ArrayList<BudgetData> data = new ArrayList<BudgetData>();
		while(res.next()) {
			data.add(new BudgetData(res.getInt("bID"),res.getString("title")));
		}
		stmt.close();
		connection.close();
		return data;
	}
	
	public ArrayList<PaymentsData> getCurrentPID() throws SQLException {
		Connection connection  = reconnect();
		sql = "SELECT MAX(paymentID) AS pID FROM Payments";
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet res = stmt.executeQuery();
		ArrayList<PaymentsData> data = new ArrayList<PaymentsData>();
		while(res.next()) {
			data.add(new PaymentsData(res.getInt("pID"),0,"","",0));
		}
		stmt.close();
		connection.close();
		return data;
		
	}
	
	private Connection reconnect() throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
		return connection;
	}
}
