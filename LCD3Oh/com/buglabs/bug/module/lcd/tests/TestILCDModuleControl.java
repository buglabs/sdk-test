package com.buglabs.bug.module.lcd.tests;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.buglabs.bug.module.lcd.pub.ILCDModuleControl;

import junit.framework.TestCase;

/**
 * Tests the ILCDModuleControl API.
 * @author barberdt
 *
 */
public class TestILCDModuleControl extends TestCase {

	private BundleContext context; 
	private ServiceReference sref;
	private ILCDModuleControl ilcdmc; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		sref = context.getServiceReference(ILCDModuleControl.class.getName());
		ilcdmc = (ILCDModuleControl) context.getService(sref);
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
	 * Tests that the ILCDModuleControl exists.
	 */
	public void testILCDModuleControlExists() {
		assertNotNull(ilcdmc);
		System.out.println("ILCDModuleControl: " + ilcdmc);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setBlackLight method.
	 * @throws Exception
	 */
	public void testSetBackLight() throws Exception {
		int backlight = 0;
		System.out.println("Setting backlight to " + backlight);
		int ioctl1 = ilcdmc.setBackLight(backlight);
		assertEquals(0, ioctl1);
		Thread.sleep();
		System.out.println("IOCTL Return: " + ioctl1);
		backlight = 100;
		System.out.println("Setting backlight to " + backlight);
		int ioctl2 = ilcdmc.setBackLight(backlight);
		assertEquals(0, ioctl2);
		System.out.println("IOCTL Return: " + ioctl2);
		System.out.println("\n\n");
	}
	
}
