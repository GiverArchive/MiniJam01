package com.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet
{
	public static BufferedImage[] triceratops;
	public static BufferedImage[] ptero;
	public static BufferedImage[] trex;
	public static BufferedImage[] velociraptor;
	
	private BufferedImage spritesheet;
	
	public Spritesheet(String path)
	{
		try
		{
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e)
		{
			System.out.println("Falha kkk");
		}
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height)
	{
		return spritesheet.getSubimage(x, y, width, height);
	}
	
	public static void init(Spritesheet sprites)
	{
		
	}
}
