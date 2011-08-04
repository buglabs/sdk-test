package com.buglabs.bug.module.gps.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.buglabs.application.ServiceTrackerHelper.ManagedRunnable;
import com.buglabs.bug.module.gps.pub.IGPSModuleControl;
import com.buglabs.bug.module.gps.pub.INMEARawFeed;
import com.buglabs.bug.module.gps.pub.INMEASentenceProvider;
import com.buglabs.bug.module.gps.pub.IPositionProvider;
import com.buglabs.bug.module.gps.pub.LatLon;
import com.buglabs.module.IModuleProperty;
import com.buglabs.nmea.DegreesMinutesSeconds;
import com.buglabs.nmea2.RMC;

import org.junit.Test;
import org.osgi.util.position.Position;

/**
 * Test suite for the GPS module.
 * @author barberdt
 *
 */
public class TestMain implements ManagedRunnable {
	
	private String lineBreak = "\n----------------------------------------\n";

	@Override
	public void run(Map<Object, Object> services) {
		IPositionProvider iPositionProvider = (IPositionProvider) services.get(IPositionProvider.class.getName());	
		IGPSModuleControl iGPSModuleControl = (IGPSModuleControl) services.get(IGPSModuleControl.class.getName());			
		INMEASentenceProvider iNMEASentenceProvider = (INMEASentenceProvider) services.get(INMEASentenceProvider.class.getName());	
		INMEARawFeed iNMEARawFeed = (INMEARawFeed) services.get(INMEARawFeed.class.getName());

		try {
				
			// IPositionProvider Tests
			testGetLatitudeLongitude(iPositionProvider);
			testGetPosition(iPositionProvider);
			
			// IPositionSubscriber Tests
		    //testPositionUpdate(iPositionSubscriber); NOT WRITTEN YET
			
			// IGPSModuleControl Tests
			testSetPassiveAntenna(iGPSModuleControl);
			testSetActiveAntenna(iGPSModuleControl);
			testGetSlotId(iGPSModuleControl);
			testGetStatus(iGPSModuleControl);
			testGetModuleName(iGPSModuleControl);
			testGetModuleProperties(iGPSModuleControl);
			testSetLEDGreen(iGPSModuleControl);
			testSetLEDRed(iGPSModuleControl);
			
			// INMEASentenceProvider Tests
			testGetIndex(iNMEASentenceProvider);
			testGetLastRMC(iNMEASentenceProvider);
			
			// INMEASentenceSubsriber Tests
			//testSentenceReceived(iNMEASentenceSubscriber); NOT WRITTEN YET
			
			// INMEARawFeed Tests
			testGetInputStream(iNMEARawFeed);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}
	
	// IPositionProvider Tests
	/**
	 * Tests the getLatitudeLongitude() method of the IPositionProvider interface.
	 * Asserts that the returned LatLon is not null and prints the returned data.
	 * @param iPositionProvider
	 * 		the IPositionProvider to be called on.
	 * @throws Exception 
	 */
	@Test
	private void testGetLatitudeLongitude(IPositionProvider iPositionProvider) throws Exception {
		System.out.println(lineBreak);
		System.out.println("***testGetLatitudeLongitude***");
		LatLon latLon = iPositionProvider.getLatitudeLongitude();
		assertNotNull(latLon);
		System.out.println("Latitude: " + latLon.latitude + " degrees");
		System.out.println("Longitude: " + latLon.longitude + " degrees");
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the getPosition() method of the IPositionProvider interface.
	 * Asserts that the returned Position is not null and prints the returned data.
	 * @param iPositionProvider
	 * 		the IPositionProvider to be called on.
	 * @throws Exception
	 */
	@Test
	private void testGetPosition(IPositionProvider iPositionProvider) throws Exception {
		System.out.println("***testGetPosition***");
		Position position = iPositionProvider.getPosition();
		assertNotNull(position);
		System.out.println("Altitude: " + position.getAltitude());
		System.out.println("Latitude: " + position.getLatitude());
		System.out.println("Longitude: " + position.getLongitude());
		System.out.println("Speed: " + position.getSpeed());
		System.out.println("Track: " + position.getTrack());
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setPassiveAntenna() method of the IGPSModuleControl interface.
	 * Asserts that the returned ioctl value is 0 and prints the value.
	 * @param iGPSModuleControl
	 * 		the IGPSModuleControl to be called on.
	 * @throws Exception
	 */
	@Test
	private void testSetPassiveAntenna(IGPSModuleControl iGPSModuleControl) throws Exception {
		System.out.println("***testSetPassiveAntenna***");
		int ioctl = iGPSModuleControl.setPassiveAntenna();
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setActiveAntenna() method of the IGPSModuleControl interface.
	 * Asserts that the returned ioctl value is 0 and prints the value.
	 * @param iGPSModuleControl
	 * 		the IGPSModuleControl to be called on.
	 * @throws Exception
	 */
	@Test
	private void testSetActiveAntenna(IGPSModuleControl iGPSModuleControl) throws Exception {
		System.out.println("***testSetActiveAntenna***");
		int ioctl = iGPSModuleControl.setActiveAntenna();
		assertEquals(ioctl, 0);
		System.out.println("Ioctl Return: " + ioctl);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the getSlotId() method of the IGPSModuleControl interface.
	 * Asserts that the returned slot ID is not null and prints the ID.
	 * @param iGPSModuleControl
	 * 		the IGPSModuleControl to be called on.
	 * @throws Exception
	 */
	@Test
	private void testGetSlotId(IGPSModuleControl iGPSModuleControl) throws Exception {
		System.out.println("***testGetSlotId***");
		int slotId = iGPSModuleControl.getSlotId();
		assertNotNull(slotId);
		System.out.println("Slot ID: " + slotId);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the getStatus() method of the IGPSModuleControl interface.
	 * Asserts that the returned status is not null and prints its value.
	 * @param iGPSModuleControl
	 * 		the IGPSModuleControl to be called on.
	 * @throws Exception
	 */
	@Test
	private void testGetStatus(IGPSModuleControl iGPSModuleControl) throws Exception {
		System.out.println("***testGetStatus***");
		int status = iGPSModuleControl.getStatus();
		assertNotNull(status);
		System.out.println("Status Value: " + status);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the getModuleName() method of the IGPSModuleControl interface.
	 * Asserts that the returned module name is not null and prints the name.
	 * @param iGPSModuleControl
	 * 		the IGPSModuleControl to be called on.
	 * @throws Exception
	 */
	@Test
	private void testGetModuleName(IGPSModuleControl iGPSModuleControl) throws Exception {
		System.out.println("***testGetModuleName***");
		String moduleName = iGPSModuleControl.getModuleName();
		assertNotNull(moduleName);
		System.out.println("Module Name: " + moduleName);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the getModuleProperties() method of the IGPSModuleControl interface.
	 * Asserts that the returned list of properties is not null and prints the returned data.
	 * @param iGPSModuleControl
	 * 		the IGPSModuleControl to be called on.
	 * @throws Exception
	 */
	@Test
	private void testGetModuleProperties(IGPSModuleControl iGPSModuleControl) throws Exception {
		System.out.println("***testGetModuleProperties***");
		@SuppressWarnings("unchecked")
		List<IModuleProperty> moduleProps = (List<IModuleProperty>)iGPSModuleControl.getModuleProperties();
		assertNotNull(moduleProps);
		for (int i=0; i < moduleProps.size(); i++) {
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
			System.out.println("Property Mutable: " + isMutableResult + "\n");

		}
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setLEDGreen() method of the IGPSModuleControl interface.
	 * Asserts that the returned ioctl value is 0 for both turning the LED on and off
	 * and prints the value after each method call.
	 * @param iGPSModuleControl
	 * 		the IGPSModuleControl to be called on.
	 * @throws Exception
	 */
	@Test
	private void testSetLEDGreen(IGPSModuleControl iGPSModuleControl) throws Exception {
		System.out.println("***testSetLEDGreen***");
		int ioctl1 = iGPSModuleControl.setLEDGreen(true);
		assertEquals(ioctl1, 0);
		System.out.println("On Ioctl Return: " + ioctl1);
		int ioctl2 = iGPSModuleControl.setLEDGreen(false);
		assertEquals(ioctl2, 0);
		System.out.println("Off Ioctl Return: " + ioctl2);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the setLEDRed() method of the IGPSModuleControl interface.
	 * Asserts that the returned ioctl value is 0 for both turning the LED on and off
	 * and prints the value after each method call.
	 * @param iGPSModuleControl
	 * 		the IGPSModuleControl to be called on.
	 * @throws Exception
	 */
	@Test
	private void testSetLEDRed(IGPSModuleControl iGPSModuleControl) throws Exception {
		System.out.println("***testSetLEDRed***");
		int ioctl1 = iGPSModuleControl.setLEDRed(true);
		assertEquals(ioctl1, 0);
		System.out.println("On Ioctl Return: " + ioctl1);
		int ioctl2 = iGPSModuleControl.setLEDRed(false);
		assertEquals(ioctl2, 0);
		System.out.println("Off Ioctl Return: " + ioctl2);
		System.out.println(lineBreak);
	}
	
	// INMEASentenceProvider Tests
	/**
	 * Tests the getIndex() method of the INMEASentenceProvider interface.
	 * Asserts that the index returned is not null and prints the index.
	 * @param iNMEASentenceProvider
	 * 		the INMEASentenceProvider to be called on.
	 * @throws Exception
	 */
	@Test
	private void testGetIndex(INMEASentenceProvider iNMEASentenceProvider) throws Exception {
		System.out.println("***testGetIndex***");
		int index = iNMEASentenceProvider.getIndex();
		assertNotNull(index);
		System.out.println("Index: " + index);
		System.out.println(lineBreak);
	}
	
	/**
	 * Tests the getLastRMC() method of the INMEASentenceProvider interface.
	 * Asserts that the returned RMC is not null and prints the returned data.
	 * @param iNMEASentenceProvider
	 * 		the INMEASentenceProvider to be called on.
	 * @throws Exception
	 */
	@Test
	private void testGetLastRMC(INMEASentenceProvider iNMEASentenceProvider) throws Exception {
		System.out.println("***testGetLastRMC***");
		RMC lastRMC = iNMEASentenceProvider.getLastRMC();
		assertNotNull(lastRMC);
		System.out.println("Data Status: " + lastRMC.getDataStatus());
		System.out.println("Date Stamp: " + lastRMC.getDateStamp());
		System.out.println("Ground Speed: " + lastRMC.getGroundSpeed());
		System.out.println("Laditude: " + lastRMC.getLongitude() + "\n");
		
		DegreesMinutesSeconds latDMS = lastRMC.getLatitudeAsDMS();
		System.out.println("Latitude as DMS: ");
		System.out.println("Degrees: " + latDMS.getDegrees());
		System.out.println("Direction: " + latDMS.getDirection());
		System.out.println("Minutes: " + latDMS.getMinutes());
		System.out.println("Seconds: " + latDMS.getSeconds());
		System.out.println("Dec. Degrees: " + latDMS.toDecimalDegrees() + "\n");
		
		System.out.println("Longitude: " + lastRMC.getLongitude() + "\n");
		
		DegreesMinutesSeconds lonDMS = lastRMC.getLongitudeAsDMS();
		System.out.println("Longitude as DMS: ");
		System.out.println("Degrees: " + lonDMS.getDegrees());
		System.out.println("Direction: " + lonDMS.getDirection());
		System.out.println("Minutes: " + lonDMS.getMinutes());
		System.out.println("Seconds: " + lonDMS.getSeconds());
		System.out.println("Dec. Degrees: " + lonDMS.toDecimalDegrees() + "\n");
		
		System.out.println("Magnetic Variation: " + lastRMC.getMagneticVariation());
		System.out.println("Time of Fix: " + lastRMC.getTimeOfFix());
		System.out.println("Track Made Good: " + lastRMC.getTrackMadeGood());
		System.out.println(lineBreak);
	}
	
	// INMEARawFeed Tests
	/**
	 * Tests the getInputStream() method of the INMEARawFeed interface.
	 * Asserts that the returned input stream is not null and prints a
	 * sample of the stream.
	 * @param iNMEARawFeed
	 * 		the INMEARawFeed to be called on.
	 * @throws Exception
	 */
	@Test
	private void testGetInputStream(INMEARawFeed iNMEARawFeed) throws Exception {
		System.out.println("***testGetInputStream***");
		InputStream inputStream = iNMEARawFeed.getInputStream();
		assertNotNull(inputStream);
		int bytesRead=0;
		int bytesToRead=1024;
		byte[] input = new byte[bytesToRead];
		while (bytesRead < bytesToRead) {
			int result = inputStream.read(input, bytesRead, bytesToRead - bytesRead);
			if (result == -1) break;
			bytesRead += result;
		}
		String toPrint = new String(input);
		System.out.println("1024 Bytes Read:");
		System.out.println(toPrint);
		System.out.println(lineBreak);
	}

}
