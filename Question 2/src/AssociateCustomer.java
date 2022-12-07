//Title     : FT MUR T122 ICT373 B – Assignment 1 (AssociateCustomer class)
//Author    : Tee Yee Kang
//Date      : 12/Feb/2022
//File Name : FTB-34315323-Assignment 01
//Purpose  	: This is the AssociateCustomer class. AssociateCustomer is derive class of Customer
//            class object. It contains all Customer's member variables and methods.
//            The purpose of this class is to represent the associate customer

import java.util.ArrayList;

/**
 * @author      TeeYeeKang    yeekang88@gmail.com
 * @version     1.1          
 */

public class AssociateCustomer extends Customer {

	//member variables
	//none
	
	//default constructor
	/**
	 * Default Constructor of AssociateCustomer class                          
	 * <p>
	 * This class is used to represent the default value of Customer parent class. 
	 * </p>
	 * Precondition - Nil  <br>
	 * Postcondition - A AssociateCustomer object is created with the all default value
	 */
	public AssociateCustomer() {
		super();
	}
	
	//constructor with parameters
	/**
	 * Constructor of AssociateCustomer class                          
	 * <p>
	 * This class is used to represent the AssociteCustomer with all member variables
	 * </p>
	 * Precondition - The value of cusName and cusEmail must be a String value and an ArrayList of supplement object<br>
	 * Postcondition - An AssociateCustomer object is created with the pass in value
	 * @param  cusName Name of the customer 
	 * @param  cusEmail Customer's email address   
	 * @param  list A list of supplement 
	 */
	public AssociateCustomer(String cusName, String cusEmail, ArrayList<Supplement> list) {
		super(cusName, cusEmail, list);
	}
	
	/*
	//testing purpose
	public static void main(String[]args) {
		
		AssociateCustomer cus = new AssociateCustomer();
		cus.SetCustomerName("Tee");
		cus.SetEmail("tyk.gmail.com");
		
		Supplement s1 = new Supplement("Book1", 10);
		Supplement s2 = new Supplement("Book2", 20);
		Supplement s3 = new Supplement("Book3", 30);
		Supplement s4 = new Supplement("Book4", 40);
		
		cus.AddSupplement(s1);
		cus.AddSupplement(s2);
		cus.AddSupplement(s3);
		cus.AddSupplement(s4);
		cus.RemoveSupplement(s3);
		
		cus.WriteOutput();
	}
	*/
}

	


