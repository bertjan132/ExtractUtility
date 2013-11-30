package org.extractutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
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
		FileNameExtensionFilter all = new FileNameExtensionFilter("Any Archive", "zip", "gz");
		FileNameExtensionFilter zip = new FileNameExtensionFilter("*.ZIP Archive", "zip");
		FileNameExtensionFilter gz = new FileNameExtensionFilter("*.GZ Archive", "gz");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(all);
		chooser.addChoosableFileFilter(zip);
		chooser.addChoosableFileFilter(gz);
		returnVal = chooser.showOpenDialog(Main.getFrame);
	}
	
	/**
	 * This will unZip the input file.
	 */
	public static void unZip(File input) throws IOException {
		byte [] buffer = new byte[1024];
		try (ZipInputStream in = new ZipInputStream(new FileInputStream(input))) {
			ZipEntry nextEntry;
			while ((nextEntry = in.getNextEntry()) != null) {
				File totalPath = new File(input.getParent() + File.separator + nextEntry.getName());
				if (nextEntry.isDirectory()) {
					totalPath.mkdirs();
				} else {
					try (FileOutputStream out = new FileOutputStream(totalPath)) {
						int length;
						while ((length = in.read(buffer)) != -1) {
							out.write(buffer, 0, length);
						}
					}
				}
			}
		}
	}
	
	/**
	 * This will unGZip the input file.
	 */
	public static void unGZIP(File input) throws IOException {
		byte [] buffer = new byte[1024];
		File fileWithoutGz = new File(input.getPath().substring(0, input.getPath().length() - 3));
		try (GZIPInputStream in = new GZIPInputStream(new FileInputStream(input));
				FileOutputStream out = new FileOutputStream(fileWithoutGz)) {
			int length;
			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
		}
	}
	
}
