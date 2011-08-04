package com.buglabs.bug.module.base.tests;

//import static org.junit.Assert.assertNotNull;

import java.util.Map;

import com.buglabs.application.ServiceTrackerHelper.ManagedRunnable;
import com.buglabs.bug.base.pub.IBUG20BaseControl;
import com.buglabs.bug.base.pub.ITimeProvider;


public class TestMain implements ManagedRunnable {
	
	private String lineBreak = "\n----------------------------------------\n";

	@Override
	public void run(Map<Object, Object> services) {
		
		IBUG20BaseControl iBUG20BaseControl = (IBUG20BaseControl) services.get(IBUG20BaseControl.class.getName());
		ITimeProvider iTimeProvider = (ITimeProvider) services.get(ITimeProvider.class.getName());
		
		try {
		
			// IBUG20BaseControl Tests
			testGetLEDBrightness(iBUG20BaseControl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}
	
	// IBUG20BaseControl Tests
	private void testGetLEDBrightness(IBUG20BaseControl iBUG20BaseControl) throws Exception {
		System.out.println("***testGetLEDBrightness***");
		
	}
	
	
	
	
}
