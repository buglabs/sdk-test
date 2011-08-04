package com.buglabs.bug.dragonfly.uitests;


/**
 * Class used to for managing eclipse perspectives and their properties.
 * @author barberd
 *
 */
public class PerspectiveManager {

	
	/**
	 * Sets the perspective to the given perspective.
	 * @param perspectiveName the name of the perspective to switch to.
	 */
	public void setPerspective(String perspectiveName) {
		TestMain.bot.menu("Window").menu("Open Perspective").menu("Other...").click(); 
		TestMain.bot.table().select(perspectiveName);
		TestMain.bot.button("OK").click();
		TestMain.bot.sleep(5000);
	}
	
}
