//Title     : FT MUR T122 ICT373 B – Assignment 1 (Client class)
//Author    : Tee Yee Kang
//Date      : 12/Feb/2022
//File Name : FTB-34315323-Assignment 01
//Purpose  	: This program is to allowed the users (customers) to create
//            a Magazine with different supplement services and a list of different
//            customer that subscribe to the magazine. The program also provide some
//            functions to allow the user to manipulate/make changes such as 
//            add customer , remove customer, display information, etc.

import java.text.DecimalFormat;
import java.util.*;

/**
 * @author      TeeYeeKang    yeekang88@gmail.com
 * @version     1.1          
 */

public class Client {
	
	static Scanner input = new Scanner (System.in);
	static DecimalFormat df = new DecimalFormat("0.00");

	public static void main(String[] args) {
		
		displayStudentDetails();
		
		boolean valid = false;
		int option=0;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		Magazine magazine = new Magazine();
		
		System.out.println("Construct a magazine with a list of supplements,");
		System.out.println("follow by a list of different customer with all information");
		while(!valid) {
			try {
				System.out.println("[0] - Hardcoded");
				System.out.println("[1] - User Scanner");
				System.out.print("Enter your option: ");
				option = input.nextInt();
				
				if(option==0 || option==1) 
					valid=true;
				else
					System.out.println("\nInvalid input!! Try again");
			}catch(InputMismatchException e) {  
				String bad_input = input.next();
	            System.out.println("\nInvalid input - " + bad_input + " is not a numerical value ! ");  
	        }
		}
		
		if(option==0) {
			magazine = PointA();
			PointB(customerList, magazine);
		}else {
			magazine = PointA_UserScanner();
			PointB_UserScanner(customerList, magazine);
		}
		
		Process(customerList, magazine);

	}//end of main
	
	public static Magazine PointA() {
		
		Supplement s1 = new Supplement("Sport", 10.5);
		Supplement s2 = new Supplement("Music", 15.5);
		Supplement s3 = new Supplement("Games", 17.55);
		Supplement s4 = new Supplement("Movie", 21.55);
	
		ArrayList<Supplement> suppList = new ArrayList<Supplement>();
		suppList.add(s1);
		suppList.add(s2);
		suppList.add(s3);
		suppList.add(s4);
		
		Magazine magazine = new Magazine("YK_Magazine", 15, suppList);
		return magazine;
	}
	
	public static void PointB(ArrayList<Customer> cusList, Magazine magazine){
		
		//supplements
		ArrayList<Supplement> list = magazine.GetMazSupplement();
		Supplement s1 = list.get(0);
		Supplement s2 = list.get(1);
		Supplement s3 = list.get(2);
		Supplement s4 = list.get(3);
		
		//Main Customer 01
		ArrayList<Supplement> cus1SuppList = new ArrayList<Supplement>();
		cus1SuppList.add(s1);
		cus1SuppList.add(s2);
		cus1SuppList.add(s3);
		AssociateCustomer cus1 = new AssociateCustomer("Michael", "michael@gmail.com", cus1SuppList);
		
		//Main Customer 02
		ArrayList<Supplement> cus2SuppList = new ArrayList<Supplement>();
		cus2SuppList.add(s1);
		cus2SuppList.add(s4);
		AssociateCustomer cus2 = new AssociateCustomer("John", "john@gmail.com", cus2SuppList);
		
		//Main Customer 03
		ArrayList<Supplement> assoCusList1_mainCus03 = new ArrayList<Supplement>();
		assoCusList1_mainCus03.add(s2);
		assoCusList1_mainCus03.add(s3);
		AssociateCustomer cus3 = new AssociateCustomer("Beckham", "beckham@gmail.com", assoCusList1_mainCus03);
		
		ArrayList<Supplement> assoCusList2_mainCus03 = new ArrayList<Supplement>();
		assoCusList2_mainCus03.add(s1);
		AssociateCustomer cus4 = new AssociateCustomer("Lucy", "lucy@gmail.com", assoCusList2_mainCus03);
		
		ArrayList<AssociateCustomer> mainCus03List = new ArrayList<AssociateCustomer>();
		mainCus03List.add(cus3);
		mainCus03List.add(cus4);
		
		ArrayList<Supplement> suppList_mainCus03 = new ArrayList<Supplement>();
		suppList_mainCus03.add(s3);
		suppList_mainCus03.add(s4);
		
		PayingCustomer payCus1 = new PayingCustomer("Lily", "lily@gmail.com", suppList_mainCus03, 12398715, 0, mainCus03List);
		
		//Main Customer 04
		ArrayList<Supplement> assoCusList1_mainCus04 = new ArrayList<Supplement>();
		assoCusList1_mainCus04.add(s1);
		assoCusList1_mainCus04.add(s2);
		AssociateCustomer cus5 = new AssociateCustomer("Mandy", "mandy@gmail.com", assoCusList1_mainCus04);
		
		ArrayList<AssociateCustomer> mainCus04List = new ArrayList<AssociateCustomer>();
		mainCus04List.add(cus5);
		
		ArrayList<Supplement> suppList_mainCus04 = new ArrayList<Supplement>();
		suppList_mainCus04.add(s2);
		suppList_mainCus04.add(s4);
		
		PayingCustomer payCus2 = new PayingCustomer("Marcus", "marcus@gmail.com", suppList_mainCus04, 45612344, 1, mainCus04List);
		
		//Main Customer 05
		ArrayList<AssociateCustomer> mainCus05List = new ArrayList<AssociateCustomer>();
		mainCus05List.add(cus1);
		mainCus05List.add(cus2);
		
		ArrayList<Supplement> suppList_mainCus05 = new ArrayList<Supplement>();
		suppList_mainCus05.add(s2);
		suppList_mainCus05.add(s3);
		suppList_mainCus05.add(s4);
		
		PayingCustomer payCus3 = new PayingCustomer("Kelly", "kelly@gmail.com", suppList_mainCus05, 41789620, 1, mainCus05List);
		
		cusList.add(cus1);
		cusList.add(cus2);
		cusList.add(payCus1);
		cusList.add(payCus2);
		cusList.add(payCus3);
	}
	
	public static void TaskC(ArrayList<Customer> cusList) {
		
		int counter=1, supNo=1;
		ArrayList<String> duplicate = new ArrayList<String>();
		
		System.out.println("");
		for(int idx=0; idx<cusList.size(); idx++) {
			if(cusList.get(idx) instanceof PayingCustomer) {
				
				boolean isDuplicate = CheckDuplicate(duplicate, cusList.get(idx));
				if(!isDuplicate) {
					duplicate.add(cusList.get(idx).GetCustomerName());
					DisplayOutput(cusList.get(idx), counter, supNo);
					counter++;
				}
				
				//print associate customer in Paying customer's associate customer list
				ArrayList<AssociateCustomer> assoCusList = ((PayingCustomer)cusList.get(idx)).GetAssoCusList();
				for(int index=0; index<assoCusList.size(); index++) {
					
					boolean isDuplicate2 = CheckDuplicate(duplicate, assoCusList.get(index));
					if(!isDuplicate2) {
						duplicate.add(assoCusList.get(index).GetCustomerName());
						DisplayOutput(assoCusList.get(index), counter, supNo);
						counter++;
					}
				}
			}else {
				boolean isDuplicate3 = CheckDuplicate(duplicate, cusList.get(idx));
				if(!isDuplicate3) {
					duplicate.add(cusList.get(idx).GetCustomerName());
					DisplayOutput(cusList.get(idx), counter, supNo);
					counter++;
				}
			}
		}
	}
	
	//check and avoid print associate customer twice 
	//because associate customer might be in Paying customer's associate customer list
	public static boolean CheckDuplicate(ArrayList<String> list, Customer customer) {
		
		boolean isDuplicate=false;
		
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(customer.GetCustomerName())) {
				isDuplicate=true;
				break;
			}
		}
		return isDuplicate;
	}
	
	public static void DisplayOutput(Customer customer, int counter, int supNo) {
		
		boolean empty=true;
		
		System.out.println("[" +counter+ "]");
		System.out.println("Customer name: " + customer.GetCustomerName());
		System.out.println("Customer email: " + customer.GetEmail());
		System.out.println("Supplements: " );
		System.out.println("--------------------" );
		ArrayList<Supplement> list = customer.GetSuppList();
		for(int i=0; i<list.size(); i++) {
			System.out.println(supNo +". "+ list.get(i).GetName());
			supNo++;
			empty=false;
		}
		
		if(empty) 
			System.out.println("--No supplement--");
		System.out.println("");
	}
	
	public static void AddCustomer(ArrayList<Customer> cusList, Magazine magazine) {
		
		int customer=0;
		boolean valid = false;
	
		while (!valid){
			try {
				System.out.println("What type of customer you want to add? [1-Associate Customer 2-Paying Customer]: ");
				System.out.println("[1]-Associate Customer ");
				System.out.println("[2]-Paying Customer ");
				System.out.print("Enter your choice: ");
				customer = input.nextInt();
				
				if(customer==1 || customer==2) {
					valid = true;
				}else {
					System.out.println("\nInvalid input. Try again\n");
				}
			}catch(InputMismatchException e) {  
				String bad_input = input.next();
	            System.out.println("\nInvalid input - " + bad_input + " is not a numerical value ! ");  
	        }
		}
		
		if(customer==1) {
			//create & add associate customer
			AssociateCustomer assoCus = CreateAssociateCustomer(magazine);
			cusList.add(assoCus);
			System.out.print("Associate customer successfully added");
		}else {
			//create & add paying customer
			PayingCustomer payCus = CreatePayingCustomer(magazine);
			cusList.add(payCus);
			System.out.print("Paying customer successfully added");
		}
	}
	
	public static AssociateCustomer CreateAssociateCustomer(Magazine magazine) {
		
		ArrayList<Supplement> list = magazine.GetMazSupplement();
		
		ArrayList<Supplement> supplementList = new ArrayList<Supplement>();
		int[] suppArray = new int[4];
		
		System.out.print("Enter customer name: ");
		String name = input.next();
		System.out.print("Enter customer email address: ");
		String email = input.next();
		
		ReadSupplement(suppArray, magazine); //read user supplement option
		StoreSupplement(suppArray, supplementList, list);  //store supplement to another ArrayList
		
		AssociateCustomer cus = new AssociateCustomer(name, email, supplementList);
		return cus;
	}
	
	public static void ReadSupplement(int[] array, Magazine magazine) {
		
		ArrayList<Supplement> m = magazine.GetMazSupplement();
		
		int counter=0, value=0;
		System.out.println("\nSupplement selection");
		System.out.println("Enter 1 - [" + m.get(0).GetName() + "]");
		System.out.println("Enter 2 - [" + m.get(1).GetName() + "]");
		System.out.println("Enter 3 - [" + m.get(2).GetName() + "]");
		System.out.println("Enter 4 - [" + m.get(3).GetName() + "]");
		
		while(counter<4) {
			try {
				System.out.println("What supplements are you interested in : ");
				value = input.nextInt();
				
				if(value>=1 && value<=4) {
					array[counter] = value;
					counter++;
				}else {
					System.out.println("Invalid input!! ");
				}
		
				if(counter<4) {
					System.out.println("Any other supplement ? [Yes/No]");
					char option = input.next().toLowerCase().charAt(0);
					if(option=='n') 
						counter=100; //terminate the while loop
				}
			}catch (Exception e) //if user enter non-numerical value
			{
				String bad_input = input.next();
	            System.out.println("Invalid input - " + bad_input + " is not a numerical value ! ");  
			}
		}
	}
	
	//used to store supplement to another ArrayList 
	//purpose of this method is to reuse and avoid rewrite same code 
	public static void StoreSupplement(int[] arr, ArrayList<Supplement> location, ArrayList<Supplement> source) {

		for(int idx=0; idx<arr.length; idx++) {
			switch(arr[idx]) 
			{
			case 1: location.add(source.get(0));
					break;
			case 2: location.add(source.get(1));
					break;
			case 3: location.add(source.get(2));
					break;
			case 4: location.add(source.get(3));
					break;
			}
		}
	}
	
	public static PayingCustomer CreatePayingCustomer(Magazine magazine) {
		
		boolean validPayment=false, moreAssoCus=true, validACNumber=false;
		int type=0;
		long bankAccNumber=0;
		ArrayList<Supplement> list = magazine.GetMazSupplement();
		ArrayList<AssociateCustomer> assoCusList = new ArrayList<AssociateCustomer>(); //associate customer list for paying customer
		
		System.out.print("Enter customer name: ");
		String name = input.next();
		System.out.print("Enter customer email address: ");
		String email = input.next();
		
		//check bank account
		while(!validACNumber) {
			try {
				System.out.print("Enter bank account number: ");
				bankAccNumber = input.nextLong();
				validACNumber = true;
			}catch(InputMismatchException e) {  
				String bad_input = input.next();
	            System.out.println("\nInvalid input - bank A/C number cannot contains non-numerical value ! ");  
	        }
		}
		
		//check payment type
		while(!validPayment) {
			try {
				System.out.print("Enter payment type [0-Direct Debit 1-Credit Card]: ");
				type = input.nextInt();
				
				if(type==0 || type==1) 
					validPayment=true;
				else
					System.out.println("Invalid input!! Try again");
			}catch(InputMismatchException e) {  
				String bad_input = input.next();
	            System.out.println("\nInvalid input - " + bad_input + " is not a numerical value ! ");  
	        }
		}
		
		//get supplement list for paying customer
		int[] payCusSuppArray = new int[4];
		ArrayList<Supplement> payCusSupplementList = new ArrayList<Supplement>();
		
		ReadSupplement(payCusSuppArray, magazine); //read user supplement option
		StoreSupplement(payCusSuppArray, payCusSupplementList, list); //store supplement to another ArrayList
		
		//check if contains associate customer
		System.out.print("Do you have any associate customer with you? [Yes/No]: ");
		char userAns = input.next().toLowerCase().charAt(0);
		
		if(userAns=='y') {
			while(moreAssoCus) {
				System.out.print("\nEnter associate customer information\n");
				AssociateCustomer cus = CreateAssociateCustomer(magazine);
				assoCusList.add(cus);
				
				System.out.print("\nContinue enter associate customer info ? [Yes/No]: ");
				char more = input.next().toLowerCase().charAt(0);
				
				if(more=='n')
					moreAssoCus=false;
			}
		}
		
		PayingCustomer payCustomer = new PayingCustomer(name, email, payCusSupplementList, bankAccNumber, type, assoCusList);
		
		return payCustomer;
	}
	
	public static void RemoveCustomer(ArrayList<Customer> cusList) {
		
		boolean found=false;
		
		System.out.print("Enter the name of customer you want to remove: ");
		String userInputName = input.next();
		
		for(int idx=0; idx<cusList.size(); idx++) {
			if(cusList.get(idx) instanceof PayingCustomer) {
				//loop through the associate customer list of paying customer
				for(int counter=0; counter<((PayingCustomer)cusList.get(idx)).GetAssoCusList().size(); counter++) {
					AssociateCustomer innerCustomer = ((PayingCustomer)cusList.get(idx)).GetAssoCusList().get(counter);
					
					//check if the userInputName is in the paying customer's associate customer list
					if(innerCustomer.GetCustomerName().equals(userInputName)) {
						((PayingCustomer)cusList.get(idx)).GetAssoCusList().remove(counter);
						found = true;
					}
				}
				
				//if userInputName is a Paying customer, remove all his/her associate customer as well
				if(cusList.get(idx).GetCustomerName().equals(userInputName)) {
					for(int counter=0; counter<((PayingCustomer)cusList.get(idx)).GetAssoCusList().size(); counter++) {
						AssociateCustomer innerCus = ((PayingCustomer)cusList.get(idx)).GetAssoCusList().get(counter);
						
						for(int i=0; i<cusList.size(); i++) {
							if(cusList.get(i).GetCustomerName().equals(innerCus.GetCustomerName())) {
								cusList.remove(i);
							}
						}
					}
				}
			}
			
			if(cusList.get(idx).GetCustomerName().equals(userInputName)) {
				cusList.remove(idx);
				found = true;
			}
		}
		
		if(!found) 
			System.out.println("Customer " + userInputName + " not found ");
		else
			System.out.println("Customer " + userInputName + " and his/her associate customer (if any) has been removed ");
		
	}//end of method
	
	public static void TaskD(ArrayList<Customer> cusList, Magazine magazine) {
		
		int counter=1;
		//Magazine magazine = PointA();
		ArrayList<Supplement> magazineSup = magazine.GetMazSupplement();
		
		System.out.println("");
		for(int idx=0; idx<cusList.size(); idx++) {
			if(cusList.get(idx) instanceof PayingCustomer) {
				int magazineCounter=0;
				PayingCustomer cus = (PayingCustomer) cusList.get(idx); //retrieve the paying customer
				LinkedHashMap<String, Integer> map = CountSupplement(cus, magazine); //CountSupplement(cusList.get(idx));
				System.out.println("[" + counter + "]");
				counter++;
				System.out.println("Customer name: " + cusList.get(idx).GetCustomerName());
				System.out.println("Bank A/C No: " + ((PayingCustomer)cusList.get(idx)).GetPayment().GetBankAcc());
				System.out.println("Bank A/C Type: " + ((PayingCustomer)cusList.get(idx)).GetPayment().GetBankAccType());
				System.out.println("Total Supplements' Weekly Cost: " + df.format(cusList.get(idx).GetWeeklyPrice()));
				System.out.println("Magazine weekly cost: " + magazine.GetWeeklyCost());
				System.out.println("Total Monthy Payment: " + df.format((4*(magazine.GetWeeklyCost()+cusList.get(idx).GetWeeklyPrice()))));
				System.out.println("Detail Info: ");
				System.out.println("--------------------------------------------");
				System.out.println("[Suppl]\t[Cost]\t[Qty]\t[Price]");
				for (String i : map.keySet()) {
					System.out.println(" " + i + "\t " + magazineSup.get(magazineCounter).GetWeeklyCost() + "\t  " 
							+ map.get(i) + "\t " + map.get(i)*magazineSup.get(magazineCounter).GetWeeklyCost());
					magazineCounter++;
				}
				System.out.println("\nAssociate Customers: ");
				System.out.println("-------------------------");
				ArrayList<AssociateCustomer> list = ((PayingCustomer)cusList.get(idx)).GetAssoCusList();
				for(int i=0; i<list.size(); i++) {
					System.out.println( i+1 + ". " + list.get(i).GetCustomerName());
				}
				System.out.println("");
			}
		}
	}
	
	public static LinkedHashMap<String, Integer> CountSupplement(PayingCustomer customer, Magazine magazine){
		
		int a=0, b=0, c=0, d=0;
		int size = magazine.GetMazSupplement().size();
		String[] strArray = new String[size];
		LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
		
		for(int i=0; i<magazine.GetMazSupplement().size(); i++) {
			strArray[i]=magazine.GetMazSupplement().get(i).GetName();
		}
		
		//retrieve Paying customer's supplement list
		ArrayList<Supplement> supList = customer.GetSuppList();
		for(int idx=0; idx<supList.size(); idx++) {
			if(supList.get(idx).GetName().equals(strArray[0])) {
				a++;
			}else if(supList.get(idx).GetName().equals(strArray[1])) {
				b++;
			}else if(supList.get(idx).GetName().equals(strArray[2])) {
				c++;
			}else {
				d++;
			}
		}
		
		//retrieve the supplement list of associate customer in Paying customer
		ArrayList<AssociateCustomer> assoCusList = customer.GetAssoCusList();
		//loop through the associate customer list
		for(int j=0; j<assoCusList.size();j++) {
			AssociateCustomer associateCus = assoCusList.get(j);
			ArrayList<Supplement> assoCusSupList = associateCus.GetSuppList();
			for(int counter=0; counter<assoCusSupList.size(); counter++) { 
				if(assoCusSupList.get(counter).GetName().equals(strArray[0])) {
					a++;
				}else if(assoCusSupList.get(counter).GetName().equals(strArray[1])) {
					b++;
				}else if(assoCusSupList.get(counter).GetName().equals(strArray[2])) {
					c++;
				}else {
					d++;
				}
			}
		}
		map.put(strArray[0], a);
		map.put(strArray[1], b);
		map.put(strArray[2], c);
		map.put(strArray[3], d);
		
		return map;
	}
	
	public static int Menu() throws Exception{
		System.out.println("\n---------< Menu ----------");
		System.out.println("[1] - Print out the text of all the emails for all customers for four weeks of magazines");
		System.out.println("[2] - Print out the text for the end of month emails for the paying customers");
		System.out.println("[3] - Add a new customer to the magazine service");
		System.out.println("[4] - Remove an existing customer from the magazine service");
		System.out.println("[5] - Exit program ");
		System.out.print("\nEnter your choice : ");
		int choice = Integer.parseInt(input.next());
		return choice;
		
	}//end of method
	
	public static void Process(ArrayList<Customer> cusList, Magazine magazine){
		int option = 0;
		do
		{
			try{
				option = Menu();
				switch(option)
				{
				case 1 : TaskC(cusList);
						 break;
				case 2 : TaskD(cusList, magazine);
						 break;
				case 3 : AddCustomer(cusList, magazine);
						 break;
				case 4 : RemoveCustomer(cusList);
						 break;
				case 5 : System.out.println("\nThank you Bye!!");
						 break;
				default : System.out.println("\nInvalid option!");
				}
			}catch (Exception e) //if user enter non-numerical value
			{
				System.out.println("\nUse a numerical value !");
			}
		}while (option != 5);
		
	}//end of method
	
	public static Magazine PointA_UserScanner() {
		
		double mazCost=0;
		boolean validCost=false;
		ArrayList<Supplement> suppList = new ArrayList<Supplement>();
		System.out.println("\nConstruct a magazine by entering 4 supplements info");
		System.out.print("Enter name of magazine: ");
		String mazName = input.next();
		
		while(!validCost) {
			try {
				System.out.print("Enter weekly cost of the magazine: ");
				mazCost = input.nextDouble();
				validCost=true;
			}catch (Exception e) //if user enter non-numerical value
			{
				String bad_input = input.next();
	            System.out.println("Invalid input - " + bad_input + " is not a numerical value ! ");  
			}
		}
		
		for(int counter=1; counter<=4; counter++) {
			System.out.print("Enter name of supplement " + counter + ": ");
			String name = input.next();
			System.out.print("Enter weekly cost of supplement " + counter + ": ");
			double cost = input.nextDouble();
			Supplement supplement = new Supplement(name, cost);
			suppList.add(supplement);
		}
		
		Magazine magazine = new Magazine(mazName, mazCost, suppList);
		return magazine;
	}
	
	public static void PointB_UserScanner(ArrayList<Customer> cusList, Magazine magazine) {

		System.out.println("\nConstruct 5 different customers");
		for(int idx=1; idx<=5; idx++) {
			System.out.println("\n\nCustomer " + idx);
			AddCustomer(cusList, magazine);
		}
	}
	
	public static void displayStudentDetails() {
		System.out.println("\nStudent Name			: Tee Yee Kang");
		System.out.println("StudentNo			: 34315323");
		System.out.println("Mode Of Enrolment    		: Full Time" );
		System.out.println("Tutor Name			: Dr. Lu " );
		System.out.println("Tutorial Attendence Day		: Tuesday" );
		System.out.println("Tutorial Attendence Time	: 1600-1815" );
		System.out.println("----------------------------------------------" );
	}
	
}//end of client class
