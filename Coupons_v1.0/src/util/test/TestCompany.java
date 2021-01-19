package util.test;

import java.time.LocalDate;

import core.beans.Category;
import core.beans.Company;
import core.beans.Coupon;
import core.exceptions.CouponsException;
import core.facade.AdminFacade;
import core.facade.CompanyFacade;

public class TestCompany {

	private static AdminFacade admin = new AdminFacade();
	private static CompanyFacade facade = new CompanyFacade();

	public static void test() throws CouponsException {

		System.out.println("============ Company test =============");
//			Company company = admin.getOneCompany(facade.getId());
		Company company = admin.getOneCompany(2);
		System.out.println(company);

		Coupon cp1 = new Coupon(company.getId(), Category.VACATION, "Turkey", "3 weekend nights at 5* hotel",
				LocalDate.of(2021, 01, 01), LocalDate.of(2021, 01, 10), 10, 5.90, null);
		Coupon cp2 = new Coupon(company.getId(), Category.TRAVELLING, "Germany", "3 weekend nights at 5* hotel",
				LocalDate.of(2021, 01, 01), LocalDate.of(2021, 01, 10), 10, 5.90, null);
		Coupon cp3 = new Coupon(company.getId(), Category.VACATION, "Austria", "3 weekend nights at 5* hotel",
				LocalDate.of(2021, 01, 01), LocalDate.of(2021, 01, 10), 10, 5.90, null);
		Coupon cp4 = new Coupon(company.getId(), Category.VACATION, "Russia", "3 weekend nights at 5* hotel",
				LocalDate.of(2021, 01, 01), LocalDate.of(2021, 01, 10), 10, 5.90, null);
		Coupon cp5 = new Coupon(company.getId(), Category.VACATION, "Africa", "3 weekend nights at 5* hotel",
				LocalDate.of(2021, 01, 01), LocalDate.of(2021, 01, 10), 10, 5.90, null);

		cp1.setId(facade.addCoupon(cp1));

		System.out.println(cp1);

	}

}
