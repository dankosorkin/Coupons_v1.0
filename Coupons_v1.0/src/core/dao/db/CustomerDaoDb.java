package core.dao.db;

import java.util.List;

import core.beans.Customer;
import core.dao.CustomerDao;
import core.exceptions.CouponsException;
import core.pool.ConnectionPool;

public class CustomerDaoDb implements CustomerDao {

	private ConnectionPool pool;

	@Override
	public boolean isCustomerExists(String name, String password) throws CouponsException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addCustomer(Customer customer) throws CouponsException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCustomer(Customer customer) throws CouponsException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCustomer() throws CouponsException {
		// TODO Auto-generated method stub

	}

	@Override
	public Customer getOneCustomer(int customerId) throws CouponsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() throws CouponsException {
		// TODO Auto-generated method stub
		return null;
	}

}
