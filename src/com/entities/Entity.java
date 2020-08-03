package com.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;

public class Entity {

	protected double x;
	protected double y;
	protected int width;
	protected int height;

	protected BufferedImage sprite;

	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		// Quando criar uma entity, temos que passar todos esses
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}

	public void tick() {

	}

	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
	}

	public int getX() {
		return (int) this.x;
	}

	public int getY() {
		return (int) this.y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setX(int newX) {
		this.x = newX;
	}

	public void setY(int newY) {
		this.y = newY;
	}

	public void destroy()
	{
		Game.entities.remove(this);
	}

}
