package core.beans;

import java.util.List;

/**
 * Class describes a Company instance
 * 
 * @autor Daniel Sorkin
 * 
 *        last update 2021-1-12
 */
public class Company {

	// attributes
	private int id;
	private String name;
	private String email;
	private String password;
	private List<Coupon> coupons;

	// empty constructor
	public Company() {
	}

	// constructor with arguments
	public Company(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * The method returns company id
	 * 
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**
	 * The method sets company id
	 * 
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * The method returns company name
	 * 
	 * @return String name
	 */
	public String getName() {
		return name;
	}

	/**
	 * The method sets company name
	 * 
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The method returns company email
	 * 
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * The method sets company email
	 * 
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * The method returns company password
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * The method sets company password
	 * 
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * The method returns List of all the coupons belonging to a company
	 * 
	 * @return List<Coupon> coupons
	 */
	public List<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * The method sets List of the coupons to a company
	 */
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	/**
	 * String representation of a company
	 */
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
