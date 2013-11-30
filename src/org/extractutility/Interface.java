package org.extractutility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import net.iharder.dnd.FileDrop;

/**
 * @author bertjan132
 */
public class Interface extends JPanel implements MouseMotionListener, MouseInputListener  {

	/**
	 * These variables store the height and width of the applet.
	 */
	private final int WIDTH;
	private final int HEIGHT;
	
	/**
	 * The constructor of the method. This will grab the panel together.
	 * @param width
	 * @param height
	 */
	public Interface(int width, int height) {
		this.WIDTH = width;
		this.HEIGHT = height;
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		addMouseMotionListener(this);
		this.addMouseListener(this);
		new FileDrop(this, new FileDrop.Listener() {
			public void filesDropped(File[] files) {
				for (int i = 0; i < files.length; i++) {
					if (!files[i].toString().endsWith(".zip")) {
						return;
					}
					try {
						Actions.unZip(files[i]);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	/**
	 * This loads miscellaneous and images etc.
	 */
	public static void loadInterface() {
		loadBufferedImages();
	}
	
	public void whatToPaint(Graphics g) {
		g.setColor(Color.decode("#bbbbbb"));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.decode("#eeeeee"));
		g.fillRect(1, 1, WIDTH - 2, HEIGHT - 2);
		g.setColor(Color.decode("#bbbbbb"));
		g.fillRect(1, 1, WIDTH - 1, 42);
		g.setColor(Color.WHITE);
		g.drawImage(backImage, WIDTH / 2 - backImage.getWidth() / 2, 60, null);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font("Verdana", Font.PLAIN, 20));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.drawString(DoFrame.getFrame.getTitle(), 50, 29);
		g2.setFont(new Font("Verdana", Font.PLAIN, 12));
		g2.setColor(Color.BLACK);
		String text = "Drop multiple or one *.zip file here.";
		g2.drawString(text, getCenter(g2, text), HEIGHT / 2 + 60);
		String text2 = "Click here to open file chooser.";
		g2.drawString(text2, getCenter(g2, text2), 117);
		update(g);
	}
	
	/**
	 * This will give instructions to paint the applet.
	 */
	@Override
	public void paintComponent(Graphics g) {
		whatToPaint(g);
	}
	
	public int getCenter(Graphics g2, String text) {
		int stringX = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();  
		return WIDTH / 2 - stringX / 2;
	}
	
	/**
	 * This stores the buffered images.
	 */
	private static BufferedImage closeImage = null, maxImage = null, minImage = null, backImage = null;
	
	/**
	 * This loads the BufferedImage one time.
	 */
	private static void loadBufferedImages() {
		try {
			closeImage = ImageIO.read(new File("res/close.png"));
			maxImage = ImageIO.read(new File("res/max.png"));
			minImage = ImageIO.read(new File("res/min.png"));
			backImage = ImageIO.read(new File("res/back.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean repaintNow = false;
	
	/**
	 * This will update the graphics.
	 */
	@Override
	public void update(Graphics g) {
		// Hovering close, maximize and minimize buttons are updating here.
		int locX = 626;
		int locY = 2;
		if (this.close) {
			g.drawImage(closeImage, locX, locY + 2, null);
			repaintNow = true;
		} else if (this.max) {
			locX -= maxImage.getWidth() + 2;
			g.drawImage(maxImage, locX, locY + 2, null);
			repaintNow = true;
		} else if (this.min) {
			locX -= maxImage.getWidth() + 2;
			locX -= maxImage.getWidth() + 2;
			g.drawImage(minImage, locX, locY + 2, null);
			repaintNow = true;
		} else if (this.back) {
			Color white = new Color(255, 255, 255, 120);
			g.setColor(white);
			g.fillRect(39, 72, 572, 76);
			repaintNow = true;
		} else {
			locX = 626;
			locY = 2;
			g.drawImage(closeImage, locX, locY, null);
			locX -= maxImage.getWidth() + 2;
			g.drawImage(maxImage, locX, locY, null);
			locX -= maxImage.getWidth() + 2;
			g.drawImage(minImage, locX, locY, null);
			if (repaintNow) {
				g.clearRect(0, 0, WIDTH, HEIGHT);
				repaintNow = false;
				whatToPaint(g);
				g.dispose();
			}
		}
	}
	
	/**
	 * This prevents class mistakes from Java.
	 */
	private static final long serialVersionUID = 8446231870653450481L;

	/**
	 * This tells if the mouse is dragged.
	 */
	@Override
	public void mouseDragged(MouseEvent e) { }
	
	/**
	 * This tells which button is hovered.
	 */
	private boolean close = false, max = false, min = false, back = false;
	
	/**
	 * This tells if it needs to stop updating.
	 */
	private boolean stopUpdating = false;
	
	/**
	 * This will help giving hints when a button is released.
	 */
	private boolean updateOnce = false;
	
	/**
	 * This tells if the mouse is moved.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (e.getX() > 626 && e.getX() < 648 && e.getY() > 2 && e.getY() < 24) {
			while (!stopUpdating) {
				close = true;
				update(this.getGraphics());
				updateOnce = false;
				stopUpdating = true;
			}
		} else if (e.getX() > 602 && e.getX() < 624 && e.getY() > 2 && e.getY() < 24) {
			while (!stopUpdating) {
				max = true;
				update(this.getGraphics());
				updateOnce = false;
				stopUpdating = true;
			}
		} else if (e.getX() > 578 && e.getX() < 600 && e.getY() > 2 && e.getY() < 24) {
			while (!stopUpdating) {
				min = true;
				update(this.getGraphics());
				updateOnce = false;
				min = true;
				stopUpdating = true;
			} 
		} else if (e.getX() > 39 && e.getX() < 611 && e.getY() > 72 && e.getY() < 148) {
			while (!stopUpdating) {
				back = true;
				update(this.getGraphics());
				updateOnce = false;
				stopUpdating = true;
			} 
		} else {
			back = false;
			close = false; 
			max = false; 
			min = false;
			stopUpdating = false;
			while (!updateOnce) {
				update(this.getGraphics());
				updateOnce = true;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (close) {
			System.exit(0);
		} else if (max) {
			// TODO Add the method, it has temporarily been disabled.
		} else if (min) {
			DoFrame.getFrame.setState(Frame.ICONIFIED);
		} else if (back) {
			Actions.openChooser();
			try { 
				if (Actions.returnVal == 0) {
					Actions.unZip(new File(Actions.chooser.getSelectedFile().getPath()));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {	}

	@Override
	public void mouseExited(MouseEvent arg0) { }

	@Override
	public void mousePressed(MouseEvent arg0) {	}

	@Override
	public void mouseReleased(MouseEvent arg0) { }
	
}
