package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

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
        x -= Valores.runSpeed;

        if((x + width) < 0)
        {
            destroy();
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(getX(), getY(), width, height);
    }
}