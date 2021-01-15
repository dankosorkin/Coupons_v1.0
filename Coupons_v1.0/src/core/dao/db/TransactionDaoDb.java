package core.dao.db;

import java.sql.Connection;

import core.dao.TransactionDao;
import core.exceptions.ConnectionPoolException;
import core.exceptions.CouponsException;
import core.pool.ConnectionPool;

// TODO
public class TransactionDaoDb implements TransactionDao {

	private ConnectionPool pool;
	private Connection conn;
	private CompanyDaoDb companyDao = new CompanyDaoDb();

	public TransactionDaoDb() throws ConnectionPoolException {
	}

	@Override
	public void startTransaction() throws CouponsException {

	}

	@Override
	public void commitTransaction() throws CouponsException {

	}

	@Override
	public void rollbackTransaction() throws CouponsException {

	}

}
