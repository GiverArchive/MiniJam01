package com.entities;

import java.awt.image.BufferedImage;

import com.graphics.Spritesheet;

public enum DinoType
{
	VELOCIRAPTOR(Spritesheet.veloci, Spritesheet.velociJump, Spritesheet.velociTransient, 52, 32, 0, 0, 0, 0, 8, 8),
	TRICERATOPS(Spritesheet.triceratops, Spritesheet.triceratopsJump, Spritesheet.triceratopsTransient, 44, 23, 0, 0, 0, 0, 8, 10),
	GALINHA(Spritesheet.galinha, Spritesheet.galinhaJump, null, 19, 18, 0, 0, 0, 0, 6, 5);
	
	public int width, height, maskX, maskY, maskW, maskH, jumpF, aF;
	public BufferedImage[] sprites, jump, change;
	
	private DinoType(BufferedImage[] sprites, BufferedImage[] jump, BufferedImage[] change, int width, int height, int maskX, int maskY, int maskW, int maskH, int jumpF, int aF)
	{
		this.width = width;
		this.height = height;
		this.maskX = maskX;
		this.maskY = maskY;
		this.maskW = maskW;
		this.maskH = maskH;
		this.sprites = sprites;
		this.jump = jump;
		this.jumpF = jumpF;
		this.aF = aF;
		this.change = change;
	}
}