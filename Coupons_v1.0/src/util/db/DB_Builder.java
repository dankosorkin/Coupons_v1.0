package util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import core.beans.Category;
import core.exceptions.ConnectionPoolException;
import core.pool.ConnectionPool;

public class DB_Builder {

	private static ConnectionPool pool;
	private static Connection connection;

	// create table queries
	private static final String COMPANIES = "CREATE TABLE IF NOT EXISTS " + DB_Config.getDb_name() + ".Companies("
			+ "id INT primary key auto_increment,name VARCHAR(30),email VARCHAR(30),password VARCHAR(30));";
	private static final String CUSTOMERS = "CREATE TABLE IF NOT EXISTS " + DB_Config.getDb_name() + ".Customers("
			+ "id INT primary key auto_increment,first_name VARCHAR(30),last_name VARCHAR(30),"
			+ "email VARCHAR(30),password VARCHAR(30));";
	private static final String CATEGORIES = "CREATE TABLE IF NOT EXISTS " + DB_Config.getDb_name() + ".Categories("
			+ "id INT primary key auto_increment,category_id int);";
	private static final String COUPONS = "CREATE TABLE IF NOT EXISTS " + DB_Config.getDb_name() + ".Coupons("
			+ "id INT primary key auto_increment,company_id int,category_id int,title VARCHAR(30),"
			+ "description VARCHAR(50),start_date date,end_date date,amount int,price double,"
			+ "image VARCHAR(50),foreign key (company_id) references " + DB_Config.getDb_name() + ".Companies(id),"
			+ "foreign key (category_id) references " + DB_Config.getDb_name() + ".Categories(id));";
	private static final String CUSTOMERS_VS_COUPONS = "CREATE TABLE IF NOT EXISTS " + DB_Config.getDb_name()
			+ ".Customers_VS_Coupons(customer_id int,coupon_id int,CONSTRAINT PK primary key(customer_id,coupon_id),foreign key(customer_id) references "
			+ DB_Config.getDb_name() + ".Customers(id),foreign key(coupon_id) references " + DB_Config.getDb_name()
			+ ".Coupons(id));";

	// drop database
	private static final String DROP_DB = "DROP DATABASE " + DB_Config.getDb_name();

	public static void build() throws ConnectionPoolException {

		executeSqlQuery(DROP_DB);
		System.out.println("[v] -> database " + DB_Config.getDb_name() + " droped & recreated");
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
		System.out.println("=========== START TEST ============");
	}

	/**
	 * Static method executes single SQL statement
	 * 
	 * @throws ConnectionPoolException
	 */
	public static void executeSqlQuery(String sql) throws ConnectionPoolException {
		try {
			if (connection == null)
				connection = DriverManager.getConnection(DB_Config.getUrl(), DB_Config.getUser(),
						DB_Config.getPassword());

			Statement stmt = connection.createStatement();
			stmt.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				pool.getInstance().restoreConnection(connection);
			connection = null;
		}
	}

	/**
	 * Static method iterates through Category enumeration and populate values to
	 * CATEGORIES table in a database. Values saved as ordinal.
	 * 
	 * @throws ConnectionPoolException
	 */
	private static void populateCategories() throws ConnectionPoolException {

		String sql = "INSERT INTO " + DB_Config.getDb_name() + ".Categories VALUES(0,?)";

		for (Category category : Category.values()) {
			try {
				if (connection == null)
					connection = DriverManager.getConnection(DB_Config.getUrl(), DB_Config.getUser(),
							DB_Config.getPassword());

				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, category.ordinal() + 1);
				pstmt.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (connection != null)
					pool.getInstance().restoreConnection(connection);
				connection = null;
			}
		}

	}

}
