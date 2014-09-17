package com.umg.blueprint;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class EmailAddress {
	private String type;
	private String address;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
