package core.dao.db;

import java.util.List;

import core.beans.Coupon;
import core.dao.CouponDao;
import core.exceptions.CouponsException;
import core.pool.ConnectionPool;

public class CouponDaoDb implements CouponDao {

	private ConnectionPool pool;

	@Override
	public int addCoupon(Coupon coupon) throws CouponsException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCoupon(Coupon coupon) throws CouponsException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCoupon(int couponId) throws CouponsException {
		// TODO Auto-generated method stub

	}

	@Override
	public Coupon getOneCoupon(int couponId) throws CouponsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Coupon> getAllCoupons() throws CouponsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCouponPurchaase(int customerId, int couponId) throws CouponsException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCouponPurchaase(int customerId, int couponId) throws CouponsException {
		// TODO Auto-generated method stub

	}

}
