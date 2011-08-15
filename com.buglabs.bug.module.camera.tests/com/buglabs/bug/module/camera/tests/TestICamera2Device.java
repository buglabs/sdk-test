package com.buglabs.bug.module.camera.tests;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import junit.framework.TestCase;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.buglabs.bug.module.camera.pub.ICamera2Device;

/**
 * Tests the ICamera2Device API.
 * @author barberdt
 *
 */
public class TestICamera2Device extends TestCase {

	private BundleContext context; 
	private ServiceReference sref;
	private ICamera2Device ic2d; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		sref = context.getServiceReference(ICamera2Device.class.getName());
		ic2d = (ICamera2Device) context.getService(sref);
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
	 * Tests that the ICamera2Device exists.
	 */
	public void testICamera2DeviceExists() {
		assertNotNull(ic2d);
		System.out.println("ICamera2Device: " + ic2d);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the cameraClose method.
	 */
	public void testCameraClose() {
		int ioctl = ic2d.cameraClose();
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the cameraOpen method. 
	 */
	public void testCameraOpen() {
		int ioctl = ic2d.cameraOpen(ICamera2Device.DEFAULT_MEDIA_NODE, -1, 2048, 1536, 320, 240);
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the cameraOpenDefault method.
	 */
	public void testCameraOpenDefault() {
		int ioctl = ic2d.cameraOpenDefault();
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the cameraStop method.
	 */
	public void testCameraStop() {
		int ioctl = ic2d.cameraStop();
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		System.out.println("\n\n");
	}
		
	/**
	 * Tests the cameraStart method.
	 */
	public void testCameraStart() {
		int ioctl = ic2d.cameraStart();
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the grabFull method.
	 */
	public void testGrabFull() {
		byte[] grabResult = ic2d.grabFull();
		assertNotNull(grabResult);
		System.out.println("Grab Output: " + grabResult);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the grabPreview method.
	 */
	public void testGrabPreview() {
		BufferedImage image = new BufferedImage(240, 320, BufferedImage.TYPE_INT_RGB);
		int[] buf = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		boolean grabResult = ic2d.grabPreview(buf);
		assertNotNull(grabResult);
		System.out.println("Boolean Return: " + grabResult);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the isCameraOpen method.
	 */
	public void testIsCameraOpen() {
		boolean isOpen = ic2d.isCameraOpen();
		assertNotNull(isOpen);
		System.out.println("Boolean Return: " + isOpen);
		System.out.println("\n\n");
	}
	
	
	/**
	 * Tests the isCameraStarted method.
	 */
	public void testIsCameraStarted() {
		boolean isStarted = ic2d.isCameraStarted();
		assertNotNull(isStarted);
		System.out.println("Boolean Return: " + isStarted);
		System.out.println("\n\n");
	}
	
}
