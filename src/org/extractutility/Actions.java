package org.extractutility;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author bertjan132
 */
public class Actions {

	public static JFileChooser chooser;
	
	/**
	 * This let you perform a certain action.
	 * @param action
	 */
	public Actions(String action) {
		if (action.equals("openChooser")) {
			openChooser();
		}
	}
	
	/**
	 * This opens the file chooser.
	 */
	private void openChooser() {
		chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.ZIP Extension", "zip");
		int returnVal = chooser.showOpenDialog(Main.frame);
	}
	
}
