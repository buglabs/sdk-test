package com.buglabs.bug.module.lcd.tests;

import java.util.Map;

import junit.framework.TestCase;

import com.buglabs.bug.module.lcd.pub.ILCDModuleControl;
import com.buglabs.bug.module.lcd.pub.IML8953Accelerometer;
import com.buglabs.bug.module.lcd.pub.IModuleDisplay;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import com.buglabs.util.osgi.ServiceTrackerUtil;

/**
 * Activator to the camera module's on-BUG automated test suite.
 *
 * @author barberdt
 */
public class Activator implements BundleActivator {
	
    /**
	 * OSGi services the application depends on.
	 */
	private static final String [] services = {		
		IML8953Accelerometer.class.getName(),		
		ILCDModuleControl.class.getName(),
		IModuleDisplay.class.getName(),
	};	
	
	private ServiceTracker serviceTracker;
	private static BundleContext context;
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext context) throws Exception {
		this.context = context;
		serviceTracker = ServiceTrackerUtil.openServiceTracker(context, new ServiceTrackerUtil.ManagedInlineRunnable() {
                       
				private ServiceRegistration reg;
				private ServiceRegistration reg1;
				private ServiceRegistration reg2;
				
				@Override
				public void run(Map<String, Object> services) {                
					reg = context.registerService(TestCase.class.getName(), new TestIML8953Accelerometer(), null);
					reg1 = context.registerService(TestCase.class.getName(), new TestILCDModuleControl(), null);
					reg2 = context.registerService(TestCase.class.getName(), new TestIModuleDisplay(), null);
				}
				
				@Override
				public void shutdown() {
					reg.unregister();
					reg1.unregister();
					reg2.unregister();
				}
		}, services);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		serviceTracker.close();
	}

	public static BundleContext getBundleContext() {
		return context;
	}
	
}