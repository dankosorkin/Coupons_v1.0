package core.facade;

import java.util.List;

import core.beans.Coupon;
import core.exceptions.CouponsException;

public class CustomerFacade extends ClientFacade {

	private int id;

	public CustomerFacade(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	protected boolean login(String email, String password) throws CouponsException {
		return customersDao.isExists(email, password);
	}

	public boolean addPurchase(Coupon coupon) throws CouponsException {
		List<Coupon> coupons = couponsDao.findAllByCustomerId(this.id);
		boolean isPurchased = false;
		// check if already purchased
		if (coupons != null) {
			for (Coupon couponDB : coupons) {
				if (couponDB.getId() == coupon.getId()) {
					isPurchased = true;
				}
			}
		}
		// if not:
		if (!isPurchased) {
			couponsDao.addPurchase(this.id, coupon.getId());
			coupon.setAmount(coupon.getAmount() - 1);
			couponsDao.update(coupon);
		}
		return !isPurchased;

	}

}
