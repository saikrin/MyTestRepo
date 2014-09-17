package com.umg.blueprint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
public class EnterpriseMessage {

	private String id;
	private String source;
	private String target;
	private List<Endpoint> endpoints = new ArrayList<Endpoint>();
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public EnterpriseMessage() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public List<Endpoint> getEndpoints() {
		return endpoints;
	}
	
	public void addEndpoint(Endpoint ep) {
		this.getEndpoints().add(ep);
	}
	public void setEndpoints(List<Endpoint> endpoints) {
		this.endpoints = endpoints;
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
