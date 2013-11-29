package org.extractutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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
	}
	
	/**
	 * This will unZip the input file.
	 */
	public static void unZip(File input) throws IOException {
		byte[] buffer = new byte[1024];
			
		ZipInputStream in = null;
		FileOutputStream out = null;
			
		try {
			in = new ZipInputStream(new FileInputStream(input));
			ZipEntry entry;
			while ((entry = in.getNextEntry()) != null) {
				String fileName = entry.getName();
				File output = new File(input.getParent() + File.separator + fileName);
				new File(output.getParent()).mkdirs();
				out = new FileOutputStream(output);
					
				int length;
				while ((length = in.read(buffer)) != -1) {
					out.write(buffer, 0, length);
				}
			}
		} finally {
			if (in != null) {
				in.closeEntry();
				in.close();
			}
			if (out != null) {
				out.close();
			}
			System.out.println(in);
			System.out.println(out);
		}
	}
	
}
