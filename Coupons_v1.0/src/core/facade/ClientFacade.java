package core.facade;

import core.dao.CompanyDao;
import core.dao.CouponDao;
import core.dao.CustomerDao;
import core.dao.db.CompanyDaoDb;
import core.dao.db.CouponDaoDb;
import core.dao.db.CustomerDaoDb;
import core.exceptions.CouponsException;

public abstract class ClientFacade {

	protected CouponDao couponsDao = new CouponDaoDb();
	protected CompanyDao companiesDao = new CompanyDaoDb();
	protected CustomerDao customersDao = new CustomerDaoDb();

	protected abstract boolean login(String email, String password) throws CouponsException;

}
