package core.dao;

import core.exceptions.CouponsException;

// TODO
public interface TransactionDao {

	void startTransaction() throws CouponsException;

	void commitTransaction() throws CouponsException;

	void rollbackTransaction() throws CouponsException;
}
