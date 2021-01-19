package core.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.beans.Customer;
import core.dao.CustomerDao;
import core.exceptions.CouponsException;
import core.pool.ConnectionPool;
import util.db.DB_Config;

public class CustomerDaoDb implements CustomerDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private List<Customer> customers;

	@Override
	public boolean isExists(String email, String password) throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Customers WHERE email=? AND password=?";

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CustomersDAO: failed to check check if customer exist", e);
		} finally {

			rs = null;
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;

		}

		return false;
	}

	@Override
	public int add(Customer customer) throws CouponsException {

		int id = 0;

		String sql = "INSERT INTO " + DB_Config.getDb_name() + ".Customers VALUES(0,?,?,?,?)";

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next())
				id = rs.getInt(1);

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CustomersDAO: failed to add customer", e);
		} finally {
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return id;
	}

	@Override
	public void update(Customer customer) throws CouponsException {

		String sql = "UPDATE " + DB_Config.getDb_name()
				+ ".Customers SET first_name=?, last_name=?, email=?, password=? WHERE id=?";

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, customer.getFirstName());
			pstmt.setString(2, customer.getLastName());
			pstmt.setString(3, customer.getEmail());
			pstmt.setString(4, customer.getPassword());
			pstmt.setInt(5, customer.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CustomersDAO: failed to update customer", e);
		} finally {
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}
	}

	@Override
	public Customer delete(int customerId) throws CouponsException {

		String sql = "DELETE FROM " + DB_Config.getDb_name() + ".Customers WHERE id=?";

		Customer customer = null;

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CustomersDAO: failed to remove customer", e);
		} finally {
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return customer;
	}

	@Override
	public Customer findById(int customerId) throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Customers WHERE id=?";

		Customer customer = null;

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CustomersDAO: failed to get customer from database", e);
		} finally {
			rs = null;
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return customer;
	}

	@Override
	public List<Customer> findAll() throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Customers";

		customers = new ArrayList<Customer>();

		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("password"));
				customers.add(customer);
			}

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CustomersDAO: failed to get customers list from database", e);
		} finally {
			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return customers;
	}

}
