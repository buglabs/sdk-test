package com.buglabs.bug.module.camera.tests;

import java.util.Map;

import junit.framework.TestCase;

import com.buglabs.bug.module.camera.pub.ICamera2Device;
import com.buglabs.bug.module.camera.pub.ICamera2ModuleControl;
import com.buglabs.bug.module.camera.pub.ICameraModuleControl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import com.buglabs.util.ogsgi.ServiceTrackerUtil;

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
		ICamera2Device.class.getName(),		
		ICamera2ModuleControl.class.getName(),
		ICameraModuleControl.class.getName(),
	};	
	
	private ServiceTracker serviceTracker;
	private static BundleContext context;
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(final BundleContext context) throws Exception {
		this.context = context;
		serviceTracker = ServiceTrackerHelper.openServiceTracker(context, services, new ServiceTrackerHelper.ManagedInlineRunnable() {
                       
				private ServiceRegistration reg;
				private ServiceRegistration reg1;
				private ServiceRegistration reg2;
				
				@Override
				public void run(Map<Object, Object> services) {                
					reg = context.registerService(TestCase.class.getName(), new TestICamera2Device(), null);
					reg1 = context.registerService(TestCase.class.getName(), new TestICamera2ModuleControl(), null);
					reg2 = context.registerService(TestCase.class.getName(), new TestICameraModuleControl(), null);
				}
				
				@Override
				public void shutdown() {
					reg.unregister();
					reg1.unregister();
					reg2.unregister();
				}
		});
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
