package com.Employee.Pojo;

public class Address 
{
	String address;
	long latitude;
	long longitude;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getLatitude() {
		return latitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Address [address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
