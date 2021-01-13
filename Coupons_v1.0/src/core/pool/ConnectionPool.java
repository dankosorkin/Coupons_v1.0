package core.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import core.exceptions.ConnectionPoolException;
import util.db.DB_Config;

/**
 * Singleton class with lazy initialization. ConnectionPool instance manages
 * pool of connections via synchronized methods getConnection() and
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
			throw new ConnectionPoolException(
					"[x] CoonectionPool CTOR -> there is a problem to add connection to the pool", e);
		} catch (ClassNotFoundException e) {
			throw new ConnectionPoolException("[x] CoonectionPool CTOR -> there is a problem to load database driver",
					e);
		}

	}

	/**
	 * Static method returns single instance of ConnectionPool on every call of the
	 * method
	 * 
	 * @throws ConnectionPoolException
	 */
	public static ConnectionPool getInstance() throws ConnectionPoolException {
		if (instance == null)
			instance = new ConnectionPool();

		return instance;
	}

	/**
	 * Synchronized method remove one connection from the pool. In case of empty
	 * pool the method calls to method wait() for caller thread.
	 * 
	 * @throws ConnectionPoolException
	 */
	public synchronized Connection getConnection() throws ConnectionPoolException {

		// if pool is empty -> wait
		while (pool.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new ConnectionPoolException(
						"[x] CoonectionPool.getConnection() -> waiting for a connection were interrupted", e);
			}
		}

		Iterator<Connection> it = pool.iterator();
		Connection conn = it.next();
		it.remove();

		return conn;
	}

	/**
	 * Synchronized method returns a connection to the pool and notifies waiting
	 * threads
	 */
	public synchronized void restoreConnection(Connection connection) {
		pool.add(connection);
		notifyAll();
	}

	/**
	 * Synchronized method close all connections and release memory resource.
	 * 
	 * @throws ConnectionPoolException
	 */
	public synchronized void closeAllConnections() throws ConnectionPoolException {

		int counter = 0;

		while (counter < POOL_SIZE) {

			while (pool.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					throw new ConnectionPoolException(
							"[x] CoonectionPool.closeAllConnections() -> waiting for a connection were interrupted", e);
				}
			}

			while (!pool.isEmpty()) {

				try {
					Iterator<Connection> it = pool.iterator();
					it.next().close();
					it.remove();
				} catch (SQLException e) {
					throw new ConnectionPoolException(
							"[x] CoonectionPool.closeAllConnections() -> there is a problem to close connection", e);
				}

				counter++;
			}

		}
	}

}
