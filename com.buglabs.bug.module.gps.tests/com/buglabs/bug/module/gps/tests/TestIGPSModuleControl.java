package com.buglabs.bug.module.gps.tests;

import java.util.List;

import junit.framework.TestCase;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.buglabs.bug.module.gps.pub.IGPSModuleControl;
import com.buglabs.bug.dragonfly.module.IModuleProperty;

/**
 * Tests the IGPSModuleControl API.
 * @author barberdt
 *
 */
public class TestIGPSModuleControl extends TestCase {

	private BundleContext context; 
	private ServiceReference sref;
	private IGPSModuleControl igmc; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		sref = context.getServiceReference(IGPSModuleControl.class.getName());
		igmc = (IGPSModuleControl) context.getService(sref);
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
	 * Tests that the IGPSModuleControl exists.
	 */
	public void testIGPSModuleControlExists() {
		assertNotNull(igmc);
		System.out.println("IGPSModuleControl: " + igmc);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setPassiveAntenna method.
	 * @throws Exception
	 */
	public void testSetPassiveAntenna() throws Exception{
		int ioctl = igmc.setPassiveAntenna();
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setActiveAntenna method.
	 * @throws Exception 
	 */
	public void testSetActiveAntenna() throws Exception {
		int ioctl = igmc.setActiveAntenna();
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getSlotId method.
	 */
	public void testGetSlotId() {
		int slotId = igmc.getSlotId();
		assertNotNull(slotId);
		System.out.println("Slot ID: " + slotId);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getStatus method.
	 * @throws Exception
	 */
	public void testGetStatus() throws Exception {
		int status = igmc.getStatus();
		assertNotNull(status);
		System.out.println("Status: " + status);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getModuleName method.
	 */
	public void testGetModuleName() {
		String moduleName = igmc.getModuleName();
		assertNotNull(moduleName);
		System.out.println("Module Name: " + moduleName);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getModuleProperties method.
	 */
	public void testGetModuleProperties() {
		List<IModuleProperty> moduleProps = (List<IModuleProperty>)igmc.getModuleProperties();
		assertNotNull(moduleProps);
		for (int i=0; i<moduleProps.size(); i++) {
			IModuleProperty currentProp = moduleProps.get(i);
			System.out.println("Property Name: " + currentProp.getName());
			System.out.println("Property Type: " + currentProp.getType());
			System.out.println("Property Value: " + currentProp.getValue());
			
			String isMutableResult = "";
			if (currentProp.isMutable()) {
				isMutableResult = "Yes";
			} else if (!currentProp.isMutable()) {
				isMutableResult = "No";
			}
			System.out.println("Property Mutable: " + isMutableResult);
		}
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setLEDGreen method.
	 * @throws Exception
	 */
	public void testSetLEDGreen() throws Exception {
		int ioctl1 = igmc.setLEDGreen(true);
		assertEquals(0, ioctl1);
		System.out.println("LED On IOCTL Return: " + ioctl1);
		int ioctl2 = igmc.setLEDGreen(false);
		assertEquals(0, ioctl2);
		System.out.println("LED Off IOCTL Return: " + ioctl2);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setLEDRed method.
	 * @throws Exception
	 */
	public void testSetLEDRed() throws Exception {
		int ioctl1 = igmc.setLEDRed(true);
		assertEquals(0, ioctl1);
		System.out.println("LED On IOCTL Return: " + ioctl1);
		int ioctl2 = igmc.setLEDRed(false);
		assertEquals(0, ioctl2);
		System.out.println("LED Off IOCTL Return: " + ioctl2);
		System.out.println("\n\n");
	}
	
}
