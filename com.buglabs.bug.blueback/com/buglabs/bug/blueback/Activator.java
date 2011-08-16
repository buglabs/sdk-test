package com.buglabs.bug.blueback;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.osgi.util.tracker.ServiceTrackerCustomizer;
import com.buglabs.util.osgi.LogServiceUtil;;

/**
 * An on-BUG testing application that listens for registered TestCase services and runs a JUnit-based
 * TestRunner on them.  Used for automated on-BUG testing of the modules and their APIs.
 * 
 * @author kgilmer, jconnolly, barberdt
 *
 */
public class Activator implements BundleActivator, ServiceTrackerCustomizer, ServiceListener {

	private static BundleContext context;
	protected List testSuites;
	protected LogService log;
	protected volatile int testComplete = 0;
	protected boolean hasFailed = false;

	/**
	 * Listens for registered TestCase services and runs the TestRunner on them.
	 * @param context 
	 * 		The relevant BundleContext.
	 * @throws Exception
	 */
	public void start(BundleContext context) throws Exception {
		Thread.sleep(5000); //Allow felix to finish doing it's thing.
		testSuites = new ArrayList();
		Activator.context = context;
		log = LogServiceUtil.getLogService(context);

		loadExistingServices();
		TestRunner runner = new TestRunner(this);

		context.addServiceListener(this, "(" + Constants.OBJECTCLASS + "=" + TestCase.class.getName() + ")");
		runner.execute();
	}

	/**
	 * Removes the service listener.
	 * @param context 
	 * 		The relevant BundleContext.
	 * @throws Exception
	 */
	public void stop(BundleContext context) throws Exception {
		context.removeServiceListener(this);
	}
	
	/**
	 * Loads existing registered TestCase services through the use of the serviceChanged method.
	 * @throws InvalidSyntaxException
	 */
	private void loadExistingServices() throws InvalidSyntaxException {
		ServiceReference[] srs = context.getServiceReferences(TestCase.class.getName(), "(" + Constants.OBJECTCLASS + "=" + TestCase.class.getName() + ")");

		if (srs != null) {
			for (int i = 0; i < srs.length; ++i) {
				this.serviceChanged(new ServiceEvent(ServiceEvent.REGISTERED, srs[i]));
			}
		}
	}

	/**
	 * Loads newly registered TestCase services.
	 * @param reference 
	 * 		The the relevant ServiceReference.
	 */
	public Object addingService(ServiceReference reference) {
		Object o = context.getService(reference);
		return o;
	}

	/**
	 * Loads recently modified TestCase services. //TODO Write this method.
	 * @param reference
	 * @param service
	 */
	public void modifiedService(ServiceReference reference, Object service) {

	}

	/**
	 * Recognized recently removed TestCase services. //TODO Write this method.
	 * @param reference
	 * @param service
	 */
	public void removedService(ServiceReference reference, Object service) {

	}

	/**
	 * Adds changed services to the list of testSuites to be run using the TestRunner.
	 * @param event
	 * 		The relevant ServiceEvent.
	 */
	public void serviceChanged(ServiceEvent event) {
		Object o = context.getService(event.getServiceReference());
		System.out.println("\n");
		System.out.println("Recognized New Service Event: " + o.getClass().getName());
		System.out.println("");
		if (o instanceof TestCase) {
			switch (event.getType()) {
			case ServiceEvent.REGISTERED:
				if (!testSuites.contains(o)) {
					testSuites.add(o);
				}
				break;
			case ServiceEvent.UNREGISTERING:
				testSuites.remove(o);
				break;
			}
		}
	}

	/**
	 * Returns the relevant context.
	 * @return context
	 * 		The relevant context.
	 */
	public static BundleContext getBundleContext() {
		return context;
	}
}