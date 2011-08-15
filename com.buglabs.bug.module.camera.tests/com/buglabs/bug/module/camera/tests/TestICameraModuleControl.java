package com.buglabs.bug.module.camera.tests;

import junit.framework.TestCase;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.buglabs.bug.module.camera.pub.ICameraModuleControl;

/**
 * Tests the ICameraModuleControl API.
 * @author barberdt
 *
 */
public class TestICameraModuleControl extends TestCase {

	private BundleContext context; 
	private ServiceReference sref;
	private ICameraModuleControl icmc; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		sref = context.getServiceReference(ICameraModuleControl.class.getName());
		icmc = (ICameraModuleControl) context.getService(sref);
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
	 * Tests that the ICameraModuleControl exists.
	 */
	public void testICameraDeviceExists() {
		assertNotNull(icmc);
		System.out.println("ICameraModuleControl: " + icmc);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getSelectedCamera method.
	 * @throws Exception
	 */
	public void testGetSelectedCamera() throws Exception {
		int slotId = icmc.getSelectedCamera();
		assertNotNull(slotId);
		System.out.println("Selected Camera Slot ID: " + slotId);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setFlashBeamIntensity method.
	 * @throws Exception
	 */
	public void testSetFlashBeamIntensity() throws Exception {
		int inputSetting = 1;
		System.out.println("Input Setting: " + inputSetting);
		int ioctl = icmc.setFlashBeamIntensity(inputSetting);
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setLEDFlash method.
	 * @throws Exception
	 */
	public void testSetLEDFlash() throws Exception {
		System.out.println("Turning Flash On:");
		int ioctl1 = icmc.setLEDFlash(true);
		assertEquals(0, ioctl1);
		System.out.println("IOCTL Return: " + ioctl1);
		System.out.println("Turning Flash Off:");
		int ioctl2 = icmc.setLEDFlash(false);
		assertEquals(0, ioctl2);
		System.out.println("IOCTL Return: " + ioctl2);
		System.out.println();
	}
	
}
