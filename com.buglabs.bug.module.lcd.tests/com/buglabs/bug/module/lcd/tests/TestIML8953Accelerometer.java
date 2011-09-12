package com.buglabs.bug.module.lcd.tests;

import junit.framework.TestCase;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.buglabs.bug.accelerometer.pub.AccelerometerSample;
import com.buglabs.bug.module.lcd.pub.IML8953Accelerometer;

/**
 * Tests the IML8953Accelerometer API.
 * @author barberdt
 *
 */
public class TestIML8953Accelerometer extends TestCase {

	private BundleContext context; 
	private ServiceReference sref;
	private IML8953Accelerometer imla; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		sref = context.getServiceReference(IML8953Accelerometer.class.getName());
		imla = (IML8953Accelerometer) context.getService(sref);
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
	 * Tests that the IML8953Accelerometer exists.
	 */
	public void testIML8953AccelerometerExists() {
		assertNotNull(imla);
		System.out.println("IML8953Accelerometer: " + imla);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the readSample method.
	 * @throws Exception
	 */
	public void testReadSample() throws Exception {
		AccelerometerSample sample = imla.readSample();
		assertNotNull(sample);
		System.out.println("Acceleration X: " + sample.getAccelerationX());
		System.out.println("Acceleration Y: " + sample.getAccelerationY());
		System.out.println("Acceleration Z: " + sample.getAccelerationZ());
		float newXYZ = 1;
		System.out.println("Setting X, Y, and Z accelerations to " + newXYZ);
		sample.setAccelerationX(newXYZ);
		sample.setAccelerationY(newXYZ);
		sample.setAccelerationZ(newXYZ);
		float newX = sample.getAccelerationX();
		float newY = sample.getAccelerationY();
		float newZ = sample.getAccelerationZ();
		assertNotNull(newX);
		assertNotNull(newY);
		assertNotNull(newZ);
		System.out.println("New Acceleration X: " + newX);
		System.out.println("New Acceleration Y: " + newY);
		System.out.println("New Acceleration Z: " + newZ);
		System.out.println("AccelerometerSample toString():");
		System.out.println(sample.toString());
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the readX method.
	 * @throws Exception
	 */
	public void testReadX() throws Exception {
		short x = imla.readX();
		assertNotNull(x);
		System.out.println("Acceleration X: " + x);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the readY method.
	 * @throws Exception
	 */
	public void testReadY() throws Exception{
		short y = imla.readY();
		assertNotNull(y);
		System.out.println("Acceleration Y: " + y);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the readZ method.
	 * @throws Exception
	 */
	public void testReadZ() throws Exception {
		short z = imla.readZ();
		assertNotNull(z);
		System.out.println("Acceleration Z: " + z);
		System.out.println("\n\n");
	}
	
}
