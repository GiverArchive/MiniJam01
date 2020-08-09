package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.entities.Entity;
import com.entities.Player;
import com.graphics.Spritesheet;
import com.world.ObstacleGenerator;
import com.world.Parallax;

public class Game extends Canvas implements KeyListener
{
	private static final long serialVersionUID = 0L;
	
	public static final int WIDTH = 240;
	public static final int HEIGHT = 135;
	
	public static int FPS = 0;
	public static long TICKS = 0;
	
	public static ObstacleGenerator generator;
	public static Player player;
	public static JFrame frame;
	public static Spritesheet spritesheet;
	public static Parallax world;
	public Menu menu;
	public static State state = State.MENU;
	
	public static List<Entity> entities;
	
	public static boolean canGenerate = true;
	
	private Thread thread;
	private Thread threadR;
	
	private static boolean isRunning = true;
	
	public static int SCALE = 4;
	
	private double distancia = 0;
	
	public Game()
	{
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		initFrame();
		
		// Inicializando Objetos
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/spritesheetDino.png");
		player = new Player(24, 24, 44, 23,
				spritesheet.getSprite(0, 16, 44, 23));
		entities.add(player);
		
		world = new Parallax();
		
		menu = new Menu();
		
		generator = new ObstacleGenerator();
		
		//Sound.music.loop(0.6f);
	}
	
	public void initFrame()
	{
		frame = new JFrame("Jogo Dino");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start()
	{
		thread = new Thread(() -> {
			
			requestFocus();
			Game.isRunning = true;
			long lastTime = System.nanoTime(); 
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			int ticks = 0;
			double timer = System.currentTimeMillis();
			
			while (isRunning)
			{
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				
				if (delta >= 1)
				{
					tick();
					
					ticks++;
					delta--;
				}
				
				if (System.currentTimeMillis() - timer >= 1000)
				{
					TICKS = ticks;
					ticks = 0;
					timer += 1000;
				}
			}
			stop();
			
		});
		
		threadR = new Thread(() -> {
			
			int fps = 0;
			long time = System.currentTimeMillis();
			
			while (isRunning)
			{
				render();
				fps++;
				
				if(System.currentTimeMillis() - time >= 1000)
				{
					time = System.currentTimeMillis();
					FPS = fps;
					fps = 0;
				}
			}
			
		});
		
		thread.start();
		threadR.start();
		
	}
	
	public synchronized void stop()
	{
		isRunning = false;
		try
		{
			thread.join();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void tick()
	{
		if (state == State.NORMAL)
		{
			world.advanceBackground();
			generator.tick();
			for (int i = 0; i < entities.size(); i++)
			{
				Entity e = entities.get(i);
				e.tick();
			}
		} else if (state == State.MENU)
		{
			menu.tick();
		}
		
		if (Menu.pause)
		{
			canGenerate = false;
		} else
		{
			canGenerate = true;
		}
		
		if(state == State.NORMAL){
			distancia+=0.5;
		}
	}
	
	public void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		/* Renderizaï¿½ao do jogo */
		
		world.dispatchRender(g);; // Primeira coisa ser renderizada
		
		/***/
		
		drawFPS(g);
		
		if (state == State.MENU)
		{
			menu.render(g);
		}
		
		if (Game.state == State.CREDITS)
		{
			String texto = "Cr\u00E9ditos:"; // Unicode: \u00E9 = E com acento agudo
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			int x = (Game.WIDTH * Game.SCALE - g.getFontMetrics().stringWidth(texto)) / 2;
			g.drawString(texto, x, 70);
			g.setFont(new Font("Arial", Font.ITALIC, 25));
			g.drawString("Programação: Giver e Lívia;", 330, 130);
			g.drawString("Design: Guilherme e Choice;", 330, 170);
			g.drawString("Roteiro: Rei;", 400, 210);
		}
		
		if(state == State.NORMAL) {
			g.setFont(new Font("arial" , Font.BOLD , 18));
			g.drawString("" +distancia, 889, 22);
		}
		
		bs.show();
	}
	
	private void drawFPS(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 12));
		g.drawString("FPS: " + FPS, 12, 12);
		g.drawString("Ticks: " + TICKS, 12, 24);
	}
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.start();
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(state == State.NORMAL)
		{
			if(e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				player.jump = true;
			}
		}
		else if(state == State.MENU)
		{
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				menu.down = true;
			} else if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				menu.up = true;
			}
			else
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					menu.enter = true;
				}
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			state = State.MENU;
			Menu.pause = true;
		}
		else
			if (e.getKeyCode() == KeyEvent.VK_P && state == State.NORMAL)
			{
				player.change();
			}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_SPACE:
				player.jump = false;
				break;
				
			default:
				break;
		}
	}
	
}
