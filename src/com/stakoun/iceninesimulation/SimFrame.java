package com.pstakoun.iceninesimulation;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class SimFrame extends JFrame implements MouseListener
{
	private BufferedImage map;
	private SimPanel panel;
	
	public SimFrame(String s) {
		super(s);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			BufferedImage img = ImageIO.read(new File("res/map.jpg"));
			Dimension size = scale(img.getWidth(), img.getHeight());
			map = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
	        
			Graphics2D g2d = map.createGraphics();
	        g2d.setComposite(AlphaComposite.Src);
	        g2d.drawImage(img, 0, 0, size.width, size.height, null);
	        g2d.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
		panel = new SimPanel(this);
		add(panel);
		panel.addMouseListener(this);
		addMouseListener(this);
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	private Dimension scale(int x, int y)
	{
	    int nw = x;
	    int nh = y;

	    if (x > 800) {
	        nw = 600;
	        nh = (nw * y) / x;
	    }

	    if (nh > 600) {
	        nh = 400;
	        nw = (nh * x) / y;
	    }

	    return new Dimension(nw, nh);
	}
	
	public BufferedImage GetMap()
	{
		return map;
	}
	
	public SimPanel GetPanel()
	{
		return panel;
	}
	
	public void SetIceNine(int x, int y)
	{
		map.setRGB(x, y, Color.decode("#CCF5FF").getRGB());
	}
	
	public boolean BesideIce(int x, int y)
	{
		if (x >= GetPanel().getWidth()-1 || y >= GetPanel().getHeight()-1) {
			return false;
		} else if (map.getRGB(x, y) == Color.decode("#CCF5FF").getRGB()) {
			return false;
		} else if (x != 0 && map.getRGB(x-1, y) == Color.decode("#CCF5FF").getRGB()) {
			return true;
		} else if (y != 0 && map.getRGB(x, y-1) == Color.decode("#CCF5FF").getRGB()) {
			return true;
		} else if (map.getRGB(x+1, y) == Color.decode("#CCF5FF").getRGB()) {
			return true;
		} else if (map.getRGB(x, y+1) == Color.decode("#CCF5FF").getRGB()) {
			return true;
		}
		return false;
	}

	public void mousePressed(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		new Simulation(x, y, this);
	}
	
	public void mouseClicked(MouseEvent e) { }

	public void mouseEntered(MouseEvent e) { }

	public void mouseExited(MouseEvent e) { }

	public void mouseReleased(MouseEvent e) { }
	
}
