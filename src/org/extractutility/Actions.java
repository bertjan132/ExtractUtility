package org.extractutility;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author bertjan132
 */
public class Actions {

	/**
	 * This stores the chooser and lets other classes use it.
	 */
	public static JFileChooser chooser;
	
	/**
	 * This stores the return value.
	 */
	public static int returnVal;
	
	/**
	 * This opens the file chooser.
	 */
	public static void openChooser() {
		chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.ZIP Archive", "zip");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(filter);
		returnVal = chooser.showOpenDialog(Main.frame);
		System.out.println(returnVal);
	}
	
}
