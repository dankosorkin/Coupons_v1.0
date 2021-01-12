package util.sql;

public class DB_Config {

	private String driver = "";
	private String protocol = "jdbc:mysql://";
	private String host = "127.0.0.1";
	private String port = ":3306";
	private String db_name = "/Coupons_v1";
	private String parametres = "?createDatabaseIfNotExist=true&serverTimezone=UTC";
	private String url =protocol+host+port+db_name+parametres ;

	private String user = "root";
	private String password = "pass1";

}
