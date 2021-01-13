package core.dao;

import java.util.List;

import core.beans.Company;
import core.exceptions.CouponsException;

public interface CompanyDao {

	boolean isCompanyExists(String name, String password) throws CouponsException;

	int addCompany(Company company) throws CouponsException;

	void updateCompany(Company company) throws CouponsException;

	void deleteCompany() throws CouponsException;

	Company getOneCompany(int companyId) throws CouponsException;

	List<Company> getAllCompanies() throws CouponsException;

}
