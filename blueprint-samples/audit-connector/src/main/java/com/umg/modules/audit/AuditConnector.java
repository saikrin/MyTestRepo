package com.umg.modules.audit;
import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.Connect;
import org.mule.api.annotations.Module;
import org.mule.api.annotations.ValidateConnection;
import org.mule.api.annotations.ConnectionIdentifier;
import org.mule.api.annotations.Disconnect;
import org.mule.api.annotations.param.ConnectionKey;
import org.mule.api.annotations.param.Payload;
import org.mule.api.ConnectionException;
import org.mule.api.MuleContext;
import org.mule.api.annotations.display.Password;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Processor;

import org.mule.api.annotations.param.Default;
import org.mule.api.annotations.rest.HttpMethod;
import org.mule.api.annotations.rest.RestCall;
import org.mule.api.annotations.rest.RestPostParam;
import org.mule.api.annotations.rest.RestQueryParam;
import org.mule.api.registry.Registry;

/**
 * Anypoint Module
 *
 * @author MuleSoft, Inc.
 */
@Module(name="audit", schemaVersion="1.0", friendlyName="Audit")
public abstract class AuditConnector
{

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
	 *            ../../../doc/audit-connector.xml.sample audit:lookup}
	 */
    @Processor
    @RestCall(uri = "http://localhost:8081/api/audit", method = HttpMethod.GET, contentType = "application/json")
	public abstract String lookup(
			@RestQueryParam("messageId") String messageId)
			throws IOException;
    /**
	 * @param message
	 *            JSON message to be published {@sample.xml
	 *            ../../../doc/audit-connector.xml.sample audit:log}
	 */

	@Processor
    @RestCall(uri = "http://localhost:8081/api/audit", method = HttpMethod.POST, contentType = "application/json")
	public abstract String log(
			@Payload String message)
			throws IOException;
	
}