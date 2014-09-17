package com.umg.modules.umgaudit;

import org.mule.tck.junit4.FunctionalTestCase;

import org.junit.Test;

public class UMGAuditConnectorTest extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "umgaudit-config.xml";
    }

    @Test
    public void testFlow() throws Exception
    {
    	String messageId = "12345";
    	System.out.println("Inside testFlow ...");
        //runFlow("testFlow", messageId);
    }
}
