package com.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.main.Valores;

public class Entity {
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	protected int maskX = 0;
	protected int maskY = 0;
	protected int maskW = 0;
	protected int maskH = 0;
	
	protected BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		// Quando criar uma entity, temos que passar todos esses
		this.x = x;
		this.y = y;
		this.width = width * Valores.entityScale;
		this.height = height * Valores.entityScale;
		this.sprite = sprite;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), width, height, null);
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
	
	public Rectangle getHitBox()
	{
		return new Rectangle(getX() + maskX, getY() + maskY, getWidth() - maskW, getHeight() - maskH);
	}
	
	public boolean isColliding(Entity entity)
	{
		return getHitBox().intersects(entity.getHitBox());
	}
}
