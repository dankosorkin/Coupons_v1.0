package core.dao;

import core.exceptions.CouponsException;

public interface TransactionDao {

	void startTransaction() throws CouponsException;

	void commitTransaction() throws CouponsException;

	void rollbackTransaction() throws CouponsException;
}
