package util.test;

import core.beans.Company;
import core.beans.Customer;
import core.exceptions.CouponsException;
import core.facade.AdminFacade;
import util.db.DB_Builder;

public class TestAdmin {

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
			System.out.println("All companies: " + admin.getAllCompanies());

			// update company
			com1.setName("NewName");
			com1.setEmail("update@mail.com");
			com1.setPassword("pass1");
			admin.updateCompany(com1);
			System.out.println("All companies(update company 1): " + admin.getAllCompanies());

			// delete company
			admin.deleteCompany(com3.getId());
			System.out.println("All companies (delete company 3): " + admin.getAllCompanies());

			// get one company by its id
			System.out.println("One company: " + admin.getOneCompany(2));

			// ==============================================
			Customer cs1 = new Customer("Shay", "Mizrahi", "shay@test.com", "shay1234");
			Customer cs2 = new Customer("Eden", "Yefet", "eden@test.com", "eden1234");
			Customer cs3 = new Customer("Daniel", "Sorkin", "or@test.com", "or1234");
			Customer cs4 = new Customer("Eldad", "Gold", "eldad@test.com", "eldad1234");
			Customer cs5 = new Customer("Gal", "Halperin", "gal@test.com", "gal1234");

			admin.addCustomer(cs1);
			admin.addCustomer(cs2);
			admin.addCustomer(cs3);
			admin.addCustomer(cs4);
			admin.addCustomer(cs5);
			System.out.println("All customers: " + admin.getAllCustomers());

		} catch (CouponsException e) {
			e.printStackTrace();
		} finally {

		}
	}

}
