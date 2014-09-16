package com.umg.blueprint;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class Endpoint<T> {

	private T payload;
	private String address;
	private String exchangePattern;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public Endpoint() {}

	public Endpoint(String exchangePattern, String address) {
		this.address = address;
		this.exchangePattern = exchangePattern;
	}

	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getExchangePattern() {
		return exchangePattern;
	}

	public void setExchangePattern(String exchangePattern) {
		this.exchangePattern = exchangePattern;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
