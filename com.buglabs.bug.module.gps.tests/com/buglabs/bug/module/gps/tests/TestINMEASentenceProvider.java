package com.buglabs.bug.module.gps.tests;

import junit.framework.TestCase;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.buglabs.bug.module.gps.pub.INMEASentenceProvider;
import com.buglabs.nmea.DegreesMinutesSeconds;
import com.buglabs.nmea2.RMC;

/**
 * Tests the INMEASentenceProvider API.
 * @author barberdt
 *
 */
public class TestINMEASentenceProvider extends TestCase {

	private BundleContext context; 
	private ServiceReference sref;
	private INMEASentenceProvider insp; 
	
	/**
	 * Sets up test dependencies.
	 * @throws Exception
	 */
	protected void setUp() throws Exception {
		context = Activator.getBundleContext();
		sref = context.getServiceReference(INMEASentenceProvider.class.getName());
		insp = (INMEASentenceProvider) context.getService(sref);
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
	 * Tests that the INMEASentenceProvider exists.
	 */
	public void testINMEASentenceProviderExists() {
		assertNotNull(insp);
		System.out.println("INMEASentenceProvider: " + insp);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getIndex method.
	 */
	public void testGetIndex() {
		int index = insp.getIndex();
		assertNotNull(index);
		System.out.println("Index: " + index);
		System.out.println("\n\n");
	}
	
	/**
	 * Tests the getLastRMC method.
	 */
	public void testGetLastRMC() {
		RMC lastRMC = insp.getLastRMC();
		assertNotNull(lastRMC);
		System.out.println("Data Status: " + lastRMC.getDataStatus());
		System.out.println("Date Stamp: " + lastRMC.getDateStamp());
		System.out.println("Ground Speed: " + lastRMC.getGroundSpeed());
		System.out.println("Laditude: " + lastRMC.getLongitude());
		System.out.println("");
		
		DegreesMinutesSeconds latDMS = lastRMC.getLatitudeAsDMS();
		assertNotNull(latDMS);
		System.out.println("Latitude as DMS: ");
		System.out.println("Degrees: " + latDMS.getDegrees());
		System.out.println("Direction: " + latDMS.getDirection());
		System.out.println("Minutes: " + latDMS.getMinutes());
		System.out.println("Seconds: " + latDMS.getSeconds());
		System.out.println("Dec. Degrees: " + latDMS.toDecimalDegrees());
		System.out.println("");
		
		System.out.println("Longitude: " + lastRMC.getLongitude());
		System.out.println("");
		
		DegreesMinutesSeconds lonDMS = lastRMC.getLongitudeAsDMS();
		assertNotNull(latDMS);
		System.out.println("Longitude as DMS: ");
		System.out.println("Degrees: " + lonDMS.getDegrees());
		System.out.println("Direction: " + lonDMS.getDirection());
		System.out.println("Minutes: " + lonDMS.getMinutes());
		System.out.println("Seconds: " + lonDMS.getSeconds());
		System.out.println("Dec. Degrees: " + lonDMS.toDecimalDegrees());
		System.out.println("");
		
		System.out.println("Magnetic Variation: " + lastRMC.getMagneticVariation());
		System.out.println("Time of Fix: " + lastRMC.getTimeOfFix());
		System.out.println("Track Made Good: " + lastRMC.getTrackMadeGood());
		System.out.println("\n\n");
	}
	
}
