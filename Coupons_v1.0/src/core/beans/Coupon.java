package core.beans;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class describes a coupon instance
 * 
 * @autor Daniel Sorkin
 * 
 *        last update 2021-1-12
 */
public class Coupon implements Serializable {

	private static final long serialVersionUID = 1L;

	// attributes
	private int id;
	private int companyId;
	private Category category;
	private String title;
	private String description;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private double price;
	private String image;

	// empty constructor
	public Coupon() {
	}

	// constructor with arguments
	public Coupon(int companyId, Category category, String title, String description, LocalDate startDate,
			LocalDate endDate, int amount, double price, String image) {
		this.companyId = companyId;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	/**
	 * The method returns a coupon id
	 * 
	 * @return int id
	 */
	public int getId() {
		return id;
	}

	/**
	 * The method sets a coupon id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * The method returns id of the company a coupon belongs to
	 * 
	 * @return int companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * The method sets id of the company a coupon belongs to
	 * 
	 * @param int companyId
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * The method returns the category of a coupon
	 * 
	 * @return Category category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * The method sets the category of a coupon
	 * 
	 * @param Category category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * The method returns the title of a coupon
	 * 
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * The method sets the title of a coupon
	 * 
	 * @param String title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * The method returns the description of a coupon
	 * 
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * The method sets the description of a coupon
	 * 
	 * @param String description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * The method returns a coupon start date
	 * 
	 * @return LocalDate startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * The method sets a coupon start date
	 * 
	 * @param LocalDate startDate
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * The method returns a coupon end date
	 * 
	 * @return LocalDate endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * The method sets a coupon end date
	 * 
	 * @param LocalDate endDate
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * The method returns available amount of a coupon
	 * 
	 * @return int amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * The method sets available amount of a coupon
	 * 
	 * @param int amount
	 * 
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * The method returns the price of a coupon
	 * 
	 * @return double price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * The method sets the price of a coupon
	 * 
	 * @param double price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * The method returns URL-string of the image related to a coupon
	 * 
	 * @return String image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * The method sets URL-string of the image related to a coupon
	 * 
	 * @param String image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * String representation of a coupon
	 */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyId=" + companyId + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

}
