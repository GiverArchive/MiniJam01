package com.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Valores;
import com.world.Camera;
import com.world.World;

public class Player extends Entity {

	public boolean right, jump;
	public int right_dir = 0, up_dir = 1;
	public int dir = right_dir;
	public double speed = 2;

	private int frames = 0 , maxFrames  = 5, index = 0, maxIndex = 3;
	private boolean moved = false;

	private BufferedImage[] rightPlayer;

	private DinoType dino = DinoType.T_REX;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		rightPlayer = new BufferedImage[1];
		rightPlayer[0] = Game.spritesheet.getSprite(0, 0, Valores.TILE_SIZE, Valores.TILE_SIZE);

	}

	public void tick() {
		moved = false;
		if(right) {
			moved = true;
			dir = right_dir;
			x+=speed;
		}

		// jump - Put jump method here

		Camera.x = Camera.clamp(this.getX()- (Game.WIDTH/2), 0, World.WIDTH* Valores.TILE_SIZE - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY()- (Game.HEIGHT/2), 0, World.HEIGHT* Valores.TILE_SIZE - Game.HEIGHT);
		
	}

	public void render(Graphics g) {
		if (dir == right_dir) {
			g.drawImage(rightPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}

	public void setType(DinoType type)
	{
		this.dino = type;
	}

	public DinoType type()
	{
		return this.dino;
	}
}
