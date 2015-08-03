package com.pstakoun.iceninesimulation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class SimPanel extends JPanel
{
	private SimFrame sim;
	
	public SimPanel(SimFrame sim)
	{
		this.sim = sim;
		int x = sim.GetMap().getWidth();
		int y = sim.GetMap().getHeight();
		setPreferredSize(new Dimension(x, y));
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(sim.GetMap(), 0, 0, null);
	}
	
}
