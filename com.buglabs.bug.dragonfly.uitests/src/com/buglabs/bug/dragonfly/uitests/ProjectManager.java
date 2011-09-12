package com.buglabs.bug.dragonfly.uitests;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;




/**
 * Class used to create new BUG applications.  Can create from the menu or the 'New BUG Application' icon.
 * @author barberd
 *
 */
public class ProjectManager {

	
	/**
	 * Creates a basic project from the menu with no advanced options selected.
	 * @param projName The project's name.
	 * @param projAuthor The project's author.
	 * @param projDescription The project's description.
	 */
	public void createBasicProjectFromMenu(String projName, String projAuthor, String projDescription) {
		TestMain.bot.menu("File").menu("New").menu("Project...").click();
		
		SWTBotShell shell = TestMain.bot.shell("New Project");
		shell.activate();
		
		TestMain.bot.tree().expandNode("Dragonfly").select("BUG Application");
		TestMain.bot.button("Next >").click();
		
		TestMain.bot.textWithLabel("Name:").setText(projName);
		TestMain.bot.textWithLabel("Author:").setText(projAuthor);
		TestMain.bot.textWithLabel("Description:").setText(projDescription);
		
		TestMain.bot.button("Finish").click();
		
		TestMain.bot.sleep(2000);
	}
	
	
	/**
	 * Creates a basic project from the icon with no advanced options selected.
	 * @param projName The project's name.
	 * @param projAuthor The project's author.
	 * @param projDescription The project's description.
	 */
	public void createBasicProjectFromIcon(String projName, String projAuthor, String projDescription) {
		TestMain.bot.toolbarButtonWithTooltip("New BUG Project").click();
		
		TestMain.bot.textWithLabel("Name:").setText(projName);
		TestMain.bot.textWithLabel("Author:").setText(projAuthor);
		TestMain.bot.textWithLabel("Description:").setText(projDescription);
		
		TestMain.bot.button("Finish").click();
		
		TestMain.bot.sleep(2000);
	}
	
	
	/**
	 * Opens a given class from the 'Project Explorer' view.
	 * @param projName The name of the project containing the class.
	 * @param className The name of the class to be opened.
	 */
	public void openClass(String projName, String className) {
		String packName = projName.toLowerCase();
		SWTBotView projectExplorer = TestMain.vg.getView("Project Explorer");
		SWTBotTree tree = projectExplorer.bot().tree();
		tree.expandNode(projName).expandNode(packName).expandNode(className).select().doubleClick();
	}
	
	
	/**
	 * Opens the manifest file for a given project.
	 * @param projName The name of the project who's manifest file is to be opened.
	 */
	public void openManifest(String projName) {
		SWTBotView projectExplorer = TestMain.vg.getView("Project Explorer");
		SWTBotTree tree = projectExplorer.bot().tree();
		tree.expandNode(projName).expandNode("META-INF").getNode("MANIFEST.MF").select().doubleClick();
		TestMain.bot.sleep(5000);
		SWTBotEditor manifest = TestMain.bot.activeEditor();
		SWTBot manifestBot = manifest.bot();
		manifestBot.cTabItem("MANIFEST.MF").activate();
		TestMain.bot.sleep(5000);
	}
	
	
	/**
	 * Runs a project on a given BUG.
	 * @param projName The name of the project to run.
	 * @param BUGName The name of the BUG to run the project on.
	 */
	public void runProject(String projName, String bugName) {
		SWTBotView projExp = TestMain.vg.getView("Project Explorer");
		SWTBotTree tree = projExp.bot().tree();
		SWTBotTreeItem project = tree.expandNode(projName).select();
		SWTBotUtils.clickContextMenu(project, "Send to BUG");
		SWTBotShell bugShell = TestMain.bot.shell("BUG Connections");
		bugShell.activate();
		TestMain.bot.tree().select(bugName);
		TestMain.bot.button("OK").click();
		if (TestMain.cc.buttonExists("Yes")) {
			TestMain.bot.button("Yes").click();
		}
		
		TestMain.bot.sleep(3000);
	}
	
	
	/**
	 * Saves the current file.
	 */
	public void saveFile() {
		TestMain.bot.toolbarButtonWithTooltip("Save (Ctrl+S)").click();
		TestMain.bot.sleep(2000);
	}

}
