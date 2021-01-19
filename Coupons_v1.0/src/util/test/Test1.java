package util.test;

import core.beans.Company;
import core.exceptions.CouponsException;
import core.facade.AdminFacade;
import util.db.DB_Builder;

public class Test1 {

	private static AdminFacade admin = new AdminFacade();

	public static void main(String[] args) {

		try {

			DB_Builder.build();

			Company com1 = new Company("FlyCarpet", "carpet@mail.com", "1234");
			Company com2 = new Company("ElAl", "elal@mail.com", "1234");
			Company com3 = new Company("Sony", "sony@mail.com", "1234");
			Company com4 = new Company("Samsung", "samsung@mail.com", "1234");
			Company com5 = new Company("LG", "lg@mail.com", "1234");

			com1.setId(admin.addCompany(com1));
			com2.setId(admin.addCompany(com2));
			com3.setId(admin.addCompany(com3));
			com4.setId(admin.addCompany(com4));
			com5.setId(admin.addCompany(com5));

			System.out.println(com1);
			System.out.println(com2);
			System.out.println(com3);
			System.out.println(com4);
			System.out.println(com5);

		} catch (CouponsException e) {
			e.printStackTrace();
		} finally {

		}
	}

}
