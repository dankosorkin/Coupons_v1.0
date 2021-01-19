package core.dao;

import java.util.List;

import core.beans.Coupon;
import core.exceptions.CouponsException;

public interface CouponDao {

	int add(Coupon coupon) throws CouponsException;

	void update(Coupon coupon) throws CouponsException;

	void delete(int id) throws CouponsException;

	Coupon findById(int id) throws CouponsException;

	Coupon findByTitle(String title) throws CouponsException;

	List<Coupon> findAll() throws CouponsException;

	List<Coupon> findAllByCompanyId(int id) throws CouponsException;

	List<Coupon> findAllByCustomerId(int id) throws CouponsException;

	void addPurchase(int customerId, int couponId) throws CouponsException;

	void deletePurchase(int couponId) throws CouponsException;

	void deleteCustomerPurchase(int customerId, int couponId) throws CouponsException;

}
