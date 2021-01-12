package util.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import core.beans.Category;

public class DB_Builder {

	private static Connection conn;

	// create table queries
	private static final String COMPANIES = "CREATE TABLE IF NOT EXISTS Coupons_v1.Companies("
			+ "id INT primary key auto_increment,name VARCHAR(30),email VARCHAR(30),password VARCHAR(30));";
	private static final String CUSTOMERS = "CREATE TABLE IF NOT EXISTS Coupons_v1.Customers("
			+ "id INT primary key auto_increment,first_name VARCHAR(30),last_name VARCHAR(30),"
			+ "email VARCHAR(30),password VARCHAR(30));";
	private static final String CATEGORIES = "CREATE TABLE IF NOT EXISTS Coupons_v1.Categories("
			+ "id INT primary key auto_increment,category_id int);";
	private static final String COUPONS = "CREATE TABLE IF NOT EXISTS Coupons_v1.Coupons("
			+ "id INT primary key auto_increment,company_id int,category_id int,title VARCHAR(30),"
			+ "description VARCHAR(50),start_date date,end_date date,amount int,price double,"
			+ "image VARCHAR(50),foreign key (company_id) references Coupons_v1.Companies(id),"
			+ "foreign key (category_id) references Coupons_v1.Categories(id));";
	private static final String CUSTOMERS_VS_COUPONS = "CREATE TABLE IF NOT EXISTS Coupons_v1.Customers_VS_Coupons("
			+ "customer_id int primary key,coupon_id int,foreign key(customer_id) references Coupons_V1.Customers(id),"
			+ "foreign key(coupon_id) references Coupons_V1.Coupons(id));";

	// drop database
	private static final String DROP_DB = "DROP DATABASE " + DB_Config.getDb_name();

	public static void main(String[] args) {

		try {
			executeSqlQuery(DROP_DB);
			System.out.println("[v] -> database " + DB_Config.getDb_name() + " droped");
			executeSqlQuery(COMPANIES);
			System.out.println("[v] -> COMPANIES table created");
			executeSqlQuery(CUSTOMERS);
			System.out.println("[v] -> CUSTOMERS table created");
			executeSqlQuery(CATEGORIES);
			System.out.println("[v] -> CATEGORIES table created");
			executeSqlQuery(COUPONS);
			System.out.println("[v] -> COUPONS table created");
			executeSqlQuery(CUSTOMERS_VS_COUPONS);
			System.out.println("[v] -> CUSTOMERS_VS_COUPONS table created");
			populateCategories();
			System.out.println("[v] -> CATEGORIES table populated");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Static method executes single SQL statement
	 * 
	 * @throws SQLException
	 */
	public static void executeSqlQuery(String sql) throws SQLException {
		try {
			if (conn == null)
				conn = DriverManager.getConnection(DB_Config.getUrl(), DB_Config.getUser(), DB_Config.getPassword());

			Statement stmt = conn.createStatement();
			stmt.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				conn.close();
			conn = null;
		}
	}

	/**
	 * Static method iterates through Category enumeration and populate values to
	 * CATEGORIES table in a database. Values saved as ordinal.
	 * 
	 * @throws SQLException
	 */
	private static void populateCategories() throws SQLException {

		String sql = "INSERT INTO " + DB_Config.getDb_name() + ".Categories VALUES(0,?)";

		for (Category category : Category.values()) {
			try {
				if (conn == null)
					conn = DriverManager.getConnection(DB_Config.getUrl(), DB_Config.getUser(),
							DB_Config.getPassword());

				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, category.ordinal() + 1);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null)
					conn.close();
				conn = null;
			}
		}

	}

}
