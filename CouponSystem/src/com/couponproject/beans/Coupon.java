package com.couponproject.beans;

import java.sql.Date;
import java.time.LocalDate;

public class Coupon {
	//
	// Attributes
	//

	// Instants variables
	private final long id; // TODO: final
	private String title;
	private LocalDate startDate;
	private LocalDate endDate;
	private int amount;
	private CouponType type;
	private String message;
	private double price;
	private String image;

	// Constructors
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
	public LocalDate getStartDate() {
		return startDate;
	}

	// Set start date
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	// Get end date
	public LocalDate getEndDate() {
		return endDate;
	}

	// Set end date
	public void setEndDate(LocalDate endDate) {
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

	//toString
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", type=" + type + ", message=" + message + ", price=" + price + ", image="
				+ image + "]";
	}
	
	
}