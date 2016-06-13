package com.couponproject.beans;

public enum CouponType {
	RESTAURANT("RESTAURANT"), 
	ELECTRICITY("ELECTRICITY"), 
	FOOD("FOOD"), 
	HEALTH("HEALTH"), 
	SPORTS("SPORTS"), 
	CAMPING("CAMPING"), 
	TRAVELLING("TRAVELLING");

	private String type;

	CouponType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	// Generating coupType enum from String
	public static CouponType fromString(String type) {
		if (type != null) {
			for (CouponType coupType : CouponType.values()) {
				if (type.equalsIgnoreCase(coupType.type)) {
					return coupType;
				}
			}
		}
		return null;
	}
}

