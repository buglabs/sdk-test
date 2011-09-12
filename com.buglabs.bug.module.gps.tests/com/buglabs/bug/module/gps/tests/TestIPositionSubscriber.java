package com.buglabs.bug.module.gps.tests;

import junit.framework.TestCase;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.position.Position;

import com.buglabs.bug.module.gps.pub.IPositionSubscriber;

/**
 * Tests the IPositionSubscriber API.
 * @author barberdt
 *
 */
public class TestIPositionSubscriber extends TestCase {

	private BundleContext context; 
	private ServiceRegistration ips; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		ips = context.registerService(IPositionSubscriber.class.getName(), this, null);
	}
	
	/**
	 * Tests that the IPositionSubsriber exists.
	 */
	public void testIPositionSubsriberExists() {
		assertNotNull(ips);
		System.out.println("		IPositionSubsriber: " + ips);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the positionUpdate method.
	 */
	public void testPositionUpdate(Position position) {
		assertNotNull(position);
		System.out.println("Position Updated: " + position.toString());
		System.out.println("\n\n");
	}
	
}
