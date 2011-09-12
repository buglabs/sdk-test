package com.buglabs.bug.dragonfly.uitests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;

import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;


/**
 * Class used to check certain condition values.
 * @author barberd
 *
 */
public class ConditionChecker {

	
	/**
	 * Checks to see if a given BUG exists in the 'My BUGs' view.
	 * @param bugName The name of the BUG to search for.
	 * @return true if the BUG exists, false if not.
	 */
	public boolean bugIsInView(String bugName) {
		try {
			SWTBotView myBugs = TestMain.vg.getView("My BUGs");
			myBugs.setFocus();
			SWTBotTree tree = myBugs.bot().tree();
			tree.getTreeItem(bugName);
			return true;
		} catch (WidgetNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * Checks for successful project creation.
	 * @param name The project's name.
	 * @return true if project exists, false if not.
	 */
	public boolean projectIsCreated(String projName) {
        try {
            SWTBotView projectExplorer = TestMain.vg.getView("Project Explorer");
            SWTBotTree tree = projectExplorer.bot().tree();
            tree.getTreeItem(projName);
            return true;
        } catch (WidgetNotFoundException e) {
        	System.out.println(e.getMessage());
            return false;
        }
    }
	
	
	/**
	 * Checks to see if a given message has been printed to the console.
	 * @param message The message to search for.
	 * @return true if the message has been printed, false if not.
	 */
	public boolean messageIsPrinted(String message) {
		try {
			SWTBotView console = TestMain.vg.getView("Console");
			console.show();
			String consoleOut = console.bot().styledText().getText();
			Pattern pattern = Pattern.compile(message);
			Matcher matcher = pattern.matcher(consoleOut);
			if (matcher.find()) {
				return true;
			} else {
				return false;
			}
		} catch (WidgetNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * Checks to see if a given BUG has a given application.
	 * @param bugName The name of the BUG to check.
	 * @param appName The name of the application to check for.
	 * @return True if the application exists, false if not.
	 */
	public boolean bugHasApp(String bugName, String appName) {
		try {
			SWTBotView myBugs = TestMain.vg.getView("My BUGs");
			myBugs.setFocus();
			SWTBotTree tree = myBugs.bot().tree();
			tree.expandNode(bugName);
			TestMain.bot.sleep(2000);
			tree.expandNode(bugName).expandNode("Applications").select(appName);
			return true;
		} catch (WidgetNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * Checks to see if a given BUG is active or not.
	 * @param bugName The name of the BUG to check.
	 * @return True if the BUG is active, false if not.
	 */
	public boolean bugIsActive(String bugName) {
		try {
			SWTBotView myBugs = TestMain.vg.getView("My BUGs");
			myBugs.setFocus();
			SWTBotTree tree = myBugs.bot().tree();
			TestMain.bot.sleep(2000);
			tree.expandNode(bugName).expandNode("Applications");
			return true;
		} catch (WidgetNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * Checks to see if a button exists and is active.
	 * @param buttonLabel The label of the button to check for.
	 * @return True if the button exists and is active, false if not.
	 */
	public boolean buttonExists(String buttonLabel) {
		try {
			TestMain.bot.buttonWithLabel(buttonLabel);
			return true;
		} catch (WidgetNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * Checks to see if an added dependency from an imported
	 * package exists.
	 * @param projName The name of the project to check.
	 * @param importName The name of the imported package to look for.
	 * @return
	 */
	public boolean dependenciesUpdated(String projName, String importName) {
		try {
			SWTBotView projectExplorer = TestMain.vg.getView("Project Explorer");
			projectExplorer.setFocus();
			SWTBotTree tree = projectExplorer.bot().tree();
			tree.expandNode(projName).expandNode("Plug-in Dependencies").getNode(importName);
			return true;
		} catch (WidgetNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * Checks to see if a given BUG connection is collapsed within the 'My BUGs' view.
	 * @param bugName The name of the BUG connection to check.
	 * @return True if the BUG is collapsed, false if not.
	 */
	public boolean bugIsCollapsed(String bugName) {
		SWTBotView myBugs = TestMain.vg.getView("My BUGs");
		SWTBotTree tree = myBugs.bot().tree();
		SWTBotTreeItem bugConnection = tree.getTreeItem(bugName);
		try {
			bugConnection.getNode("Applications");
			return false;
		} catch (WidgetNotFoundException e) {
			System.out.println(e.getMessage());
			return true;
		}
	}
	
	
	/**
	 * Checks to see if BUGnet has been successfully disabled.
	 * @return True if it has been disabled, false if not.
	 */
	public boolean bugNetIsDisabled() {
		SWTBotView bugNet = TestMain.vg.getView("BUGnet");
		bugNet.setFocus();
		if (!bugNet.bot().label().getText().equals("BUGnet is disabled. You can enable BUGnet in your preferences")) {
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * Checks to see if the BUGnet preferences are default.
	 * @return True if they are default, false if not.
	 */
	public boolean bugNetIsDefault() {
		TestMain.prefm.openPreferences();
		if (!TestMain.bot.checkBox("Enable BUGnet").isChecked() || 
				!TestMain.bot.textWithLabel("Server Name:").getText().equals("https://api.buglabs.net") || 
				!TestMain.bot.textWithLabel("Number of Applications to display:").getText().equals("15")) {
			TestMain.prefm.closePreferences();
			return false; 
		} else {
			TestMain.prefm.closePreferences();
			return true;
		}
	}
	
	
	/**
	 * Checks to see if the proper error for too few 'Number of Applications to display' in the BUGnet preferences
	 * window is displayed.
	 * @return True if it is displayed, false if not.
	 */
	public boolean numAppsMinErrorDisplayed() {
		try {
			TestMain.bot.textWithTooltip("Minimum number of applications is 1");
			return true;
		} catch (WidgetNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	/**
	 * Checks to see if the proper error for too many 'Number of Applications to display' in the BUGnet preferences
	 * window is displayed.
	 * @return True if it is displayed, false if not.
	 */
	public boolean numAppsMaxErrorDisplayed() {
		try {
			TestMain.bot.textWithTooltip("Maximum number of applications is 100");
			return true;
		} catch (WidgetNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
}
