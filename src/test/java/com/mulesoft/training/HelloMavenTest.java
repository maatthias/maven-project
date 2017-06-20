package com.mulesoft.training;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.tck.junit4.rule.DynamicPort;
import org.mule.api.MuleEvent;

public class HelloMavenTest extends FunctionalTestCase {
	
	@Rule
	public DynamicPort dynamicPort = new DynamicPort("http.port");
	
    @Test
    public void mavenFlowReturnsHelloMaven() throws Exception {
    	System.out.print("\n\nJUnit Test case #1: HTTP Dynamic Port -------> " + dynamicPort.getNumber() + "\n\n");
        runFlowAndExpect("mavenFlow", "Hello Maven");
    }
    
    @Test
    public void retrieveFlightsAddsAppropriateHeader() throws Exception {
      MuleEvent event = runFlow("retrieveFlights");
      System.out.print("\n\nJUnit Test case #1: HTTP Dynamic Port -------> " + dynamicPort.getNumber() + "\n\n");
      String contentType = event.getMessage().getOutboundProperty("Content-Type");
      assertEquals("application/json", contentType);
    }
    
    @Override
    protected String[] getConfigFiles() {
    	String[] files = {"maven-project.xml", "global.xml"};
        return files;
    }

}
