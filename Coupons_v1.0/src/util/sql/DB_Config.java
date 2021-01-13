package util.sql;

/**
 * Database configuration file. Default connection to MySQL server.
 */
public class DB_Config {

	// driver and connection
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String PROTOCOL = "jdbc:mysql://";
	private final static String HOST = "127.0.0.1";
	private final static String PORT = ":3306/";
	private final static String DB = "coupons_v1";
	private final static String PARAMS = "?createDatabaseIfNotExist=true&serverTimezone=UTC";
	private final static String URL = PROTOCOL + HOST + PORT + DB + PARAMS;

	// user credentials
	private final static String USER = "root";
	private final static String PASS = "pass1";

	/**
	 * Static method returns driver
	 * 
	 * @return String DRIVER
	 */
	public static String getDriver() {
		return DRIVER;
	}

	/**
	 * Static method returns database name
	 * 
	 * @return String
	 */
	public static String getDb_name() {
		return DB;
	}

	/**
	 * Static method returns connection URL
	 * 
	 * @return String
	 */
	public static String getUrl() {
		return URL;
	}

	/**
	 * Static method returns user name
	 * 
	 * @return String
	 */
	public static String getUser() {
		return USER;
	}

	/**
	 * Static method returns user password
	 * 
	 * @return String
	 */
	public static String getPassword() {
		return PASS;
	}

}
