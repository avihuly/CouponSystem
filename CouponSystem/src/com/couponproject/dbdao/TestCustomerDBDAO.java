package com.couponproject.dbdao;

import java.sql.SQLException;

import com.couponproject.beans.Customer;

// TODO: description 
public class TestCustomerDBDAO {

	public static void main(String[] args) {

		// CustomerDBDAO instance for testing
		CustomerDBDAO custDbDao = new CustomerDBDAO();

		// Add new 'Customer's to DB
		for (int i = 0; i < 25; i++) {
			// Customer instance 
			Customer customer = new Customer(custNameGen(), custPassGen());
			try {
				// this is the hart of the test 
				// converting costumer object into a sql query and urning it
				custDbDao.createCustomer(customer);
			} catch (SQLException e) {
				System.out.println("catch");
				e.printStackTrace();
			}
		}
	}

	// Methods //
	private static String custNameGen() {
		return "Customr " + (int) (Math.random() * 1001);
	}

	private static String custPassGen() {
		return "Customr " + (int) (Math.random() * 1001);
	}

}
