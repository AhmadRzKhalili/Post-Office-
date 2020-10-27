package ir.ac.kntu;

import java.util.*;

/**
 * @author Ahmad Reza Khalili
 */
//Date
 
 enum pMethod{
    GROUNG, AIR, SEA;
}
 
public class Main {
	
	static Scanner scan = new Scanner(System.in);
	static ArrayList<Customer> customers = new ArrayList<Customer>();
	static ArrayList<Consignment> consignments = new ArrayList<Consignment>();
	
	static public void addCustomer(Customer customer){
		customers.add(customer);
	}
	
	static public void addCon(Consignment con){
		consignments.add(con);
	}
	
	static public boolean customerExists(Customer customer){
		int i;
		for(i = 0; i < customers.size(); i++){
			if(customers.get(i).equals(customer) == true)
				return true;
		}
		return false;
	}
	
	static public void menu(){
		System.out.println("**************************");
		System.out.println("1.Define a new consignment");
		System.out.println("2.Send a consignment");
		System.out.println("3.Find a customer");
		System.out.println("4.Track a consignment");
		System.out.println("5.Filters");
		System.out.println("-1.Exit");
		System.out.println("**************************");
	}
	
	static public City addCity(){
		String cityName;
		double x;
		double y;
		
		System.out.print("City name: ");
		cityName = scan.next();
		System.out.print("City geographic length(x): ");
		x = scan.nextDouble();
		System.out.print("City geographic width(y): ");
		y = scan.nextDouble();
		
		City city = new City(cityName, x, y);
		return city;
	}
	
	static public Customer newCustomer(){
		String name;
		long nationalCode;
		
		System.out.print("Customer's name: ");
		name = scan.next();
		System.out.print("Customer's national code: ");
		nationalCode = scan.nextLong();
		
		Customer c = new Customer(name, nationalCode);
		addCustomer(c);
		return c;
	}
	
	static public void sendCon(Consignment con){
		
		Date sendDate;
		Date receivDate;
		
		int d, m, y;
		
		Consignment c;
		
		System.out.println("Enter send date");
        System.out.print("day: ");
        d = scan.nextInt();
        System.out.print("month: ");
        m = scan.nextInt();
        System.out.print("year: ");
        y = scan.nextInt();
        sendDate = new Date(y, m, d);
        con.setSendDate(sendDate);

        System.out.println("Enter receive date");
        System.out.print("day: ");
        d = scan.nextInt();
        System.out.print("month: ");
        m = scan.nextInt();
        System.out.print("year: ");
        y = scan.nextInt();
        receivDate = new Date(y, m, d);
        con.setReceiveDate(receivDate);
		
		for(int i = 0; i < consignments.size(); i++){
			c = consignments.get(i);
			Date d1 = c.getSendDate();
			Date d2 = c.getReceiveDate();
			if(d1.before(sendDate) == true)
				c.setStatus(1);
			else
				if(d2.compareTo(sendDate) == 0)
					c.setStatus(3);
			else
				c.setStatus(2);
		}
	}
	
	static public void setPostInfo(Consignment con){
		
		int choice;
		
		System.out.println("###Choose your posting method:");
        System.out.println("1.Ground");
        System.out.println("2.Air");
        System.out.println("3.Sea");
        choice = scan.nextInt();
        con.setPostMethod(choice);

        System.out.println("###Choose your posting type:");
        System.out.println("1.Regular");
        System.out.println("2.Express");
        choice = scan.nextInt();
        con.setPostType(choice);
		
	}
	
	static public void defineCon(){
		
		String conName;
		double weight;
		Consignment con;
		Customer sender;
		Customer receiver;
		City origin;
		City destination;
		int choice;
		
		System.out.print("Enter consignment's name: ");
		conName = scan.next();
		
		System.out.print("Enter consignment's weight(Kg): ");
		weight = scan.nextDouble();

		con = new Consignment(conName, weight);

		System.out.println("###Enter sender's info");
		sender = newCustomer();
		con.setSender(sender);
		
		System.out.println("###Enter receiver's info");
		receiver = newCustomer();
		con.setReceiver(receiver);
		
		System.out.println("###Enter origin city info");
		origin = addCity();
		con.setOriginCity(origin);

		System.out.println("###Enter destination city info");
		destination = addCity();
		con.setDestinationCity(destination);
		
        setPostInfo(con);
		double price = con.getPrice(origin, destination, sender, weight, con.getMethod(), con.getType());
		System.out.println("$$$Posting price: "+price);
		
		addCon(con);
		//return con;
		
	}
	
	public static void findCustomer(){
		String name;
		long nationalCode;
		System.out.println("1.Find By name");
		System.out.println("2.Find By national code");
		int choice = scan.nextInt();
		if(choice == 1){
			name = scan.next();
			for(int i = 0; i < customers.size(); i++){
				Customer c = customers.get(i);
				if(c.getName().equals(name) == true){
					System.out.println(c.toString1());
					c.toString2();
					return;
				}
			}
		}
		else
		if(choice == 2){
			nationalCode = scan.nextLong();
			for(int i = 0; i < customers.size(); i++){
				Customer c = customers.get(i);
				if(c.getNationalCode() == nationalCode){
					System.out.println(c.toString1());
					c.toString2();
					return;
				}
			}
		}
	}
	
	public static void trackCon(){
		String name;
		Consignment con;
		System.out.print("Enter your consignment's name: ");
		name = scan.next();
		for(int i = 0; i < consignments.size(); i++){
				con = consignments.get(i);
				if(con.getConName().equals(name) == true)
					System.out.println(con.getCurLocation());
		}
	}
	
	public static void filter(){
		System.out.println("1.Filter by posting method");
		System.out.println("2.Filter by posting type");
		int choice = scan.nextInt();
		if(choice == 1){
			System.out.println("1.Send by ground method");
			System.out.println("2.Send by air method");
			System.out.println("3.Send by sea method");
			choice = scan.nextInt();
			if(choice == 1)
				for(int i = 0; i < consignments.size(); i++){
					Consignment con = consignments.get(i);
					if(con.getMethod().equals(postingMethod.GROUNG) == true){
						System.out.println(con.getConName());
					}
				}
			if(choice == 2)
				for(int i = 0; i < consignments.size(); i++){
					Consignment con = consignments.get(i);
					if(con.getMethod().equals("AIR") == true){
						System.out.println(con.getConName());
					}
				}
			if(choice == 3)
				for(int i = 0; i < consignments.size(); i++){
					Consignment con = consignments.get(i);
					if(con.getMethod().equals("SEA") == true){
						System.out.println(con.getConName());
					}
				}
		}
		else
		if(choice == 2){
			System.out.println("1.Regular type");
			System.out.println("2.Express type");
			choice = scan.nextInt();
			if(choice == 1)
				for(int i = 0; i < consignments.size(); i++){
					Consignment con = consignments.get(i);
					if(con.getType().equals("REGULAR") == true){
						System.out.println(con.getConName());
					}
				}
			if(choice == 2)
				for(int i = 0; i < consignments.size(); i++){
					Consignment con = consignments.get(i);
					if(con.getType().equals("EXPRESS") == true){
						System.out.println(con.getConName());
					}
				}
		}
	}
	
	static public Consignment findCon(){
		String conName;
		System.out.print("Enter consignment's name: ");
		conName = scan.next();
		for(int i = 0; i < consignments.size(); i++){
			Consignment c = consignments.get(i);
			if(conName.equals(c.getConName())== true)
				return c;
		}
		System.out.println("Consignment not found!");
		return null;
	}
	
    public static void main(String[] args) {
       /* //Good for showing one location
        MapConsumer.getInstance().accept("Tehran");
        //Good for showing two locations
        MapConsumer.getInstance().accept("Tehran","Dubai");*/

        //ArrayList<Consignment> consignments = new ArrayList<Consignment>();
		
		int choice = 0;
		
		System.out.println("Welcome!");
		
		while(choice != -1){
			
			System.out.println("Please enter the task you want to be done");
			menu();
			choice = scan.nextInt();
			switch(choice){
				case 1:
					defineCon();
					break;
				case 2:
					sendCon(findCon());
					break;
				case 3:
					findCustomer();
					break;
				case 4:
					trackCon();
					break;
				case 5:
					filter();
					break;
				default:
					System.out.println("Invalid choice!");
			}
		}
		
    }
}
