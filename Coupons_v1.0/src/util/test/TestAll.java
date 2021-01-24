package util.test;

import core.exceptions.CouponsException;
import util.db.DB_Builder;

public class TestAll {

	public static void main(String[] args) {

		try {

			// create database
			DB_Builder.build();

			TestAdmin.test();
			TestCompany.test();
//			TestCustomer.test();

		} catch (CouponsException e) {
			e.printStackTrace();
		}

	}

}
