package com.entities;

import java.awt.image.BufferedImage;

import com.graphics.Spritesheet;

public enum DinoType
{
	T_REX(Spritesheet.trex, 10, 10, 0, 0, 0, 0),
	VELOCIRAPTOR(Spritesheet.velociraptor, 10, 10, 0, 0, 0, 0),
	TRICERATOPS(Spritesheet.triceratops, 44, 23, 0, 0, 0, 0),
	PTERO(Spritesheet.ptero, 10, 10, 0, 0, 0, 0);
	
	public int width, height, maskX, maskY, maskW, maskH;
	public BufferedImage[] sprites;
	
	private DinoType(BufferedImage[] sprites, int width, int height, int maskX, int maskY, int maskW, int maskH)
	{
		this.width = width;
		this.height = height;
		this.maskX = maskX;
		this.maskY = maskY;
		this.maskW = maskW;
		this.maskH = maskH;
		this.sprites = sprites;
	}
}