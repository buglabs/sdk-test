package com.buglabs.bug.module.gps.tests;

import junit.framework.TestCase;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.position.Position;

import com.buglabs.bug.module.gps.pub.IPositionProvider;
import com.buglabs.bug.module.gps.pub.LatLon;

/**
 * Tests the IPositionProvider API.
 * @author barberdt
 *
 */
public class TestIPositionProvider extends TestCase {

	private BundleContext context; 
	private ServiceReference sref;
	private IPositionProvider ipp; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		sref = context.getServiceReference(IPositionProvider.class.getName());
		ipp = (IPositionProvider) context.getService(sref);
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
	 * Tests that the IPositionProvider exists.
	 */
	public void testIPositionProviderExists() {
		assertNotNull(ipp);
		System.out.println("IPositionProvider: " + ipp);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getLatitudeLongitude method.
	 */
	public void testGetLatitudeLongitude() {
		LatLon latlon = ipp.getLatitudeLongitude();
		assertNotNull(latlon);
		System.out.println("Latitude: " + latlon.latitude);
		System.out.println("Longitude: " + latlon.longitude);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getPosition method.
	 */
	public void testGetPosition() {
		Position position = ipp.getPosition();
		assertNotNull(position);
		System.out.println("Altitude: " + position.getAltitude());
		System.out.println("Latitude: " + position.getLatitude());
		System.out.println("Longitude: " + position.getLongitude());
		System.out.println("Speed: " + position.getSpeed());
		System.out.println("Track: " + position.getTrack());
		System.out.println("\n\n");
	}

}
