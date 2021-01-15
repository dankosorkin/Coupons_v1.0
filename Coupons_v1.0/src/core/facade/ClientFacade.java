package core.facade;

import core.dao.CompanyDao;
import core.dao.CouponDao;
import core.dao.CustomerDao;

public abstract class ClientFacade {

	protected CouponDao couponsDao;
	protected CompanyDao companiesDao;
	protected CustomerDao customersDao;

	protected abstract boolean login(String email, String password);

}
