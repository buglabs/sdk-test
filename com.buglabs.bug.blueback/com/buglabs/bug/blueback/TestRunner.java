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
		System.out.println("*** BEGINNING BLUEBACK AUTOMATED TEST SUITE ***");
		System.out.println("-----------------------------------------------");
		System.out.println("");
		System.out.println("");
		Thread tt = new TestThread();
		tt.start();
	}
	
	/**
	 * Thread responsible for running the tests and printing the results to the felix.log file.
	 */
	private class TestThread extends Thread {
		
		public void run() {
			if (activator.testSuites.size() == 0) {
				System.out.println("No tests to run!");
				return;
			}
			
			activator.testComplete = 0;
			System.out.println("Executing " + activator.testSuites.size() + " Test(s):");
			System.out.println("");
			System.out.println("");
			for (Iterator<TestCase> i = activator.testSuites.iterator(); i.hasNext();) {
				TestSuite tsu = new TestSuite();
				TestCase ts = i.next();
				activator.testComplete++;
				System.out.println("|EXECUTING TEST CASE: " + ts.getClass().getName() + "|");
				System.out.println("");

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
						System.out.println("Running Test: " + test.toString());
						System.out.println("----------------------------------------------------------------------------------------------------");
					}										    
					
				});
				tsu.run(tr);

				if (tr.wasSuccessful()) {
					System.out.println("TEST RESULTS: Test passed!");
					System.out.println("");
					System.out.println("");
					activator.testComplete = 0;
					
				} else {
					activator.testComplete = 0;
					System.out.println("TEST RESULTS: Test did not pass :(  Failures: " + tr.failureCount() + "; Errors: " + tr.errorCount());

					System.out.println("Errors: ");
					for (Enumeration e = tr.errors(); e.hasMoreElements();) {
						Object o = e.nextElement();
						System.out.println("" + o.toString());
					}
					
					System.out.println("");
					
					System.out.println("Failures: ");
					for (Enumeration e = tr.failures(); e.hasMoreElements();) {
						TestFailure t = (TestFailure) e.nextElement();
						System.out.println("" + t.exceptionMessage());
					}
					System.out.println("");
					System.out.println("");
				}
			}
		}
	}
}