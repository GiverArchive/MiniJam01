package com.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.main.Game;
import com.main.State;
import com.main.Valores;

public class Parallax {
	
	public static int WIDTH, HEIGHT;
	
	public static BufferedImage layer1;
	public static BufferedImage layer2;
	public static BufferedImage layer3;
	public static BufferedImage layer4;
	public static BufferedImage layer5;
	public static BufferedImage layer6;
	public static BufferedImage layer8;
	
	static
	{
		try 
		{
			layer1 = ImageIO.read(Game.class.getResourceAsStream("/Teste.png"));
		}
		catch(IOException e)
		{
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static int x1;
	public static int x2;
	
	public Parallax()
	{
		WIDTH = layer1.getWidth();
		HEIGHT = layer1.getHeight();
		
		x1 = 0;
		x2 = WIDTH;
	}
	
	public void advanceBackground()
	{
		if(Game.state != State.NORMAL)
			return;
		
		x1 -= Valores.runSpeed;
		x2 -= Valores.runSpeed;
		
		if(x1 < -WIDTH)
			x1 = 0;
		
		if(x2 < 0)
			x2 = WIDTH;
	}
	
	public void dispatchRender(Graphics g)
	{
		renderCamada1(g);
		renderCamada7(g);
	}
	
	private void renderCamada1(Graphics g)
	{
		g.drawImage(layer1, x1, 0, null);
		g.drawImage(layer1, x2, 0, null);
	}
	
	private void renderCamada2(Graphics g)
	{
		g.drawImage(layer2, x1, 0, null);
		g.drawImage(layer2, x2, 0, null);
	}
	
	private void renderCamada3(Graphics g)
	{
		g.drawImage(layer3, x1, 0, null);
		g.drawImage(layer3, x2, 0, null);
	}
	
	private void renderCamada4(Graphics g)
	{
		g.drawImage(layer4, x1, 0, null);
		g.drawImage(layer4, x2, 0, null);
	}
	
	private void renderCamada5(Graphics g)
	{
		g.drawImage(layer5, x1, 0, null);
		g.drawImage(layer5, x2, 0, null);
	}
	
	private void renderCamada6(Graphics g)
	{
		g.drawImage(layer6, x1, 0, null);
		g.drawImage(layer6, x2, 0, null);
	}
	
	private void renderCamada7(Graphics g)
	{
		for(int i = 0; i < Game.entities.size(); i++)
		{
			Game.entities.get(i).render(g);
		}
	}
	
	private void renderCamada8(Graphics g)
	{
		g.drawImage(layer8, x1, 0, null);
		g.drawImage(layer8, x2, 0, null);
	}
}
