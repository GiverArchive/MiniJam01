package com.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Valores;

public class Player extends Entity
{
	
	public boolean right, jump, isJumping, animChangeStage, moving;
	
	private int anim = 0;
	private int anim_frames = 0;
	
	public int right_dir = 0, up_dir = 1;
	public int dir = right_dir;
	
	public double speed = 2;
	
	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;
	private boolean moved = false;
	
	private BufferedImage[] rightPlayer;
	
	private DinoType dino;
	
	// Gravidade
	private double gravity = 0.4;
	private double vspd = 0;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite)
	{
		super(x, y, width, height, sprite);
		rightPlayer = new BufferedImage[1];
		rightPlayer[0] = Game.spritesheet.getSprite(0, 16, 44, 23);
		setType(DinoType.TRICERATOPS);
	}
	
	public void tick()
	{
		moved = false;
		if (right)
		{
			moved = true;
			dir = right_dir;
			x += speed;
		}
		
		advanceJump();
	}
	
	public void render(Graphics g)
	{
		if (dir == right_dir)
		{
			g.drawImage(rightPlayer[0], this.getX(), this.getY(), width, height, null);
		}
	}
	
	public void advanceJump()
	{
		vspd += gravity;
		
		if (jump && !moveAllowed(getX(), (int) (y + 1)) && moveAllowed(getX(), (int) (y - 1)))
		{
			vspd = -6;
			jump = false;
			// Sound.jump.play();
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
			
			while (moveAllowed((int) x, (int) (y + 0.1)))
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
				
				if ("Name" == "Name"
						.toLowerCase() /* anim >= Entity.SPRITE_PLAYER_RIGHT.length - 1 */)
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
		this.height = type.height * Valores.entityScale;
		this.width = type.width * Valores.entityScale;
		this.maskX = type.maskX * Valores.entityScale;
		this.maskY = type.maskY * Valores.entityScale;
		this.maskW = type.maskW * Valores.entityScale;
		this.maskH = type.maskH * Valores.entityScale;
	}
	
	public DinoType type()
	{
		return this.dino;
	}
}
