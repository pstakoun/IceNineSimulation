package com.pstakoun.iceninesimulation;

import java.awt.Color;
import java.util.ArrayList;

public class Simulation
{
	private SimFrame frame;
	
	public Simulation(int x, int y, SimFrame frame)
	{
		this.frame = frame;
		
		int rgb = frame.GetMap().getRGB(x, y);
		Color c = new Color(rgb);
		
		if (c.getBlue() > c.getGreen()+10 && c.getBlue() > c.getRed()+10 && c.getRGB() < -6000000 /*&& c.getBlue() > 35*/ /*c.getBlue() - (c.getRed() + c.getGreen()) >= -150*/)
		{
			frame.SetIceNine(x, y);
			refresh();
			simulate();
		}
	}
	
	private void simulate()
	{
		while (true)
		{
			ArrayList<int[]> newIce = new ArrayList<int[]>();
			for (int i = 0; i < frame.GetPanel().getHeight(); i++)
			{
				for (int j = 0; j < frame.GetPanel().getWidth(); j++)
				{
					int rgb = frame.GetMap().getRGB(j, i);
					Color c = new Color(rgb);
					
					if (c.getBlue() > c.getGreen()+10 && c.getBlue() > c.getRed()+10  && c.getRGB() < -6000000 /*&& c.getBlue() > 35*/ /*c.getBlue() - (c.getRed() + c.getGreen()) >= -150*/)
					{
						if (frame.BesideIce(j, i))
						{
							int[] arr = {j, i};
							newIce.add(arr);
						}
					}
				}
			}
			if (newIce.isEmpty()) { break; }
			for (int[] arr : newIce)
			{
				frame.SetIceNine(arr[0], arr[1]);
			}
			if (frame.getX() * frame.getY() < 1000)
			{
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			refresh();
		}
	}
	
	private void refresh()
	{
		frame.paintComponents(frame.getGraphics());
	}

}
