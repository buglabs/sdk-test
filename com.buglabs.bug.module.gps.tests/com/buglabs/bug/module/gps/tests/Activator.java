package com.buglabs.bug.module.gps.tests;

import java.util.Map;

import junit.framework.TestCase;

import com.buglabs.bug.module.gps.pub.IPositionProvider;
import com.buglabs.bug.module.gps.pub.IGPSModuleControl;
import com.buglabs.bug.module.gps.pub.INMEASentenceProvider;
import com.buglabs.bug.module.gps.pub.INMEARawFeed;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import com.buglabs.util.osgi.ServiceTrackerUtil;

/**
 * Activator to the GPS module's on-BUG automated test suite.
 * 
 * @author barberdt
 *
 */
public class Activator implements BundleActivator {
	
    /**
	 * OSGi services the application depends on.
	 */
	private static final String [] services = {		
		IPositionProvider.class.getName(),		
		IGPSModuleControl.class.getName(),		
		INMEASentenceProvider.class.getName(),		
		INMEARawFeed.class.getName(),
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
                        private ServiceRegistration reg3;
                        private ServiceRegistration reg4;
 
                        @Override
                        public void run(Map<String, Object> services) {                
                                reg = context.registerService(TestCase.class.getName(), new TestIPositionProvider(), null);
                                reg1 = context.registerService(TestCase.class.getName(), new TestIGPSModuleControl(), null);
                                reg2 = context.registerService(TestCase.class.getName(), new TestINMEASentenceProvider(), null);
                                reg3 = context.registerService(TestCase.class.getName(), new TestINMEARawFeed(), null);
                                reg4 = context.registerService(TestCase.class.getName(), new TestIPositionSubscriber(), null);
                        }
                        
                        @Override
                        public void shutdown() {
                                reg.unregister();
                                reg1.unregister();
                                reg2.unregister();
                                reg3.unregister();
                                reg4.unregister();
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