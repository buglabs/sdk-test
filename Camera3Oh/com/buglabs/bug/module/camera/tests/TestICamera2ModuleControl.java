package com.buglabs.bug.module.camera.tests;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.buglabs.bug.module.camera.pub.ICamera2ModuleControl;

import junit.framework.TestCase;

/**
 * Tests the ICamera2ModuleControl API.
 * @author barberdt
 *
 */
public class TestICamera2ModuleControl extends TestCase {

	private BundleContext context; 
	private ServiceReference sref;
	private ICamera2ModuleControl ic2mc; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		sref = context.getServiceReference(ICamera2ModuleControl.class.getName());
		ic2mc = (ICamera2ModuleControl) context.getService(sref);
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
	 * Tests that the ICamera2ModuleControl exists.
	 */
	public void testICamera2DeviceExists() {
		assertNotNull(ic2mc);
		System.out.println("ICamera2ModuleControl: " + ic2mc);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getColorEffects method.
	 */
	public void testGetColorEffects() {
		int effects = ic2mc.getColorEffects();
		assertNotNull(effects);
		System.out.println("Color Effects Setting: " + effects);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getExsposureLevel method.
	 */
	public void testGetExposureLevel() {
		int level = ic2mc.getExposureLevel();
		assertNotNull(level);
		System.out.println("Exposure Level: " + level);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getHorizontalMirror method.
	 */
	public void testGetHorizontalMirror() {
		int mirror = ic2mc.getHorizontalMirror();
		assertNotNull(mirror);
		System.out.println("Horizontal Mirror Setting: " + mirror);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getTestPattern method.
	 */
	public void testGetTestPattern() {
		int pattern = ic2mc.getTestPattern();
		assertNotNull(pattern);
		System.out.println("Test Pattern: " + pattern);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setColorEffects
	 */
	public void testSetColorEffects() {
		System.out.println("Current Setting: " + ic2mc.getColorEffects());
		int inputSetting = 1;
		System.out.println("Input Setting: " + inputSetting);
		int ioctl = ic2mc.setColorEffects(inputSetting);
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		int resultingSetting = ic2mc.getColorEffects();
		assertEquals(inputSetting, resultingSetting);
		System.out.println("Resulting Setting: " + resultingSetting);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setExposureLevel method.
	 */
	public void testSetExposureLevel() {
		System.out.println("Current Setting: " + ic2mc.getExposureLevel());
		int inputSetting = 1;
		System.out.println("Input Setting: " + inputSetting);
		int ioctl = ic2mc.setExposureLevel(inputSetting);
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		int resultingSetting = ic2mc.getExposureLevel();
		System.out.println("Resulting Setting: " + resultingSetting);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setHorizontalMirror method.
	 */
	public void testSetHorizontalMirror() {
		System.out.println("Current Setting: " + ic2mc.getHorizontalMirror());
		int inputSetting = 1;
		System.out.println("Input Setting: " + inputSetting);
		int ioctl = ic2mc.setHorizontalMirror(inputSetting);
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		int resultingSetting = ic2mc.getHorizontalMirror();
		assertEquals(inputSetting, resultingSetting);
		System.out.println("Resulting Setting: " + resultingSetting);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setTestPattern method.
	 */
	public void testSetTestPattern() {
		System.out.println("Current Setting: " + ic2mc.getTestPattern());
		int inputSetting = 1;
		System.out.println("Input Setting: " + inputSetting);
		int ioctl = ic2mc.setTestPattern(inputSetting);
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		int resultingSetting = ic2mc.getTestPattern();
		assertEquals(inputSetting, resultingSetting);
		System.out.println("Resulting Setting: " + resultingSetting);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the setVerticalFlip method.
	 */
	public void testSetVerticalFlip() {
		System.out.println("Current Setting: " + ic2mc.getVerticalFlip());
		int inputSetting = 1;
		System.out.println("Input Setting: " + 1);
		int ioctl = ic2mc.setVerticalFlip(inputSetting);
		assertEquals(0, ioctl);
		System.out.println("IOCTL Return: " + ioctl);
		int resultingSetting = ic2mc.getVerticalFlip();
		assertEquals(inputSetting, resultingSetting);
		System.out.println("Resulting Setting: " + resultingSetting);
		System.out.println("\n\n");
	}
	
}
