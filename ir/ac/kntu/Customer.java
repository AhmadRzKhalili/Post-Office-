package ir.ac.kntu;

import java.util.*;
import java.lang.*;

public class Customer{

    private String name;
    private long nationalCode;
	private int countCon = 0;
	private ArrayList<Consignment> conArr = new ArrayList<Consignment>();

    public Customer(String name, long nationalCode){
        setName(name);
        setNationalCode(nationalCode);
    }

    private void setName(String name){
        this.name = name;
    }

    private void setNationalCode(long nationalCode){
        this.nationalCode = nationalCode;
    }

    public String getName(){
        return name;
    }

    public long getNationalCode(){
        return nationalCode;
    }
	
	public void incCountCon(){
		countCon++;
	}
	
	public void addCon(Consignment con){
		conArr.add(con);
		incCountCon();
	}
	
	public int getNumOfCon(){
		return countCon;
	}
	
	public String toString1(){
		return "*Customer's name: "+name+" *Customer's national code: "+nationalCode+" **Number of consignments: "+countCon;
	}
	
	public void toString2(){
		Consignment[] arr = new Consignment[conArr.size()];
		for(int i = 0; i < conArr.size(); i++)
			arr[i] = conArr.get(i);
		System.out.println(Arrays.toString(arr));
	}
	
	@Override
    public boolean equals(Object obj) 
    { 
		if(this == obj) 
			return true; 
		if(obj == null || obj.getClass()!= this.getClass()) 
			return false; 
			  
		  
		Customer customer = (Customer) obj; 
			
		return (customer.name == this.name && customer.nationalCode == this.nationalCode); 
    } 
}
