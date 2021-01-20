package util.test;

import core.beans.Customer;
import core.exceptions.CouponsException;
import core.facade.AdminFacade;
import core.facade.CustomerFacade;

public class TestCustomer {

	private static AdminFacade admin = new AdminFacade();

	public static void test() throws CouponsException {

		System.out.println("============ Customer test =============");
		Customer customer = admin.getOneCustomer(1);
		CustomerFacade facade = new CustomerFacade(customer.getId());

		System.out.println(customer);

	}

}
