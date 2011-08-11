package com.buglabs.bug.blueback;

import java.util.Enumeration;
import java.util.Iterator;

import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestFailure;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import org.osgi.service.log.LogService;

/**
 * The class responsible for running the JUnit tests on the registered TestCase services. 
 * 
 * @author kgilmer, jconnolly, barberdt
 *
 */
class TestRunner {

	private final Activator activator;

	/**
	 * Constructor.
	 * @param activator
	 * 		The BlueBack Activator.
	 */
	public TestRunner(Activator activator) {
		this.activator = activator;
	}

	/**
	 * Starts the TestRunner.
	 * @throws Exception
	 */
	public void execute() throws Exception {
		Thread tt = new TestThread();
		tt.start();
		
	}
	
	/**
	 * Thread responsible for running the tests and printing the results to the felix.log file.
	 * 
	 * @author kgilmer, jconnolly, barberdt 
	 *
	 */
	private class TestThread extends Thread {
		
		public void run() {
			if (activator.testSuites.size() == 0) {
				return;
			}
			
			activator.testComplete = 0;
			activator.log.log(LogService.LOG_INFO, "Executing " + activator.testSuites.size() + " Tests");
			for (Iterator<TestCase> i = activator.testSuites.iterator(); i.hasNext();) {
				TestSuite tsu = new TestSuite();
				TestCase ts = i.next();
				activator.testComplete++;
				activator.log.log(LogService.LOG_INFO, "Executing Test Case: " + ts.getClass().getName());

				tsu.addTestSuite(ts.getClass());
				TestResult tr = new TestResult();
				tr.addListener(new TestListener() {

					public void addError(Test test, Throwable t) {
						activator.hasFailed = true;
					}

					public void addFailure(Test test, AssertionFailedError t) {
						activator.hasFailed = true;
					}

					public void endTest(Test test) {
					}

					public void startTest(Test test) {
						activator.log.log(LogService.LOG_INFO, "Running test " + test.toString());
					}
					
				});
				tsu.run(tr);

				if (tr.wasSuccessful()) {
					activator.log.log(LogService.LOG_INFO, "Test Passed.");
					activator.testComplete = 0;
					
				} else {
					activator.testComplete = 0;
					activator.log.log(LogService.LOG_INFO,"Failed");
					activator.log.log(LogService.LOG_INFO, "Test Failed.  Failure count: " + tr.failureCount() + " Error count: " + tr.errorCount());

					activator.log.log(LogService.LOG_INFO, "Errors: ");
					for (Enumeration e = tr.errors(); e.hasMoreElements();) {
						Object o = e.nextElement();
						activator.log.log(LogService.LOG_INFO, o.toString());
					}

					activator.log.log(LogService.LOG_INFO, "Failures: ");
					for (Enumeration e = tr.failures(); e.hasMoreElements();) {
						TestFailure t = (TestFailure) e.nextElement();
						activator.log.log(LogService.LOG_INFO, t.exceptionMessage());
					}
				}
			}
		}
	}
}