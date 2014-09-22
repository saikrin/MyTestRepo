package com.umg.modules.audit;

import org.mule.tck.junit4.FunctionalTestCase;

import org.junit.Test;

public class AuditConnectorTest extends FunctionalTestCase
{
    @Override
    protected String getConfigResources()
    {
        return "audit-config.xml";
    }

    @Test
    public void testFlow() throws Exception
    {
        runFlow("testFlow", "{id=123,name=Amjad,dept=Mule}");
    }
}
