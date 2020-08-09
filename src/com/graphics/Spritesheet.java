package com.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet
{
	public static BufferedImage[] triceratops;
	public static BufferedImage[] triceratopsJump;
	public static BufferedImage[] triceratopsTransient;
	
	public static BufferedImage[] galinha;
	public static BufferedImage[] galinhaJump;
	
	public static BufferedImage[] veloci;
	public static BufferedImage[] velociJump;
	public static BufferedImage[] velociTransient;
	
	public static BufferedImage[] ovos;
	public static BufferedImage[] ovosClaros;
	
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
		init(this);
	}
	
	public BufferedImage getSprite(int x, int y, int width, int height)
	{
		return spritesheet.getSubimage(x, y, width, height);
	}
	
	public static void init(Spritesheet sprites)
	{
		triceratops = new BufferedImage[2];
		triceratopsJump = new BufferedImage[6];
		triceratopsTransient = new BufferedImage[7];
		
		veloci = new BufferedImage[4];
		velociJump = new BufferedImage[6];
		velociTransient = new BufferedImage[6];
		
		galinhaJump = new BufferedImage[7];
		galinha = new BufferedImage[6];
		
		ovos = new BufferedImage[10];
		ovosClaros = new BufferedImage[12];
		
		// --------------------------------------------
		
		triceratops[0] = sprites.getSprite(0, 23, 46, 25);
		triceratops[1] = sprites.getSprite(50, 24, 45, 24);
		
		triceratopsJump[0] = sprites.getSprite(0, 48, 46, 32);
		triceratopsJump[1] = sprites.getSprite(48, 48, 46, 32);
		triceratopsJump[2] = sprites.getSprite(96, 48, 44, 32);
		triceratopsJump[3] = sprites.getSprite(144, 48, 44, 32);
		triceratopsJump[4] = sprites.getSprite(192, 48, 44, 32);
		triceratopsJump[5] = sprites.getSprite(240, 48, 44, 32);
		
		triceratopsTransient[0] = sprites.getSprite(0, 199, 46, 25);
		triceratopsTransient[1] = sprites.getSprite(48, 199, 46, 25);
		triceratopsTransient[2] = sprites.getSprite(96, 199, 46, 25);
		triceratopsTransient[3] = sprites.getSprite(143, 192, 48, 32);
		triceratopsTransient[4] = sprites.getSprite(192, 192, 48, 32);
		triceratopsTransient[5] = sprites.getSprite(240, 192, 48, 32);
		triceratopsTransient[6] = sprites.getSprite(272, 160, 48, 32);
		
		// ------------------------------------------
		
		veloci[0] = sprites.getSprite(0, 80, 48, 32);
		veloci[1] = sprites.getSprite(48, 80, 48, 32);
		veloci[2] = sprites.getSprite(96, 80, 48, 32);
		veloci[3] = sprites.getSprite(144, 80, 48, 32);
		
		velociJump[0] = sprites.getSprite(0, 112, 48, 32);
		velociJump[1] = sprites.getSprite(49, 112, 52, 32);
		velociJump[2] = sprites.getSprite(102, 112, 52, 32);
		velociJump[3] = sprites.getSprite(160, 112, 52, 32);
		velociJump[4] = sprites.getSprite(214, 112, 52, 32);
		velociJump[5] = sprites.getSprite(268, 112, 52, 32);
		
		velociTransient[0] = sprites.getSprite(0, 244, 48, 32);
		velociTransient[1] = sprites.getSprite(48, 224, 48, 32);
		velociTransient[2] = sprites.getSprite(96, 224, 48, 32);
		velociTransient[3] = sprites.getSprite(145, 224, 48, 32);
		velociTransient[4] = sprites.getSprite(200, 39, 15, 17);
		velociTransient[5] = sprites.getSprite(222, 239, 15, 17);
		
		// -----------------------------------------------
		
		galinha[0] = sprites.getSprite(0, 144, 16, 17);
		galinha[1] = sprites.getSprite(16, 144, 16, 17);
		galinha[2] = sprites.getSprite(32, 144, 16, 17);
		galinha[3] = sprites.getSprite(48, 144, 16, 17);
		galinha[4] = sprites.getSprite(64, 144, 16, 17);
		galinha[5] = sprites.getSprite(80, 144, 16, 17);
		
		galinhaJump[0] = sprites.getSprite(120, 144, 19, 18);
		galinhaJump[1] = sprites.getSprite(139, 144, 19, 18);
		galinhaJump[2] = sprites.getSprite(160, 144, 19, 18);
		galinhaJump[3] = sprites.getSprite(179, 144, 19, 18);
		galinhaJump[4] = sprites.getSprite(199, 144, 19, 18);
		galinhaJump[5] = sprites.getSprite(217, 144, 19, 18);
		galinhaJump[6] = sprites.getSprite(236, 144, 19, 18);
		
		// -----------------------------------------------
		
		ovos[0] = sprites.getSprite(0, 161, 11, 13);
		ovos[1] = sprites.getSprite(11, 161, 11, 13);
		ovos[2] = sprites.getSprite(22, 161, 11, 13);
		ovos[3] = sprites.getSprite(33, 161, 11, 13);
		ovos[4] = sprites.getSprite(44, 161, 11, 13);
		ovos[5] = sprites.getSprite(55, 161, 11, 13);
		ovos[6] = sprites.getSprite(66, 161, 11, 13);
		ovos[7] = sprites.getSprite(77, 161, 11, 13);
		ovos[8] = sprites.getSprite(88, 161, 11, 13);
		ovos[8] = sprites.getSprite(99, 161, 11, 13);
		
		ovosClaros[0] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[1] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[2] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[3] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[4] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[5] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[6] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[7] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[8] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[9] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[10] = sprites.getSprite(0, 0, 11, 9);
		ovosClaros[11] = sprites.getSprite(0, 0, 11, 9);
	}
}
