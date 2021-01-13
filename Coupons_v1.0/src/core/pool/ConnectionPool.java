package core.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import util.sql.DB_Config;

public class ConnectionPool {

	private static ConnectionPool instance;
	private static Set<Connection> pool = new HashSet<Connection>();
	private static final int POOL_SIZE = 10;

	private ConnectionPool() throws SQLException {
		if (instance == null)
			instance = new ConnectionPool();

		for (int i = 0; i < POOL_SIZE; i++) {
			pool.add(DriverManager.getConnection(DB_Config.getUrl(), DB_Config.getUser(), DB_Config.getPassword()));
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public synchronized Connection getConnection() {

		while (pool.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Iterator<Connection> it = pool.iterator();
		Connection conn = it.next();
		it.remove();

		return conn;
	}

	public synchronized void restoreConnection(Connection connection) {
		pool.add(connection);
		notifyAll();
	}

	public synchronized void closeAllConnections() {

		int counter = 0;

		while (counter < POOL_SIZE) {

			while (pool.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			while (!pool.isEmpty()) {

				try {
					Iterator<Connection> it = pool.iterator();
					it.next().close();
					it.remove();
				} catch (SQLException e) {
					e.printStackTrace();
				}

				counter++;
			}

		}
	}

}
