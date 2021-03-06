package core.facade;

import java.util.List;

import core.beans.Category;
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

	public void updateCoupon(Coupon coupon) throws CouponsException {
		couponsDao.update(coupon);
	}

	public void deleteCoupon(Coupon coupon) throws CouponsException {
		// check if exists and belongs to a company
		Coupon couponDB = couponsDao.findById(coupon.getId());

		if (couponDB != null && couponDB.getCompanyId() == this.id) {
			// check purchases and delete them
			couponsDao.deletePurchase(coupon.getId());
			// delete coupon
			couponsDao.delete(coupon.getId());

		} else {
			throw new CouponsException(
					"[x] OPERATION FAILED >>> delete coupon: not exists or not belonging to company_id=" + this.id);
		}
	}

	public Coupon findOne(int id) throws CouponsException {
		return couponsDao.findById(id);
	}

	public List<Coupon> findAll() throws CouponsException {
		return couponsDao.findAllByCompanyId(this.id);
	}

	public List<Coupon> findAllByCategory(Category category) throws CouponsException {
		return couponsDao.findAllByCategory(this.id, category);
	}

	public List<Coupon> findAllByPrice(double price) throws CouponsException {
		return couponsDao.findAllByPrice(this.id, price);
	}

}
