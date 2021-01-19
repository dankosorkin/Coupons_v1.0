package core.dao;

import java.util.List;

import core.beans.Company;
import core.exceptions.CouponsException;

public interface CompanyDao {

	boolean isExists(String email, String password) throws CouponsException;

	int add(Company company) throws CouponsException;

	void update(Company company) throws CouponsException;

	void delete(int id) throws CouponsException;

	Company findById(int id) throws CouponsException;

	Company findByName(String name) throws CouponsException;

	Company findByEmail(String email) throws CouponsException;

	List<Company> findAll() throws CouponsException;

}
