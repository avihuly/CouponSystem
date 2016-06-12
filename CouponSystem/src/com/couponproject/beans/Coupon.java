package com.couponproject.beans;

import java.sql.Date;

public class Coupon {
	//
	// Attributes
	//

	// static variables
	private static long idGen = 1000; // TODO: dynamic setting
	
	// Instants variables
	private /*final*/ long id; // TODO: final
	private String title;
	private Date startDate;
	private Date endDate;
	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;

	
	public Coupon(String title, Date startDate, Date endDate, 
			int amount, CouponType type, String message, double price, String image) {
		setId();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}

	//
	// Methods - getters & setters
	//
	
	// Get ID
	public long getId() {
		return id;
	}

	// Get title
	public String getTitle() {
		return title;
	}

	// Set title
	public void setTitle(String title) {
		this.title = title;
	}

	// Get start date
	public Date getStartDate() {
		return startDate;
	}

	// Set start date
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	// Get end date
	public Date getEndDate() {
		return endDate;
	}

	// Set end date
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	// Get amount
	public int getAmount() {
		return amount;
	}

	// Set amount
	public void setAmount(int amount) {
		this.amount = amount;
	}

	// Get coupon Type
	public CouponType getType() {
		return type;
	}

	// Set coupon Type
	public void setType(CouponType type) {
		this.type = type;
	}

	// Get message
	public String getMessage() {
		return message;
	}

	// Set message
	public void setMessage(String message) {
		this.message = message;
	}

	// Get price
	public double getPrice() {
		return price;
	}

	// Set price
	public void setPrice(double price) {
		this.price = price;
	}

	// Get image location (string)
	public String getImage() {
		return image;
	}

	// Set image location (string)
	public void setImage(String image) {
		this.image = image;
	}

	//
	// Private method
	//
	private void setId() {
		id = idGen;
		idGen++;
	}
}