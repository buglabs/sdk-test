package com.buglabs.bug.module.lcd.tests;

import java.awt.Frame;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.buglabs.bug.module.lcd.pub.IModuleDisplay;

import junit.framework.TestCase;

public class TestIModuleDisplay extends TestCase {

	private BundleContext context; 
	private ServiceReference sref;
	private IModuleDisplay imd; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		sref = context.getServiceReference(IModuleDisplay.class.getName());
		imd = (IModuleDisplay) context.getService(sref);
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
	 * Tests that the IModuleDisplay exists.
	 */
	public void testIModuleDisplayExists() {
		assertNotNull(imd);
		System.out.println("IModuleDisplay: " + imd);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getFrame method.
	 */
	public void testGetFrame() {
		Frame frame = imd.getFrame();
		assertNotNull(frame);
		String frameTitle = frame.getTitle();
		System.out.println("Frame Title: " + frameTitle);
		System.out.println("\n\n");
	}
	
}
