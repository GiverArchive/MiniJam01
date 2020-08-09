package com.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.main.Game;
import com.main.Sound;
import com.main.Valores;

public class Player extends Entity
{
	
	public boolean jump, isJumping;
	
	private int anim, anim_jump = 0;
	private int anim_frames, anim_frames_jump = 0;
	
	private int maxFrames = 10;
	private int maxFramesJump = 10;
	private boolean moved = false;
	
	private BufferedImage[] sprites, spritesJump;
	
	private DinoType dino;
	
	// Gravidade
	private double gravity = 0.4;
	private double vspd = 0;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite)
	{
		super(x, y, width, height, sprite);
		setType(DinoType.GALINHA);
	}
	
	public void tick()
	{
		advanceJump();
	}
	
	public void render(Graphics g)
	{
		if (!isJumping)
		{
			g.drawImage(sprites[anim], this.getX(), this.getY(), width, height, null);
		}
		else
		{
			g.drawImage(spritesJump[anim_jump], this.getX(), this.getY(), width, height, null);
		}
	}
	
	public void advanceJump()
	{
		vspd += gravity;
		
		if (jump && !moveAllowed(getX(), (int) (y + 1)) && moveAllowed(getX(), (int) (y - 1)))
		{
			vspd = -9;
			jump = false;
			Sound.jump.play(0.85f);
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
		
		if(!isJumping)
		{
			isJumping = vspd < 0;
		}
		
		if (isJumping)
		{
			anim_frames_jump++;
			
			if (anim_frames_jump >= maxFramesJump)
			{
				anim_frames_jump = 0;
				
				anim_jump++;
				
				if (anim_jump >= spritesJump.length)
				{
					anim_jump = 0;
					isJumping = false;
					Sound.jump.stop();
				}
			}
			
		} else // rever
		{
			anim_frames++;
			
			if (anim_frames >= maxFrames)
			{
				anim_frames = 0;
				anim++;
				
				if (anim >= sprites.length)
				{
					anim = 0;
				}
			}
		}
	}
	
	public boolean moveAllowed(int x, int y)
	{
		return x < Game.WIDTH && x > 0 && y > 0 && y + height < Valores.floor;
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
		this.sprites = type.sprites;
		this.spritesJump = type.jump;
		
		this.maxFrames = type.aF;
		this.maxFramesJump = type.jumpF;
		
		this.y = Valores.floor - this.height - 10;
		
		if(isJumping)
			isJumping = false;
		
		anim = 0;
		anim_jump = 0;
		
		anim_frames_jump = 0;
		anim_frames = 0;
	}
	
	public DinoType type()
	{
		return this.dino;
	}

	public void change()
	{
		synchronized (this)
		{
			setType(DinoType.values()[new Random().nextInt(3)]);
		}
	}
}
