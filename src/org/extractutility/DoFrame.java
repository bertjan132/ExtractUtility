package org.extractutility;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * @author bertjan132
 */
public class DoFrame extends JFrame {

	/**
	 * This prevents mistakes between extending classes.
	 */
	private static final long serialVersionUID = -2954563707513467945L;

	/**
	 * This is the width of the utility.
	 */
	public static final int WIDTH = 650;
	
	/**
	 * This is the height of the utility.
	 */
	public static final int HEIGHT = 450;
	
	/**
	 * This makes the frame.
	 */
	public DoFrame() {
		setTitle("ExtractUtility - UI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
