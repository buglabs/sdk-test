package com.buglabs.bug.dragonfly.uitests;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;


/**
 * Class used to grab and return SWTBotViews of a given name. 
 * @author barberd
 *
 */
public class ViewGetter {

	
	/**
	 * Gets the view with the given name.
	 * @param viewName The name of the view to get.
	 * @return The view.
	 */
	public SWTBotView getView(String viewName) {
		SWTBotView view = TestMain.bot.viewByTitle(viewName);
		return view;
	}
	
}
