package core.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import core.beans.Company;
import core.dao.CompanyDao;
import core.exceptions.CouponsException;
import core.pool.ConnectionPool;
import util.db.DB_Config;

public class CompanyDaoDb implements CompanyDao {

	private ConnectionPool pool;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public boolean isCompanyExists(String email, String password) throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Companies WHERE email=? AND password=?";

		try {
			conn = pool.getConnection();
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
				pool.getInstance().restoreConnection(conn);
			conn = null;

		}

		return false;

	}

	@Override
	public int addCompany(Company company) throws CouponsException {

		int id = 0;
		String sql = "INSERT INTO " + DB_Config.getDb_name() + ".Companies VALUES(?,?,?)";

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, company.getName());
			pstmt.setString(2, company.getEmail());
			pstmt.setString(3, company.getPassword());

			id = pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to add company", e);
		} finally {
			pstmt = null;

			if (conn != null)
				pool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return id;
	}

	@Override
	public void updateCompany(Company company) throws CouponsException {

		String sql = "UPDATE " + DB_Config.getDb_name() + ".Companies SET email=?, password=? WHERE id=?";

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, company.getEmail());
			pstmt.setString(2, company.getPassword());
			pstmt.setInt(3, company.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to update company", e);
		} finally {
			pstmt = null;

			if (conn != null)
				pool.getInstance().restoreConnection(conn);
			conn = null;
		}
	}

	@Override
	public void deleteCompany(int companyId) throws CouponsException {

		String sql = "DELETE FROM " + DB_Config.getDb_name() + ".Companies WHERE id=?";

		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, companyId);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponsException("[x] -> CompaniyDAO: failed to remove company", e);
		} finally {
			pstmt = null;

			if (conn != null)
				pool.getInstance().restoreConnection(conn);
			conn = null;
		}

	}

	@Override
	public Company getOneCompany(int companyId) throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Companies WHERE id=?";

		Company company = null;

		try {
			conn = pool.getConnection();
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
				pool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return company;
	}

	@Override
	public List<Company> getAllCompanies() throws CouponsException {

		String sql = "SELECT * FROM " + DB_Config.getDb_name() + ".Companies";

		List<Company> companies = null;

		try {
			conn = pool.getConnection();
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
				pool.getInstance().restoreConnection(conn);
			conn = null;
		}

		return companies;
	}

}
