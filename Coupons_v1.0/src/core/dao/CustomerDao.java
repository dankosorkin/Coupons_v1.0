package core.dao;

import java.util.List;

import core.beans.Customer;
import core.exceptions.CouponsException;

public interface CustomerDao {

	boolean isExists(String email, String password) throws CouponsException;

	int add(Customer customer) throws CouponsException;

	void update(Customer customer) throws CouponsException;

	Customer delete(int id) throws CouponsException;

	Customer findById(int id) throws CouponsException;

	List<Customer> findAll() throws CouponsException;

}
