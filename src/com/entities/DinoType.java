package com.entities;

import java.awt.image.BufferedImage;

public enum DinoType
{
	T_REX(null, 10, 10, 10, 10),
	VELOCIRAPTOR(null, 10, 10, 10, 10),
	PTERO(null, 10, 10, 10, 10);
	
	public int width, height, maskX, maskY;
	public BufferedImage[] sprites;
	
	private DinoType(BufferedImage[] sprites, int width, int height, int maskX, int maskY)
	{
		this.width = width;
		this.height = height;
		this.maskX = maskX;
		this.maskY = maskY;
		this.sprites = sprites;
	}
}