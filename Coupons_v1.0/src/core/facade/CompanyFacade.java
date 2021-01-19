package core.facade;

import core.beans.Company;
import core.beans.Coupon;
import core.exceptions.CouponsException;

public class CompanyFacade extends ClientFacade {

	private int id;

	public CompanyFacade() {
	}

	public int getId() {
		return id;
	}

	@Override
	protected boolean login(String email, String password) throws CouponsException {
		if (companiesDao.isExists(email, password)) {
			Company company = companiesDao.findByEmail(email);
			this.id = company.getId();
			return true;
		}
		return false;
	}

	public int addCoupon(Coupon coupon) throws CouponsException {
		return couponsDao.add(coupon);
	}

}
