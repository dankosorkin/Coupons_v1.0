package core.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import core.exceptions.ConnectionPoolException;
import util.sql.DB_Config;

/**
 * Singleton class with lazy initialization. ConnectionPool instance manages
 * pool of connections via syncrhonzed methods getConnection() and
 * restoreConnection().
 */
public class ConnectionPool {

	// attributes
	private static ConnectionPool instance;
	private static Set<Connection> pool = new HashSet<Connection>();
	private static final int POOL_SIZE = 10;

	// private constructor loads driver and populates pool with connections
	private ConnectionPool() throws ConnectionPoolException {
		try {
			Class.forName(DB_Config.getDriver());
			for (int i = 0; i < POOL_SIZE; i++)
				pool.add(DriverManager.getConnection(DB_Config.getUrl(), DB_Config.getUser(), DB_Config.getPassword()));
		} catch (SQLException e) {
			throw new ConnectionPoolException("", e);
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("There is a problem to load database driver", e);
		}

	}

	public static ConnectionPool getInstance() throws ConnectionPoolException {
		if (instance == null)
			instance = new ConnectionPool();

		return instance;
	}

	public synchronized Connection getConnection() throws ConnectionPoolException {

		while (pool.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new ConnectionPoolException("POOL", e);
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
