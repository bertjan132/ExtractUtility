package org.extractutility;

import javax.swing.JFrame;

/**
 * @author bertjan132
 */
public class DoFrame extends JFrame {

	/**
	 * This will return the version of the applet.
	 */
	private enum version {
		Alpha(1.0);
		
		public double version;
		
		private version(double version) {
			this.version = version;
		}
	}
	
	/**
	 * This prevents mistakes between extending classes.
	 */
	private static final long serialVersionUID = -2954563707513467945L;

	/**
	 * This is the width of the utility.
	 */
	private final int WIDTH = 650;
	
	/**
	 * This is the height of the utility.
	 */
	private final int HEIGHT = 450;
	
	/**
	 * This will return the frame.
	 */
	public static JFrame getFrame;
	
	/**
	 * This makes the frame.
	 */
	public DoFrame() {
		setUndecorated(true);
		setTitle("ExtractUtility - UI | " + version.values()[0] + " " + version.values()[0].version);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new Interface(WIDTH, HEIGHT));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		getFrame = this;
	}
	
}
