package core.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.beans.Company;
import core.dao.CompanyDao;
import core.exceptions.CouponsException;
import core.pool.ConnectionPool;
import util.db.DB_Config;

public class CompanyDaoDb implements CompanyDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private List<Company> companies;

	@Override
	public boolean isExists(String email, String password) throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Companies WHERE email=? AND password=?";

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next())
				return true;

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to check check if company exist", e);
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
	public int add(Company company) throws CouponsException {

		int id = 0;
		String sql = "INSERT INTO " + DB_Config.getDb_name() + ".Companies VALUES(0,?,?,?)";

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			if (rs.next())
				id = rs.getInt(1);

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to add company", e);
		} finally {
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return id;
	}

	@Override
	public void update(Company company) throws CouponsException {

		String sql = "UPDATE " + DB_Config.getDb_name() + ".Companies SET email=?, password=? WHERE id=?";

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ((Company) company).getEmail());
			pstmt.setString(2, ((Company) company).getPassword());
			pstmt.setInt(3, ((Company) company).getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to update company", e);
		} finally {
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}
	}

	@Override
	public void delete(int id) throws CouponsException {

		String sql = "DELETE FROM " + DB_Config.getDb_name() + ".Companies WHERE id=?";

		Company company = null;

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to remove company", e);
		} finally {
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}

	}

	@Override
	public Company findById(int companyId) throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Companies WHERE id=?";

		Company company = null;

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, companyId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to get company from database", e);
		} finally {
			rs = null;
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return company;
	}

	@Override
	public Company findByName(String name) throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Companies WHERE name=?";

		Company company = null;

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to get company from database", e);
		} finally {
			rs = null;
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return company;
	}

	@Override
	public Company findByEmail(String email) throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Companies WHERE email=?";

		Company company = null;

		try {
			conn = ConnectionPool.getInstance().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to get company from database", e);
		} finally {
			rs = null;
			pstmt = null;

			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return company;
	}

	@Override
	public List<Company> findAll() throws CouponsException {
		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Companies";

		companies = new ArrayList<Company>();

		try {
			conn = ConnectionPool.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Company company = new Company();
				company.setId(rs.getInt("id"));
				company.setName(rs.getString("name"));
				company.setEmail(rs.getString("email"));
				company.setPassword(rs.getString("password"));
				companies.add(company);
			}

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to get compaies list from database", e);
		} finally {
			if (conn != null)
				ConnectionPool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return companies;
	}

}
