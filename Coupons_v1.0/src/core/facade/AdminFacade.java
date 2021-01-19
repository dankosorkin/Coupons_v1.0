package core.facade;

import java.util.List;

import core.beans.Company;
import core.beans.Coupon;
import core.beans.Customer;
import core.exceptions.CouponsException;

public class AdminFacade extends ClientFacade {

	private String email = "admin@admin.com";
	private String password = "admin";

	public AdminFacade() {
	}

	@Override
	protected boolean login(String email, String password) {
		return this.email == email && this.password == password;
	}

	/**
	 * The method add a company to the database and returns auto generated id from
	 * database.
	 */
	public int addCompany(Company company) throws CouponsException {

		if (companiesDao.findByName(company.getName()) == null && companiesDao.findByEmail(company.getEmail()) == null)
			return companiesDao.add(company);
		else
			throw new CouponsException("[x] OPERATION FAILED >>> add company: already exists");
	}

	public void updateCompany(Company company) throws CouponsException {
		companiesDao.update(company);
	}

	public void deleteCompany(int id) throws CouponsException {

		// if company found
		if (companiesDao.findById(id) != null) {
			// get all coupons of a company
			List<Coupon> coupons = couponsDao.findAllByCompanyId(id);

			// if coupons found
			if (coupons != null) {
				// 1. delete all coupon purchase from customer_vs_coupon
				for (Coupon coupon : coupons) {
					couponsDao.deletePurchase(coupon.getId());
					// 2. delete coupons
					couponsDao.delete(coupon.getId());
				}
			}
			// 3. delete company
			companiesDao.delete(id);
		}
	}

	public Company getOneCompany(int companyId) throws CouponsException {
		return companiesDao.findById(companyId);
	}

	public List<Company> getAllCompanies() throws CouponsException {
		return companiesDao.findAll();
	}

	public int addCustomer(Customer customer) throws CouponsException {
		if (!customersDao.isExists(customer.getEmail(), customer.getPassword()))
			return customersDao.add(customer);
		else
			throw new CouponsException("[x] OPERATION FAILED >>> add customer: already exists");
	}

	public void updateCustomer(Customer customer) throws CouponsException {
		customersDao.update(customer);
	}

	public Customer getOneCustomer(int customerId) throws CouponsException {
		return customersDao.findById(customerId);
	}

	public List<Customer> getAllCustomers() throws CouponsException {
		return customersDao.findAll();
	}

}
