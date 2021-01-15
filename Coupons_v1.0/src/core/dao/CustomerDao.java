package core.dao;

import java.util.List;

import core.beans.Customer;
import core.exceptions.CouponsException;

public interface CustomerDao {

	boolean isCustomerExists(String email, String password) throws CouponsException;

	int addCustomer(Customer customer) throws CouponsException;

	void updateCustomer(Customer customer) throws CouponsException;

	void deleteCustomer(int customerId) throws CouponsException;

	Customer getOneCustomer(int customerId) throws CouponsException;

	List<Customer> getAllCustomers() throws CouponsException;

}
