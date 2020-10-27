package ir.ac.kntu;

import java.util.*;

public class City{

    private String cityName;

    private double x;
    private double y;

    public City(String cityName, double x, double y){
        setCityName(cityName);
        setCoordinate(x, y);
    }

    private void setCityName(String cityName){
        this.cityName = cityName;
    }

    private void setCoordinate(double x, double y){
        this.x = x;
        this.y = y;
    }

    public String getCityName() {
        return cityName;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
	
	public double disFrom(City city){
		double xX = city.getX();
		double yY = city.getY();
		return Math.sqrt(Math.pow(x-xX,2) + Math.pow(y-yY,2));
	}
	
	
}