package com.buglabs.bug.dragonfly.uitests;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;

/**
 * Class used to manage the 'BUG Simulator'.
 * @author barberd
 *
 */
public class BugSimulator {
	
	
	/**
	 * Starts the simulator. 
	 */
	public void start() {
		TestMain.bot.toolbarButtonWithTooltip("Launch BUG Simulator").click();
		TestMain.bot.sleep(2000);
	}
	
	
	/**
	 * Stops the simulator.
	 */
	public void stop() {
		SWTBotView console = TestMain.vg.getView("Console");
		console.setFocus();
		TestMain.bot.toolbarButtonWithTooltip("Terminate").click();
		TestMain.bot.sleep(2000);
	}
	
}
