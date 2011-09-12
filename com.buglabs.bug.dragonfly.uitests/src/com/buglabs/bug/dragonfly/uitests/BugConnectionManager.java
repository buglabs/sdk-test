package com.buglabs.bug.dragonfly.uitests;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;


/**
 * Class used to create new BUG connections in the 'My BUGs' veiw.
 * @author barberd
 *
 */
public class BugConnectionManager {

	
	/**
	 * Creates a new BUG connection with the given title and hostname.
	 * @param bugTitle
	 * @param bugHostname
	 */
	public void newBugConnectionFromIcon(String bugTitle, String bugHostname) {
		SWTBotView myBugs = TestMain.vg.getView("My BUGs");
		myBugs.setFocus();
		TestMain.bot.toolbarButtonWithTooltip("New BUG Connection").click();
		TestMain.bot.textWithLabel("Name:").setText(bugTitle);
		TestMain.bot.textWithTooltip("Enter the URL of the BUG.").setText("http://" + bugHostname);
		TestMain.bot.button("Finish").click();
	}
	
	
	public void newBugConnectionFromContextMenu(String bugTitle, String bugHostname) {
		SWTBotView myBugs = TestMain.vg.getView("My BUGs");
		myBugs.setFocus();
		SWTBotTree tree = myBugs.bot().tree();
		tree.contextMenu("New BUG Connection").click();
		TestMain.bot.textWithLabel("Name:").setText(bugTitle);
		TestMain.bot.textWithTooltip("Enter the URL of the BUG.").setText("http://" + bugHostname);
		TestMain.bot.button("Finish").click();
	}
	
	
	/**
	 * Deletes the specified BUG connection from the 'My BUGs' view.
	 * @param connectionTitle The title of the BUG connection to delete.
	 */
	public void deleteBugConnection(String bugName) {
		SWTBotView myBugs = TestMain.vg.getView("My BUGs");
		myBugs.setFocus();
		SWTBotTree tree = myBugs.bot().tree();
		tree.getTreeItem(bugName).contextMenu("Delete Connection").click();
		TestMain.bot.button("Yes").click();
	}
	
	
	/**
	 * Updates the URL variable of a given BUG connection and refreshes the connection.
	 * @param bugName The name of the BUG to update.
	 * @param newURL The new URL to update the BUG with.
	 */
	public void updateBugURL(String bugName, String newURL) {
		SWTBotView myBugs = TestMain.vg.getView("My BUGs");
		myBugs.setFocus();
		SWTBotTree tree = myBugs.bot().tree();
		tree.getTreeItem(bugName).contextMenu("Properties").click();
		TestMain.bot.textWithTooltip("Enter the URL of the BUG.").setText(newURL);
		TestMain.bot.button("OK").click();
		tree.getTreeItem(bugName).contextMenu("Refresh Connection").click();
		TestMain.bot.sleep(5000);
	}
	
}
