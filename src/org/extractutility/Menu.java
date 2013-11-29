package org.extractutility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author bertjan132
 */
public class Menu extends JMenuBar implements ActionListener {

	/**
	 * This prevents Java from making extending classes mistakes.
	 */
	private static final long serialVersionUID = 1592948355822397893L;
	
	/**
	 * This is the constructor which will set-up the menu.
	 */
	public Menu() {
		JMenu file = new JMenu("File");
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(this);
		file.add(open);
		this.add(file);
	}

	/**
	 * This will perform the appropriated action.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand() == "Open") {
			Actions.openChooser();
			try {
				Actions.unZip(new File(Actions.chooser.getSelectedFile().getAbsolutePath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
