package core.facade;

import java.util.List;

import core.beans.Company;
import core.beans.Coupon;
import core.dao.db.CompanyDaoDb;
import core.dao.db.CouponDaoDb;
import core.exceptions.CouponsException;

public class AdminFacade extends ClientFacade {

	private String email = "admin@admin.com";
	private String password = "admin";

	public AdminFacade() {
		super.companiesDao = new CompanyDaoDb();
		super.couponsDao = new CouponDaoDb();
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

	public Company deleteCompany(int id) throws CouponsException {
		// if company found
		if (companiesDao.findById(id) != null) {
			// get all coupons of a company
			List<Coupon> coupons = couponsDao.findAllByCompanyId(id);

			// if coupons found
			if (coupons != null) {
				// 1. delete all coupon purchase from customer_vs_coupon

				// 2. delete coupons

			}

		}

		// 3. delete company
		Company company = companiesDao.delete(id);

		if (company != null)
			return company;
		else
			throw new CouponsException("[x] OPERATION FAILED >>> delete company: not found");
	}

}
