package ir.ac.kntu;

import java.util.Date;

enum postingMethod{
    GROUNG, AIR, SEA;
}

enum postingType{
    REGULAR, EXPRESS;
}

enum status{
    NOT_SENDED, ON_THE_WAY, DELIVERED;
}

public class Consignment{
	
    private String conName;
    private Customer sender;
    private Customer receiver;
    private City originCity;
    private City destinationCity;
    private City curCity;
    private Double weight;
	private Date sendDate;
	private Date receiveDate;
    private postingMethod postMethod;
    private postingType postType;
    private status postStatus = status.NOT_SENDED;
	private double price;
	
	public Consignment(String conName, double weight){
		setConName(conName);
		setWeight(weight);
	}
	
	public double getPrice(City oCity, City dCity, Customer sndr,double w, postingMethod method, postingType type){
		double cost;
		double dis;
		cost = w * 1000;
		dis = oCity.disFrom(dCity);
		cost = cost + (dis * 100);
		if(method == postingMethod.AIR)
			cost = cost * 2;
		if(method == postingMethod.SEA)
			cost = cost * 1.5;
		if(type == postType.EXPRESS)
			cost = cost * 2;
		if(sndr.getNumOfCon() > 5)
			cost = cost * 0.9;
		price = cost;	
		return cost;
	}
	
	public void setConName(String conName){
		this.conName = conName;
	}

    public void setWeight(double weight){
        this.weight = weight;
    }

	public void setSender(Customer sender){
		this.sender = sender;
	}
	
	public void setReceiver(Customer receiver){
		this.receiver = receiver;
	}
	
	public void setOriginCity(City originCity){
	    this.originCity = originCity;
    }

    public void setDestinationCity(City destinationCity) {
	    this.destinationCity = destinationCity;
    }

    public void setPostMethod(int c){
		if(c == 2){
			postMethod = postingMethod.AIR;
			return;
		}
		if(c == 3){
			postMethod = postingMethod.SEA;
			return;
		}
	    if(originCity.disFrom(destinationCity) > 500 && c != 1){
	        if(c == 2)
	            postMethod = postingMethod.AIR;
	        else
	            postMethod = postingMethod.SEA;
        }
	    else
	        if(originCity.disFrom(destinationCity) > 500 && c == 1) {
                System.out.println("The distance between " + originCity.getCityName() + " and "
                        + destinationCity.getCityName() + " is more than 500 Km and the consignment cannot be sended by ground method. "
                        + "It will be sended by air method.");
                postMethod = postingMethod.AIR;
            }
	    else
            if(originCity.disFrom(destinationCity) < 500 && c == 1)
                postMethod = postingMethod.GROUNG;
        else
            if(c == 0 || c > 3){
                System.out.println("Invalid choice");
                if(originCity.disFrom(destinationCity) > 500){
                    System.out.println("Your consignment will be sended by air method.");
                    postMethod = postingMethod.AIR;
                }
                if(originCity.disFrom(destinationCity) < 500){
                    System.out.println("Your consignment will be sended by ground method.");
                    postMethod = postingMethod.GROUNG;
                }
            }
    }
//Date
    public void setPostType(int c){
	    if(c == 1)
	        postType = postingType.REGULAR;
	    if(c == 2)
	        postType = postingType.EXPRESS;
    }

    public void setSendDate(Date sendDate){
	    this.sendDate = sendDate;
    }

    public void setReceiveDate(Date receiveDate){
	    this.receiveDate = receiveDate;
    }
	
	public void setStatus(int s){
		if(s == 1)
			postStatus = status.NOT_SENDED;
		if(s == 2)
			postStatus = status.ON_THE_WAY;
		if(s == 1)
			postStatus = status.DELIVERED;
	}
	
	public String getConName(){
		return conName;
	}
	
	public Date getSendDate(){
	    return sendDate;
    }
	
	public Date getReceiveDate(){
	    return receiveDate;
    }
	
	public String getCurLocation(){
		if(postStatus == status.NOT_SENDED)
			return originCity.getCityName();
		else
			if(postStatus == status.DELIVERED)
				return destinationCity.getCityName();
		return "On the way to the "+destinationCity.getCityName()+".";
	}
	
	public postingMethod getMethod(){
		return postMethod;
	}
	
	public postingType getType(){
		return postType;
	}
	
	public status getStatus(){
		return postStatus;
	}
	
	@Override
	public String toString(){
		return "*Consignment name: "+conName+" *Weight: "+weight+" **Sender: "+sender.getName()+" , "+sender.getNationalCode()+
			" **Receiver: "+receiver.getName()+" , "+receiver.getNationalCode()+" ***Origin city: "+originCity.getCityName()+
			" ***Destination city: "+destinationCity.getCityName();
	}
	
}
