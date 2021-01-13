package core.dao;

import java.util.List;

import core.beans.Coupon;
import core.exceptions.CouponsException;

public interface CouponDao {

	int addCoupon(Coupon coupon) throws CouponsException;

	void updateCoupon(Coupon coupon) throws CouponsException;

	void deleteCoupon(int couponId) throws CouponsException;

	Coupon getOneCoupon(int couponId) throws CouponsException;

	List<Coupon> getAllCoupons() throws CouponsException;

	void addCouponPurchaase(int customerId, int couponId) throws CouponsException;

	void deleteCouponPurchaase(int customerId, int couponId) throws CouponsException;

}
