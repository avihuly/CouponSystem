package com.couponproject.beans;

import java.time.LocalDate;

import com.couponproject.constants.CouponType;

/**
 * Represents a Coupon in the Coupon System
 * <p>A Coupon is owned by a Company and can be bought by a Customer</p>
 * 
 * @author Avi Huly and Orit Blum
 * @version 1.0
 */
public class Coupon {
	//
	// Attributes
	//

	// Instants variables
	/**
	 * Holds the ID of this Coupon saved in the DB
	 * @see #id
	 */
	private long id; // TODO: final
	/**
	 * Holds a short description of the Coupon
	 * @see #title
	 */
	private String title;
	/**
	 * Holds the date from which this Coupon is valid
	 * @see #startDate
	 */
	private LocalDate startDate;
	/**
	 * Holds the date in which this Coupon expires
	 * @see #endDate
	 */
	private LocalDate endDate;
	/**
	 * Hold the number of Coupons, whit a specific title available for purchase by Customers
	 * @see #amount
	 */
	private int amount;
	/**
	 * Hold the category for which this Coupon belongs to out of a specific list
	 * @see #type
	 */
	private CouponType type;
	/**
	 * Holds an elaborated description of this Coupon
	 */
	private String message;
	/**
	 * Holds the price of this Coupon
	 * @see #price
	 */
	private double price;
	/**
	 * Holds a String which directs to a location of the Coupon's image
	 * @see #image
	 */
	private String image;

	// Constructors
	/**
	 * Constructs a Coupon with given ID, Title, StartDate, EndDate, Amount, Type,
	 * Message, Price and Image location
	 * 
	 * @param id
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param type
	 * @param message
	 * @param price
	 * @param image
	 */
	public Coupon(long id, String title, LocalDate startDate, LocalDate endDate, 
			int amount, CouponType type, String message, double price, String image) {
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	/**
	 * Constructs a Coupon with given Title, StartDate, EndDate, Amount, Type,
	 * Message, Price and Image location
	 * 
	 * @param title
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param type
	 * @param message
	 * @param price
	 * @param image
	 */
	public Coupon(String title, LocalDate startDate, LocalDate endDate, 
		int amount, CouponType type, String message, double price, String image) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
		//TODO: check how to get id from DB?
		this.id=0;
	}

	
	//
	// Methods - getters & setters
	//
	
	// Get ID
	/**
	 * Returns this Coupon's ID
	 * @return This Coupon's ID
	 */
	public long getId() {
		return id;
	}
	
	// Get title
	/**
	 * Returns this Coupon's Title
	 * @return This Coupon's Title
	 */
	public String getTitle() {
		return title;
	}

	// Set title
	/**
	 * Sets a new Title for this Coupon
	 * @param title A new Title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	// Get start date
	/**
	 * Returns this Coupon's StartDate
	 * @return This Coupon's StartDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	// Set start date
	/**
	 * Sets a new StartDate for this Coupon
	 * @param startDate A new StartDate
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	// Get end date
	/**
	 * Returns this Coupon's EndDate
	 * @return This Coupon's EndDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	// Set end date
	/**
	 * Sets a new EndDate for this Coupon
	 * @param endDate New EndDate
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	// Get amount
	/**
	 * Returns this Coupon's Amount
	 * @return This Coupon's Amount
	 */
	public int getAmount() {
		return amount;
	}

	// Set amount
	/**
	 * Set a new Amount for this Coupon
	 * @param amount New Amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	// Get coupon Type
	/**
	 * Returns this Coupon's Type
	 * @return This Coupon's Type
	 */
	public CouponType getType() {
		return type;
	}

	// Set coupon Type
	/**
	 * Set a new CouponType
	 * @param type New CouponType
	 */
	public void setType(CouponType type) {
		this.type = type;
	}

	// Get message
	/**
	 * Returns this Coupon's Message
	 * @return This Coupon's Message
	 */
	public String getMessage() {
		return message;
	}

	// Set message
	/**
	 * Sets a new Message for this Coupon
	 * @param message New Message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	// Get price
	/**
	 * Returns this Coupon's Price
	 * @return Coupon's Price
	 */
	public double getPrice() {
		return price;
	}

	// Set price
	/**
	 * Set a new Price for this Coupon
	 * @param price New Price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	// Get image location (string)
	/**
	 * Returns this Coupon's image location
	 * @return Image location
	 */
	public String getImage() {
		return image;
	}

	// Set image location (string)
	/**
	 * Sets a new Image location
	 * @param image New Image location
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	// Set coupon id
	public void setId (long id) {
		this.id = id;
	}
	
	

	//toString
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coupon other = (Coupon) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}