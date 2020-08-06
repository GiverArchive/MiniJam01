package com.entities;

import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Valores;

public class Obstacle extends Entity
{
	public Obstacle(int x, int y, int width, int height, BufferedImage sprite)
	{
		super(x, y, width, height, sprite);
	}
	
	@Override
	public void tick()
	{
		if(isColliding(Game.player))
		{
			System.out.println("Colidiu");
		}
		
		x -= Valores.runSpeed;
		
		if((x + width) < 0)
		{
			destroy();
		}
	}
}