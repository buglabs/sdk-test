package com.buglabs.bug.dragonfly.uitests;


import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Test suite for automated testing of the Dragonfly SDK.  See 'SDK Test Suite'
 * spreadsheet for documentation.
 * @author barberd
 *
 */
public class TestMain {

	//INIT
	
	//test driver
	public static SWTWorkbenchBot bot = new SWTWorkbenchBot();
	
	//support classes
	public static ProjectManager projm = new ProjectManager();
	public static ViewGetter vg = new ViewGetter();
	public static BugConnectionManager bcm = new BugConnectionManager();
	public static BugSimulator bs = new BugSimulator();
	public static PerspectiveManager perm = new PerspectiveManager();
	public static ConditionChecker cc = new ConditionChecker();
	public static Verifier v = new Verifier();
	public static InputManager im = new InputManager();
	public static PreferencesManager prefm = new PreferencesManager();
	
	
	//before and after cases
	/**
	 * Executed once in beginning.
	 * @throws Exception
	 */
	@BeforeClass
	public static void beforeAll() throws Exception {
		
		bot.viewByTitle("Welcome").close();
		perm.setPerspective("Dragonfly");
		bs.start();
		
		bcm.newBugConnectionFromIcon(Strings.SDKTESTBUG, Strings.SDKTESTBUG_HOSTNAME);
		
		bot.sleep(5000);
	}
	
	
	/**
	 * Executed after each test case has run to completion.
	 * @throws Exception
	 */
	@After
	public void afterEach() throws Exception {
		bot.sleep(3000);
	}
	
	
	//TESTS
	
	//console command tests
	/**
	 * Summary: The 'printenv' command displays the system information.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective. The BUG Simulator has been launched.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC1() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("printenv");
		assertTrue(cc.messageIsPrinted("os.name"));
	}
	
	
	/**
	 * Summary: The printenv [filter] command displays the system information matching the given filter.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC2() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("printenv bug");
		assertTrue(cc.messageIsPrinted("user.dir"));
	}
	
	
	/**
	 * Summary: The 'printlog' command displays the OSGi log.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC3() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("printlog");
		assertTrue(cc.messageIsPrinted("Registered servlet /package"));
	}
	
	
	/**
	 * Summary: The 'printlog [filter]' command displays the OSGi log matching the given filter.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC4() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("printlog bundle");
		assertTrue(cc.messageIsPrinted("Tracking Service"));
	}
	
	
	/**
	 * Summary: The 'producers' command returns a list of services that each bundle is currently offering.          
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC5() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("producers");
		assertTrue(cc.messageIsPrinted("com.buglabs.bug.service"));
	}
	
	
	/**
	 * Summary: The 'producers [bundle ID]' command returns a list of services that the bundle matching the bundle ID is currently offering.          
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC6() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("producers 1");
		assertTrue(cc.messageIsPrinted("com.buglabs.bug.event"));
	}
	
	
	/**
	 * Summary: The 'producers [bundleID]' command returns a list of services that the bundle matching bundle ID is currently offering.          
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched. There is no bundle whose ID is or contains the string "100" in the bundle name.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC7() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("producers 100");
		assertTrue(cc.messageIsPrinted("Id__Bundle__Produced Services__")); //This message only works as long as the bundle name is longer than the word 'Bundle', which appears to always be the case as far as I can tell
	}
	
	
	/**
	 * Summary: The 'consumers' command returns list of services that each bundles is currently consuming.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC8() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("consumers");
		assertTrue(cc.messageIsPrinted("Id__Bundle________________________Consumed Services___"));
	}
	
	
	/**
	 * Summary: The 'consumers [bundle ID]' command returns a list of services that the bundle matching the bundle ID is currently consuming.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC9() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("consumers \"Http Service\"");
		assertTrue(cc.messageIsPrinted("org.osgi.service.log.LogService"));
	}
	
	
	/**
	 * Summary: The 'services' returns a list of services present in the runtime. 
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched.
	 * Depends On: None 
	 * @throws Exception
	 */
	@Test
	public void TCC10() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("services");
		assertTrue(cc.messageIsPrinted("org.osgi.service.startlevel.StartLevel"));
	}
	
	
	/**
	 * Summary: The 'services [bundle ID]' command returns a list of services present in the runtime matching the bundle ID.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched. There is no bundle whose ID is or contains the string "100" in the bundle name.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC11() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("services 100");
		assertTrue(cc.messageIsPrinted("Svc Id__Object Class__Other"));
	}
	
	
	/**
	 * Summary: The 'headers [bundle ID]' command returns the headers for the matching bundle.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC12() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("headers 1");
		assertTrue(cc.messageIsPrinted("Bundle-Vendor: Bug Labs, Inc."));
	}
	
	
	/**
	 * Summary: The 'headers [bundle ID]' command returns the headers for the matching bundle. Tests for invalid bundle ID.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC13() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("headers garbage");
		assertTrue(cc.messageIsPrinted("Name__Value__"));
	}
	
	
	/**
	 * Summary: The 'gc' command collects the JVM garbage (unused objects in memory).  In theory, this is supposed to help the JVM use less memory.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC14() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("gc");
		assertTrue(cc.messageIsPrinted("Garbage collected."));
	}
	
	
	/**
	 * Summary: The 'update [bundle ID]' command stops and starts the bundle matching the bundle ID. 
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched. Http Service has bundle ID 8.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC15() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("update 8");
		assertTrue(cc.messageIsPrinted("com.buglabs.osgi.http: updated"));
	}
	
	
	/**
	 * Summary: The 'update [bundle ID]' command stops and starts the bundle matching the bundle ID. 
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched. No bundle has ID 100.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC16() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("update 100");
		assertTrue(cc.messageIsPrinted("Unable to find bundle 100."));
	}
	
	
	/**
	 * Summary: The 'start/stop [bundle ID]' command starts/stops the specified bundle (flips from active to resolved or vise versa).  If the bundle name string is given and the bundle name is a string containing two or more words, it must be enclosed in quotations.  It only makes sense to start a previously stopped bundle, or a newly installed bundle that is in the resolved state.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched. Http Service has service ID 8. 
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC17() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("stop 8");
		assertTrue(cc.messageIsPrinted("com.buglabs.osgi.http: active -> resolved"));
		
		im.consoleCommand("start 8");
		assertTrue(cc.messageIsPrinted("com.buglabs.osgi.http: resolved -> active"));
	}
	
	
	/**
	 * Summary: The 'stop [bundle ID]' command stops the specified bundle (flips from active to resolved). Tests when invalid bundle ID is given.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched. There is no service with ID 82.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC18() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("stop 82");
		assertTrue(cc.messageIsPrinted("Invalid bundle id or name: 82"));
	}
	
	
	/**
	 * Summary: The 'bundles [bundle ID]' command returns a list of bundles present in current OSGi runtime environment, displaying only the results matching the filter. Tests for the 'osgi' bundle ID.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC19() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("bundles osgi");
		assertTrue(cc.messageIsPrinted("active BUG OSGi Console"));
	}
	
	
	/**
	 * Summary: The 'install bundle URL' command installs a bundle from the given URL to the current BUG.  
	 * The bundle URL is a required parameter for headers.  This tests the exception handling for an 
	 * invalid bundle URL.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective. The BUG Simulator has been launched.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC20() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("install INVALID");
		assertTrue(cc.messageIsPrinted("The file URL does not provide a valid bundle."));
	}
	

	/**
	 * Summary: The 'install file:[file path]' command installs a bundle from the given file path.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective. The 'OSGiLogWS.1.jar' file has been downloaded from "http://www.buglabs.net/application/download/72" and placed on your desktop. The correct file path (specific to your machine's user) has been substituted in the test case within Eclipse.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC21() throws Exception {
		v.verifyBugSim();
		im.consoleCommand("install file:///home/barberd/Desktop/OSGiLogWS.1.jar");
		im.consoleCommand("bundles");
		assertTrue(cc.messageIsPrinted("installed OSGiLogWS"));
	}

	
	/**
	 * Summary: The 'uninstall [bundle ID]' command stops the bundle matching the bundle ID and removes it from the runtime environment.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched. Http Service has service ID 8. 
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC22() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("uninstall 8");
		assertTrue(cc.messageIsPrinted("com.buglabs.osgi.http: active -> uninstalled"));
	}
	
	
	/**
	 * Summary: The 'uninstall [bundle ID]' command stops the bundle matching the bundle ID and removes it from the runtime environment. Tests invalid bundle ID input.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.  The BUG Simulator has been launched. There is no bundle with ID 100.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCC23() throws Exception {
		v.verifyBugSim();
		
		im.consoleCommand("uninstall 100");
		assertTrue(cc.messageIsPrinted("Invalid bundle id or name: 100"));
	}
	
	
	//project creation tests
	/**
	 * Summary: User is unable to create a project with a name longer than 150 characters.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCP1() throws Exception {
		bot.toolbarButtonWithTooltip("New BUG Project").click();
		
		bot.textWithLabel("Name:").setText("aaaaaaaaaabbbbbbbbbbccccccccccddddddddddeeeeeeeeeeffffffffffgggggggggghhhhhhhhhh" +
				"iiiiiiiiiijjjjjjjjjjkkkkkkkkkklllllllllmmmmmmmmmmnnnnnnnnnnoooooooooopp");
		assertEquals(bot.textWithLabel("New BUG Project").getText(), " Project name limit reached!");
		assertFalse(cc.buttonExists("Finish"));
		
		bot.button("Cancel").click();
	}
	
	
	/**
	 * Summary: User is unable to create a project using a subset of java keywords and special characters in the project name.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCP2() throws Exception {
		bot.toolbarButtonWithTooltip("New BUG Project").click();
		
		bot.textWithLabel("Name:").setText("Interface");
		assertEquals(bot.textWithLabel("New BUG Project").getText(), " Invalid project name.");
		assertFalse(cc.buttonExists("Finish"));
		
		bot.textWithLabel("Name:").setText("Implements");
		assertEquals(bot.textWithLabel("New BUG Project").getText(), " Invalid project name.");
		assertFalse(cc.buttonExists("Finish"));
		
		bot.textWithLabel("Name:").setText("Extends");
		assertEquals(bot.textWithLabel("New BUG Project").getText(), " Invalid project name.");
		assertFalse(cc.buttonExists("Finish"));
		
		bot.textWithLabel("Name:").setText("A!A");
		assertEquals(bot.textWithLabel("New BUG Project").getText(), " Invalid project name.");
		assertFalse(cc.buttonExists("Finish"));
		
		bot.button("Cancel").click();
	}
	
	
	/**
	 * Summary: Tests validity of the Import and Export functionality of the manifest.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective.
	 * Depends On: None
	 */
	@Test
	public void TCP3() throws Exception {
		projm.createBasicProjectFromIcon(Strings.PROJNAME_3, Strings.PROJAUTHOR_1, Strings.PROJDESCRIPTION_1);
		projm.createBasicProjectFromIcon(Strings.PROJNAME_4, Strings.PROJAUTHOR_1, Strings.PROJDESCRIPTION_1);
		
		projm.openManifest(Strings.PROJNAME_3);
		SWTBotEditor manifest = bot.activeEditor();
		manifest.toTextEditor().insertText(11, 1, "Export-Package: " + Strings.PROJNAME_3 + "\n");
		projm.saveFile();
		
		projm.openManifest(Strings.PROJNAME_4);
		manifest = bot.activeEditor();
		manifest.toTextEditor().insertText(10, 34, ", " + Strings.PROJNAME_3);
		projm.saveFile();

		assertTrue(cc.dependenciesUpdated(Strings.PROJNAME_4, Strings.PROJNAME_3));
	}
	
	
	/**
	 * Summary: Send an application created in SDK to a connected BUG.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective. A BUG is connected with the title 'SDKTestBug'.  
	 * Depends On: None	
	 * @throws Exception
	 */
	@Test
	public void TCP4() throws Exception {
		v.verifyBugConnection(Strings.SDKTESTBUG_LABEL, Strings.SDKTESTBUG_HOSTNAME);
		
		projm.createBasicProjectFromIcon(Strings.PROJNAME_2, Strings.PROJAUTHOR_1, Strings.PROJDESCRIPTION_1);

//		projm.runProject(Strings.PROJNAME_2, Strings.SDKTESTBUG_LABEL);
//		assertTrue(cc.bugHasApp(Strings.SDKTESTBUG_LABEL, Strings.PROJNAME_2));
	}
	
	
	/**
	 * Summary: Create a new BUG application that prints a message to the console.
	 * Assumptions: None
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCP5() throws Exception {
		v.verifyBugSim();
		
		projm.createBasicProjectFromIcon(Strings.PROJNAME_1, Strings.PROJAUTHOR_1, Strings.PROJDESCRIPTION_1);
		assertTrue(cc.projectIsCreated(Strings.PROJNAME_1));
		
		projm.openClass(Strings.PROJNAME_1, "Activator.java");
		bot.editorByTitle("Activator.java").toTextEditor().insertText(7, 2, "System.out.println(\"Hello World :)\");");
		projm.saveFile();
	
//		projm.runProject(Strings.PROJNAME_1, Strings.BUG_SIM);
//		assertTrue(cc.messageIsPrinted("Hello World :\\)")); //Double escape for ')' since used as regex
//		assertTrue(cc.bugIsInView(Strings.BUG_SIM));
	}
	
	
	/**
	 * Summary: Run a BUG application and confirm that the 'bundles' console command executes correctly.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective. The BUG Simulator has been launched. 
	 * PrintMessageTest app has been created.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCP6() throws Exception {
		v.verifyBugSim();
		
		projm.createBasicProjectFromIcon(Strings.PROJNAME_5, Strings.PROJAUTHOR_1, Strings.PROJDESCRIPTION_1);
		
//		projm.runProject(Strings.PROJNAME_5, Strings.BUG_SIM);
//		
//		im.consoleCommand("bundles");
//		assertTrue(cc.bugHasApp(Strings.BUG_SIM, Strings.PROJNAME_5));
//		assertTrue(cc.messageIsPrinted("System Bundle"));
//		assertTrue(cc.messageIsPrinted("PrintMessageTest"));
	}
	
	
	//bugnet tests
	/**
	 * Summary: Confirm that the 'New BUG Connection' wizard can be accessed from the 'My BUGs' view and that the connection can be created and tested.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective. The 'BUG Simulator' is not running.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCBn1() throws Exception {
		v.verifyNoBugSim();
		
		bcm.newBugConnectionFromContextMenu(Strings.SDKTESTBUG_2, Strings.LOCAL_HOSTNAME);
		bs.start();
		assertTrue(cc.bugIsInView(Strings.SDKTESTBUG_2_LABEL));
		
		bot.toolbarButtonWithTooltip("Refresh Discovered BUGs").click();
		bot.sleep(5000);    
		assertTrue(cc.bugIsInView(Strings.SDKTESTBUG_2_LABEL));
		assertTrue(cc.bugIsActive(Strings.SDKTESTBUG_2_LABEL));
		
		bcm.deleteBugConnection(Strings.SDKTESTBUG_2_LABEL);
		assertFalse(cc.bugIsInView(Strings.SDKTESTBUG_2_LABEL));
	}
	
	
	/**
	 * Summary: A created BUG connection with an inactive address can be updated via the right click properties in the 'My BUGs' view to an active address.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective. The 'BUG Simulator' is running.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCBn2() throws Exception {
		bcm.newBugConnectionFromIcon(Strings.SDKTESTBUG_3, Strings.INVALID_HOSTNAME);
		
		bot.toolbarButtonWithTooltip("Refresh Discovered BUGs").click();
		bot.sleep(5000);
		assertTrue(cc.bugIsInView(Strings.SDKTESTBUG_3_LABEL));
		assertFalse(cc.bugIsActive(Strings.SDKTESTBUG_3_LABEL));
		
		bcm.updateBugURL(Strings.SDKTESTBUG_3_LABEL, "http://localhost:8082");
		assertTrue(cc.bugIsActive(Strings.SDKTESTBUG_3_LABEL));
	
		bot.toolbarButtonWithTooltip("Collapse All").click();
		bot.sleep(2000);
		assertTrue(cc.bugIsCollapsed(Strings.SDKTESTBUG_3_LABEL));
	}
	
	
// NOT COMPLETED YET 
//	/**
//	 * Summary: Tests the ability to import an application from the 'BUGnet' view to the 'Project Explorer' view using right click functionality.
//	 * Assumptions: Eclipse is running and is in Dragonfly perspective.
//	 * Depends On: None
//	 * @throws Exception
//	 */
//	@Test
//	public void TCBn3() throws Exception {
//		SWTBotView bugNet = vg.getView("BUGnet");
//		bugNet.setFocus();
//		bot.sleep(2000);
//		SWTBotImageHyperlink 
//		
//	}
// NOT COMPLETED YET ^^
	
	
	//bugnet preferences tests	
	/**
	 * Summary: User is able to deselect the 'Enable BUGnet' preference.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective. BUGnet settings are default.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCBnP1() throws Exception {
		projm.createBasicProjectFromIcon(Strings.PROJNAME_6, Strings.PROJAUTHOR_1, Strings.PROJDESCRIPTION_1);
		v.verifyDefaultBugNet();
		prefm.toggleBugNet();
		assertTrue(cc.bugNetIsDisabled());
	}
	
	
	/**
	 * Summary: Default BUGnet preferences are successfully restored.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective. BUGnet settings are default.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCBnP2() throws Exception {
		v.verifyDefaultBugNet();
		prefm.setPreferences("Not Default Server", 100);
		prefm.toggleBugNet();
		prefm.restoreDefaults();
		assertTrue(cc.bugNetIsDefault());
	}
	
	
	/**
	 * Summary: User cannot set 'Number of Applications to display:' in BUGnet preferences window to 0 or 100.
	 * Assumptions: Eclipse is running and is in Dragonfly perspective. BUGnet settings are default.
	 * Depends On: None
	 * @throws Exception
	 */
	@Test
	public void TCBnP3() {
		v.verifyDefaultBugNet();
		prefm.openPreferences();
		
		bot.textWithLabel("Number of Applications to display:").setText("0");
		assertTrue(!cc.buttonExists("Apply"));
		assertTrue(!cc.buttonExists("OK"));
		assertTrue(cc.numAppsMinErrorDisplayed());
	
		bot.textWithLabel("Number of Applications to display:").setText("101");
		assertTrue(!cc.buttonExists("Apply"));
		assertTrue(!cc.buttonExists("OK"));
		assertTrue(cc.numAppsMaxErrorDisplayed());
		
		bot.button("Cancel").click();
	}
}
