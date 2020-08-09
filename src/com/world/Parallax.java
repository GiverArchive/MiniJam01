package com.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.main.Game;
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
			layer1 = ImageIO.read(Game.class.getResourceAsStream("/backgrounds/layer1.png"));
			layer2 = ImageIO.read(Game.class.getResourceAsStream("/backgrounds/layer2.png"));
			layer3 = ImageIO.read(Game.class.getResourceAsStream("/backgrounds/layer3.png"));
			layer4 = ImageIO.read(Game.class.getResourceAsStream("/backgrounds/layer4.png"));
			layer5 = ImageIO.read(Game.class.getResourceAsStream("/backgrounds/layer5.png"));
			layer6 = ImageIO.read(Game.class.getResourceAsStream("/backgrounds/layer6.png"));
			layer8 = ImageIO.read(Game.class.getResourceAsStream("/backgrounds/layer8.png"));
		}
		catch(IOException e)
		{
			System.out.println("Erro: " + e.getMessage());
		}
	}

	public static int x1_l2;
	public static int x2_l2;
	
	public static int x1_l3;
	public static int x2_l3;
	
	public static int x1_l4;
	public static int x2_l4;
	
	public static int x1_l5;
	public static int x2_l5;
	
	public static int x1_l6;
	public static int x2_l6;
	
	public static int x1_l8;
	public static int x2_l8;
	
	public Parallax()
	{
		WIDTH = layer1.getWidth();
		HEIGHT = layer1.getHeight();
		
		x1_l2 = 0;
		x2_l2 = layer2.getWidth() * 4;
		
		x1_l3 = 0;
		x2_l3 = layer3.getWidth() * 4;
		
		x1_l4 = 0;
		x2_l4 = layer4.getWidth() * 4;
		
		x1_l5 = 0;
		x2_l5 = layer5.getWidth() * 4;
		
		x1_l6 = 0;
		x2_l6 = layer6.getWidth() * 4;
		
		x1_l8 = 0;
		x2_l8 = layer8.getWidth() * 4;
	}
	
	public void advanceBackground()
	{
		x1_l2 -= Valores.runSpeed / 4;
		x2_l2 -= Valores.runSpeed / 4;
		
		if(x1_l2 < -layer2.getWidth() * 4)
			x1_l2 = 0;
		
		if(x2_l2 < 0)
			x2_l2 = layer2.getWidth() * 4;
		
		// -------------------------
		
		x1_l3 -= Valores.runSpeed / 3;
		x2_l3 -= Valores.runSpeed / 3;
		
		if(x1_l3 < -layer3.getWidth() * 4)
			x1_l3 = 0;
		
		if(x2_l3 < 0)
			x2_l3 = layer3.getWidth() * 4;
		
		// -------------------------
		
		x1_l4 -= Valores.runSpeed / 4;
		x2_l4 -= Valores.runSpeed / 4;
		
		if(x1_l4 < -layer4.getWidth() * 4)
			x1_l4 = 0;
		
		if(x2_l4 < 0)
			x2_l4 = layer4.getWidth() * 4;
		
		// -------------------------
		
		x1_l5 -= Valores.runSpeed / 2;
		x2_l5 -= Valores.runSpeed / 2;
		
		if(x1_l5 < -layer5.getWidth() * 4)
			x1_l5 = 0;
		
		if(x2_l5 < 0)
			x2_l5 = layer5.getWidth() * 4;
		
		// -------------------------
		
		x1_l6 -= Valores.runSpeed;
		x2_l6 -= Valores.runSpeed;
		
		if(x1_l6 < -layer6.getWidth() * 4)
			x1_l6 = 0;
		
		if(x2_l6 < 0)
			x2_l6 = layer6.getWidth() * 4;
		
		// -------------------------
		
		x1_l8 -= Valores.runSpeed;
		x2_l8 -= Valores.runSpeed;
		
		if(x1_l8 < -layer8.getWidth() * 4)
			x1_l8 = 0;
		
		if(x2_l8 < 0)
			x2_l8 = layer8.getWidth() * 4;
	}
	
	public void dispatchRender(Graphics g)
	{
		renderCamada1(g);
		renderCamada2(g);
		renderCamada3(g);
		renderCamada4(g);
		renderCamada5(g);
		renderCamada6(g);
		renderCamada7(g);
		renderCamada8(g);
	}
	
	private void renderCamada1(Graphics g)
	{
		g.drawImage(layer1, 0, 0, 960, 540, null);
		g.drawImage(layer1, 0, 0, 960, 540, null);
	}
	
	private void renderCamada2(Graphics g)
	{
		g.drawImage(layer2, x1_l2, 0, layer2.getWidth() * 4, layer2.getHeight() * 4, null);
		g.drawImage(layer2, x2_l2, 0, layer2.getWidth() * 4, layer2.getHeight() * 4, null);
	}
	
	private void renderCamada3(Graphics g)
	{
		g.drawImage(layer3, x1_l3, -40 + 40, layer3.getWidth() * 4, layer3.getHeight() * 4, null);
		g.drawImage(layer3, x2_l3, -40 + 40, layer3.getWidth() * 4, layer3.getHeight() * 4, null);
	}
	
	private void renderCamada4(Graphics g)
	{
		g.drawImage(layer4, x1_l4, -50, layer4.getWidth() * 4, layer4.getHeight() * 4, null);
		g.drawImage(layer4, x2_l4, -50, layer4.getWidth() * 4, layer4.getHeight() * 4, null);
	}
	
	private void renderCamada5(Graphics g)
	{
		g.drawImage(layer5, x1_l5, -80, layer5.getWidth() * 4, layer5.getHeight() * 4, null);
		g.drawImage(layer5, x2_l5, -80, layer5.getWidth() * 4, layer5.getHeight() * 4, null);
	}
	
	private void renderCamada6(Graphics g)
	{
		g.drawImage(layer6, x1_l6, 484, layer6.getWidth() * 4, layer6.getHeight() * 4, null);
		g.drawImage(layer6, x2_l6, 484, layer6.getWidth() * 4, layer6.getHeight() * 4, null);
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
		g.drawImage(layer8, x1_l8, 460, layer8.getWidth() * 4, layer8.getHeight() * 4, null);
		g.drawImage(layer8, x2_l8, 460, layer8.getWidth() * 4, layer8.getHeight() * 4, null);
	}
}
