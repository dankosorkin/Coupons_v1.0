package util.test;

import core.beans.Coupon;
import core.beans.Customer;
import core.exceptions.CouponsException;
import core.facade.AdminFacade;
import core.facade.CompanyFacade;
import core.facade.CustomerFacade;

public class TestCustomer {

	private static AdminFacade admin = new AdminFacade();
	private static CompanyFacade companyFacade = new CompanyFacade(2);

	public static void test() throws CouponsException {

		System.out.println("============ Customer test =============");
		Customer customer = admin.getOneCustomer(1);
		CustomerFacade facade = new CustomerFacade(customer.getId());

		System.out.println(customer);

		Coupon c1 = companyFacade.findOne(1);
		Coupon c2 = companyFacade.findOne(2);
		Coupon c3 = companyFacade.findOne(3);
		Coupon c4 = companyFacade.findOne(4);
		System.out.println(facade.addPurchase(c1));
		System.out.println(facade.addPurchase(c2));

	}

}
