package util.test;

import core.beans.Company;
import core.exceptions.CouponsException;
import core.facade.AdminFacade;
import util.db.DB_Builder;

public class Test1 {

	private static AdminFacade admin = new AdminFacade();

	public static void main(String[] args) {

		try {

			// create database
			DB_Builder.build();

			// create companies
			Company com1 = new Company("FlyingCarpet", "carpet@mail.com", "1234");
			Company com2 = new Company("ElAl", "elal@mail.com", "1234");
			Company com3 = new Company("Sony", "sony@mail.com", "1234");
			Company com4 = new Company("Samsung", "samsung@mail.com", "1234");
			Company com5 = new Company("LG", "lg@mail.com", "1234");
			Company com6 = new Company("LG", "lg@mail.com", "1234");

			// add companies to database
			com1.setId(admin.addCompany(com1));
			com2.setId(admin.addCompany(com2));
			com3.setId(admin.addCompany(com3));
			com4.setId(admin.addCompany(com4));
			com5.setId(admin.addCompany(com5));

			// adding company that already exist -> exception
//			com6.setId(admin.addCompany(com6));
			System.out.println(admin.getAllCompanies());

//			com1.setName("NewName");
//			com1.setEmail("update@mail.com");
//			com1.setPassword("pass1");
//			admin.updateCompany(com1);
//			System.out.println(admin.getAllCompanies());
//
//			admin.deleteCompany(com5.getId());
//			System.out.println(admin.getAllCompanies());

		} catch (CouponsException e) {
			e.printStackTrace();
		} finally {

		}
	}

}
