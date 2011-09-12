package com.buglabs.bug.module.gps.tests;

import java.io.InputStream;

import junit.framework.TestCase;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.buglabs.bug.module.gps.pub.INMEARawFeed;

/**
 * Tests the INMEARawFeed API.
 * @author barberdt
 *
 */
public class TestINMEARawFeed extends TestCase {

	private BundleContext context; 
	private ServiceReference sref;
	private INMEARawFeed inrf; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		sref = context.getServiceReference(INMEARawFeed.class.getName());
		inrf = (INMEARawFeed) context.getService(sref);
	}
	
	/**
	 * Tests that the service reference exists.
	 */
	public void testServiceReferenceExists() {
		assertNotNull(sref);
		System.out.println("Service Reference: " + sref);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests that the INMEARawFeed exists.
	 */
	public void testINMEARawFeedExists() {
		assertNotNull(inrf);
		System.out.println("INMEARawFeed: " + inrf);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getInputStream method.
	 * @throws Exception
	 */
	public void testGetInputStream() throws Exception {
		InputStream inputStream = inrf.getInputStream();
		assertNotNull(inputStream);
		int bytesRead=0;
		int bytesToRead=1024;
		byte[] input = new byte[bytesToRead];
		while (bytesRead < bytesToRead) {
			int result = inputStream.read(input, bytesRead, bytesToRead - bytesRead);
			if (result == -1) break;
			bytesRead += result;
		}
		String toPrint = new String(input);
		System.out.println("1024 Bytes Read:");	
		System.out.println(toPrint);
		System.out.println("\n\n");
	}

}
