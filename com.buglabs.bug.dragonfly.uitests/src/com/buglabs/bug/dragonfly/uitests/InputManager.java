package com.buglabs.bug.dragonfly.uitests;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.keyboard.Keyboard;
import org.eclipse.swtbot.swt.finder.keyboard.KeyboardFactory;


/**
 * Class that manages the bot's text and keyboard input methods.
 * @author barberd
 *
 */
public class InputManager {

	
	/**
	 * Hits the return key.
	 */
	public void hitReturn() {
		KeyStroke ret = KeyStroke.getInstance(SWT.CR);
		TestMain.bot.sleep(2000);
		Keyboard k = KeyboardFactory.getSWTKeyboard();
		k.pressShortcut(ret);
	}
	
	
	/**
	 * Enters a given command into the console.
	 * @param command The command to enter.
	 */
	public void consoleCommand(String command) {
		SWTBotView console = TestMain.vg.getView("Console");
		console.setFocus();
		TestMain.bot.sleep(3000);
		console.bot().styledText().typeText(command);
		hitReturn();
		TestMain.bot.sleep(2000);
	}
	
}
