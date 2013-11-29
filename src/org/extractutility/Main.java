package org.extractutility;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 * @author bertjan132
 */
public class Main {
	
	public static JFrame frame;
	public static JMenuBar menu;
	public static Actions actions;
	
	/**
	 * This is the first method Java reads.
	 * @param args
	 */
	public static void main(String[] args) {
		frame = new DoFrame();
	}
	
}
