package core.facade;

import core.beans.Company;
import core.beans.Coupon;
import core.exceptions.CouponsException;

public class CompanyFacade extends ClientFacade {

	private int id;

	public CompanyFacade(int id) {
		this.id = id;
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
		// look for the coupon in the database
		Coupon couponDB = couponsDao.findByTitle(coupon.getTitle());
		if (couponDB == null || coupon.getCompanyId() != this.id)
			return couponsDao.add(coupon);
		throw new CouponsException("[x] OPERATION FAILED >>> add coupon: already exists");
	}

}
