package com.umg.modules.umgaudit;
import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.log4j.Logger;
import org.mule.api.MuleContext;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.rest.HttpMethod;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestHeaderParam;
import org.mule.api.annotations.rest.RestPostParam;
import org.mule.api.annotations.rest.RestQueryParam;
import org.mule.api.registry.Registry;

/**
 * Anypoint Connector
 *
 * @author MuleSoft, Inc.
 */
@Connector(name="umgaudit", schemaVersion="1.0", friendlyName="UMGAudit")
public abstract class UMGAuditConnector
{
	
	private static Logger log = Logger.getLogger(UMGAuditConnector.class);

	@Inject
	protected MuleContext muleContext;

	@Configurable
	/**
	 * Context parameters to be used in Logging and Auditing. It would be set
	 * in Log4J data context.
	 */
	protected Map<String, String> contextParams;

	@Inject
	protected Registry registry;

	public MuleContext getMuleContext() {
		return muleContext;
	}

	public void setMuleContext(MuleContext muleContext) {
		this.muleContext = muleContext;
	}

	public Registry getRegistry() {
		return registry;
	}

	public void setRegistry(Registry registry) {
		this.registry = registry;
	}

	public Map<String, String> getContextParams() {
		return contextParams;
	}

	public void setContextParams(Map<String, String> contextParams) {
		this.contextParams = contextParams;
	}

	/**
	 * Create a CSV format of this instance.
	 */
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * @param messageId
	 *            Unique Message ID to be searched for {@sample.xml
	 *            ../../../doc/umgaudit-connector.xml.sample umgaudit:lookup}
	 */
	@Processor
    @RestCall(uri = "http://localhost:8081/api/audit", method = HttpMethod.GET, 
    	contentType = "application/json"
    )
	public abstract String lookup(
			@RestQueryParam("messageId") String messageId)
			throws IOException;
}
	
