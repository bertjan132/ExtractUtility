package org.extractutility;

import javax.swing.JFrame;

/**
 * @author bertjan132
 */
public class Main {
	
	public static JFrame getFrame;
	
	/**
	 * This is the first method Java reads.
	 * @param args
	 */
	public static void main(String[] args) {
		Interface.loadInterface();
		getFrame = new DoFrame();
	}
	
}
