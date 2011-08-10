package com.buglabs.bug.module.camera.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Map;

import org.junit.Test;

import com.buglabs.bug.module.camera.pub.ICamera2Device;
import com.buglabs.bug.module.camera.pub.ICamera2ModuleControl;
import com.buglabs.bug.module.camera.pub.ICameraModuleControl;

import com.buglabs.application.ServiceTrackerHelper.ManagedRunnable;

/**
 * Test suite for the Camera module.
 * @author barberdt
 *
 */
public class TestMain implements ManagedRunnable {
	
	private String lineBreak = "\n----------------------------------------\n";

	@Override
	public void run(Map<Object, Object> services) {
		ICamera2Device iCamera2Device = (ICamera2Device) services.get(ICamera2Device.class.getName());			
		ICamera2ModuleControl iCamera2ModuleControl = (ICamera2ModuleControl) services.get(ICamera2ModuleControl.class.getName());
		ICameraModuleControl iCameraModuleControl = (ICameraModuleControl) services.get(ICameraModuleControl.class.getName());
		
		try {
	
			// ICamera2Device tests
			testCameraClose(iCamera2Device);
			testCameraOpen(iCamera2Device);
			testCameraOpenDefault(iCamera2Device);
			testCameraStart(iCamera2Device);
			testCameraStop(iCamera2Device);
			testGrabFull(iCamera2Device);
			testGrabPreview(iCamera2Device);
			testIsCameraOpen(iCamera2Device);
			testIsCameraStarted(iCamera2Device);
			
			// ICamera2ModuleControl tests
			testGetColorEffects(iCamera2ModuleControl);
			testGetExposureLevel(iCamera2ModuleControl);
			testGetHorizontalMirror(iCamera2ModuleControl);
			testGetTestPattern(iCamera2ModuleControl);
			testGetVerticalFlip(iCamera2ModuleControl);
			testSetColorEffects(iCamera2ModuleControl);
			testSetExposureLevel(iCamera2ModuleControl);
			testSetHorizontalMirror(iCamera2ModuleControl);
			testSetTestPattern(iCamera2ModuleControl);
			testSetVerticalFlip(iCamera2ModuleControl);
			
			// ICameraModuleControl tests
			testGetSelectedCamera(iCameraModuleControl);
			testSetFlashBeamIntensity(iCameraModuleControl);
			testSetLEDFlash(iCameraModuleControl);
			//testSetSelectedCamera(iCameraModuleControl); NOT WRITTEN YET, METHOD NOT FUNCTIONAL
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}
	
	// ICamera2Device tests
	/**
	 * Tests the cameraClose() method of the ICamera2Device interface.
	 * Asserts that the ioctl return value is 0 and prints the value.
	 * @param iCamera2Device
	 * 		the ICamera2Device to be called on
	 * @throws Exception
	 */
	@Test
	public void testCameraClose(ICamera2Device iCamera2Device) throws Exception {
		System.out.println(lineBreak);
		System.out.println("***testCameraClose***");
		int ioctl = iCamera2Device.cameraClose();
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the cameraOpen() method of the ICamera2Device interface.
	 * Asserts that the ioctl return value is 0 and prints the value.
	 * @param iCamera2Device
	 * 		the ICamera2Device to be called on
	 * @throws Exception
	 */
	@Test
	public void testCameraOpen(ICamera2Device iCamera2Device) throws Exception {
		System.out.println("***testCameraOpen***");
		System.out.println("Method Output: ");
		int ioctl = iCamera2Device.cameraOpen(ICamera2Device.DEFAULT_MEDIA_NODE, -1, 2048, 1536, 320, 240);
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the cameraOpenDefault() method of the ICamera2Device interface.
	 * Asserts that the ioctl return value is 0 and prints the value. 
	 * @param iCamera2Device
	 * 		the ICamera2Device to be called on
	 * @throws Exception
	 */
	public void testCameraOpenDefault(ICamera2Device iCamera2Device) throws Exception {
		System.out.println("***testCameraOpenDefault***");
		System.out.println("Method Output:");
		int ioctl = iCamera2Device.cameraOpenDefault();
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		System.out.println(lineBreak);
	}

	/**
	 * Tests the cameraStart() method of the ICamera2Device interface.
	 * Asserts that the ioctl return value is 0 and prints the value.
	 * @param iCamera2Device
	 * 		the ICamera2Device to be called on.
	 * @throws Exception
	 */
	public void testCameraStart(ICamera2Device iCamera2Device) throws Exception {
		System.out.println("***testCameraStart***");
		System.out.println("Method Output");
		int ioctl = iCamera2Device.cameraStart();
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the cameraStop() method of the ICamera2Device interface.
	 * Asserts that the ioctl return value is 0 and prints the value.
	 * @param iCamera2Device
	 * 		the ICamera2Device to be called on.
	 * @throws Exception
	 */
	public void testCameraStop(ICamera2Device iCamera2Device) throws Exception {
		System.out.println("***testCameraStop***");
		System.out.println("Method Output:");
		int ioctl = iCamera2Device.cameraStop();
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the grabFull() method of the ICamera2Device interface.
	 * Asserts that the returned byte array is not null and prints the array.
	 * @param iCamera2Device
	 * 		the ICamera2Device to be called on.
	 * @throws Exception
	 */
	public void testGrabFull(ICamera2Device iCamera2Device) {
		System.out.println("***testGrabFull");
		System.out.println("Method Output");
		iCamera2Device.cameraStart();	
		System.out.println("Method Output:");
		byte[] grabResult = iCamera2Device.grabFull();
		assertNotNull(grabResult);
		System.out.println("Grab Out: " + grabResult);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the grabPrieview() method of the ICamera2Device interface.
	 * Asserts that the returned boolean is not null and prints the boolean.
	 * @param iCamera2Device
	 * 		the ICamera2Device to be called on.
	 * @throws Exception
	 */
	@Test
	public void testGrabPreview(ICamera2Device iCamera2Device) throws Exception {
		System.out.println("***testGrabPreview***");
		System.out.println("Method Output");
		iCamera2Device.cameraStart();
		System.out.println("Method Output:");
		BufferedImage image = new BufferedImage(240, 320, BufferedImage.TYPE_INT_RGB);
		int [] buf = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		boolean grabResult = iCamera2Device.grabPreview(buf);
		assertNotNull(grabResult);
		System.out.println("Boolean Return: " + grabResult);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the isCameraOpen() method of the ICamera2Device interface.
	 * Asserts that the returned boolean is not null and prints the boolean.
	 * @param iCamera2Device
	 * 		the ICamera2Device to be called on.
	 * @throws Exception
	 */
	@Test
	public void testIsCameraOpen(ICamera2Device iCamera2Device) throws Exception {
		System.out.println("***testIsCameraOpen***");
		boolean isOpenResult = iCamera2Device.isCameraOpen();
		assertNotNull(isOpenResult);
		System.out.println("Boolean Return: " + isOpenResult);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the isCameraStarted() method of the ICamera2Device interface.
	 * Asserts that the returned boolean is not null and prints the boolean.
	 * @param iCamera2Device
	 * 		the ICamera2Device to be called on.
	 * @throws Exception
	 */
	@Test
	public void testIsCameraStarted(ICamera2Device iCamera2Device) throws Exception {
		System.out.println("***testIsCameraStarted***");
		boolean isStartedResult = iCamera2Device.isCameraStarted();
		assertNotNull(isStartedResult);
		System.out.println("Boolean Return: " + isStartedResult);
		System.out.println(lineBreak);
	}
	
	// ICamera2ModuleControl Tests
	/**
	 * Tests the getColorEffects() method of the ICamera2ModuleControl interface.
	 * Asserts that the returned setting is not null and prints the setting.
	 * @param iCamera2ModuleControl
	 * 		the ICamera2ModuleControl to be called on
	 * @throws Exception
	 */
	public void testGetColorEffects(ICamera2ModuleControl iCamera2ModuleControl) throws Exception {
		System.out.println("***testGetColorEffects***");
		int effectsSetting = iCamera2ModuleControl.getColorEffects();
		assertNotNull(effectsSetting);
		System.out.println("Color Effects Setting: " + effectsSetting);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the getExposureLevel() method of the ICamera2ModuleControl interface.
	 * Asserts that the returned level is not null and prints the level.
	 * @param iCamera2ModuleControl
	 * 		the ICamera2ModuleControl to be called on.
	 * @throws Exception
	 */
	public void testGetExposureLevel(ICamera2ModuleControl iCamera2ModuleControl) throws Exception {
		System.out.println("***testGetExposureLevel***");
		int exposureLevel = iCamera2ModuleControl.getExposureLevel();
		assertNotNull(exposureLevel);
		System.out.println("Exposure Level: " + exposureLevel);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the getHorizontalMirror() method of the ICamera2ModuleControl interface.
	 * Asserts that the returned int is not null and prints the int.
	 * @param iCamera2ModuleControl
	 * 		the ICamear2ModuleControl to be called on.
	 * @throws Exception
	 */
	public void testGetHorizontalMirror(ICamera2ModuleControl iCamera2ModuleControl) throws Exception {
		System.out.println("***testGetHorizontalMirror***");
		int horizMirror = iCamera2ModuleControl.getHorizontalMirror();
		assertNotNull(horizMirror);
		System.out.println("Horizontal Mirror: " + horizMirror);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the getTestPattern() method of the ICamera2ModuleControl interface.
	 * Asserts that the returned pattern is not null and prints the pattern.
	 * @param iCamera2ModuleControl
	 * 		the ICamera2ModuleControl to be called on.
	 * @throws Exception
	 */
	public void testGetTestPattern(ICamera2ModuleControl iCamera2ModuleControl) throws Exception {
		System.out.println("***testGetTestPattern***");
		int testPattern = iCamera2ModuleControl.getTestPattern();
		assertNotNull(testPattern);
		System.out.println("Test Pattern: " + testPattern);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the getVerticalFlip() method of the ICamera1ModuleControl interface.
	 * Asserts that the returned int is not null and prints the int.
	 * @param iCamera2ModuleControl
	 * 		the ICamera2ModuleControl to be called on.
	 * @throws Exception
	 */
	public void testGetVerticalFlip(ICamera2ModuleControl iCamera2ModuleControl) throws Exception {
		System.out.println("***testGetVerticalFlip***");
		int vertFlip = iCamera2ModuleControl.getVerticalFlip();
		assertNotNull(vertFlip);
		System.out.println("Vertical Flip: " + vertFlip);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setColorEffects() method of the ICamear2ModuleControl interface.
	 * Asserts that the returned ioctl value is 0 and prints the value.
	 * Asserts that the resulting setting is equal to the input setting and prints 
	 * the resulting setting.
	 * @param iCamera2ModuleControl
	 * 		the ICamera2ModuleControl to be called on.
	 * @throws Exception
	 */
	public void testSetColorEffects(ICamera2ModuleControl iCamera2ModuleControl) throws Exception {
		System.out.println("***testSetColorEffects***");
		System.out.println("Current Setting: " + iCamera2ModuleControl.getColorEffects());
		int inputSetting = 1;
		System.out.println("Input Setting: " + inputSetting);
		int ioctl = iCamera2ModuleControl.setColorEffects(inputSetting);
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		int resultingSetting = iCamera2ModuleControl.getColorEffects();
		assertEquals(resultingSetting, inputSetting);
		System.out.println("Resulting Setting: " + iCamera2ModuleControl.getColorEffects());
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setExposureLevel() method of the ICamera2ModuleControl interface.
	 * Asserts that the returned ioctl value is 0 and prints the value.
	 * Asserts that the resulting level is equal to the input level and prints the
	 * resulting level.
	 * @param iCamera2ModuleControl
	 * 		the ICamera2ModuleControl to be called on.
	 * @throws Exception
	 */
	public void testSetExposureLevel(ICamera2ModuleControl iCamera2ModuleControl) throws Exception {
		System.out.println("***testSetExposureLevel");
		System.out.println("Current Level: " + iCamera2ModuleControl.getExposureLevel());
		int inputLevel = 100;
		System.out.println("Input Level: " + inputLevel);
		int ioctl = iCamera2ModuleControl.setExposureLevel(inputLevel);
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		int resultingLevel = iCamera2ModuleControl.getExposureLevel();
		assertEquals(resultingLevel, inputLevel);
		System.out.println("Resulting Level: " + resultingLevel);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setHorizontalMirror() method of the ICamera2ModuleControl interface.
	 * Asserts that the returned ioctl value is 0 and prints the value.
	 * Asserts that the resulting setting is equal to the input setting and prints
	 * the resulting setting.
	 * @param iCamera2ModuleControl
	 * 		the ICamera2ModuleControl to be called on.
	 * @throws Exception
	 */
	public void testSetHorizontalMirror(ICamera2ModuleControl iCamera2ModuleControl) throws Exception {
		System.out.println("***testSetHorizontalMirror***");
		System.out.println("Current Setting: " + iCamera2ModuleControl.getHorizontalMirror());
		int inputSetting = 1;
		System.out.println("Input Setting: " + inputSetting);
		int ioctl = iCamera2ModuleControl.setHorizontalMirror(inputSetting);
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		int resultingSetting = iCamera2ModuleControl.getHorizontalMirror();
		assertEquals(resultingSetting, inputSetting);
		System.out.println("Resulting Setting: " + resultingSetting);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setTestPattern() method of the ICamera2ModuleControl interface.
	 * Asserts that the returned ioctl value is 0 and prints the value.
	 * Asserts that the resulting setting is equal to the input setting and prints
	 * the resulting setting.
	 * @param iCamera2ModuleControl
	 * 		the ICamera2ModuleControl to be called on.
	 * @throws Exception
	 */
	public void testSetTestPattern(ICamera2ModuleControl iCamera2ModuleControl) throws Exception {
		System.out.println("***testSetTestPattern");
		System.out.println("Current Setting: " + iCamera2ModuleControl.getTestPattern());
		int inputSetting = 1;
		System.out.println("Input Setting: " + inputSetting);
		int ioctl = iCamera2ModuleControl.setTestPattern(inputSetting);
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		int resultingSetting = iCamera2ModuleControl.getTestPattern();
		assertEquals(resultingSetting, inputSetting);
		System.out.println("Resulting Setting: " + resultingSetting);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setVerticalFlip() method of the ICamera2ModuleControl interface.
	 * Asserts that the returned ioctl value is 0 and prints the value.
	 * Asserts that the resulting setting is equal to the input setting and prints
	 * the resulting setting.
	 * @param iCamera2ModuleControl
	 * 		the ICameara2ModuleControl to be called on.
	 * @throws Exception
	 */
	public void testSetVerticalFlip(ICamera2ModuleControl iCamera2ModuleControl) throws Exception {
		System.out.println("***testSetVerticalFlip***");
		System.out.println("Current Setting: " + iCamera2ModuleControl.getVerticalFlip());
		int inputSetting = 1;
		System.out.println("Input Setting: " + inputSetting);
		int ioctl = iCamera2ModuleControl.setVerticalFlip(inputSetting);
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		int resultingSetting = iCamera2ModuleControl.getVerticalFlip();
		assertEquals(resultingSetting, inputSetting);
		System.out.println("Resulting Setting: " + resultingSetting);
		System.out.println(lineBreak);
	}
	
	// ICameraModuleControl tests
	/**
	 * Tests the getSelectedCamera() method of the ICameraModuleControl interface.
	 * Asserts that the returned slot ID is not null and prints the ID.
	 * @param iCameraModulecontrol
	 * 		the ICameraModuleControl to be called on
	 * @throws Exception
	 */
	public void testGetSelectedCamera(ICameraModuleControl iCameraModuleControl) throws Exception {
		System.out.println("***testGetSelectedCamera***");
		int slotId = iCameraModuleControl.getSelectedCamera();
		assertNotNull(slotId);
		System.out.println("Selected Camera Slot ID: " + slotId);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setFlashBeamIntensity() method of the ICameraModuleControl interface.
	 * Asserts that the returned ioctl value is 0 and prints the value.
	 * @param iCameraModuleControl
	 * 		the ICameraModulecontrol to be called on.
	 * @throws Exception
	 */
	public void testSetFlashBeamIntensity(ICameraModuleControl iCameraModuleControl) throws Exception {
		System.out.println("***testSetFlashBeamIntensity***");
		int inputSetting = 1;
		System.out.println("Input Setting: " + inputSetting);
		int ioctl = iCameraModuleControl.setFlashBeamIntensity(inputSetting);
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setLEDFlash() method of the ICameraModuleControl interface.
	 * Asserts that the returned ioctl value is 0 for both turning the flash on and off.
	 * @param iCameraModuleControl
	 * 		the ICameraModuleControl to be called on.
	 * @throws Exception
	 */
	public void testSetLEDFlash(ICameraModuleControl iCameraModuleControl) throws Exception {
		System.out.println("***testSetLEDFlash***");
		System.out.println("Turning On:");
		int ioctl1 = iCameraModuleControl.setLEDFlash(true);
		assertEquals(ioctl1, 0);
		System.out.println("Ioctl Return: " + ioctl1);
		System.out.println("Turning Off:");
		int ioctl2 = iCameraModuleControl.setLEDFlash(false);
		assertEquals(ioctl2, 0);
		System.out.println("Ioctl Return: " + ioctl2);
		System.out.println(lineBreak);
	}
	
}
