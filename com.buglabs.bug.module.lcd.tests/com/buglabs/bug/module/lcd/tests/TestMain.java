package com.buglabs.bug.module.lcd.tests;

import java.awt.Frame;
import java.util.Map;

import com.buglabs.bug.accelerometer.pub.AccelerometerSample;
import com.buglabs.bug.module.lcd.pub.IML8953Accelerometer;
import com.buglabs.bug.module.lcd.pub.ILCDModuleControl;
import com.buglabs.bug.module.lcd.pub.IModuleDisplay;

import com.buglabs.application.ServiceTrackerHelper.ManagedRunnable;
/**
 * This class represents the running application when all service dependencies are fulfilled.
 * 
 * The run() method will be called with a map containing all the services specified in ServiceTrackerHelper.openServiceTracker().
 * The application will run in a separate thread than the caller of start() in the Activator.  See 
 * ManagedInlineRunnable for a thread-less application.
 * 
 * By default, the application will only be started when all service dependencies are fulfilled.  For 
 * finer grained service binding logic, see ServiceTrackerHelper.openServiceTracker(BundleContext context, String[] services, Filter filter, ServiceTrackerCustomizer customizer)
 */
public class TestMain implements ManagedRunnable {

	private String lineBreak = "\n----------------------------------------\n";

	@Override
	public void run(Map<Object, Object> services) {
		
		IML8953Accelerometer iML8953Accelerometer = (IML8953Accelerometer) services.get(IML8953Accelerometer.class.getName());			
		ILCDModuleControl iLCDModuleControl = (ILCDModuleControl) services.get(ILCDModuleControl.class.getName());
		IModuleDisplay iModuleDisplay = (IModuleDisplay) services.get(IModuleDisplay.class.getName());
		
		try {
			
			// IML8953Accelerometer Tests
			testReadSample(iML8953Accelerometer);
			testReadX(iML8953Accelerometer);
			testReadY(iML8953Accelerometer);
			testReadZ(iML8953Accelerometer);
			
			// ILCDModuleControl Tests
			//testDisable(iLCDModuleControl); Method does not exist at the moment
			//testEnable(iLCDModuleControl); Method does not exist at the moment
			testSetBackLight(iLCDModuleControl);
			
			// IModuleDisplay Tests
			testGetFrame(iModuleDisplay);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		// TODO Add shutdown code here if necessary.
	}
	
	// IML8953Accelerometer Tests
	private void testReadSample(IML8953Accelerometer iML8953Accelerometer) throws Exception {
		System.out.println(lineBreak);
		System.out.println("***testReadsample***");
		AccelerometerSample sample = iML8953Accelerometer.readSample();
		System.out.println("Acceleration X: " + sample.getAccelerationX());
		System.out.println("Acceleration Y: " + sample.getAccelerationY());
		System.out.println("Acceleration Z: " + sample.getAccelerationZ());
		float newXYZ = 1;
		System.out.println("Setting X, Y, and Z accelerations to " + newXYZ);
		sample.setAccelerationX(newXYZ);
		sample.setAccelerationY(newXYZ);
		sample.setAccelerationZ(newXYZ);
		System.out.println("New Acceleration X: " + sample.getAccelerationX());
		System.out.println("New Acceleration Y: " + sample.getAccelerationY());
		System.out.println("New Acceleration Z: " + sample.getAccelerationZ());
		System.out.println("AccelerometerSample toString():");
		System.out.println(sample.toString());
		System.out.println(lineBreak);
	}
	
	private void testReadX(IML8953Accelerometer iML8953Accelerometer) throws Exception {
		System.out.println("***testReadX***");
		short x = iML8953Accelerometer.readX();
		System.out.println("Acceleration X: " + x);
		System.out.println(lineBreak);
	}
	
	private void testReadY(IML8953Accelerometer iML8953Accelerometer) throws Exception {
		System.out.println("***testReadY***");
		short y = iML8953Accelerometer.readY();
		System.out.println("Acceleration Y: " + y);
		System.out.println(lineBreak);
	}
	
	private void testReadZ(IML8953Accelerometer iML8953Accelerometer) throws Exception {
		System.out.println("***testReadZ***");
		short z = iML8953Accelerometer.readZ();
		System.out.println("Acceleration Z: " + z);
		System.out.println(lineBreak);
	}
	
	// ILCDModuleControl Tests
	private void testDisable(ILCDModuleControl iLCDModuleControl) throws Exception {
		System.out.println("***testDisable***");
		int disableOut = iLCDModuleControl.disable();
		System.out.println("Disable Out: " + disableOut);
		System.out.println(lineBreak);
	}
	
	private void testEnable(ILCDModuleControl iLCDModuleControl) throws Exception {
		System.out.println("***testEnable***");
		int enableOut = iLCDModuleControl.enable();
		System.out.println("Enable Out: " + enableOut);
		System.out.println(lineBreak);
	}
	
	private void testSetBackLight(ILCDModuleControl iLCDModuleControl) throws Exception {
		System.out.println("***testSetBacklight***");
		int backLight = 0;
		System.out.println("Setting back light to " + backLight);
		int methodOut = iLCDModuleControl.setBackLight(backLight);
		System.out.println("Method Out: " + methodOut);
		backLight = 100;
		System.out.println("Setting back light to " + backLight);
		methodOut = iLCDModuleControl.setBackLight(backLight);
		System.out.println("Method Out: " + methodOut);
		System.out.println(lineBreak);
	}
	
	// IModuleDisplay Tests
	private void testGetFrame(IModuleDisplay iModuleDisplay) throws Exception {
		System.out.println("***testGetFrame***");
		Frame frame = iModuleDisplay.getFrame();
		String frameTitle = frame.getTitle();
		System.out.println("Frame Title: " + frameTitle);
		System.out.println(lineBreak);
	}
	
	
}