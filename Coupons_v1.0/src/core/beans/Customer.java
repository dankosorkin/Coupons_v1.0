package core.beans;

import java.util.List;

/**
 * Class describes a customer instance
 * 
 * @autor Daniel Sorkin
 * 
 *        last update 2021-1-11
 */
public class Customer {

	// attributes
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Coupon> coupons;

	// empty constructor
	public Customer() {
	}

	// constructor with arguments
	public Customer(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/**
	 * The method returns a customer id
	 * 
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**
	 * The method sets a customer id
	 * 
	 * @param int id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * The method returns a customer first name
	 * 
	 * @return String firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * The method sets a customer first name
	 * 
	 * @param String firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * The method returns a customer last name
	 * 
	 * @return String lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * The method sets a customer last name
	 * 
	 * @param String lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * The method returns a customer email
	 * 
	 * @return String email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * The method sets a customer email
	 * 
	 * @param String email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * The method returns a customer password
	 * 
	 * @return String password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * The method sets a customer password
	 * 
	 * @param String password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * The method returns list of all the coupons belongs to a customer
	 * 
	 * @return List<Coupon> coupons
	 */
	public List<Coupon> getCoupons() {
		return coupons;
	}

	/**
	 * The method sets list of all the coupons belongs to a customer
	 * 
	 * @param List<Coupon> coupons
	 */
	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	/**
	 * String representation of a customer
	 */
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}

}
