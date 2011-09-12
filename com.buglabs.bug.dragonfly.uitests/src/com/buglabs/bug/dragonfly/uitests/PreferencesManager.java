package com.buglabs.bug.dragonfly.uitests;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;


/**
 * Class used to manage the BUGnet preferences dialog.
 * @author barberd
 *
 */
public class PreferencesManager {

	
	/**
	 * Disables BUGnet.
	 */
	public void toggleBugNet() {
		openPreferences();
		TestMain.bot.checkBox("Enable BUGnet").click();
		closePreferences();
	}
	
	
	/**
	 * Sets the BUGnet preferences to the desired settings.
	 * @param serverName The name of the server.
	 * @param numApps The number of applications to display.
	 */
	public void setPreferences(String serverName, int numApps) {
		openPreferences();
		TestMain.bot.textWithLabel("Server Name:").setText(serverName);
		TestMain.bot.textWithLabel("Number of Applications to display:").setText(new Integer(numApps).toString());
		closePreferences();
	}
	
	
	/**
	 * Restores the default BUGnet settings.
	 */
	public void restoreDefaults() {
		openPreferences();
		TestMain.bot.button("Restore Defaults").click();
		closePreferences();
	}
	
	
	/**
	 * Opens the BUGnet preferences window.
	 */
	public void openPreferences() {
		TestMain.bot.menu("Window").menu("Preferences").click();
		SWTBotShell prefShell = TestMain.bot.shell("Preferences");
		prefShell.activate();
		TestMain.bot.sleep(2000);
		TestMain.bot.tree().select("BUGnet");
		TestMain.bot.sleep(2000);
	}
	
	
	/**
	 * Closes the BUGnet preferences window.
	 */
	public void closePreferences() {
		TestMain.bot.button("Apply").click();
		TestMain.bot.button("OK").click();
		TestMain.bot.sleep(2000);
	}
	
}
