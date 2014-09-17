package com.umg.modules.umglogger;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.mule.api.MuleContext;
import org.mule.api.annotations.Configurable;
import org.mule.api.annotations.Module;
import org.mule.api.annotations.Processor;
import org.mule.api.registry.Registry;

/**
 * UMG Logger Module for Logging through log4j.
 * 
 * @author MuleSoft, Inc.
 */
@Module(name = "umglogger", friendlyName = "UMGLogger")
public class UMGLoggerModule {
	
	private static Logger log = Logger.getLogger(UMGLoggerModule.class);
	
	@Inject
	protected MuleContext muleContext;


	@Configurable
	/**
	 * Context parameters to be used in Logging. It would be set
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

	/**
	 * @param message
	 *            Message to be logged {@sample.xml
	 *            ../../../doc/umglogger-module.xml.sample umglogger:log}
	 * @param level
	 *            Log4j level (example: INFO, DEBUG, TRACE, OFF)
	 */
	@Processor
	public void log(String message, String level) {
		log.log(Level.toLevel(level), message);
	}

	@PostConstruct
	/**
	 * Initialize Log4J context using context parameters. Refer to these parameters
	 * through Log4J properties using %X{context_param} syntax.
	 */
    public void init() {
		log.info(contextParams);
    	Iterator entries = contextParams.entrySet().iterator();
    	while (entries.hasNext()) {
    	  Entry<String, Object> entry = (Entry) entries.next();
    	  MDC.put(entry.getKey(), entry.getValue());
    	}
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

}