package com.umg.modules.umglogger;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.util.FileUtils;
import org.mule.util.FileUtilsTestCase;
import org.mule.util.IOUtils;

import org.junit.Test;

public class UMGLoggerConnectorTest extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "umglogger-config.xml";
    }

    @Test
    public void testFlow() throws Exception
    {
    	String message = "UMG Logger Test Message";
    	String contextParamValue = "UMGLoggerTestService";
        runFlow("testFlow", message);
        String logFileContent = 
        		IOUtils.getResourceAsString("logs/mule-app-umglogger.log", UMGLoggerConnectorTest.class);
        //Check if context parameter is logged
        assertTrue(logFileContent.indexOf(contextParamValue) != -1);
        
        //Check if log message is logged
        assertTrue(logFileContent.indexOf(message) != -1);
        
    }	
}
