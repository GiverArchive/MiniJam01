package com.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Valores;

public class Tile {

	public static BufferedImage TILE_SKY = Game.spritesheet.getSprite(32, 0, Valores.TILE_SIZE, Valores.TILE_SIZE);
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(16, 0, Valores.TILE_SIZE, Valores.TILE_SIZE);
	// Colocar os outros tiles e etc;\
	
	private BufferedImage sprite;
	private int x, y;

	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void render(Graphics g) {
		//g.drawImage(sprite, x - Camera.x , y - Camera.y, null);
	}

}
