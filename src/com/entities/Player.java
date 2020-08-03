package com.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Valores;
import com.world.Camera;
import com.world.World;

public class Player extends Entity {
	
	public boolean right, jump, isJumping, animChangeStage, moving;
	
	private int anim = 0;
	private int anim_frames = 0;
	
	public int right_dir = 0, up_dir = 1;
	public int dir = right_dir;
	
	
	public double speed = 2;
	
	private int frames = 0 , maxFrames  = 5, index = 0, maxIndex = 3;
	private boolean moved = false;
	
	private BufferedImage[] rightPlayer;
	
	private DinoType dino = DinoType.T_REX;
	
	// Gravidade
	private double gravity = 0.4;
	private double vspd = 0;
	
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
		
		advanceJump();
		
		Camera.x = Camera.clamp(this.getX()- (Game.WIDTH/2), 0, World.WIDTH* Valores.TILE_SIZE - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY()- (Game.HEIGHT/2), 0, World.HEIGHT* Valores.TILE_SIZE - Game.HEIGHT);
		
	}
	
	public void render(Graphics g) {
		if (dir == right_dir) {
			g.drawImage(rightPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}
	
	public void advanceJump()
	{
		vspd += gravity;
		
		if (jump && !moveAllowed(getX(), (int) (y + 1)) && moveAllowed(getX(), (int) (y -1)))
		{
			vspd = -6;
			jump = false;
			//Sound.jump.play();
		}
		
		if (!moveAllowed((int) x, (int) (y + vspd)))
		{
			
			int signVsp = 0;
			
			if (vspd >= 0)
			{
				signVsp = 1;
			} else
			{
				signVsp = -1;
			}
			
			while (moveAllowed((int) x, (int) (y + signVsp)))
			{
				y = y + signVsp;
			}
			
			vspd = 0;
		}
		
		y = y + vspd;
		
		if (isJumping)
		{
			
		} else if (moving)
		{
			anim_frames++;
			
			if (anim_frames >= maxFrames)
			{
				anim_frames = 0;
				
				if (!animChangeStage)
					anim++;
				else
					anim--;
				
				if ("Name" == "Name".toLowerCase() /*anim >= Entity.SPRITE_PLAYER_RIGHT.length - 1*/)
				{
					anim--;
					animChangeStage = !animChangeStage;
				} else if (anim < 0)
				{
					anim++;
					animChangeStage = !animChangeStage;
				}
			}
		}
	}
	
	public boolean moveAllowed(int x, int y)
	{
		return x < Game.WIDTH && x > 0 && y > 0 && y < Valores.floor;
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
