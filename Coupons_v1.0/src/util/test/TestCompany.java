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

	public static void test() throws CouponsException {

		System.out.println("============ Company test =============");
//			Company company = admin.getOneCompany(facade.getId());
		Company company = admin.getOneCompany(2);
		CompanyFacade facade = new CompanyFacade(company.getId());

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
		Coupon cp6 = new Coupon(1, Category.VACATION, "Africa", "3 weekend nights at 5* hotel",
				LocalDate.of(2021, 01, 01), LocalDate.of(2021, 01, 10), 10, 5.90, null);

		cp1.setId(facade.addCoupon(cp1));
		cp2.setId(facade.addCoupon(cp2));
		cp3.setId(facade.addCoupon(cp3));
		cp4.setId(facade.addCoupon(cp4));
		cp5.setId(facade.addCoupon(cp5));

		// add coupon that already exists by title -> exception
		// in this case coupon with the same title have different company_id ->
		// operation allowed
		cp6.setId(facade.addCoupon(cp6));

		System.out.println("All company coupons: " + facade.findAll());

		// update coupon 2
		cp2.setTitle("Spain"); // operation not allowed and will ignored for test
		cp2.setAmount(5);
		cp2.setCategory(Category.VACATION);
		cp2.setPrice(3.90);
		facade.updateCoupon(cp2);
		System.out.println("All company coupons (updated coupon 2): " + facade.findAll());

		// delete coupon
		facade.deleteCoupon(cp1);

	}

}
