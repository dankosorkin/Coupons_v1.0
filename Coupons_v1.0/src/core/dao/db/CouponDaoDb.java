package core.dao.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import core.beans.Category;
import core.beans.Coupon;
import core.dao.CouponDao;
import core.exceptions.CouponsException;
import core.pool.ConnectionPool;
import util.db.DB_Config;

public class CouponDaoDb implements CouponDao {

	private ConnectionPool pool;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int addCoupon(Coupon coupon) throws CouponsException {

		int id = 0;
		String sql = "INSERT INTO " + DB_Config.getDb_name() + ".Coupons VALUES(?,?,?,?,?,?,?,?,?)";

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, coupon.getCompanyId());
			pstmt.setInt(2, coupon.getCategory().ordinal() + 1);
			pstmt.setString(3, coupon.getTitle());
			pstmt.setString(4, coupon.getDescription());
			pstmt.setDate(5, Date.valueOf(coupon.getStartDate()));
			pstmt.setDate(6, Date.valueOf(coupon.getEndDate()));
			pstmt.setInt(7, coupon.getAmount());
			pstmt.setDouble(8, coupon.getPrice());
			pstmt.setString(9, coupon.getImage());

			id = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CouponsDAO: failed to add coupon", e);
		} finally {
			pstmt = null;

			if (conn != null)
				pool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return id;
	}

	@Override
	public void updateCoupon(Coupon coupon) throws CouponsException {

		String sql = "UPDATE " + DB_Config.getDb_name()
				+ " SET category_id=?, title=?, description=?, start_date=? end_date? amount=?, price=? image=? WHERE id=?";

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coupon.getCategory().ordinal() + 1);
			pstmt.setString(2, coupon.getTitle());
			pstmt.setString(3, coupon.getDescription());
			pstmt.setDate(4, Date.valueOf(coupon.getStartDate()));
			pstmt.setDate(5, Date.valueOf(coupon.getEndDate()));
			pstmt.setInt(6, coupon.getAmount());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());
			pstmt.setInt(9, coupon.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CouponsDAO: failed to update coupon", e);
		} finally {
			pstmt = null;

			if (conn != null)
				pool.getInstance().restoreConnection(conn);
			conn = null;
		}
	}

	@Override
	public void deleteCoupon(int couponId) throws CouponsException {

		String sql = "DELETE FROM " + DB_Config.getDb_name() + ".Coupons WHERE id=?";

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, couponId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponsException("[x] -> CouponsDAO: failed to delete coupon", e);
		} finally {
			pstmt = null;

			if (conn != null)
				pool.getInstance().restoreConnection(conn);
			conn = null;
		}

	}

	@Override
	public Coupon getOneCoupon(int couponId) throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Coupons WHERE id=?";

		Coupon coupon = null;

		try {
			conn = pool.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, couponId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				coupon = new Coupon();
				coupon.setId(rs.getInt("id"));
				coupon.setCompanyId(rs.getInt("company_id"));
				coupon.setCategory(Category.values()[rs.getInt("category_id") - 1]);
				coupon.setTitle(rs.getString("title"));
				coupon.setDescription(rs.getString("description"));
				coupon.setStartDate((rs.getDate("start_date")).toLocalDate());
				coupon.setEndDate((rs.getDate("end_date")).toLocalDate());
				coupon.setAmount(rs.getInt("amount"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));
			}

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CouponsDAO: failed to get coupon", e);
		} finally {
			rs = null;
			pstmt = null;

			if (conn != null)
				pool.restoreConnection(conn);
			conn = null;
		}

		return coupon;
	}

	@Override
	public List<Coupon> getAllCoupons() throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Coupons";

		List<Coupon> coupons = null;

		try {
			conn = pool.getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Coupon coupon = new Coupon();
				coupon.setId(rs.getInt("id"));
				coupon.setCompanyId(rs.getInt("company_id"));
				coupon.setCategory(Category.values()[rs.getInt("category_id") - 1]);
				coupon.setTitle(rs.getString("title"));
				coupon.setDescription(rs.getString("description"));
				coupon.setStartDate((rs.getDate("start_date")).toLocalDate());
				coupon.setEndDate((rs.getDate("end_date")).toLocalDate());
				coupon.setAmount(rs.getInt("amount"));
				coupon.setPrice(rs.getDouble("price"));
				coupon.setImage(rs.getString("image"));
				coupons.add(coupon);
			}

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CouponsDAO: failed to get all coupons", e);
		} finally {
			rs = null;

			if (conn != null)
				pool.restoreConnection(conn);
			conn = null;
		}

		return coupons;
	}

	@Override
	public void addCouponPurchaase(int customerId, int couponId) throws CouponsException {

		String sql = "INSERT INTO " + DB_Config.getDb_name() + ".Customers_VS_Coupons VALUES(?,?)";

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, couponId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CouponsDAO: failed to add coupon purchase", e);
		} finally {
			pstmt = null;

			if (conn != null)
				pool.restoreConnection(conn);
			conn = null;
		}
	}

	@Override
	public void deleteCouponPurchaase(int customerId, int couponId) throws CouponsException {

		String sql = "DELETE FROM" + DB_Config.getDb_name()
				+ ".Customers_VS_Coupons WHERE customer_id=? AND coupon_id=?";

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, couponId);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CouponsDAO: failed to delete coupon purchase", e);
		} finally {
			pstmt = null;

			if (conn != null)
				pool.restoreConnection(conn);
			conn = null;
		}
	}

}
