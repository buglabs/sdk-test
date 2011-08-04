package com.buglabs.bug.dragonfly.uitests;


/**
 * Class used to verify specific states of the test suite before continuing.
 * @author barberd
 *
 */
public class Verifier {
	
	
	/**
	 * Checks to see if 'BUG Simulator' is running. If not,
	 * starts the simulator.
	 */
	public void verifyBugSim() {
		if (TestMain.cc.bugIsInView(Strings.BUG_SIM)) {
			return;
		} else {
			TestMain.bs.start();
		}
	}
	
	
	/**
	 * Checks to see if the 'BUG Simulator' is running. If yes,
	 * stops the simulator.
	 */
	public void verifyNoBugSim() {
		if (!TestMain.cc.bugIsInView(Strings.BUG_SIM)) {
			return;
		} else {
			TestMain.bs.stop();
		}
	}
	
	
	/**
	 * Verifies that the BUGnet preferences are at their default settings.
	 */
	public void verifyDefaultBugNet() {
		TestMain.prefm.openPreferences();
		if (!TestMain.bot.checkBox("Enable BUGnet").isChecked()) {
			TestMain.bot.checkBox("Enable BUGnet").click();
		} else if (!TestMain.bot.textWithLabel("Server Name:").getText().equals("https://api.buglabs.net")) {
			TestMain.bot.textWithLabel("Server Name:").setText("https://api.buglabs.net");
		} else if (!TestMain.bot.textWithLabel("Number of Applications to display:").getText().equals("15")) {
			TestMain.bot.textWithLabel("Number of Applications to display:").setText("15");
		} 
		TestMain.prefm.closePreferences();
	}
	
	
	/**
	 * Checks to see if a given BUG is connected. If not, creates a new connection for
	 * that BUG.
	 * @param bugTitle
	 * @param bugHostname
	 */
	public void verifyBugConnection(String bugTitle, String bugHostname) {
		if (TestMain.cc.bugIsInView(bugTitle)) {
			return;
		} else {
			TestMain.bcm.newBugConnectionFromIcon(bugTitle, bugHostname);
		}
	}
	
}
