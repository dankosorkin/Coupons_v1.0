package util.test;

import core.beans.Company;
import core.dao.CompanyDao;
import core.dao.db.CompanyDaoDb;
import core.exceptions.CouponsException;
import core.facade.AdminFacade;
import util.db.DB_Builder;

public class Test1 {

	private static CompanyDao companyDao = new CompanyDaoDb();
	private static AdminFacade admin = new AdminFacade();

	public static void main(String[] args) {

		try {

			DB_Builder.build();

			Company com1 = new Company("FlyCarpet", "carpet@mail.com", "1234");
			Company com2 = new Company("ElAl", "elal@mail.com", "1234");

			com1.setId(admin.addCompany(com1));
			System.out.println(com1);

			com2.setId(admin.addCompany(com2));
			System.out.println(com2);

		} catch (CouponsException e) {
			e.printStackTrace();
		} finally {

		}
	}

}
